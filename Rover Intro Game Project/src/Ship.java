import java.awt.image.BufferedImage;

/**
 * @author noahhendlish
 * ship is a subclass of parts class
 * 
 */
public class Ship extends Parts{
	public boolean isBroken;
	public String part;
	/**
	 * @param shipPart name in string that is sent to parts class
	 */
	public Ship(String shipPart){
		super(shipPart);
		part = shipPart;
	}
	
	/**
	 * called when ship is fixed, set isBroken to false and 
	 * changes the image name of the part by adding 2 
	 * @return new ship
	 */
	public Ship fixed (){
		isBroken = false;
		part = part + "2";
		Ship ship = new Ship(part);
		return ship;
		
	}

}
	

	
	

