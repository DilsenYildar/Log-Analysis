package com.dilo.maven.quickstart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * When the /path is equal to the /logtodb this class runs. Add logs from the
 * log file to the PostgreSQL database.
 * 
 */
public class LogToDBContext extends AbstractHandler {

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		FileHandler fh = new FileHandler();
		fh.inputsFromLogFile();
		fh.addDB();
		response.getWriter().write("<h1>Your log files in the postgresqldb now!</h1>");
		fh.deleteLogFile();
		fh.createLogFile();

		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpStatus.OK);
		baseRequest.setHandled(true);
	}

}
