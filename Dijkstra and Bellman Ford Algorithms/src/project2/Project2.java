package project2;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Project2 {

	//Method for testing/comparing adjacency list with GUI, prints Adjacency List in console by printing:
	//each vertex (its ID, x and y coordinates)
	//the adjacent vertices stored in the vertex (each of their IDs, and x and y coordinates)
	//and the weight of the edge from the given vertex to an adjacent vertex
    public static void printAdjacencyList(Graph G){
    	System.out.println("\nGRAPH - " + G.Gfilename + " ADJACENCY LIST");
    	System.out.println("-----------------------------------------------------------------");

    	for(int v = 0; v< G.n; v++){
    		Vertex curr =G.vertices.get(v);
    		System.out.println("Vertex ID: " + curr.ID + "\t\t\tx: " +curr.x + "\ty: " + curr.y);
    		for(int av =0; av < curr.adjacencyList.size(); av++){
    		   ArrayList<Integer> adjV= curr.adjacencyList.get(av);
 			   int IDto = adjV.get(0);
 			   Vertex to = G.vertices.get(IDto);
 			   int weight = adjV.get(1);
 			   System.out.println("\tAdjecent Vertex ID: " + to.ID + "\tx: " +to.x + "\ty: " + to.y + "\tEdge Weight: " + weight);
    		}
        	System.out.println("-   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -    ");

    		
    		 //System.out.println("DONE");
        	 //System.out.print(G.vertices.get(v).adjacentVertices);
    	}
    	System.out.println("-----------------------------------------------------------------");

    }
    public static void printAllEdges(Graph G){
    	//System.out.println("\nGraph - " + G.Gfilename + " Edges: \n");
    	System.out.println("\nGRAPH - " + G.Gfilename + " EDGES: ");
    	System.out.println("----------------------------------------------------------------");
		//System.out.println();
		System.out.println("Source Vertex ID\tDestination Vertex ID\t\tWeight");
    	for(int e = 0; e< G.edges.size(); e++){
    		Vertex s = G.edges.get(e).source;
    		Vertex d =  G.edges.get(e).dest;
    		Integer weight = G.edges.get(e).weight;
    		
    		System.out.println("    \t" + s.ID + "         \t\t   " + d.ID + "    \t\t  " + weight );
    		//System.out.println("Destination ID: \t" + d.ID);
    		//System.out.println("Weight:         \t" + weight);
    		//System.out.println();
    		
    	}
    	System.out.println("----------------------------------------------------------------");
    	
    }

    public static void printMenu(){
    	System.out.println("\nGraph Action Menu\nType the corresponding Number to preform actions on the graph: ");
	    System.out.print("\t1. Select New Graph File\n\t2. Run Bellman-Ford's Shortest Path Algorithm on Graph"  
	    		+ "\n\t3. Run Dijkstra's Shortest Path Algorithm on Graph" + "\n\t4. Print Graph's info (adjacency list and edges) \n\t5. Quit\n");
	    System.out.print("Entry: ");
    }
    
	public static void main(String[] args){
		String entry;
	 	Scanner sc = new Scanner(System.in);
	    // Set up application frame
	    JFrame window = new JFrame("Project 2");
	    window.setBounds(50,50,800,800); // Dimensions of frame
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    System.out.println("Project 2: Single Source Shortest Paths \t\tCMPS 2200 Introduction to Algorithms – Fall 17");
	    System.out.println("\nWritten by Noah Hendlish\n");
	    Integer run = 1;
	    Graph G = new Graph();    
	    G.readGraphFromFile();
	    GraphCanvas canvas = new GraphCanvas(G);
   	    canvas.setBackground(new Color(245,241,222));
   	    window.getContentPane().add(canvas); // component added to content pane
   	    window.setVisible(true); 	
        printMenu();
	    entry = sc.nextLine();
	    while(run == 1){							//run while user doesnt want to quit
	   	    window.getContentPane().add(canvas); // component added to content pane
	   	    window.setVisible(true); 			 // displays the frame 
	   	    
			if("1".equals(entry)){
				G.source = null;
				G.dValues.clear();
				G.predArray.clear();
				G.treeEdges.clear();
			    G.readGraphFromFile();
			    
				window.getContentPane().add(canvas); // component added to content pane
			    window.setVisible(true); 	
			    printMenu();
			    entry = sc.nextLine();
			   
			}
			else if("2".equals(entry)){		//run bellman ford
	
				Integer srcID;
				String srcEntry;
				Integer validSource = 0;
				System.out.println("\nEnter the Source Vertex's ID from the graph to run Bellman-Ford's Algorithm\n");
			    System.out.print("Source ID: ");
				srcEntry = sc.nextLine();
				srcID = Integer.valueOf(srcEntry);
				for(int j =0; j < G.vertices.size(); j ++){
					if(srcID.equals(G.vertices.get(j).ID)){
						validSource += 1;
						}
					}
				while(validSource==0){	//run 
					System.out.println("\nInvalid Entry\nEnter the Source Vertex's ID from the graph to run Bellman-Ford's Algorithm\n");
				    System.out.print("Source ID: ");
					srcEntry = sc.nextLine();
					srcID = Integer.valueOf(srcEntry);
					for(int j =0; j < G.vertices.size(); j ++){
						if(srcID.equals(G.vertices.get(j).ID)){
							validSource += 1;
							}
						}
				}
				if(!G.predArray.isEmpty()){
		    		G.predArray.clear();
		    	}
		    	if(!G.dValues.isEmpty()){
		    		G.dValues.clear();
		    	}
				G.bellmanFord(srcID);
				
				window.getContentPane().add(canvas); // component added to content pane
				window.setVisible(true); 
				try {
					TimeUnit.SECONDS.sleep(1);		//break to allow user to read printed content
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				G.printdValues();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				G.printPredArray();
				G.dValues.clear();
				G.predArray.clear();
				G.negCycle = 0;
			 	try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				printMenu();
				entry = sc.nextLine();
		    }
			else if("3".equals(entry)){		//run dijkstra
				Integer srcID;
				String srcEntry;
				Integer validSource = 0;
				System.out.println("\nEnter the Source Vertex's ID from the graph to run Dijkstra's Algorithm\n");
			    System.out.print("Source ID: ");
				srcEntry = sc.nextLine();
				srcID = Integer.valueOf(srcEntry);
				for(int j =0; j < G.vertices.size(); j ++){
					if(srcID.equals(G.vertices.get(j).ID)){
						validSource += 1;
						}
					}
				while(validSource==0){
					System.out.println("\nInvalid Entry\nEnter the Source Vertex's ID from the graph to run Dijkstra's Algorithm\n");
				    System.out.print("Source ID: ");
					srcEntry = sc.nextLine();
					srcID = Integer.valueOf(srcEntry);
					for(int j =0; j < G.vertices.size(); j ++){
						if(srcID.equals(G.vertices.get(j).ID)){
							validSource += 1;
							}
						}
				}
				G.dijkstra(srcID);
				window.getContentPane().add(canvas); // component added to content pane
			    window.setVisible(true); 
			    try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				G.printdValues();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				G.printPredArray();
				G.dValues.clear();
				G.predArray.clear();
				G.negWeight = 0;
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printMenu();
			    entry = sc.nextLine();

		    }
			else if("4".equals(entry)){
				printAdjacencyList(G);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printAllEdges(G);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    printMenu();
			    entry = sc.nextLine();
		    }
			else if("5".equals(entry)){
		    	run = 0;
			    sc.close();
			    window.setVisible(false); //you can't see me!
			    window.dispose();
		    }
			else{
				System.out.println("Invalid Entry, Try Again");
				//printMenu();
				System.out.print("Entry: ");
			    entry = sc.nextLine();
	    
			}
			
	    }
	   

	    
	}
	
}

/*
 * TO DO:
 * Create Graph and pass into GraphCanvas
 * Run Bellman-Ford or Dijkstra.
 * You probably want to just run one of the algorithms at a time.
 */

//For  directed Positive weighted Graph:
//Graph directedPositive = new Graph();    
//directedPositive.readGraphFromFile("graph.dat");

/*
//For running on directed Negative weighted, negative cycle or undirected Graph testing:
 
//Graph directedNeg = new Graph();    
//directedNeg.readGraphFromFile("graphNeg.dat");
//GraphCanvas canvas = new GraphCanvas(directedNeg)

//Graph negCycle = new Graph();
//negCycle.readGraphFromFile("graphNegCycle.dat");
//GraphCanvas canvas = new GraphCanvas(negCycle)

//Graph undirected = new Graph();
//undirected.readGraphFromFile("graphUndirected.dat");
//GraphCanvas canvas = new GraphCanvas(undirected)
*/

