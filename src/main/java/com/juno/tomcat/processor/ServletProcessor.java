package com.juno.tomcat.processor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

import com.juno.tomcat.connector.http.HttpRequest;
import com.juno.tomcat.connector.http.HttpRequestFacade;
import com.juno.tomcat.connector.http.HttpResponse;
import com.juno.tomcat.connector.http.HttpResponseFacade;
import com.juno.tomcat.constants.Constants;

public class ServletProcessor {
	
	
	public void process(HttpRequest request, HttpResponse response){
		String uri = request.getRequestURL().toString();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		
		try {
			URLStreamHandler streamHandler = null;
			URL[] urls = new URL[1];
			File classPath = new File(Constants.WEB_ROOT);
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator).toString());
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Servlet servlet = null;
		HttpRequestFacade requestFacade = new HttpRequestFacade(request);
		HttpResponseFacade responseFacade = new HttpResponseFacade(response);
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service(requestFacade, responseFacade);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
}
