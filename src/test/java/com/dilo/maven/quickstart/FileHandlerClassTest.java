package com.dilo.maven.quickstart;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileHandlerClassTest {
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
		}else
			fh.createLogFile();
			System.out.println("Propertieslogs.log yaratıldı.");
	}
}
