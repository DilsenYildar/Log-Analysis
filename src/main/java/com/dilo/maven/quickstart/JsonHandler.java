package com.dilo.maven.quickstart;

import java.io.IOException;

import com.google.gson.Gson;

public class JsonHandler {
	/**
	 * Called in the LoggingInDB class when needed.
	 * 
	 * @param la
	 *            Which want to convert to json, comes from FileHandler to
	 *            LoggingInDB.
	 * @return jsonStr Holds json string for each la.
	 * @throws IOException
	 */
	public String toJson(LogAttributes la) throws IOException {
		Gson g = new Gson();
		String jsonStr;
		jsonStr = g.toJson(la);
		return jsonStr;
	}

	/**
	 * Called in the LoggingInDB class when needed.
	 *
	 * @param str
	 *            Which want to convert from json, comes from FileHandler to
	 *            LoggingInDB.
	 * @return Holds la for each json string.
	 */
	public LogAttributes fromJson(String str) {
		Gson g = new Gson();
		LogAttributes la = g.fromJson(str, LogAttributes.class);
		return la;
	}

}
