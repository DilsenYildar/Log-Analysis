package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author Dilsen Yildar <dilsenyldr@yandex.com>
 */
public class DeleteReqContext extends AbstractHandler {
	private Logger logger = LogManager.getLogger();

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		deleteFromDB();
		
		response.setContentType("text/html;charset=utf-8"); 
		response.setStatus(HttpStatus.FORBIDDEN_403);;
		response.getWriter().write("<h1>welcome to deletereq</h1>");
		baseRequest.setHandled(true);
	}

	public static void main(String[] args) throws Exception {
		///
	}
	public void deleteFromDB() {
		LoggingInDB lidb = new LoggingInDB();
		try {
			lidb.deleteOp();
		}catch (SQLException | IOException e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}
}

