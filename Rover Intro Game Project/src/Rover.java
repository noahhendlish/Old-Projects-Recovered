import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.imageio.ImageIO;
 
/**
NOTE TO STUDENTS
This class implements IRover interface. 
You will see the stubs of some methods here that I included in my 
game. Essentially, this file gives you hints of some classes and 
methods I have in my implementation. You can choose to use them in
your implementation or not. If you feel unsure about the game, 
you can study my design and complete the missing pieces; 
this requires some effort upfront but is easier in the long run. 
If you feel adventurous, you can design from scratch, any design is 
good as long as it implements IRover. - Prof. Kurdia
@author noah hendlish and will crum
 */

public class Rover implements IRover {
	/** The current room the rover is in */
	private Room room;
	/** The rover's location in the room */
	private Point location;
	/** The rover's inventory **/
	//private LinkedList<Inventory> inventory;
	private Inventory inventory;
	/** A map of how to get back to the space ship. */
	private Stack<Portal> mapToBase;
	/** A list of tasks the rover must complete. */
	private Queue<Task> tasks;

	/**
	 * 
	 * Create the rover.
	 * 
	 * Creates the initial room. The rover starts in location [2,2]. The
	 * inventory is empty.
	 */
	public Rover() {
		room = new Room();
		location = new Point(2, 2);
		inventory = new Inventory();
		tasks = new ArrayDeque();
		mapToBase = new Stack();
		generateTasks();
	}

	private void generateTasks() {
		
	}
	
	/**
	 * Returns the current location of the rover as a coordinate: column (x) and
	 * row (y). Called by the GuiPanel so it can draw the rover.
	 * 
	 * @return the current location of the rover
	 */
	public Point getLocation() {
		return location;
	}
		
	/**
	 * Returns the current task. Called by the Gui so it can update the task
	 * display. Note the encapsulation: outside the rover, you never know how
	 * the tasks are implemented.
	 */
	public String getTask() {
		//return tasks.peek().toString(); 
		String g = "Task ";
		return g;
		}
		
	/**
	 * Returns the inventory. Called by the Gui so it can update the inventory
	 * display. Note the encapsulation: outside the rover, you never know how
	 * the inventory is implemented.
	 */
	public String getInventory() {
		return "";
		//inventory.inv.toString();
	}

	/**
	 * Pick up an item in the room at the rover's location.
	 * <P>
	 * Strategy: If we are standing on an item, pick it up. If not, do nothing.
	 * <P>
	 * Postconditions: the item is now in the inventory, the item is no longer
	 * in the room.
	 * <P>
	 * The GuiPanel now needs to be updated.
	 * <P>
	 * Called by the Gui.
	 */
	public void pickUp() {
		if ((room.isOccupied(getLocation()) == true)  && (room.isLiftable(getLocation()))==true) {
			Item i = room.removeItem(getLocation());
			inventory.addtoInventory(i.toString());
		}
	}

	/**
	 * The rover steps to the right.
	 * <P>
	 * Strategy: If possible, the rover takes a step to the right. If the robot
	 * lands on a portal, it will be teleported. If the outer wall is in the
	 * way, nothing happens.
	 * <P>
	 * The GuiPanel now needs to be updated.
	 * <P>
	 * Called by the Gui.
	 */
	public void goRight() {
		if (location.x < Room.SIZE - 1) {
			location.x++;
			checkForPortal();
		}
	}

	/**
	 * The rover steps to the left.
	 * <P>
	 * Strategy: If possible, the rover takes a step to the left. If the robot
	 * lands on a portal, it will be teleported. If the outer wall is in the
	 * way, nothing happens.
	 * <P>
	 * The GuiPanel now needs to be updated.
	 * <P>
	 * Called by the Gui.
	 */

	public void goLeft() {
		if (location.x > 0) {
			location.x--;
			checkForPortal();
		}
	}

	/**
	 * The rover steps downwards.
	 * <P>
	 * Strategy: If possible, the rover takes a step downwards. If the robot
	 * lands on a portal, it will be teleported. If the outer wall is in the
	 * way, nothing happens.
	 * <P>
	 * The GuiPanel now needs to be updated.
	 * <P>
	 * Called by the Gui.
	 */

	public void goDown() {
		if (location.y < Room.SIZE - 1) {
			location.y++;
			checkForPortal();
		}
	}

	/**
	 * The rover steps upwards.
	 * 
	 * Strategy: If possible, the rover takes a step to the right. If the robot
	 * lands on a portal, it will be teleported. If the outer wall is in the
	 * way, nothing happens.
	 * 
	 * The GuiPanel now needs to be updated.
	 * 
	 * Called by the Gui.
	 */
	public void goUp() {
		if (location.y > 0) {
			location.y--;
			checkForPortal();
		}
	}

	/**
	 * incomplete
	 * If the robot is standing on a portal, the whole room gets replaced!
	 */
	private void checkForPortal() {
		//if getLocation()
	}

	/**
	 * Called by GuiPanel paint()
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Pops the last portal we went through of the mapToBase stack and makes
	 * that portal flash.
	 */
	public void showTheWayBack() {
		
	}

	/** 
	 * implements IRover.getImage() method.
	 * creates new image for the rover.
	 * @return image given a file, otherwise null
	 */

	public Image getImage() {
		try {
			return ImageIO.read(new File("rover.jpg"));
		} 
		catch (IOException ie) {	
			}
		return null;
	}
	

	/**
	 * Perform the task in the task bar. If we are not standing on the relevant
	 * broken ship part or we do not have all the supplies, nothing happens. If
	 * we do happen to accomplish the task, that part of the ship gets fixed and
	 * the next task appears. If we run out of tasks, we win.
	 */
	public void performTask() {
		// if you are standing on a broken ship part for the task
		// and we have all the supplies, fix it, then get a new task.
		// if there are no more tasks, you win. 
	}



}

