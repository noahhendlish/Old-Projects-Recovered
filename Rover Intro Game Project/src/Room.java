import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
NOTE TO STUDENTS
This class implements IRoom interface. You will see the stubs of some methods here that I included in my game.  
Essentially, this file gives you hints of some classes and methods I have in my implementation. 
You can choose to use them in your implementation or not. 
If you feel unsure about the game, you can study my design and complete the missing pieces; 
this requires some effort upfront but is easier in the long run. 
If you feel adventurous, you can design from scratch. - Prof. Kurdia
@author noah hendlish and will crum
 */

/**
 * A 15 x 15 grid full of portals (2 to 4), items (screws, gears, etc), the
 * rover, and also the ship if it is the base room.
 * 
 */

public class Room implements IRoom {
	/** Percentage of the room that should be full of items */
	private static final double DENSITY = .05;
	/** Rooms are 15x15 */
	public static final int SIZE = 16;
	/** The grid for the room */
	private Item[][] grid;
	
	public String cabbage = "cabbage";
	public String gear = "gear";
	public String cake = "cake";
	public String screw = "screw";
	
	/**
	 * Create the starting room that is 15x15.
	 * Put 4 portals on the grid and the rover.
	 * Adds the broken ship parts to the center of the grid.
	 * and some random items at random locations.
	 */

	public Room() { 
		ArrayList<String> items = new ArrayList<String>();
		//LinkedList<String> inventoryList = new LinkedList<>();

		items.add(cabbage);
		items.add(gear);
		items.add(cake);
		items.add(screw);

		grid = new Item[SIZE][SIZE];
		
		grid[3][1] = new Portal();
		grid[4][4] = new Portal();
		grid[11][10] = new Portal();
		grid[15][14] = new Portal();
		
		int gea = (int)Math.round(Math.random() * 5);
		int scr = (int)Math.round(Math.random() * 5);
		int cab = (int)Math.round(Math.random() * 5);
		int cak = (int)Math.round(Math.random() * 5);
		//Adds Random amount of each Inventoriable Part 
		for (int i = 0; i < gea; i++){
		addItem("gear");
		}
		for (int i = 0; i < cab; i++){
		addItem("cabbage");
		}
		for (int i = 0; i < scr; i++){
		addItem("screw");
		}
		for (int i = 0; i < cak; i++){
		addItem("cake");
		}
		this.grid[8][8] = new Ship("cabin");
		this.grid[7][8] = new Ship("ramp");
		this.grid[8][7] = new Ship("engine");
		this.grid[8][9] = new Ship("wheel");
		this.grid[9][8] = new Ship("exhaust");

		
	}
	/**
	 * Create a new room with a connection to the given parent room at the given
	 * portal coordinates for the parent room
	 * 
	 * @param connection is the connecting portal in the parent room
	 */
	public Room(Portal connection) {
		
	}
	
	/**
	 * Add a random item to an unused location on the grid. Calls
	 * getNewLocation().
	 * 
	 */

	private void addItem(String type) { 
		//int x = (int)Math.round(Math.random() * 4);
		//for (int i = 0; i < x; i++){
		Point pt = getNewLocation();
		grid[pt.x][pt.y] = new Parts(type);
		}

	/**
	 * Returns a random unused location on the grid
	 * 
	 * Creates two random points between 0 and 15 for the x and y coordinates.
	 * checks if the point (x,y) is occupied. if it is, it loops, 
	 * randomly generating new points, until it gets an unoccupied space
	 * @return random unoccupied point
	 */
	private Point getNewLocation() {
		int x;
		int y;

		x = (int)Math.round(Math.random() * 15);
		y = (int)Math.round(Math.random() * 15);
		if (isOccupied(x, y) == true){
			while (isOccupied(x, y) == true){
			x = (int)Math.round(Math.random() * 15);
			y = (int)Math.round(Math.random() * 15);
			return new Point(x, y);
			}
		}
		return new Point(x,y);
	}
	
