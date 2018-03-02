package com.dilo.maven.quickstart;

import java.io.IOException;

import com.google.gson.Gson;

public class JsonHandler {
	/**
	 * Create a json file with using logAttributes object that i fill above. Send to
	 * postgresql db.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public String toJson(LogAttributes la) throws IOException {
		Gson g = new Gson();
		String jsonStr;
		jsonStr = g.toJson(la);
		return jsonStr;
	}
	public LogAttributes fromJson(String str) {
		Gson g = new Gson();
		LogAttributes la = g.fromJson(str, LogAttributes.class);
		return la;
	}
	

}
