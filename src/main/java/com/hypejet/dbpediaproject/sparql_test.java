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
//import org.neo4j.graphdb.index.Index;

import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.kernel.Config;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import com.tinkerpop.blueprints.pgm.AutomaticIndex;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jVertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.sail.SailGraph;
import com.tinkerpop.blueprints.pgm.oupls.sail.GraphSail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// author rogueleaderr
 
public class sparql_test {
	
	private static List<Map<String,Vertex>> sparqlQuest (String query, SailGraph sail)
	{
		List<Map<String, Vertex>> results = sail.executeSparql(query);
		return results;
		
	}
	public static void main(String[] args) {
		
		final Neo4jGraph neo  = new Neo4jGraph("/Users/rogueleaderr/Data/var/dbpedia4neo_onto_new_all");
		registerShutdownHook( neo );
		final GraphSail gsail = new GraphSail(neo);
		final SailGraph sail  = new SailGraph(gsail);
		Index<Vertex> vIndex = 	neo.getIndex(Index.VERTICES, Vertex.class);
		
		Iterable<Vertex> results = vIndex.get("value", "http://dbpedia.org/resource/Autism");
		Iterator<Vertex> vIterator = results.iterator();
		while ( vIterator.hasNext() ){

			Vertex vertex = vIterator.next();
			System.out.println("in the loop");
			System.out.println(vertex.getProperty("value") );
			Iterator<Edge> outEdges = vertex.getOutEdges().iterator();
			while (outEdges.hasNext()){
				System.out.println(outEdges.next());
			}
			
		}
		
		String query = "SELECT DISTINCT ?x ?z WHERE {?x <http://dbpedia.org/ontology/knownFor> ?z}";
		
		List<Map<String, Vertex>> s_results = sparqlQuest(query, sail);
		int counter = 0;
		for (Map<String, Vertex> element : s_results){
			counter++;
			System.out.println("#"+counter+" sparql results = " + element);	
		}
		//System.out.println("Full results iterator = " + results);
		
		
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
