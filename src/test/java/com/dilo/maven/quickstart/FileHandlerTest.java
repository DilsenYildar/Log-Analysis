package com.dilo.maven.quickstart;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileHandlerTest {
	private FileHandler fh;
	private File file;
	private LogAttributes la;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before FindHandlerClass");
	}

	/**
	 * Initializing Tests with @Before Methods
	 *
	 */
	@Before
	public void setUp() {
		System.out.println("before");
		fh = new FileHandler();
		file = new File(fh.logFile);
		la = new LogAttributes("[FATAL]", " 2018-03-02 20:59:59.062 ", "[main] CreateSampleLogFile ",
				"This is a fatal message.");
	}

	/**
	 * 
	 * Test that if propertieslogs.log file is created.
	 */
	@Test
	public void CreateLogFile() {

		assertTrue(file.exists());
		if (file.exists()) {
			System.out.println("Propertieslogs.log mevcuttur.");
		} else
			fh.createLogFile();
		System.out.println("Propertieslogs.log yaratıldı.");
	}

	/**
	 * Test that the HashSet is null after the logs put into db, and test that the
	 * hashset is not null after add a la object into hashset.
	 */
	@Test
	public void inputsFromLogFile() {
		assertTrue(fh.hsAttr.isEmpty()); // hashset is empty first.
		System.out.println("Is the HashSet is empty? " + fh.hsAttr.isEmpty());

		fh.hsAttr.add(la); // add to la object into HashSet
		assertFalse(fh.hsAttr.isEmpty()); // hashset is not empty now.
		System.out.println("Is the HashSet is empty? " + fh.hsAttr.isEmpty());
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("after");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after FindHandlerClass");
	}
}
