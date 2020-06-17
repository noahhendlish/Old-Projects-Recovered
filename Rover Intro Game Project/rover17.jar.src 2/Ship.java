/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Ship
/*    */   extends Item
/*    */ {
/*    */   private boolean state;
/*    */   public static final boolean BROKEN = false;
/*    */   public static final boolean WORKING = true;
/*    */   private BufferedImage imageBroken;
/*    */   private BufferedImage imageFixed;
/*    */   private String type;
/*    */   
/*    */   public Ship(boolean isWorking, ShipComp sc)
/*    */   {
/* 22 */     this.state = isWorking;
/* 23 */     this.type = sc.type;
/*    */     try {
/* 25 */       this.imageBroken = ImageIO.read(new File(sc.broken));
/* 26 */       this.imageFixed = ImageIO.read(new File(sc.working));
/*    */     }
/*    */     catch (IOException ie) {
/* 29 */       this.imageBroken = null;
/* 30 */       this.imageFixed = null;
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isBroken() {
/* 35 */     return !this.state;
/*    */   }
/*    */   
/*    */   public BufferedImage getImage() {
/* 39 */     if (isBroken()) {
/* 40 */       return this.imageBroken;
/*    */     }
/* 42 */     return this.imageFixed;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 46 */     return this.type;
/*    */   }
/*    */   
/*    */   public void becomeFixed() {
/* 50 */     this.state = true;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Ship.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */