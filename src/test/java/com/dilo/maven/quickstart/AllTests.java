package com.dilo.maven.quickstart;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple LogToDBContext.
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ AllTests.class, LoggingInDbTest.class, CreateSampleLogFileTest.class })
public class AllTests extends TestCase {
	String logAttr;
	String logAttrType;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AllTests(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AllTests.class);
	}

	/**
	 * Test the generated log entries in myLogs.log file.
	 */
	public void testPerformSomeTask() {
		CreateSampleLogFileTest clf = new CreateSampleLogFileTest();
		clf.performSomeTask();
	}

	/**
	 * Test that the created logAttributes object is registered in the database.
	 */
	public void testDBcreate() {
		String query = null;
		Connection connection = null;
		Statement stmnt = null;
		LoggingInDbTest lidb = new LoggingInDbTest();
		LogAttributes la = new LogAttributes();
		la.setLogger("[main] CreateSampleLogFile ");
		la.setLogLevel("[FATAL]");
		la.setTimestamp(" 2018-03-02 20:59:59.062 ");
		la.setMessage("This is a fatal message.");

		try {
			lidb.createOp(la);

			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();

			String sql = "select  * from json where data ->> 'timestamp' = ' 2018-03-02 20:59:59.062 ';";
			ResultSet rs = stmnt.executeQuery(sql);
			while (rs.next()) {
				query = rs.getString("data");
			}
			System.out.println("Test başarılı. Kayıt db'ye eklendi: " + query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test that the specific log given the parameters from the URI is deleted.
	 */
	public void testDBdelete() {
		LoggingInDbTest lidb = new LoggingInDbTest();
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
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void testCreateLogFile() {
		FileHandler fh = new FileHandler();
		File file = new File("/home/dilo/eclipse-workspace/quickstart/logs/propertieslogs.log");
		if (file.exists()) {
			System.out.println("Propertieslogs.log mevcuttur.");
		}else
			fh.createLogFile();
			System.out.println("Propertieslogs.log yaratıldı.");
	}
}
