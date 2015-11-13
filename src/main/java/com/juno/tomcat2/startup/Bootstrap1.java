package com.juno.tomcat2.startup;

import org.apache.catalina.Loader;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

import com.juno.tomcat2.core.SimpleLoader;
import com.juno.tomcat2.core.SimpleWrapper;
import com.juno.tomcat2.values.ClientIPLoggerValve;
import com.juno.tomcat2.values.HeaderLoggerValve;

public class Bootstrap1 {

	public static void main(String[] args) {
		HttpConnector connector = new HttpConnector();
		
		Wrapper wrapper = new SimpleWrapper();
		wrapper.setServletClass("ModernServlet");
		
		Loader loader = new SimpleLoader();
		wrapper.setLoader(loader);
		
		ClientIPLoggerValve ipValve = new ClientIPLoggerValve();
		HeaderLoggerValve headerValve = new HeaderLoggerValve();
		
		((Pipeline)wrapper).addValve(ipValve);
		((Pipeline)wrapper).addValve(headerValve);
		
		connector.setContainer(wrapper);
	
		try{
			connector.initialize();
			connector.start();
			
			// make the application wait until we press a key;
			System.in.read();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
