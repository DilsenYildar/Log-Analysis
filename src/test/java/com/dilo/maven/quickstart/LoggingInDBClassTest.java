package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class LoggingInDBClassTest {

	/**
	 * Test the generated log entries in myLogs.log file.
	 */
	@Test
	public void PerformSomeTask() {
		CreateSampleLogFile clf = new CreateSampleLogFile();
		clf.performSomeTask();
	}
	
	/**
	 * 
	 * Test that the created logAttributes object is registered in the database.
	 */
	@Test
	public void DBcreate() {
		
		LoggingInDB lidb = new LoggingInDB();
		LogAttributes la = new LogAttributes();
		
		la.setLogger("[main] CreateSampleLogFile ");
		la.setLogLevel("[FATAL]");
		la.setTimestamp(" 2018-03-02 20:59:59.062 ");
		la.setMessage("This is a fatal message.");

		try {
			lidb.createOp(la);
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
		String logAttr = "loglevel";
		String logAttrType = "DEBUG";

		try {
			lidb.deleteOp(logAttr, logAttrType);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}

