package com.juno.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	private boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}
	
	public void await(){
		ServerSocket serverSocket = null;
		int port = 8080;
		
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		while(!shutdown){
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				HttpRequest request = new HttpRequest(input);
				request.parse();
				
				HttpResponse response = new HttpResponse(output);
				response.setHttpRequest(request);

				if(request.getUri().startsWith("/servlet/")){
					ServletProcessor servletProcessor = new ServletProcessor();
					servletProcessor.process(request, response);
				}else{
					StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
					staticResourceProcessor.process(request, response);
				}
				
				socket.close();
				
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
