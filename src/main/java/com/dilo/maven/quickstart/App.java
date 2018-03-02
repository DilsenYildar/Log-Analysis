package com.dilo.maven.quickstart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashSet;

/**
 *
 * @author Dilsen Yildar <dilsenyldr@yandex.com>
 */
public class App {
	static HashSet<LogAttributes> hsAttr = new HashSet<LogAttributes>();
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		CreateLogFile clf = new CreateLogFile();
		FileHandler fh = new FileHandler();
		/**
		 * This object is using for create a .log file.
		 */
		clf.performSomeTask();
		/**
		 * This is where i convert to log inputs to json.
		 */
		fh.inputsFromLogFile(hsAttr);
		addDB();
	}
	private static void addDB() {
		LoggingInDB lidb = new LoggingInDB();
        try {
        	for (LogAttributes la : hsAttr) {
			lidb.createOp(la);
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
