package com.juno.tomcat2.values;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

public class HeaderLoggerValve implements Valve, Contained {

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
		return "A HeaderLoggerValve";
	}

	@Override
	public void invoke(Request req, Response resp, ValveContext context)
			throws IOException, ServletException {
		System.out.println("----- Start invoke HeaderLoggerValve ----");
		
		// Pass this request on to the next valve in our pipeline
		context.invokeNext(req, resp);
		
		System.out.println("----  after HeaderLoggerValve ----");
		ServletRequest sreq = req.getRequest();
		if(sreq instanceof HttpServletRequest){
			HttpServletRequest request = (HttpServletRequest)sreq;
			java.util.Enumeration headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()){
				String headerName = headerNames.nextElement().toString();
				String headerValue = request.getHeader(headerName);
				System.out.println(headerName + " --> " + headerValue);
			}
		}else{
			System.out.println("Not an HTTP Request");
		}
		
		System.out.println("Finish invoke HeaderLoggerValve");
	}

}
