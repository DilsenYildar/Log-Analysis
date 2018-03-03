package com.dilo.maven.quickstart;

import java.io.IOException;

import com.google.gson.Gson;

public class JsonHandler {
	/**
	 * @param la
	 * @return
	 * @throws IOException
	 */
	public String toJson(LogAttributes la) throws IOException {
		Gson g = new Gson();
		String jsonStr;
		jsonStr = g.toJson(la);
		return jsonStr;
	}
	/**
	 * @param str
	 * @return
	 */
	public LogAttributes fromJson(String str) {
		Gson g = new Gson();
		LogAttributes la = g.fromJson(str, LogAttributes.class);
		return la;
	}
	

}
