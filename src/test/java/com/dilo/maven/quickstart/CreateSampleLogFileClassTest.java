package com.dilo.maven.quickstart;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateSampleLogFileClassTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before class");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("before");
	}

	/**
	 * Test the generated log entries in myLogs.log file.
	 */
	@Test
	public void PerformSomeTask() {
		CreateSampleLogFile clf = new CreateSampleLogFile();
		clf.performSomeTask();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("after");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after class");
	}

}
