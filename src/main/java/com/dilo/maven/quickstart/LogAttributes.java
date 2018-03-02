package com.dilo.maven.quickstart;

public class LogAttributes {
	private String message;
	private String logger;
	private String timestamp;
	private String loglevel;

	public LogAttributes() {
	}

	public LogAttributes(String loglevel, String timestamp, String logger, String message) {
		this.loglevel = loglevel;
		this.timestamp = timestamp;
		this.logger = logger;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getLogLevel() {
		return loglevel;
	}

	public void setLogLevel(String logLevel) {
		this.loglevel = logLevel;
	}

	public String toString() {
		return loglevel + timestamp + logger + message;
	}
}
