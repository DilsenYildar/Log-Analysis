package com.dilo.maven.quickstart;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileHandlerClassTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before FindHandlerClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("before");
	}

	/**
	 * 
	 * Test that if propertieslogs.log file is created.
	 */
	@Test
	public void CreateLogFile() {
		FileHandler fh = new FileHandler();
		File file = new File(fh.logFile);
		assertTrue(file.exists());

		if (file.exists()) {
			System.out.println("Propertieslogs.log mevcuttur.");
		} else
			fh.createLogFile();
		System.out.println("Propertieslogs.log yaratıldı.");
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
