package com.juno.tomcat2.core;

import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.catalina.Cluster;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.InstanceListener;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Logger;
import org.apache.catalina.Manager;
import org.apache.catalina.Mapper;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Realm;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.util.LifecycleSupport;

public class SimpleWrapper implements Wrapper, Pipeline, Lifecycle {

	private Servlet instance = null;
	private SimplePipeline pipeline = new SimplePipeline(this);
	private String servletClass;
	private Loader loader;
	protected Container parent;
	private String name;
	protected LifecycleSupport lifecycle = new LifecycleSupport(this);
	protected boolean started = false;
	
	public SimpleWrapper(){
		this.pipeline.setBasic(new SimpleWrapperValve());
	}
	
	@Override
	public void addChild(Container arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addContainerListener(ContainerListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMapper(Mapper arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Container findChild(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container[] findChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerListener[] findContainerListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapper findMapper(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapper[] findMappers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cluster getCluster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loader getLoader() {
		if(loader != null){
			return loader;
		}
		
		if(parent != null){
			return parent.getLoader();
		}
		
		return null;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager getManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Container getParent() {
		return this.parent;
	}

	@Override
	public ClassLoader getParentClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Realm getRealm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirContext getResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invoke(Request req, Response resp) throws IOException,
			ServletException {
		this.pipeline.invoke(req, resp);
	}

	@Override
	public Container map(Request arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeChild(Container arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeContainerListener(ContainerListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMapper(Mapper arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCluster(Cluster arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	@Override
	public void setLogger(Logger arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setManager(Manager arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setParent(Container container) {
		this.parent = container;
	}

	@Override
	public void setParentClassLoader(ClassLoader arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRealm(Realm arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResources(DirContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addValve(Valve valve) {
		this.pipeline.addValve(valve);
	}

	@Override
	public Valve getBasic() {
		return pipeline.getBasic();
	}

	@Override
	public Valve[] getValves() {
		return this.pipeline.getValves();
	}

	@Override
	public void removeValve(Valve valve) {
		this.pipeline.removeValve(valve);
	}

	@Override
	public void setBasic(Valve basic) {
		this.pipeline.setBasic(basic);
	}

	@Override
	public void addInitParameter(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addInstanceListener(InstanceListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSecurityReference(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Servlet allocate() throws ServletException {
		if(this.instance == null){
			try{
				this.instance = this.loadServlet();
			}catch(Exception e){
				throw e;
			}catch(Throwable e){
				throw new ServletException("Cannot allocate a servlet instance");
			}
			
		}
		return instance;
	}

	@Override
	public void deallocate(Servlet arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public String findInitParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findInitParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findSecurityReference(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findSecurityReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getAvailable() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJspFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoadOnStartup() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getRunAs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletClass() {
		return null;
	}

	@Override
	public boolean isUnavailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void load() throws ServletException {
		this.instance = this.loadServlet();
	}

	@Override
	public void removeInitParameter(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeInstanceListener(InstanceListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSecurityReference(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAvailable(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJspFile(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoadOnStartup(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRunAs(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}

	@Override
	public void unavailable(UnavailableException arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unload() throws ServletException {
		// TODO Auto-generated method stub

	}
	
	
	private Servlet loadServlet() throws ServletException{
		if(this.instance != null){
			return this.instance;
		}
		
		Servlet servlet = null;
		String actualClass = servletClass;
		if(actualClass == null){
			throw new ServletException("servlet class has not been specified");
		}
		
		Loader loader = getLoader();
		if(loader == null){
			throw new ServletException("No loader.");
		}
		ClassLoader classLoader = loader.getClassLoader();
		
		Class classClass = null;
		try{
			if(classLoader != null){
				classClass = classLoader.loadClass(actualClass);
			}
		}catch(ClassNotFoundException e){
			throw new ServletException("Servlet class not found");
		}
		
		try {
			servlet = (Servlet)classClass.newInstance();
		} catch (Throwable e) {
			throw new ServletException("Failed to instantiate servlet");
		}		
		
		try{
			servlet.init(null);
		}catch(Throwable f){
			throw new ServletException("Failed to initialize servlet.");
		}
		
		return servlet;
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
		System.out.println("Starting Wrapper " + name);
	    if (started)
	      throw new LifecycleException("Wrapper already started");

	    // Notify our interested LifecycleListeners
	    lifecycle.fireLifecycleEvent(BEFORE_START_EVENT, null);
	    started = true;

	    // Start our subordinate components, if any
	    if ((loader != null) && (loader instanceof Lifecycle))
	      ((Lifecycle) loader).start();

	    // Start the Valves in our pipeline (including the basic), if any
	    if (pipeline instanceof Lifecycle)
	      ((Lifecycle) pipeline).start();

	    // Notify our interested LifecycleListeners
	    lifecycle.fireLifecycleEvent(START_EVENT, null);
	    // Notify our interested LifecycleListeners
	    lifecycle.fireLifecycleEvent(AFTER_START_EVENT, null);
	}

	@Override
	public void stop() throws LifecycleException {
		System.out.println("Stopping wrapper " + name);
	    // Shut down our servlet instance (if it has been initialized)
	    try {
	      instance.destroy();
	    }
	    catch (Throwable t) {
	    }
	    instance = null;
	    if (!started)
	      throw new LifecycleException("Wrapper " + name + " not started");
	    // Notify our interested LifecycleListeners
	    lifecycle.fireLifecycleEvent(BEFORE_STOP_EVENT, null);

	    // Notify our interested LifecycleListeners
	    lifecycle.fireLifecycleEvent(STOP_EVENT, null);
	    started = false;

	    // Stop the Valves in our pipeline (including the basic), if any
	    if (pipeline instanceof Lifecycle) {
	      ((Lifecycle) pipeline).stop();
	    }

	    // Stop our subordinate components, if any
	    if ((loader != null) && (loader instanceof Lifecycle)) {
	      ((Lifecycle) loader).stop();
	    }

	    // Notify our interested LifecycleListeners
	    lifecycle.fireLifecycleEvent(AFTER_STOP_EVENT, null);
	}

}
