import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * parts is a subclass of item. it holds all the 
 * parts that are inventoriable/ rover can pick up.
 * 
 * @author noah hendlish and will crum
 *
 */
public class Parts extends Item{
	public static final int COUNT = 3;
	public String partType;
	public BufferedImage partImg;
	

	/**
	 * 
	 * @param part name in string format
	 */
	public Parts(String part){
		partType = part;
		try {
		    partImg = ImageIO.read(new File(getItemImg(partType)));
		} 
		catch (IOException e) {	
			partImg = null;
		}	
	}
	
	/**
	 * @param part name
	 * @return name of part in image format
	 */
	public String getItemImg(String part) {
		partType = part;
		return partType + ".jpg";
	}

	@Override
	public BufferedImage getImage() {
		return partImg;
	}

	@Override
	public String toString() {
		return partType;
		}
	 	
}
