package com.hypejet.dbpediaproject;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.lang.StringEscapeUtils;

//import com.tinkerpop.blueprints.pgm.Vertex;
//import com.tinkerpop.blueprints.pgm.impls.sail.SailGraph;
import com.tinkerpop.blueprints.pgm.oupls.sail.GraphSail;

//import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Element;
import com.tinkerpop.blueprints.pgm.Index;
//import com.tinkerpop.blueprints.pgm.IndexableGraph;
//import com.tinkerpop.blueprints.pgm.TransactionalGraph;
//import com.tinkerpop.blueprints.pgm.Vertex;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;

@SuppressWarnings("serial")
public class IndexHandler extends HttpServlet {
    
	private GraphSail gsail;
    
    protected IndexHandler(GraphSail gsail) {
    	this.gsail = gsail;
    }
    
	// post will seek the passed in fields in the index, and try to return the URI

    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	PrintWriter writer = resp.getWriter();
//    	String query;
    	writer.println("checking that for you");
    	//Index<Vertex> vertex = new Class<Vertex>;
    	
    	//if ((query = req.getParameter("query")) != null)
    	   	
    		writer.print("here's a line 1");
	    	try {
//	    		double start = System.currentTimeMillis();
//	    		String search = "oo";
	    		writer.println("here's a line 2");
	    		Iterable<Index<? extends Element>> results = gsail.getGraph().getIndices();
	    		  
	        	writer.print("results are: " + results);
	        	writer.flush();
	
	    	} catch (RuntimeException e) {
	    		writer.write(e.getMessage());
	    		e.printStackTrace(writer);
    	}
 	
    }
    
} //class



