package com.juno.tomcat.startup;

import com.juno.tomcat.connector.http.HttpConnector;

public class Bootstrap {
	public static void main(String[] args) {
		new HttpConnector().start();
	}
}
