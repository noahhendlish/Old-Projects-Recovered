package project2;

import java.io.*;
import java.util.*;


public class Graph {
	// This class is used to pass into the GraphCanvas. 
	// Variables stored in this class can be used for drawing on the screen.

	 public class Edge {		//Class to create edges
	        Vertex source;
	        Vertex dest;
	        Integer weight;
	        public Edge(Vertex s, Vertex d, Integer w) {
	            this.source = s;
	            this.dest = d;
	            this.weight = w;
	        }
	        public String toString(){
	        	return "Edge Source: " + source.ID + " Dest: " + dest.ID + " Weight: " + weight;
	        }
	    };
	    
	// Class variables
    protected int n; 							// Number of vertices in the graph
    protected ArrayList<Vertex> vertices;   	// ArrayList to store Vertices in Graph
    protected ArrayList<Integer>dValues;		//d-Values for bellman ford algo
    protected ArrayList<Vertex>  predArray;		//predecessor array stores vertices 
    protected ArrayList<Integer> predArrayID;	//extra predecessor array that only stores vertices' IDs for printing
    protected String algoName;					//specify which algorithm is running
    protected String Gfilename;					//stores filename (type of graph)
    protected ArrayList<Edge> edges;			//stores all edges in graph
    protected Vertex source;					//stores source vertex when running BF or Djikstra
	protected Integer negCycle;					//to report if negative weight cycle exists (for Bellman Ford)
	protected Integer negWeight;				//to report if negative edge weight exists (for Djikstra)
    protected ArrayList<Edge> treeEdges;		//stores shortest path/tree when running BF or Djikstra
    /*
     * TO DO:
     * Create class variables to store basic graph information:
     *   - All information about each of the vertices
     *   - The adjacency lists (= all information about the edges, including their weight)
     *   
     * Create class variables to store results computed by the graph algorithms:
     *   - The predecessor array
     *   - The priority queue
     *   - Any other information that you might want to draw. For example, a string
     *   specifying which of the two algorithms you have run for which you are showing
     *   its results.
     */
    
    
    public Graph(){
    	 vertices = new ArrayList<Vertex>(); 
    	 dValues= new ArrayList<Integer>();
    	 predArray = new ArrayList<Vertex>();
    	 Gfilename = null;
    	 edges = new ArrayList<Edge>();
    	 source= null;
    	 treeEdges= new ArrayList<Edge>();
    	 negCycle = 0;
    	 algoName = null;
    	 negWeight = 0;
    }
	Scanner scanner = new Scanner(System.in);
	public void readGraphFromFile(){
		scanner = new Scanner(System.in);		// Optional; asks for keyboard input.
		String filename;		
		System.out.println("Graph Selection Menu\nGraph #\t\tGraph Type\t\t\t\t\tGraph Filename");	//initlal menu selection
		System.out.println("  1\t\tNon-Negative Weight Directed Graph\t\tgraph.dat");
		System.out.println("  2\t\tNegative Weight Directed Graph\t\t\tgraphNeg.dat");
		System.out.println("  3\t\tNegative Cycle Directed Graph\t\t\tgraphNegCycle.dat");
		System.out.println("  4\t\tUndirected Graph\t\t\t\tgraphUndirected.dat");
		System.out.println("  5\t\tTest Graph 1\t\t\t\t\ttest1.dat");
		System.out.println("  6\t\tTest Graph 2\t\t\t\t\ttest2.dat");

		System.out.print("\nPlease enter name for graph file OR Correspending Graph Number: ");
		filename = scanner.nextLine();
		if("graph.dat".equals(filename)||"1".equals(filename)){
			Gfilename = "graph.dat";
		}
		else if("graphNeg.dat".equals(filename)||"2".equals(filename)) {
			Gfilename = "graphNeg.dat";
		}
		else if("graphNegCycle.dat".equals(filename)||"3".equals(filename)) {
			Gfilename = "graphNegCycle.dat";
		}
		else if("graphUndirected.dat".equals(filename)||"4".equals(filename)) {
			Gfilename = "graphUndirected.dat";
		}
		else if("test1.dat".equals(filename)||"5".equals(filename)) {
			Gfilename = "test1.dat";
		}
		else if("test2.dat".equals(filename)||"6".equals(filename)) {
			Gfilename = "test2.dat";
		}
		else{									//if not a valid graph, continue to ask
			System.out.println("\nInvalid Entry");	
			System.out.println("Try one of the following: ");
			readGraphFromFile();
		}
		readGraphFromFile(Gfilename);
	}
    
