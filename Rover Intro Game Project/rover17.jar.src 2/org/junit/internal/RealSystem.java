/*    */ package org.junit.internal;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ public class RealSystem
/*    */   implements JUnitSystem
/*    */ {
/*    */   @Deprecated
/*    */   public void exit(int code)
/*    */   {
/* 12 */     System.exit(code);
/*    */   }
/*    */   
/*    */   public PrintStream out() {
/* 16 */     return System.out;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/RealSystem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */