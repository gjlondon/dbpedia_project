package com.hypejet.dbpediaproject.inserter;

import java.io.File;
import com.tinkerpop.blueprints.pgm.impls.neo4jbatch.Neo4jBatchGraph;


public class file_looper {

	public static void main(String[] args) {
		String myDirectoryPath = "/Users/rogueleaderr/Data/instanc_dir";  
		File dir = new File(myDirectoryPath);
		  for (File child : dir.listFiles()) {
		    if (".".equals(child.getName()) || "..".equals(child.getName())) {
		      continue;  // Ignore the self and parent aliases.
		    }
		    System.out.println(child);
		  }
		System.out.println(dir.listFiles());

	}

}
