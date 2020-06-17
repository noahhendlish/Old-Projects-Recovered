import java.awt.image.BufferedImage;
/**
 * @author noah hendlish and will crum
 * abstract class that creates methods to be passed to subclasses of item
 */
public abstract class Item {

	//public abstract Point location();

	/**
	 * creates abstract methods that will be implemented by the parts, ship, and portal classes
	 * creating images, and overriding toString() method  
	 */
	public abstract BufferedImage getImage(); 
	public abstract String toString();
	//public abstract String getItemImg(String i);
	/**
	 * overrides compareTo method so that
	 * @param item to be compared to
	 * @return integer depending on how similar the items are.
	 */
	public int compareTo(Item item) {
	return toString().compareTo(item.toString());
	}
 
	/**
	 * overrides the equals() method to take in an item
	 * 
	 * @param item to check against
	 * @return true or false depending on if they're the same or not
	 */
	public boolean equals(Item item) {
	return toString().equals(item.toString());
	}
	
} 