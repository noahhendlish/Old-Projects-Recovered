package project2;

import java.awt.*;
import java.util.ArrayList;

public class GraphCanvas extends Canvas {
	
	static final int diameter = 30;
	static final int arrowWidth = 6;	//changed to make display of d-values more clear
	static final int arrowLength =15;
	
	protected Graph G;
	
	public GraphCanvas(Graph G){
	  this.G=G;
	}
	  
	public void drawDValue(Graphics graphics, Vertex v){
	  /* Draws dValue of vertex in upper left corner */
	  graphics.setColor(Color.RED);
	  graphics.drawString(Integer.toString(v.dValue), v.x-diameter/2, v.y-diameter/2);	  
	}
	  
	public void drawVertex(Graphics graphics, Vertex v){
	  /* Draws vertexID in circle centered at (vertex.x,vertex.y) */
	  graphics.setColor(Color.BLACK);
	  graphics.drawOval(v.x-diameter/2, v.y-diameter/2, diameter, diameter);
	  graphics.drawString(Integer.toString(v.ID), v.x-diameter/4, v.y+diameter/4);
	}
	  
	public void drawEdge(Graphics graphics, Vertex v, Vertex u, int weight){
	  /* Draws a weighted DIRECTED edge v->u as an arrow from v to u . 
	   * Note that edges v->u and u->v will be side-to-side and not right on top of each other. 
	   */
	  double l = Math.sqrt((u.x-v.x)*(u.x-v.x)+(u.y-v.y)*(u.y-v.y));
	  graphics.setColor(new Color(110,60,240));
      // edge    	        
	  graphics.drawLine((int)(u.x+(l-diameter/2)*(v.x-u.x)/l-arrowWidth*(u.y-v.y)/l), 
	      (int)(u.y+(l-diameter/2)*(v.y-u.y)/l+arrowWidth*(u.x-v.x)/l),
	      (int)(v.x+(l-diameter/2)*(u.x-v.x)/l-arrowWidth*(u.y-v.y)/l), 
	      (int)(v.y+(l-diameter/2)*(u.y-v.y)/l+arrowWidth*(u.x-v.x)/l)); 

	  // arrow
	  Polygon arrowhead = new Polygon();
	  arrowhead.addPoint((int)(v.x+(l-diameter/2)*(u.x-v.x)/l-arrowWidth*(u.y-v.y)/l), 
	      (int)(v.y+(l-diameter/2)*(u.y-v.y)/l+arrowWidth*(u.x-v.x)/l)); 
	  arrowhead.addPoint((int)(v.x+(l-diameter/2-arrowLength)*(u.x-v.x)/l-2*arrowWidth*(u.y-v.y)/l), 
	      (int)(v.y+(l-diameter/2-arrowLength)*(u.y-v.y)/l+2*arrowWidth*(u.x-v.x)/l)); 
	  arrowhead.addPoint((int)(v.x+(l-diameter/2-arrowLength)*(u.x-v.x)/l+0*arrowWidth*(u.y-v.y)/l), 
	      (int)(v.y+(l-diameter/2-arrowLength)*(u.y-v.y)/l-0*arrowWidth*(u.x-v.x)/l)); 
	  graphics.fillPolygon(arrowhead);
	      
	  // weight
	  graphics.drawString(Integer.toString(weight), 
	       (int)(v.x+(l-diameter/2-2*arrowLength)*(u.x-v.x)/l-2*arrowWidth*(u.y-v.y)/l), 
	       (int)(v.y+(l-diameter/2-2*arrowLength)*(u.y-v.y)/l+2*arrowWidth*(u.x-v.x)/l));
		  
	}
	
	public void highlightEdge(Graphics graphics, Vertex v, Vertex u){
	  /* Highlights the line segment of the DIRECTED edge v->u.  
	   * Note that edges v->u and u->v will be side-to-side and not right on top of each other.
	   */
	  double l = Math.sqrt((u.x-v.x)*(u.x-v.x)+(u.y-v.y)*(u.y-v.y));
	  graphics.setColor(new Color(240,20,110));
	  // edge    	        
	  graphics.drawLine((int)(u.x+(l-diameter/2)*(v.x-u.x)/l-arrowWidth*(u.y-v.y)/l), 
	       (int)(u.y+(l-diameter/2)*(v.y-u.y)/l+arrowWidth*(u.x-v.x)/l),
	       (int)(v.x+(l-diameter/2)*(u.x-v.x)/l-arrowWidth*(u.y-v.y)/l), 
	       (int)(v.y+(l-diameter/2)*(u.y-v.y)/l+arrowWidth*(u.x-v.x)/l)); 
	  }
	  
	public void highlightVertex(Graphics graphics, Vertex v){
	  /* Highlights circle around the vertex v. 
	   */
	  graphics.setColor(Color.GREEN);
	  graphics.drawOval(v.x-diameter*5/12, v.y-diameter*5/12, diameter*10/12, diameter*10/12);
	}
	public void drawHeader(Graphics graphics, String s){
		  /* Draws header with project info in small font on top of window.
		   */
			Font oldFont = graphics.getFont();
			graphics.setFont(oldFont.deriveFont((float)12));
		    graphics.setColor(new Color(30,100,100));
		    graphics.drawString(s, 30, 30);
		    graphics.setFont(oldFont);
		  }

	  public void drawTitle(Graphics graphics, String s){
	  /* Draws title in large font on screen.
	   * Could use this method to draw algorithms type on screen (e.g., "Dijkstra") 	  
	   */

		Font oldFont = graphics.getFont();
		graphics.setFont(oldFont.deriveFont((float)30));
	    graphics.setColor(new Color(240,20,110));
	    graphics.drawString("Graph File: " + s, 200, 500);
	    graphics.setFont(oldFont);
	  }

	  public void drawnegWeightCycleError(Graphics graphics){
	  /* Draws negative weight cycle error if neg cycle exists in large font on screen.
	   */

		Font oldFont = graphics.getFont();
		graphics.setFont(oldFont.deriveFont((float)15));
	    graphics.setColor(new Color(90,80,90));
	    graphics.drawString("NEGATIVE WEIGHT CYCLE IN GRAPH, CANNOT RUN PROPERLY", 200, 430);
	    graphics.setFont(oldFont);
	  }
	  public void drawnegWeightEdgeError(Graphics graphics){
		  /* Draws negative weight cycle error if neg cycle exists in large font on screen.
		   */
			Font oldFont = graphics.getFont();
			graphics.setFont(oldFont.deriveFont((float)15));
		    graphics.setColor(new Color(90,80,90));
		    graphics.drawString("NEGATIVELY WEIGHTED EDGE IN GRAPH, CANNOT RUN PROPERLY", 200, 430);
		    graphics.setFont(oldFont);
		  }
	  public void drawAlgoName(Graphics graphics, String s){
		  /* Draws algo type in large font on screen.
		   */
			Font oldFont = graphics.getFont();
			graphics.setFont(oldFont.deriveFont((float)30));
		    graphics.setColor(new Color(160,80,200));
		    graphics.drawString("Alogrithm: " + s, 200, 580);
		    graphics.setFont(oldFont);
		  }
		  
	public void paint(Graphics graphics){
	  // Don't confuse "Graphics" (which is used to draw on) with "Graph" (which stores your graph)
      // This method has to contain all the drawing/painting code
		
      /*
	   * TO DO:
	   * All the paint code needs to go here. Need to draw various structures stored in G:
	   *   - Draw vertices and edges of G, once read from file.
	   *   - Draw results from the algorithms (shortest path tree, dValues), once computed.
	   *     (Only attempt to draw non-null structures...)
	   */
	   
	   // Create and draw vertices:
		
		if (graphics != null){
			for (int i =0; i < G.n; i++){			//for the number of vertices in the graph, same as: for (int i =0; i < G.vertices.size(); i++){
			   Vertex newV = G.vertices.get(i);		//temporary /currently iterated vertex stored
			   drawVertex(graphics, newV);			//paint the vertex
			   if(newV.dValue != Integer.MAX_VALUE){
				   drawDValue(graphics, newV);
				   newV.dValue=Integer.MAX_VALUE;
			   }
			   for(int j = 0; j < newV.adjacencyList.size();j++){		//paint edges by iterating adjacency list of each vertex
				   ArrayList<Integer> e = newV.adjacencyList.get(j);	//temporary arraylist that stores each adjacent vertex's ID and weight of edge between vertices
				   int IDto = e.get(0);									//get the first/ "zero-th" element of the array which is the adjacent vertex's ID
				   Vertex to = G.vertices.get(IDto);					//get the correspoding vertex (based on ID/position in array of vertices)			   
				   int weight = e.get(1);								//temporarly store the weight of the edge
				   drawEdge(graphics, newV, to, weight);				//draw the edge from the vertex to its adjacent vertex with the given weight
				   
			   }
			}
			drawHeader(graphics, "Project 2: Single Source Shortest Path        Written by Noah Hendlish        CMPS 2200 Introduction to Algorithms – Fall 17");
			drawTitle(graphics, G.Gfilename);
			if(G.algoName != null){					//only draw algoname on GUI if running algorithm
				drawAlgoName(graphics, G.algoName);
				//G.algoName=null;
			}
			if(G.source != null){		//only highlight source vertex if given a source vertex (source exists)
				   highlightVertex(graphics, G.source);  
				   G.source = null;
			}
			if(G.treeEdges.size()>0){		//if there is a path...
				for(int i =0; i < G.treeEdges.size();i++){
					highlightEdge(graphics, G.treeEdges.get(i).source, G.treeEdges.get(i).dest);	//draw the path
				}
				G.treeEdges.clear();		//clear for next operation
			}
			if(G.negCycle !=0){
				drawnegWeightCycleError(graphics);	//error for bellman-ford. detects negative cycle, print on GUI
				G.negCycle = 0;			
			}
			if(G.negWeight!= 0){		//error for dijkstra when using negative weight graphs, prints on GUI
				drawnegWeightEdgeError(graphics);
				G.negWeight	=0;
			}
		}
		else{
			System.out.print("No Graphics/Null Graphic structure");	
		}
	   /*
	   // Example code below:
	   Vertex u = new Vertex(0, 300, 300);
	   Vertex v = new Vertex(1, 400, 200);
	   Vertex w = new Vertex(2, 400, 400);
	   drawVertex(graphics, u);
	   drawVertex(graphics, v);
	   drawVertex(graphics, w);
		 
	   // Draw edges between two vertices. The direction is important to distinguish.
	   drawEdge(graphics, u, v, 5);
	   drawEdge(graphics, v, w, 10);
	   drawEdge(graphics, u, w, 20);
		 
	   // Highlight an edge. The direction is important to distinguish.
	   highlightEdge(graphics, u, v);
	   
	   // Highlight a vertex.
	   highlightVertex(graphics, u);     
	   
	   // Draw title string in large font on screen.
	   drawTitle(graphics, "Example vertices and edges.");
	   */
	 }

}
