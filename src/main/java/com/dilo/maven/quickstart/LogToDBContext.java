package com.dilo.maven.quickstart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author Dilsen Yildar <dilsenyldr@yandex.logtodbcom>
 */
public class LogToDBContext extends AbstractHandler {

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		FileHandler fh = new FileHandler();
		fh.inputsFromLogFile();
		fh.addDB();
		fh.deleteLogFile();
		fh.createLogFile();
		
		response.setContentType("text/html;charset=utf-8"); 
		response.setStatus(HttpStatus.FORBIDDEN_403);;		
		response.getWriter().write("<h1>welcome to logtodb</h1>");
		baseRequest.setHandled(true);
	}

	public static void main(String[] args) throws Exception {
	////
	}
}

