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
		FileHandler fh = new FileHandler();
		/**
		 * This is where i convert to log inputs to json.
		 */
		fh.inputsFromLogFile(hsAttr);
		/**
		 * This is where i add to logInputs into postgresql db.
		 */
		addDB();
		fh.deleteLogFile();
		fh.createLogFile();
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
