import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Portal extends Item{

	@Override
	public BufferedImage getImage() {
		try {
			return ImageIO.read(new File("portal.jpg"));
			}
		catch (IOException ie) {}
			return null;
			}	
	

	@Override
	public String toString() {
		return null;
	}


	
	public String getItemImg(String i) {
		// TODO Auto-generated method stub
		return null;
	}

}