	/**
	 * checks if point on grid is occupied
	 * @param location to check if grid point is occupied
	 * @return boolean value of false if grid point is null and otherwise returns true
	 */
	public boolean isOccupied(Point location) {
		if(grid[location.x] [location.y] == null) {
			return false;
		}
		return true;
		}

	/**
	 * Returns true if the given space is occupied, false if it is not.
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return boolean value of true if grid point is occupied and false if null
	 */
	public boolean isOccupied(int x, int y) {
		if( grid[x][y] != null){
			return true;
			}
		return false;
		}
		

	/**
	 * method to remove item from the grid
	 * @param location of item to be removed
	 * @return the removed item
	 */
	public Item removeItem(Point location) {
		if (isOccupied(location) == true){
			Item removedItem = grid[location.x][location.y];
			grid[location.x][location.y] = null;
		
			return removedItem;
			}

		return null;
		}
	
	 
	/**
	 * Checks if an Item is liftable: 
	 * if Item is a inventoriable supply part
	 * then it is Liftable.
	 * 
	 * @param location of an Item
	 * @return boolean value of true if it is liftable and false if not
	 */
	public boolean isLiftable(Point location) {
		if (getItem(location) instanceof Parts) {
			return true;
			}
		else{
			return false;
			}
		}

	/**
	 * checks if the item at the given location is a ship
	 * @param location of item
	 * @return boolean value of true or false
	 */
	public boolean isShip(Point location) {
		if (getItem(location) instanceof Ship){
			return true;
			}
		return false;
		}

	/**
	 * Incomplete Method, similar to other isPortal method but takes in x and y coordinates
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return boolean value of true or false
	 */
	public boolean isPortal(int x, int y) {
		 return isPortal(new Point(x, y));		
		}

	/**
	 * Incomplete Method, but after portal class is complete 
	 * this can be implemented the same as isShip method
	 * 
	 * @param location of item is taken in and checks if it is an instance of the portal class
	 * @return boolean value of true or false
	 */
	public boolean isPortal(Point location) {
		if (isOccupied(location)){
			if (getItem(location) instanceof Portal){
				return true;
				}
			}
			return false;	
		}
		
	
	/**
	 * still incomplete but,
	 * checks if there is an item at the location using isOccupied method, 
	 * if is location is occupied, it returns the item at that location
	 * @param location to check for item
	 * @return item at the location if it exists
	 */
	public Item getItem(Point location) {
		if (isOccupied(location) == true){
			return grid[location.x][location.y];
		}
		return null;
	}

 
	/**
	 * Return the image of the item in the grid at this location. Assumes that
	 * the location is occupied. If the location is not occupied, this will
	 * throw a null pointer exception. If something is wrong with the image, it
	 * may be null.
	 */
	public Image getImage(int i, int j) {
		Item A = getItem(i, j);
		Image Z = A.getImage(); 
		return Z;
		}

	/**
	 * Similar to other isShip method but uses x, y coordinates instead of a point location
	 * @param width coordinate
	 * @param height coordinate
	 * @return boolean true if it is a ship, otherwise false by 
	 * creating a new point and calling other isShip method with point location parameter
	 * 
	 */
	public boolean isShip(int width, int height) {
		 return isShip(new Point(width, height));
	}
	
	/**
	 * @param width coordinate
	 * @param height coordinate
	 * @return calls the other getItem method with a point
	 */
	public Item getItem(int width, int height) {
		return getItem(new Point(width, height));
		}

 	/**
 	 * Incomplete: havent started task class yet
 	 * @param width coordinate
 	 * @param height coordinate
 	 * @return a task
 	 */
 	public Task generateTask(int width, int height) {
 		Task task = new Task();
		return task;
				}

	/**
	 * incomplete: tells portals to stop flashing?
	 */
	public void stopFlashingPortals() {} 

}
