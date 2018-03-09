package com.dilo.maven.quickstart;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class JsonHandlerClassTest {

	@Test
	public void toJson() {
		String json = "{\"message\":\"This is a fatal message.\",\"logger\":\"[main] CreateSampleLogFile \",\"timestamp\":\" 2018-03-02 20:59:59.062 \",\"loglevel\":\"[FATAL]\"}";
		JsonHandler jh = new JsonHandler();
		
		LogAttributes la = new LogAttributes();
		la.setLogger("[main] CreateSampleLogFile ");
		la.setLogLevel("[FATAL]");
		la.setTimestamp(" 2018-03-02 20:59:59.062 ");
		la.setMessage("This is a fatal message.");
		
		try {
			assertEquals(json ,jh.toJson(la));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
