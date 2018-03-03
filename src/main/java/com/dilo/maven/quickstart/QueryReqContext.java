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
 *
 * @author Dilsen Yildar <dilsenyldr@yandex.com>
 */
public class QueryReqContext extends AbstractHandler {
	
	String logAttr;
	String logAttrType;
	
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		LoggingInDB lidb = new LoggingInDB();
	
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			logAttr = (String) parameterNames.nextElement();
			logAttrType = request.getParameter(logAttr).toString();
			
			response.getWriter().write(String.format("Logs that you want to delete:%s%s\n", logAttr, logAttrType));
		}
		
		if(logAttr.equals("contains") ){
			response.getWriter().write("<p>"+lidb.queryOp("contains", logAttrType)+"</p>");
		}
		else {
			response.getWriter().write("<p>"+lidb.queryOp(logAttr, logAttrType)+"</p>");
		}	
		

		response.setContentType("text/html;charset=utf-8"); 
		response.setStatus(HttpStatus.FORBIDDEN_403);;		
		baseRequest.setHandled(true);
	}
}
