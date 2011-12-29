package com.hypejet.dbpediaproject.inserter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.openrdf.model.ValueFactory;
import org.openrdf.rio.ParseErrorListener;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.ntriples.NTriplesParser;
import org.openrdf.sail.Sail;
import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.repository.sail.SailRepositoryConnection;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.neo4jbatch.Neo4jBatchGraph;
import com.tinkerpop.blueprints.pgm.oupls.sail.GraphSail;

import com.tinkerpop.blueprints.pgm.impls.neo4jbatch.Neo4jBatchVertex;
import com.tinkerpop.blueprints.pgm.impls.neo4jbatch.Neo4jBatchEdge;


public class DBpediaLoaderAll 
{
    private static final String LOAD_FROM_DIR = "/Users/rogueleaderr/Data/smallo_dir";
    private static final String DB_DIR = "/Users/rogueleaderr/Data/var/dbpedia4neo_onto_new";
    private static boolean indices_exist;
    
	public static void main( String[] args ) throws Exception
    	
    {

		Neo4jBatchGraph neo = new Neo4jBatchGraph(DB_DIR);
		registerShutdownHook( neo );
	//	neo.createAutomaticIndex(Index.VERTICES, Vertex.class, null);
    //	neo.createAutomaticIndex(Index.EDGES, Edge.class, null);
    	Sail sail = new GraphSail(neo);
    	sail.initialize();	
	    
	    File dir = new File(LOAD_FROM_DIR);
    	String[] fileList = dir.list();
  	
    	try{
	    	if (dir.isDirectory()){
	    		for (String child : fileList) {
	    			if (".".equals(child) || "..".equals(child) || ".DS_Store".equals(child)) {
	    			      continue;  // Ignore the self and parent aliases.
	    			    }
	    			
	    			System.out.println("Loading " + child + ": ");
	    			File childFile = new File(LOAD_FROM_DIR + "/" + child);
	    			addFileToDB(childFile, neo, sail);  
	    			break;
	        	}	
	    	
	    	}
    	}
    	catch ( Exception e1 )
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		sail.shutDown();
		neo.shutdown();
    }
	
	private static void addFileToDB(File childFile, Neo4jBatchGraph neo, Sail sail) throws RepositoryException, SailException{
		
		//Neo4jBatchGraph neo = new Neo4jBatchGraph(DB_DIR);
		//registerShutdownHook( neo );
		
       
    	SailRepositoryConnection connection;
   		
   		
		long start = System.currentTimeMillis();
		connection = new SailRepository( sail ).getConnection();		

		try{
				connection.add( childFile, null, RDFFormat.NTRIPLES);
		}
		catch ( Exception e1)
        {
            e1.printStackTrace();
        }

		long duration = System.currentTimeMillis() - start;
		System.out.println("Duration of import: " + duration);
		connection.close();
    	

	//	neo.shutdown();	
			
	}
	private static void registerShutdownHook( final Neo4jBatchGraph graphDb )
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


