package com.dilo.maven.quickstart;

import org.junit.Test;

public class CreateSampleLogFileClassTest {
	
	/**
	 * Test the generated log entries in myLogs.log file.
	 */
	@Test
	public void PerformSomeTask() {
		CreateSampleLogFile clf = new CreateSampleLogFile();
		clf.performSomeTask();
	}

}
