import java.awt.Image;
import java.awt.Point;

public abstract interface IRover
{
  public abstract void pickUp();
  
  public abstract void showTheWayBack();
  
  public abstract void performTask();
  
  public abstract void goLeft();
  
  public abstract void goDown();
  
  public abstract void goRight();
  
  public abstract void goUp();
  
  public abstract String getInventory();
  
  public abstract String getTask();
  
  public abstract IRoom getRoom();
  
  public abstract Point getLocation();
  
  public abstract Image getImage();
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/IRover.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */