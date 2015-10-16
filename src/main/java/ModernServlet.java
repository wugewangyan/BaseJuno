import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ModernServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("ModernServlet -- init");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Modern Servlet</title>");
	    out.println("</head>");
	    out.println("<body>");

	    out.println("<h2>Headers</h2>");
	    
	    Enumeration headers = req.getHeaderNames();
	    while(headers.hasMoreElements()){
	    	String header = (String)headers.nextElement();
	    	out.println("<br>" + header + " : " + req.getHeader(header) + "</br>");
	    }

	    out.println("<br><h2>Method</h2></br>");
	    out.println("<br>" + req.getMethod() + "</br>");
	    
	    out.println("<br><h2>Parameters</h2></br>");
	    Enumeration parameters = req.getParameterNames();
	    while(parameters.hasMoreElements()){
	    	String parameter = (String)parameters.nextElement();
	    	out.println("<br>" + parameter + " : " + req.getParameter(parameter) + "</br>");
	    }
	    
	    out.println("<br><h2>Query String</h2");
	    out.println("<br>" + req.getQueryString());

	    out.println("<br><h2>Request URI</h2");
	    out.println("<br>" + req.getRequestURI());

	    out.println("</body>");
	    out.println("</html>");
	    out.close();
	}
}
