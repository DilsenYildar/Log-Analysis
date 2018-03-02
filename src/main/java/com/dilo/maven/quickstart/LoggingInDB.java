package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingInDB {
	private Logger logger = LogManager.getLogger();
	Connection connection = null;
	Statement stmnt = null;
	
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
	
	public void updateOp(LogAttributes la) {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();
		} catch (SQLException e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}
	
	public void deleteOp(LogAttributes la) throws SQLException {
	
		JsonHandler jh = new JsonHandler();
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();
			String sql = "DELETE FROM json WHERE = '"+jh.toJson(la)+"';";
			stmnt.executeUpdate(sql);
		} catch (IOException e) {
			logger.warn(e);
			e.printStackTrace();
		}
		
	}
}
