package com.juno.tomcat2.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Container;
import org.apache.catalina.HttpRequest;
import org.apache.catalina.Mapper;
import org.apache.catalina.Request;
import org.apache.catalina.Wrapper;

public class SimpleContextMapper implements Mapper {

	private SimpleContext context = null;
	
	@Override
	public Container getContainer() {
		return context;
	}

	@Override
	public String getProtocol() {
		return null;
	}

	
	/**
	   * Return the child Container that should be used to process this Request,
	   * based upon its characteristics.  If no such child Container can be
	   * identified, return <code>null</code> instead.
	   *
	   * @param request Request being processed
	   * @param update Update the Request to reflect the mapping selection?
	   *
	   * @exception IllegalArgumentException if the relative portion of the
	   *  path cannot be URL decoded
	   */
	@Override
	public Container map(Request req, boolean update) {
		String contextPath = ((HttpServletRequest)req.getRequest()).getContextPath();
		String requestURI = ((HttpRequest)req).getDecodedRequestURI();
		String relativeURI = requestURI.substring(contextPath.length());
		
		Wrapper wrapper = null;
		String name = context.findServletMapping(relativeURI);
		if(name != null){
			wrapper = (Wrapper)context.findChild(name);
		}
		
		return wrapper;
	}

	@Override
	public void setContainer(Container container) {
		if(!(container instanceof SimpleContext)){
			throw new IllegalArgumentException("Illegal type of container");
		}
		
		this.context = (SimpleContext)container;
	}

	@Override
	public void setProtocol(String arg0) {
		// TODO Auto-generated method stub

	}

}
