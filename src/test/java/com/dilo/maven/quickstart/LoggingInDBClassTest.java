package com.dilo.maven.quickstart;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoggingInDBClassTest {
	
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
        System.out.println("before LoggingInDBClass");  
    }  
    @Before  
    public void setUp() throws Exception {  
        System.out.println("before");  
    }  
	
	/**
	 * 
	 * Test that the created logAttributes object is registered in the database.
	 */
	@Test
	public void DBcreate() {
		
		String query = null;
		String jsonintoDB = "{\"logger\": \"[main] CreateSampleLogFile \", \"message\": \"This is a fatal message.\", \"loglevel\": \"[FATAL]\", \"timestamp\": \" 2018-03-02 20:59:59.062 \"}";
		//database'de olmasını istediğim format 
		Connection connection = null;
		Statement stmnt = null;
		LoggingInDB lidb = new LoggingInDB();
		LogAttributes la = new LogAttributes();
		la.setLogger("[main] CreateSampleLogFile ");
		la.setLogLevel("[FATAL]");
		la.setTimestamp(" 2018-03-02 20:59:59.062 ");
		la.setMessage("This is a fatal message.");

		try {
			lidb.createOp(la); //bu fonk. çalıştırıldıktan sonra database'e eklenen la'yı olduğu gibi query değişkenine çekeceğim...
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();
			String sql = "select  * from json where data ->> 'timestamp' = ' 2018-03-02 20:59:59.062 ';";
			ResultSet rs = stmnt.executeQuery(sql);
			while (rs.next()) {
				query = rs.getString("data");
			}
			
			assertEquals(jsonintoDB, query); //beklediğim format ile createOp fonk. çalıştıktan sonra dbye eklenen aynı..
			System.out.println("Test başarılı. Kayıt db'ye eklendi: ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * Test that the specific log given the parameters from the URI is deleted.
	 */
	@Test 
	public void DBdelete() {
		LoggingInDB lidb = new LoggingInDB();
		Connection connection = null;
		Statement stmnt = null;
		String query = null, query2 = null;
		String logAttr = "loglevel";
		String logAttrType = "DEBUG";

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();

			String sql = "select  * from json where data ->> 'loglevel' = '[DEBUG]';";
			ResultSet rs = stmnt.executeQuery(sql);
			while (rs.next()) {
				query = rs.getString("data");
			}
			
			lidb.deleteOp(logAttr, logAttrType);
			String sql2 = "select  * from json where data ->> 'loglevel' = '[DEBUG]';";
			ResultSet rs2 = stmnt.executeQuery(sql2);
			while (rs2.next()) {
				query2 = rs2.getString("data");
			}
			if (query != null && query2 == null) {
				System.out.println("Test başarılı. Silmek istediğiniz kayıt db'den silindi.");
			} else
				System.out.println("Silmek istediğiniz kayıt db'de yok.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

    @After  
    public void tearDown() throws Exception {  
        System.out.println("after");  
    }  
  
    @AfterClass  
    public static void tearDownAfterClass() throws Exception {  
        System.out.println("after LoggingInDBClass");  
    }  
}

