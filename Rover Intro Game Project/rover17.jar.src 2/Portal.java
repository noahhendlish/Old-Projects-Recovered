/*    */ import java.awt.Point;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Portal
/*    */   extends Item
/*    */ {
/*    */   private Portal otherEnd;
/*    */   private Room room;
/*    */   private Point location;
/*    */   private BufferedImage image;
/*    */   private BufferedImage flashImage;
/*    */   private boolean imageFlashing;
/*    */   
/*    */   public Portal(Room room, Point location)
/*    */   {
/*    */     try
/*    */     {
/* 25 */       this.image = ImageIO.read(new File("portal.jpg"));
/* 26 */       this.flashImage = ImageIO.read(new File("flashing.jpg"));
/*    */     } catch (IOException ie) {
/* 28 */       this.image = null;
/*    */     }
/* 30 */     this.otherEnd = null;
/* 31 */     this.room = room;
/* 32 */     this.location = location;
/* 33 */     this.imageFlashing = false;
/*    */   }
/*    */   
/*    */   public Portal(Room room, Point location, Portal connection) {
/* 37 */     this(room, location);
/* 38 */     this.otherEnd = connection;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setImageFlashing(boolean flash)
/*    */   {
/* 47 */     this.imageFlashing = flash;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Portal getOtherEnd()
/*    */   {
/* 56 */     return this.otherEnd;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public final void setOtherEnd(Portal otherEnd)
/*    */   {
/* 63 */     this.otherEnd = otherEnd;
/*    */   }
/*    */   
/*    */   public final void setLocation(Point location) {
/* 67 */     this.location = location;
/*    */   }
/*    */   
/*    */   public Room getRoom() {
/* 71 */     return this.room;
/*    */   }
/*    */   
/*    */   public Point getLocation() {
/* 75 */     return this.location;
/*    */   }
/*    */   
/*    */   public BufferedImage getImage() {
/* 79 */     if (this.imageFlashing)
/* 80 */       return this.flashImage;
/* 81 */     return this.image;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 85 */     return "portal";
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Portal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */