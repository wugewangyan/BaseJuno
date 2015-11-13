package com.juno.tomcat2.startup;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Mapper;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.logger.FileLogger;

import com.juno.tomcat2.core.SimpleContextLifecycleListener;
import com.juno.tomcat2.core.SimpleContextMapper;
import com.juno.tomcat2.core.SimpleWrapper;
import com.juno.tomcat2.values.ClientIPLoggerValve;
import com.juno.tomcat2.values.HeaderLoggerValve;

public final class Bootstrap3 {
  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector();
    Wrapper wrapper1 = new SimpleWrapper();
    wrapper1.setName("Primitive");
    wrapper1.setServletClass("PrimitiveServlet");
    Wrapper wrapper2 = new SimpleWrapper();
    wrapper2.setName("Modern");
    wrapper2.setServletClass("ModernServlet");

    Context context = new StandardContext();
    context.setPath("/myApp");
    context.setDocBase("/myApp");
    context.addChild(wrapper1);
    context.addChild(wrapper2);

    Valve valve1 = new HeaderLoggerValve();
    Valve valve2 = new ClientIPLoggerValve();

    ((Pipeline) context).addValve(valve1);
    ((Pipeline) context).addValve(valve2);

    Mapper mapper = new SimpleContextMapper();
    mapper.setProtocol("http");
    context.addMapper(mapper);
    
    LifecycleListener listener = new SimpleContextLifecycleListener();
    ((Lifecycle)context).addLifecycleListener(listener);
    
    Loader loader = new WebappLoader();
    context.setLoader(loader);
    // context.addServletMapping(pattern, name);
    context.addServletMapping("/Primitive", "Primitive");
    context.addServletMapping("/Modern", "Modern");
    
    // add logger
    System.out.println("user.dir : " + System.getProperty("user.dir"));
    System.out.println("catalina.base : " + System.getProperty("catalina.base"));
    System.setProperty("catalina.base", System.getProperty("user.dir"));
    FileLogger logger = new FileLogger();
    logger.setPrefix("FileLog_");
    logger.setSuffix(".txt");
    logger.setTimestamp(true);
    logger.setDirectory("/Users/wuge/Documents/localRepository/juno/log");
    context.setLogger(logger);
    
    connector.setContainer(context);
    try {
      connector.initialize();
      ((Lifecycle)connector).start();
      ((Lifecycle)context).start();

   // now we want to know some details about WebappLoader
      WebappClassLoader classLoader = (WebappClassLoader) loader.getClassLoader();
      String[] repositories = classLoader.findRepositories();
      for (int i=0; i<repositories.length; i++) {
        System.out.println("  repository: " + repositories[i]);
      }
      
      // make the application wait until we press a key.
      System.in.read();
      logger.log("hahaha");
      
      ((Lifecycle)context).stop();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}