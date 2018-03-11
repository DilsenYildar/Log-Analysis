package com.dilo.maven.quickstart;

public class LogAttributes {
	private String loglevel;
	private String timestamp;
	private String logger;
	private String message;
	


	public LogAttributes() {
	}

	/**
	 * Log inputs were parsed and thrown into this object.
	 * 
	 * @param loglevel
	 *            Level of the log like debug, error, warn etc.
	 * @param timestamp
	 *            Date-Time of the log like '2018-03-02 20:42:25.062'
	 * @param logger
	 *            Location of the log. '[main] CreateSampleLogFile'
	 * @param message
	 *            Message of the log content like 'This is a fatal message.'
	 */
	public LogAttributes(String loglevel, String timestamp, String logger, String message) {
		this.loglevel = loglevel;
		this.timestamp = timestamp;
		this.logger = logger;
		this.message = message;
	}

	public String getLogLevel() {
		return loglevel;
	}

	public void setLogLevel(String logLevel) {
		this.loglevel = logLevel;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return loglevel + timestamp + logger + message;
	}
}
