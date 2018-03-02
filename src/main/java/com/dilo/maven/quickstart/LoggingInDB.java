package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoggingInDB {
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
			e.printStackTrace();
		}
	}
	
	public void updateOp(LogAttributes la) {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
