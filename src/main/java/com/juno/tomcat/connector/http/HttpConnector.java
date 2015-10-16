package com.juno.tomcat.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {

	private boolean stopped = false;
	private String scheme = "http";
	
	public String getScheme(){
		return this.scheme;
	}
	
	@Override
	public void run() {
		int port = 8080;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		while(!stopped){
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			
			// Hand this socket off to an HttpProcessor
			HttpProcessor httpProcessor = new HttpProcessor(this);
			httpProcessor.process(socket);
		}
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
}
