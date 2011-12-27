package com.hypejet.dbpediaproject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.kernel.Config;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.sail.SailGraph;
import com.tinkerpop.blueprints.pgm.oupls.sail.GraphSail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// author rogueleaderr
 
public class sparql_test {

	static String beans;
	/**
	 * @param args
	 */
	public sparql_test(){
		this.beans = "beans!";
	}
	
	private static enum RelTypes implements RelationshipType
	{
	    KNOWS
	}
	
	private static List<Map<String,Vertex>> sparqlQuest (String query, SailGraph sail)
	{
		List<Map<String, Vertex>> results = sail.executeSparql(query);
		return results;
		
	}
	public static void main(String[] args) {
		
		final Neo4jGraph neo  = new Neo4jGraph("/Users/rogueleaderr/Data/var/dbpedia4neo_small_test");
		registerShutdownHook( neo );
		final GraphSail gsail = new GraphSail(neo);
		final SailGraph sail  = new SailGraph(gsail);
		String query = "SELECT ?x WHERE {?x ?y ?z} LIMIT 10";
		
		List<Map<String, Vertex>> results = sparqlQuest(query, sail);
		
		System.out.println(results);
		
		neo.shutdown();
		
		System.out.println("Hi jim!");
		
	}

	private static void registerShutdownHook( final Neo4jGraph graphDb )
	{
	    // Registers a shutdown hook for the Neo4j instance so that it
	    // shuts down nicely when the VM exits (even if you "Ctrl-C" the
	    // running example before it's completed)
	    Runtime.getRuntime().addShutdownHook( new Thread()
	    {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	        }
	    } );
	}


}
