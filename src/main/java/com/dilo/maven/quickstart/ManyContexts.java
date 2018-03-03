package com.dilo.maven.quickstart;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class ManyContexts {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ContextHandler context = new ContextHandler();
		context.setContextPath("/logtodb");
		context.setHandler(new LogToDBContext());
		
		ContextHandler contextDR = new ContextHandler("/delete");
		contextDR.setHandler(new DeleteReqContext());
		
		ContextHandler contextUR = new ContextHandler("/update");
		contextUR.setHandler(new UpdateReqContext());
		
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] {context, contextDR, contextUR});
		
		server.setHandler(contexts);
		server.start();
		server.join();
	}

}
