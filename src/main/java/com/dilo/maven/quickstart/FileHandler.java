package com.dilo.maven.quickstart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileHandler {
	private Logger logger = LogManager.getLogger();
	HashSet<LogAttributes> hsAttr = new HashSet<LogAttributes>();
	String logFile = "/home/dilo/eclipse-workspace/quickstart/logs/propertieslogs.log";

	/**
	 * Get log inputs from the logfile and add these to the hashset that type is
	 * LogAttributes.
	 * 
	 * @throws FileNotFoundException
	 */
	public void inputsFromLogFile() throws FileNotFoundException {
		String line = "";
		FileReader file = new FileReader(logFile);
		try (BufferedReader br = new BufferedReader(file)) {
			while ((line = br.readLine()) != null) {
				LogAttributes la = new LogAttributes();

				la.setLogLevel(line.substring(line.indexOf("["), line.indexOf("]") + 1));
				line = line.substring(line.indexOf("]") + 1);
				la.setTimestamp(line.substring(0, line.indexOf("[")));
				line = line.substring(line.indexOf("["));
				la.setLogger(line.substring(0, line.indexOf("-")));
				la.setMessage(line.substring(line.indexOf("-") + 2));
				hsAttr.add(la);
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
	}

	/**
	 * Add logs that in the hashset into the database.
	 * 
	 */
	public void addDB() {
		LoggingInDB lidb = new LoggingInDB();
		try {
			Iterator<LogAttributes> hsAttrItr = hsAttr.iterator();
			while (hsAttrItr.hasNext()) {
				lidb.createOp(hsAttrItr.next());
				hsAttrItr.remove();
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * After creating the sample log file, it is deleted and a new empty file is
	 * created.
	 */

	public void deleteLogFile() {
		try {
			File file = new File(logFile);
			file.delete();
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
	}

	public void createLogFile() {
		try {
			File file = new File(logFile);
			file.createNewFile();
		} catch (IOException e) {
			logger.fatal(e);
			e.printStackTrace();
		}
	}
}
