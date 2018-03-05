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
public class LoggingInDbTest {
	
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
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
				stmnt = connection.createStatement();

				String sql = "INSERT INTO json (data) VALUES ('" + jh.toJson(la) + "');";
				stmnt.executeUpdate(sql);

			} catch (IOException e) {
				logger.error(e);
				e.printStackTrace();
			}
		}

		/**
		 * Database delete operation of logs given the URI parameters.
		 * 
		 * @param logAttr
		 *            log attributes like 'loglevel'
		 * @param logAttrType
		 *            given for log attributes type like 'DEBUG'
		 * @throws SQLException
		 * @throws IOException
		 */
		public void deleteOp(String logAttr, String logAttrType) throws SQLException, IOException {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
				stmnt = connection.createStatement();
				if (logAttrType.length() == 4) { // this is for INFO and WARN attributes type. OTHER POSSIBLE SOLUTIONS????
					String sql = " delete from json where data ->> '" + logAttr + "' = '[" + logAttrType + " ]';";
					stmnt.executeUpdate(sql);
				}
				String sql = " delete from json where data ->> '" + logAttr + "' = '[" + logAttrType + "]';";
				stmnt.executeUpdate(sql);
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
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
				stmnt = connection.createStatement();
				String sql = "update json set data = '" + logAttrType
						+ "' where data ->> 'message'='This is a debug message.';";
				// sorgulama yanlış update skntılı
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
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
				stmnt = connection.createStatement();
				if (logAttr.equals("contains")) {
					String sql = "select  * from json where data ->> 'logger' = '" + logAttrType + " ';";
					ResultSet rs = stmnt.executeQuery(sql);
					while (rs.next()) {
						String query = rs.getString("data");
						queries.add(query);
					}
				} else {
					if (logAttrType.length() == 4) { // this is for INFO and WARN attributes type. OTHER POSSIBLE
														// SOLUTIONS????
						String sql = "select  * from json where data ->> '" + logAttr + "' = '[" + logAttrType + " ]';";
						ResultSet rs = stmnt.executeQuery(sql);
						while (rs.next()) {
							String query = rs.getString("data");
							queries.add(query);
						}
					}
					String sql = "select  * from json where data ->> '" + logAttr + "' = '[" + logAttrType + "]';";
					ResultSet rs = stmnt.executeQuery(sql);
					while (rs.next()) {
						String query = rs.getString("data");
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
