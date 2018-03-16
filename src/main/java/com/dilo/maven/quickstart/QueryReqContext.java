package com.dilo.maven.quickstart;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * When the /path is equal to the /query/ this class runs. Get logs from the
 * PostgreSQL database.
 *
 */
public class QueryReqContext extends AbstractHandler {

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
			response.getWriter().write(String.format("<p><i><b>Logs that you want to query: '%s' : '%s'\n </b></i></p>", logAttr, logAttrType));
		}

		if (logAttr.equals("contains")) {
			response.getWriter().write("<p>" + lidb.queryOp("contains", logAttrType) + "</p>");
		}else {
			response.getWriter().write("<p>" + lidb.queryOp(logAttr, logAttrType) + "</p>");
		}
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpStatus.OK_200);
		baseRequest.setHandled(true);
	}
}
