package com.juno.tomcat2.values;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

public class ClientIPLoggerValve implements Valve, Contained {

	protected Container container;
	
	@Override
	public Container getContainer() {
		return this.container;
	}

	@Override
	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public String getInfo() {
		return "A ClientIPLoggerValve";
	}

	@Override
	public void invoke(Request req, Response resp, ValveContext context)
			throws IOException, ServletException {
		System.out.println("----- Start invoke ClientIPLoggerValve ----");
		
		// Pass this request on to the next valve in our pipeline
		context.invokeNext(req, resp);
		
		System.out.println("----  after ClientIPLoggerValve ----");
		ServletRequest sreq = req.getRequest();
		System.out.println("The Client Host is : " + sreq.getRemoteAddr());
		System.out.println("Finish invoke ClientIPLoggerValve");
	}

}
