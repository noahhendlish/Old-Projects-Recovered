/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Gui$FixedHeightTextField
/*    */   extends JTextField
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public Gui$FixedHeightTextField(Gui paramGui, String s, int chars)
/*    */   {
/* 76 */     super(s, chars);
/*    */   }
/*    */   
/*    */   public Dimension getMinimumSize() {
/* 80 */     return getPreferredSize();
/*    */   }
/*    */   
/*    */   public Dimension getMaximumSize() {
/* 84 */     return getPreferredSize();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Gui$FixedHeightTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */