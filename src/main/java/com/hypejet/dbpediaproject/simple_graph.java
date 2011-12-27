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


/*import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.sail.SailGraph;
import com.tinkerpop.blueprints.pgm.oupls.sail.GraphSail;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Element;
//import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.IndexableGraph;
import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.Vertex;*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// author rogueleaderr
 
public class simple_graph {

	/**
	 * @param args
	 */
	public simple_graph(){
		
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

		/*Map<String, String> config = new HashMap<String, String>();
		config.put( "NODE_KEYS_INDEXABLE", "values" );
		//config.put( Config.RELATIONSHIP_KEYS_INDEXABLE, "relProp1, relProp2" );
		config.put( "NODE_AUTO_INDEXING", "true" );
		config.put( "RELATIONSHIP_AUTO_INDEXING", "true" );
		*/
		
		final Neo4jGraph neo  = new Neo4jGraph("/Users/rogueleaderr/Data/var/dbpedia4neo_onto_back");
		final GraphSail gsail = new GraphSail(neo);
		final SailGraph sail  = new SailGraph(gsail);
		String query = "SELECT ?x WHERE {?x ?y ?z} LIMIT 10";
		
		List<Map<String, Vertex>> results = sparqlQuest(query, sail);
		
		System.out.println(results);
		
		/*EmbeddedGraphDatabase graphDb = new EmbeddedGraphDatabase( "/Users/rogueleaderr/Data/var/dbpedia4neo_onto_back", config );
		registerShutdownHook( graphDb );
		IndexManager index = graphDb.index();*/

		/*
		Transaction tx = graphDb.beginTx();
		try
		{
	//		Index<Node> autoNodeIndex = graphDb.index().getNodeAutoIndexer().getAutoIndex();
			// node1 and node2 both had auto indexed properties, get them
//			System.out.println(autoNodeIndex.get( "nodeProp1", "nodeProp1Value" ).getSingle() );
			tx.success();
		}
		finally
		{
		    tx.finish();
		}
		


		Index<Node> fullTextNodes = index.forNodes( "nodes"); //,     MapUtil.stringMap("provider", "lucene", "type", "fulltext" ) );
		
		System.out.println(fullTextNodes + "is balls");
		Node bergo = fullTextNodes.get("value", "U http://dbpedia.org/resource/University_of_Colorado_at_Boulder").getSingle();
		System.out.println(bergo);
*/		
		neo.shutdown();
		
		/*Node firstNode = graphDb.createNode();
		Node secondNode = graphDb.createNode();
		 
		Relationship relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
		 
		firstNode.setProperty( "message", "Hello, " );
		secondNode.setProperty( "message", "world!" );
		relationship.setProperty( "message", "brave Neo4j " );
		
		System.out.print( firstNode.getProperty( "message" ) );
		System.out.print( relationship.getProperty( "message" ) );
		System.out.print( secondNode.getProperty( "message" ) );
		*/		
		
		
		
		//System.out.println(nodeIndex);
		System.out.println("Hi jim!");
		
		//Node foundUser = nodeIndex.get("<http://www.dbpedia.org/resource/Steven_Spielberg>", "<http://www.dbpedia.org/resource/Steven_Spielberg>" ).getSingle();
		
		
		/*int i = 0;
		for (Node item: nodeIndex){
			System.out.println(item);
			i = i++;
			if (i>20) {
				break;
			}
		}
		
		*/
		
		


		
	//	final Neo4jGraph neo  = new Neo4jGraph("/Users/rogueleaderr/Data/var/dbpedia4neo_onto_back");
		

	}

	private static void registerShutdownHook( final GraphDatabaseService graphDb )
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
