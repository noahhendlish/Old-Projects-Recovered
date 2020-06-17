/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Part
/*    */   extends Item
/*    */ {
/*    */   public static final int COUNT = 3;
/*    */   BufferedImage image;
/*    */   String type;
/*    */   
/*    */   public Part(Parts part)
/*    */   {
/*    */     try
/*    */     {
/* 19 */       this.image = ImageIO.read(new File(part.img));
/*    */     } catch (IOException ie) {
/* 21 */       this.image = null;
/*    */     }
/* 23 */     this.type = part.type;
/*    */   }
/*    */   
/*    */   public BufferedImage getImage() {
/* 27 */     return this.image;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 31 */     return this.type;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Part.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */