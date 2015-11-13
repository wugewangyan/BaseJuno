package com.juno.tomcat2.core;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

public class SimplePipeline implements Pipeline, Lifecycle {
	
	protected Valve basic = null;
	protected Container container = null;
	protected Valve[] valves = new Valve[0];

	public SimplePipeline(Container container){
		this.setContainer(container);
	}
	
	@Override
	public void addValve(Valve valve) {
		if(valve instanceof Contained){
			((Contained)valve).setContainer(this.container);
		}
		
		synchronized(valves){
			Valve[] results = new Valve[valves.length + 1];
			System.arraycopy(valves, 0, results, 0, valves.length);
			results[valves.length] = valve;
			valves = results;
		}
	}
	
	public void setContainer(Container container){
		this.container = container;
	}

	@Override
	public Valve getBasic() {
		return this.basic;
	}

	@Override
	public Valve[] getValves() {
		return this.valves;
	}

	@Override
	public void invoke(Request req, Response resp) throws IOException,
			ServletException {
		new SimplePipelineValveContext().invokeNext(req, resp);
	}

	@Override
	public void removeValve(Valve arg0) {
	}

	@Override
	public void setBasic(Valve basic) {
		this.basic = basic;
		((Contained)basic).setContainer(this.container);
	}
	
	
	protected class SimplePipelineValveContext implements ValveContext{
		
		protected int stage = 0;
		
		@Override
		public String getInfo() {
			return null;
		}
		
		@Override
		public void invokeNext(Request req, Response resp) throws IOException,
				ServletException {
			int subscript = stage;
			stage = stage + 1;
			if(subscript < valves.length){
				valves[subscript].invoke(req, resp, this);
			}else if((subscript == valves.length) && (basic != null)){
				basic.invoke(req, resp, this);
			}else{
				throw new ServletException("No valve");
			}
		}
	}


	@Override
	public void addLifecycleListener(LifecycleListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLifecycleListener(LifecycleListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws LifecycleException {
		System.out.println("SimplePipeline has started");
	}

	@Override
	public void stop() throws LifecycleException {
		System.out.println("SimplePipeline has stopped");
	}

}
