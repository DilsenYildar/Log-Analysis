package com.dilo.maven.quickstart;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JsonHandlerTest {
	private JsonHandler jh;
	private LogAttributes la;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before JsonHandlerClass");
	}

	/**
	 * Initializing Tests with @Before Methods
	 * 
	 */
	@Before
	public void setUp() {
		System.out.println("before");
		jh = new JsonHandler();
		la = new LogAttributes();
	}

	@Test
	public void toJson() {
		String expected = "{\"message\":\"This is a fatal message.\",\"logger\":\"[main] CreateSampleLogFile \",\"timestamp\":\" 2018-03-02 20:59:59.062 \",\"loglevel\":\"[FATAL]\"}";
		// logAttributes nesnesinde tanıttığım sıraya göre json oluşturulmasını
		// bekliyorum..

		la.setLogger("[main] CreateSampleLogFile ");
		la.setLogLevel("[FATAL]");
		la.setTimestamp(" 2018-03-02 20:59:59.062 ");
		la.setMessage("This is a fatal message.");

		try {
			String result = jh.toJson(la);
			assertEquals(expected, result); // toJson() fonk ile oluşturulan json, beklediğim ile aynı..
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("after");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after JsonHandlerClass");
	}

}
