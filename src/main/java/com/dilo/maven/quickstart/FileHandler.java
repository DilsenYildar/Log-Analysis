package com.dilo.maven.quickstart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;

public class FileHandler {
	String line = "";
	String logFile = "/home/dilo/eclipse-workspace/quickstart/logs/propertieslogs.log";
	/**
	 * 
	 * @param hsAttr
	 * @throws FileNotFoundException
	 */
	public void inputsFromLogFile(HashSet<LogAttributes> hsAttr) throws FileNotFoundException {
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
			e.printStackTrace();
		}
	}
	
	
	
	
}
