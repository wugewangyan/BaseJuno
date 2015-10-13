package com.juno.tomcat.processor;

import java.io.IOException;

import com.juno.tomcat.connector.http.HttpRequest;
import com.juno.tomcat.connector.http.HttpResponse;

public class StaticResourceProcessor {

	public void process(HttpRequest request, HttpResponse response){
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
