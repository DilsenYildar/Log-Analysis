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
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before FindHandlerClass");
	}
	
	/**
	 * Initializing Tests with @Before Methods
	 *
	 */
	@Before
	public void setUp(){
		System.out.println("before");
		fh = new FileHandler();
		file = new File(fh.logFile);
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

	@After
	public void tearDown() throws Exception {
		System.out.println("after");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after FindHandlerClass");
	}
}
