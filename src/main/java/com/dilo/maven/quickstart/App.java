package com.dilo.maven.quickstart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

/**
 *
 * @author Dilsen Yildar <dilsenyldr@yandex.com>
 */
public class App extends AbstractHandler {

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		FileHandler fh = new FileHandler();
		fh.inputsFromLogFile();
		fh.addDB();
		fh.deleteLogFile();
		fh.createLogFile();
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ContextHandler context = new ContextHandler();
		context.setContextPath("/logtodb");
		context.setHandler(new App());

		server.setHandler(context);
		server.start();
		server.join();
	}
}
