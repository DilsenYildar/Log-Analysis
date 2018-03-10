package com.dilo.maven.quickstart;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateSampleLogFileTest {
	private CreateSampleLogFile clf;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("before class");
	}

	/**
	 * Initializing Tests with @Before Methods
	 *
	 */
	@Before
	public void setUp() {
		System.out.println("before method");
		clf = new CreateSampleLogFile();
	}

	/**
	 * Test the generated log entries in myLogs.log file.
	 */
	@Test
	public void PerformSomeTask() {
		clf.performSomeTask();
	}

	@After
	public void tearDown() {
		System.out.println("after method");
	}

	@AfterClass
	public static void tearDownAfterClass(){
		System.out.println("after class");
	}

}
