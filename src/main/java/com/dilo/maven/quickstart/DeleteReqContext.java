package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * When the /path is equal to the /delete this class runs. Delete logs from the
 * PostgreSQL database according to given parametres...
 * 
 */
public class DeleteReqContext extends AbstractHandler {
	private Logger logger = LogManager.getLogger();
	/**
	 * Given URI like /path/?logAttr=logAttrType
	 * 
	 * @description logAttr, log attributes like 'loglevel'
	 * @description logAttrType, given for log attributes type like 'DEBUG'
	 * 
	 */
	String logAttr;
	String logAttrType;

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		LoggingInDB lidb = new LoggingInDB();

		/**
		 * Get parameters from the URI that entered.
		 */
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			logAttr = (String) parameterNames.nextElement();
			logAttrType = request.getParameter(logAttr).toString();

			response.getWriter().write(String.format("<p><i><b>Logs that you want to delete: '%s' : '%s' \n </b></i></p>", logAttr, logAttrType));
		}
		try {
			lidb.deleteOp(logAttr, logAttrType);
			response.getWriter().write("Log is deleted!");

		} catch (SQLException e) {
			logger.warn(e);
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpStatus.FORBIDDEN_403);
		baseRequest.setHandled(true);
	}
}
