package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class for the db operation CRUD.
 * 
 */
public class LoggingInDB {
	private Logger logger = LogManager.getLogger();
	Connection connection = null;
	Statement stmnt = null;

	/**
	 * La objects that convert to json, put into the database.
	 * 
	 * @param la
	 * @throws SQLException
	 */
	public void createOp(LogAttributes la) throws SQLException {
		JsonHandler jh = new JsonHandler();
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:6002/dilo", "galaksiya", "galaksiya");
			stmnt = connection.createStatement();
			String sql = "INSERT INTO logattr (attributes) VALUES ('" + jh.toJson(la) + "');";
			stmnt.executeUpdate(sql);

		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * Database delete operation of logs given the URI parameters.
	 * 
	 * silme işlemi sadece logAttr=loglevel, logAttrType=[blablabla..] olduğunda
	 * gerçekleştirilmektedir.
	 * 
	 * @param logAttr
	 *            log attributes like 'loglevel'
	 * @param logAttrType
	 *            given for log attributes type like 'DEBUG'
	 * @throws SQLException
	 * @throws IOException
	 */
	public void deleteOp(String logAttr, String logAttrType) throws SQLException, IOException {
		String result = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:6002/postgres", "postgres", "mysecretpassword");
			stmnt = connection.createStatement();
			if (logAttrType.length() == 4) // this is for INFO and WARN attributes type.
				logAttrType+=" ";
			
			String sql = "select  * from logattr where attributes ->> '" + logAttr + "' = '[" + logAttrType + "]';";
			ResultSet rs = stmnt.executeQuery(sql);
			while (rs.next()) {
				result = rs.getString("attributes");
			}
			if(result != null) {
				String sql2 = " delete from logattr where attributes ->> '" + logAttr + "' = '[" + logAttrType + "]';";
				stmnt.executeUpdate(sql2);
			}
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}

	/**
	 * Database update operation of logs given the URI parameters.
	 * 
	 * @param logAttr
	 *            log attributes like 'loglevel'
	 * @param logAttrType
	 *            given for log attributes type like 'DEBUG'
	 */
	public void updateOp(String logAttr, String logAttrType) {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:6002/postgres", "postgres", "mysecretpassword");
			stmnt = connection.createStatement();
			String sql = "update logattr set attributes ->> 'loglevel' = '" + logAttrType
					+ "' where attributes ->> 'message'='This is a debug message.';";
			// update sıkıntılı
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}

	/**
	 * Database read operation of logs. Given the URI parameters it returns a
	 * specific log json input
	 * 
	 * @param logAttr
	 *            log attributes like 'loglevel'
	 * @param logAttrType
	 *            given for log attributes type like 'DEBUG'
	 * @return ArrayList<String> queries log rows thrown into the queries ArrayList.
	 */
	public ArrayList<String> queryOp(String logAttr, String logAttrType) {
		List<String> queries = new ArrayList<String>();

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:6002/dilo", "galaksiya", "galaksiya");
			stmnt = connection.createStatement();
			if (logAttr.equals("contains")) {
				String sql = "select  * from logattr where attributes ->> 'logger' = '" + logAttrType + " ';";
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					String query = rs.getString("attributes");
					queries.add(query);
				}
			} else {
				if (logAttrType.length() == 4) // this is for INFO and WARN attributes type.
					logAttrType+=" ";
				
				String sql = "select  * from logattr where attributes ->> '" + logAttr + "' = '[" + logAttrType + "]';";
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					String query = rs.getString("attributes");
					queries.add(query);
				}
			}
		} catch (SQLException e) {
			logger.warn(e);
			e.printStackTrace();
		}
		return (ArrayList<String>) queries;
	}
}
