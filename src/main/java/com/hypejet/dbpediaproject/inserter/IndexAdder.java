package com.hypejet.dbpediaproject.inserter;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.ReadableIndex;
//import org.neo4j.graphdb.index.Index;

import org.neo4j.kernel.Config;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class IndexAdder {
	
	private static final String DB_TO_LOAD = "/Users/rogueleaderr/Data/var/dbpedia4neo_small_test";
	

	
	public static void main( String[] args ) throws Exception{
		
		Map<String, String> config = new HashMap<String, String>();
		config.put( Config.NODE_KEYS_INDEXABLE, "name" );
		config.put( Config.NODE_AUTO_INDEXING, "true" );
		
		EmbeddedGraphDatabase graphDb = new EmbeddedGraphDatabase(
		        DB_TO_LOAD, config );
		registerShutdownHook( graphDb );
		
		ReadableIndex<Node> autoNodeIndex = graphDb.index().getNodeAutoIndexer().getAutoIndex();
		System.out.println("ABout to check the index " + autoNodeIndex.getEntityType());
		Iterator<Node> results = autoNodeIndex.query("name", "http://dbpedia.org/resource/Autism").iterator();
		 
		while ( results.hasNext() ){

			Node node = results.next();
			System.out.println("in the loop");
			System.out.println(node.getProperty("name") );
						
		}

	}
	
	private static void registerShutdownHook( final EmbeddedGraphDatabase graphDb )
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
