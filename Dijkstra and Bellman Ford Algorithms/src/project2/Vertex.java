package project2;

import java.util.ArrayList;

public class Vertex {

	protected int ID;
	protected int dValue; 		// Integer.MAX_VALUE by default
	protected int indexInHeap; // -1 if not in heap
	//protected ArrayList<Vertex> adjacentVertices;
	protected ArrayList<ArrayList<Integer>> adjacencyList; //Each vertex stores adjacency list which is a list of ArrayLists
														   //Each arraylist of integers in the adjacnecy list array list
														   // stores the adjacent vertex's ID and the weight of the edge
	// For drawing
	protected int x;
	protected int y;
	
	public Vertex(int ID, int x, int y){
		this(ID, x, y, Integer.MAX_VALUE, -1);	//when first creating graph, set all vertices d-values to infinity (INTEGER.MAX_VALUE)
																		//and set all vertices indexInHeap to -1 (not in heap)
	}
	
	public Vertex(int ID, int x, int y, int dValue, int indexInHeap){
		this.adjacencyList	= new ArrayList<ArrayList<Integer>>();	//when vertex created, initializes a new empty adjacency list to store adjacent vertices info
		this.ID=ID;
		this.dValue=dValue;
		this.indexInHeap=indexInHeap;
		this.x=x;
		this.y=y;
	}
	
    public int compareTo(Vertex v){
    	// compare d-values for heap operations
    	if (dValue < v.dValue)
    		return -1;
    	if (dValue==v.dValue)
    		return 0;
    	return 1;
    }
    
    public String toString(){
    	return "ID: " + ID + " x: " + x + " y: " + y;
    	//return "ID: " + ID + " x: " + x + " y: " + y + " ("+indexInHeap+","+dValue+")";
    }
	
	
}
