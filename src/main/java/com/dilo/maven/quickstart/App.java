package com.dilo.maven.quickstart;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author Dilsen Yildar <dilsenyldr@yandex.com>
 */
public class App extends AbstractHandler {
	private static Logger logger = LogManager.getLogger();
	static HashSet<LogAttributes> hsAttr = new HashSet<LogAttributes>();

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		FileHandler fh = new FileHandler();
		fh.inputsFromLogFile(hsAttr);
		addDB();
		fh.deleteLogFile();
		fh.createLogFile();
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new App());
		server.start();
		server.join();
	}

	private static void addDB() {
		LoggingInDB lidb = new LoggingInDB();
		try {			
			Iterator<LogAttributes> hsAttrItr = hsAttr.iterator();
			while(hsAttrItr.hasNext()) {
				lidb.createOp(hsAttrItr.next());
				hsAttrItr.remove();
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
