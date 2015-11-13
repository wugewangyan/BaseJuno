package com.juno.tomcat2.core;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;


/**
 * base valve
 * @author wuge
 *
 */
public class SimpleWrapperValve implements Contained, Valve {

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
		return null;
	}

	@Override
	public void invoke(Request request, Response response, ValveContext context)
			throws IOException, ServletException {

		ServletRequest sreq = request.getRequest();
		ServletResponse sresp = response.getResponse();
		
		HttpServletRequest hreq = null;
		if(sreq instanceof HttpServletRequest){
			hreq = (HttpServletRequest)sreq;
		}
		
		HttpServletResponse hresp = null;
		if(sresp instanceof HttpServletResponse){
			hresp = (HttpServletResponse)sresp;
		}
		
		Servlet servlet = null;
		SimpleWrapper wrapper = (SimpleWrapper)this.getContainer();
		
		try{
			servlet = wrapper.allocate();
			if(hreq != null && hresp != null){
				servlet.service(hreq, hresp);
			}else{
				servlet.service(sreq, sresp);
			}
		}catch(ServletException e){
			e.printStackTrace();
		}
	}
}
