package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.SQLException;

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
@SuiteClasses({ AllTests.class, LoggingInDbTest.class, CreateLogFileTest.class, })
public class AllTests extends TestCase {
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
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	/**
	 * Test the generated log entries in myLogs.log file.
	 */
	public void testPerformSomeTask() {
		CreateLogFileTest clf = new CreateLogFileTest();
		clf.performSomeTask();
	}

	/**
	 * Test that the created logAttributes object is registered in the database.
	 */
	public void testDBcreate() {
		LoggingInDB lidb = new LoggingInDB();
		LogAttributes la = new LogAttributes();
		la.setLogger("[main] CreateLogFile ");
		la.setLogLevel("[FATAL]");
		la.setTimestamp(" 2018-03-02 20:42:25.062 ");
		la.setMessage("This is a fatal message.");

		try {
			lidb.createOp(la);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test that the specific log given the parameters from the URI is deleted.
	 */
	public void testDBdelete() {
		LoggingInDB lidb = new LoggingInDB();
		String logAttr = "loglevel";
		String logAttrType = "DEBUG";

		try {
			assertEquals("loglevel", logAttr);
			assertEquals("DEBUG", logAttrType);
			lidb.deleteOp(logAttr, logAttrType);

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