    public void readGraphFromFile(String filename){
    	  /*
         * Reads graph from file into the adjacency list structures.
         * 
         * Useful methods include: scanner.nextInt(), scanner.hasNext()
         * Note that exceptions will be caught below.
         * --------------------
         * 
         * Order of .dat files:
         * Number of vertices in graph
         * 
         * All rest lines:
         * 
         * vertex's ID, Xcoordinate, Ycoordinate
         * degree of vertex (length of adj. list)
         * adjacent vertex's ID, Adj. Vertex's weight
         * 
         */
    	scanner = new Scanner(System.in);
		
	    try{
	    	Gfilename = filename;				//set file name
	        scanner = new Scanner(new File(filename));		
	        //Graph G = new Graph();
	        vertices = new ArrayList<Vertex>();	//intialize array list of vertices
	        while(scanner.hasNext()){
		        n = scanner.nextInt();			//number of vertices in graph
		        //n = numVertices;
		        for (int j = 0; j < n; j++){	//for all vertices in graph
			        int ID = scanner.nextInt(); //reads ID, X Coordinate, Y Coordinate
			        int x = scanner.nextInt();
			        int y = scanner.nextInt();			        
			        Vertex newVertex = new Vertex(ID, x, y);	//creates new vertex
			        vertices.add(newVertex);					//add vertex to arraylist of vertices
			        int lenAdjVertices = scanner.nextInt();
			        for (int i = 0; i < lenAdjVertices; i++){	//store vertex's adjacent vertices in adjacency list
				        ArrayList<Integer> adjEdge = new ArrayList<>();	//temporary list of edges/adjacent vertices info
				        int AdjID = scanner.nextInt();		//get ID of adj vertex
				        int Adjweight = scanner.nextInt(); //get weight of adj vertex
				        adjEdge.add(AdjID);				//add ID (index 0)
				        adjEdge.add(Adjweight);			//add weight (index 1)
				        newVertex.adjacencyList.add(adjEdge);	//store the vertex's adjacent vertex
			        }
		        } 
	        }

	        for(int x = 0; x < n; x++){
	        	Vertex curr = vertices.get(x);
	        	for(int y = 0; y < curr.adjacencyList.size(); y++){			//for the size of the vertex's adjacency list
	        		Integer adjID = curr.adjacencyList.get(y).get(0);		//get the ID of the adjacent vertex (index zero)
	        		Vertex adj = vertices.get(adjID);						//get the corresponding vertex 
	        		Integer weight = curr.adjacencyList.get(y).get(1);		//get the weight of edge between vertex and adj. vertex (index 1)
	        		Edge e = new Edge(curr, adj,weight);					//create new edge 
	        		edges.add(e);											//add edge to array of edges that stores all edges in graph
	        	}
	        }        
	        scanner.close();
	    } catch(FileNotFoundException e){
	        System.err.println("Could not find file "+filename+". "+e);
	        System.exit(0);
	    } catch(IOException e){
	        System.err.println("Error reading integer from file "+filename+". "+e);
	        System.exit(0);
	    }
	  
	}
    
    public void bellmanFord(Integer sourceVertexID){
    	algoName = "Bellman-Ford";		//set algo name 
	    Edge treePath;					//initalize temporary edge to store edges in path (i.e for GUI)
		negCycle = 0;					//if neg cycle = 0, there is no negative cycle (already set to zero)
    	System.out.println("Running Bellman Ford on " + Gfilename + "\n");
    	source  = vertices.get(sourceVertexID);		//get source vertex
    							//initialize: all vertices already set to infinity, source d value = 0
   		for(int e = 0; e < vertices.size(); e ++){		//allocate space for predecessor array and dValues array and shortest path tree edges
   			Vertex v = new Vertex(-1,-1,-1);		//temporary vertex, to intialize predecessor array
   			predArray.add(v);						// add temp vertex (-1 values to predarray) for each vertex in graph
			dValues.add(null);						//add null values to dvalue array for each vertex in graph
			treePath = new Edge(source,source,0); 	//create path edge for displaying
			treeEdges.add(treePath);				//add to array of edges 
		}
    	source.dValue = 0;							//initialize: all vertices d values already set to infinity, set source d value = 0
    	dValues.set(source.ID,0);					//set d-value and d-values array (for printing in console) to zero.

    	for (int i =0; i < n; i++){    				//for all vertices in graph	
    		for(int j = 0; j< edges.size(); j++){	//for all edges in graph
    			Edge currE = edges.get(j);			//get an edge
    			Vertex u = currE.source;			//get edges current u (source/origin)
    			Vertex v = currE.dest;				//get edges current v (destination) 
    			Integer w = currE.weight;			// get edge weight
    			if(u.dValue != Integer.MAX_VALUE && v.dValue > u.dValue + w){	//relaxation: if origin vertex d value isnt infinity (ensuring source vertex value isnt changed b/c has d value set to zero already, & if adjacent vertex has a larger d value than current source vertex + the weight of the edge between them...
    				v.dValue = u.dValue + w; 	//change/update the adjacent/destination vertex's d-value to d-value of origin vertex + weight of edge between them
    				predArray.set(v.ID, u);		//set predecessor/origin vertex's position in pred array to index of its destination
    				dValues.set(v.ID,v.dValue);	//set the adjacent vertex, w/ updated d-values to have a correct updated d-value
    				treePath = new Edge(u,v,w);	//store correct edge in shortest path (from u-->v with weight w)
    				treeEdges.set(v.ID,treePath);		//add that edge to shortest path array (treeEdges), at the position of adj. vertex
    			}
    			
    		}
    		
    	}
    	for(int x =0; x < edges.size(); x++){	//check for negative weight cycle
    		Edge currEdge = edges.get(x);
    		Vertex s = currEdge.source;
    		Vertex d = currEdge.dest;
    		Integer w = currEdge.weight;
    		if (s.dValue!= Integer.MAX_VALUE && s.dValue+w < d.dValue){
    			 System.out.println("Graph contains negative weight cycle. \nnegative weight cycle caused by edge between vertex " + s.ID + " to vertex " + d.ID );
    			 System.out.println();
    			 negCycle = 1;
    		 }
    	}
    	
    	if(negCycle == 0){	//negative cycle doest NOT exist
    		System.out.println("Graph does not contain any negative weight cycles.\n");
    	}
    	
    	
    }
    
    public void dijkstra(Integer sourceVertexID){
    	algoName = "Dijkstra";
    	ArrayListHeap queue= new ArrayListHeap(n);
    	Edge treePath;
    	System.out.println("Running Djikstra on " + Gfilename + "\n");
    	source  = vertices.get(sourceVertexID);			//store source vertex
    	
    	for(int e = 0; e < vertices.size(); e ++){		//allocate space for predecessor array and dValues array and shortest path tree edges
   			Vertex v = new Vertex(-1,-1,-1);   //same process as for Bellman Ford
   			predArray.add(v);	
			dValues.add(null);
			treePath = new Edge(source,source,0); 		
			treeEdges.add(treePath);
		}
    	//INITALIZATION OVER... ALGORITHM:
    	dValues.set(source.ID,0);					//in slides psuedo code, s, set source's dvalue to zero ina array list of d-values
    	source.dValue = 0;							//initialize: all vertices already set to infinity, source d value = 0
    	for (int i =0; i < n; i++){
    		queue.insert(vertices.get(i));			//add all vertices to queue
    	}
    	while(!queue.isEmpty()){						//while the queue is empty
    		Vertex u = queue.extractMin();				//get the vertex with min d-value, relaxaiton...
	    	for (int i =0; i < u.adjacencyList.size(); i++){    	//for all vertices adjacent to vertex
	    		Integer adjVID = u.adjacencyList.get(i).get(0);		//get adjacent v's ID
	    		Integer weight = u.adjacencyList.get(i).get(1);		//get weight of edge between them
	    		Vertex adj = vertices.get(adjVID);					//get vertex from ID 
	    		Integer newdValue = u.dValue + weight;				//compute the d-value to be compared (current vertex d-value + weight)
    			//if(u.dValue!= Integer.MAX_VALUE && u.indexInHeap!=-1&& adj.dValue > newdValue ){	//relaxation:
	    		if(weight < 0){		//identifies if graph has edges with negative weights
	    			negWeight = 1;	//can report to user and suggest using bellman-ford
	    		}
        		if(adj.indexInHeap != -1 && adj.dValue > newdValue ){	// if adj vertex is in the queue and it has a d-value greater than the d-value of its source +weight of edge...
    				adj.dValue= newdValue;			//update its d-value
    				predArray.set(adj.ID, u);		//set correct position in pred array (SAME as falling into similar if statement in Bellman-Ford)
    				dValues.set(adj.ID,adj.dValue);		//set correct d-value in dvalue array (SAME as falling into similar if statement in Bellman-Ford)
    				treePath = new Edge(u,adj,weight);		//create edge (SAME as falling into similar if statement in Bellman-Ford)
    				treeEdges.set(adj.ID,treePath);			// add edge (SAME as falling into similar if statement in Bellman-Ford)
		    		queue.decreaseKey(adj.indexInHeap, newdValue);	//decrease/update the adjacent values key to the new value 
	    		}
	    	}

    	}
    	if(negWeight ==1){
    		System.out.println(Gfilename +" contains edges with Negative weights, Dijkstra cannot handle negatively weighted graphs. "
    				+ "\nTry using Bellman Ford on " + Gfilename + "\n");
    	}
    	else{
    		System.out.println("Graph does not contain any negatively weighted edges. All Edge weights positive. Dijkstra can find the shortest path.\n");
    	}

	}
    public void printdValues(){			//goes through all vertices and prints their d-values.
    	System.out.println("d-Values for " + algoName + " on " + Gfilename +"\n\t*(d-Value of 0 means vertex is source, d-value of null means vertex has infinity/max d-value. \n\t  If Vertex's d-value is null (e.g. it has infinity/max-d-value), it was not traversed since it is inaccessible from source)\n");
		System.out.print("Vertex:      ");
    	for(int i=0;i< dValues.size(); i++){
    		System.out.print("\t" + i);
    	}
    	
		System.out.print("\nd-Value: ");
		for(int i=0;i< dValues.size(); i++){
    		System.out.print("\t" + dValues.get(i));
    	}
		System.out.println();
    }

    public void printPredArray(){//goes through predecessor array for given shortest path algo and prints each vertex's predecessor
    	System.out.println("\nPredecessor Array for " + algoName + " on " + Gfilename +"\n\t*(Predecessor ID / Value of -1 means vertex has no predecessor, e.g., the source vertex has predecessor of -1. \n\t  Non-source vertex with -1 predecessor ID was not traversed since it is inaccessible from source)\n");
		System.out.print("Vertex:      ");
    	for(int i=0;i< predArray.size(); i++){
    		System.out.print("\t" + i);
    	}
		System.out.print("\nPredecessor: ");
		for(int i=0;i< predArray.size(); i++){
			if(predArray.get(i) != null){
    		System.out.print("\t" + predArray.get(i).ID);
			}
    	}
		System.out.println();
		}
    
    
    
}

	
    /*
     * TO DO:
     * Add code to implement Bellman-Ford's or Dijkstra's algorithms and 
     * fill result structures such as the predecessor array to then be drawn 
     * in GraphCanvas.
     */


//System.out.println();

/*
 * FROM BELLMAN FORD ALGO:
ArrayList<Vertex> currAdjVertices = new ArrayList<Vertex>();

for(int a = 0; a < currV.adjacencyList.size(); a ++){
	ArrayList<Integer> currAdjV = currV.adjacencyList.get(0) ;
*/

/*for(int j = 0; j< currV.adjacencyList.size();j++){
	//Vertex currAdj =
	Integer adjID = currV.adjacencyList.get(j).get(0);    			
	Integer adjEdgeWeight = currV.adjacencyList.get(j).get(1);
	Vertex adjV = vertices.get(adjID);
	if(currV.dValue != Integer.MAX_VALUE && adjV.dValue > currV.dValue + adjEdgeWeight){
		adjV.dValue = currV.dValue + adjEdgeWeight;
	}
	//curr.compareTo(adjVertex); 			//???

}

for(int v=0;v< treeEdges.size(); v++){
			Edge currs = edges.get(v);
			if(currs.source.equals(r)){
				System.out.println("\t" + currs.dest.ID);
				Vertex newSource = currs.dest;
				}
			for(int d =0; d < treeEdges.size(); d++){
				Edge currd = edges.get(d);
			//System.out.println(currE.source.ID);
				
				
				if(currd.dest.equals(newSource)){
					System.out.println("\t" + currE.dest.ID);

				}
				
				}
			}
*/
