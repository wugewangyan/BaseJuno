package com.juno.tomcat2.core;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import org.apache.catalina.Container;
import org.apache.catalina.DefaultContext;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;

public class SimpleLoader implements Loader, Lifecycle {

	public static final String WEB_ROOT = "/Users/wuge/Documents/webroot";
	private ClassLoader classLoader;
	private Container container;
	
	public SimpleLoader(){
		try{
			URLStreamHandler streamHandler = null;
			File classPath = new File(WEB_ROOT);
			String repository = new URL("file", null, classPath.getCanonicalPath() + File.separator).toString();
			URL[] urls = new URL[1];
			urls[0] = new URL(null, repository, streamHandler);
			
			// URLClassLoader是ClassLoader的子类，他用于从指向JAR文件和目录的URL的搜索路径加载类和资源
			// 也就是说通过URLClassLoader就可以加载指定jar中的class到内存中。
			classLoader = new URLClassLoader(urls);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {

	}

	@Override
	public void addRepository(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] findRepositories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		return this.classLoader;
	}

	@Override
	public Container getContainer() {
		return this.container;
	}

	

	@Override
	public boolean getDelegate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getInfo() {
		return "A Simple ClassLoader";
	}

	@Override
	public boolean getReloadable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContainer(Container container) {
		this.container = container;
	}


	@Override
	public void setDelegate(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReloadable(boolean arg0) {
		// TODO Auto-generated method stub

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
		System.out.println("SimpleLoader has started");
	}


	@Override
	public void stop() throws LifecycleException {
		System.out.println("SimpleLoader has stopped");
	}

}
