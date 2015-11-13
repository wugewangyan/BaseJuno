package com.juno.tomcat2.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.HttpRequest;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;
import org.apache.catalina.Wrapper;

public class SimpleContextValve implements Valve, Contained {

	protected Container container;
	
	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	public void invoke(Request req, Response resp, ValveContext valveContext)
			throws IOException, ServletException {

		if(!(req.getRequest() instanceof HttpServletRequest)
				|| !(req.getResponse() instanceof HttpServletResponse)){
			return;
		}
		
		HttpServletRequest hreq = (HttpServletRequest)req;
		String contextPath = hreq.getContextPath();
		String requestURI = ((HttpRequest)req).getDecodedRequestURI();
		String relativeURI = requestURI.substring(contextPath.length()).toUpperCase();
		
		Context context = (Context)this.getContainer();
		
		Wrapper wrapper = null;
		try{
			wrapper = (Wrapper)context.map(req, true);
		}catch(IllegalArgumentException e){
			this.badRequest(requestURI, (HttpServletResponse)resp.getResponse());
			return;
		}
		
		if(wrapper == null){
			this.notFound(requestURI, (HttpServletResponse)resp.getResponse());
			return;
		}
		
		resp.setContext(context);
		wrapper.invoke(req, resp);
	}
	
	
	private void badRequest(String requestURI, HttpServletResponse response) {
	    try {
	      response.sendError(HttpServletResponse.SC_BAD_REQUEST, requestURI);
	    }
	    catch (IllegalStateException e) {
	      ;
	    }
	    catch (IOException e) {
	      ;
	    }
	  }

	  private void notFound(String requestURI, HttpServletResponse response) {
	    try {
	      response.sendError(HttpServletResponse.SC_NOT_FOUND, requestURI);
	    }
	    catch (IllegalStateException e) {
	      ;
	    }
	    catch (IOException e) {
	      ;
	    }
	  }


}
