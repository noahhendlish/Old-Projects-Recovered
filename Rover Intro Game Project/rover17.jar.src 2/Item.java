/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Item
/*    */ {
/*    */   public abstract BufferedImage getImage();
/*    */   
/*    */   public abstract String toString();
/*    */   
/*    */   public int compareTo(Item i)
/*    */   {
/* 16 */     return toString().compareTo(i.toString());
/*    */   }
/*    */   
/*    */   public boolean equals(Item i) {
/* 20 */     return toString().equals(i.toString());
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */