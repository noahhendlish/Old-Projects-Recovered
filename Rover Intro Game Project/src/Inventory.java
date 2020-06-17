import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author noahhendlish
 * this class is incomplete but will set up an inventory 
 * that tracks what items and how many items the rover picks up.
 */
public class Inventory {
	public int countCabbage;
	public int countGear;
	public int countScrew;
	public int countCake;
	
	String invPart;
	String invPrint;
	
	public ArrayList <String> inv = new ArrayList<String>();
	
	public Inventory (){
		/*for (int i = 0; i< inv.size(); i++){
			invPrint += inv.get(i) + " "  ;
		}
		*/
	}
	
	
	public int getCount(String part){
		if (part.equals("cabbage")){
			return countCabbage;
			}
		if (part.equals("screw")){
			return countScrew;
			}
		if (part.equals("gear")){
			return countGear;
			}
		if (part.equals("cake"));{
			return countCake;
			}
	}
	
	
	public void resetCount(){
		countCabbage = 0;
		countGear = 0;
		countScrew = 0;
		countCake = 0;
	}

	/*
	@Override
	public String toString(){
		
	}
	*/

	/**
	 * @param part to be added to inventory
	 */
	public void addtoInventory(String part) {
		invPart = part;
		inv.add(invPart);
		if (part.equals("cabbage"));{
			countCabbage++;
			}
		if (part.equals("screw"));{
			countScrew++;
			}
		if (part.equals("gear"));{
			countGear++;
			}
		if (part.equals("cake"));{
			countCake++;
			}
	}
	
	 
	
}
