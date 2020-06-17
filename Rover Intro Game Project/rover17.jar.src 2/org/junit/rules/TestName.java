/*    */ package org.junit.rules;
/*    */ 
/*    */ import org.junit.runner.Description;
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
/*    */ public class TestName
/*    */   extends TestWatcher
/*    */ {
/*    */   private String name;
/*    */   
/*    */   protected void starting(Description d)
/*    */   {
/* 32 */     this.name = d.getMethodName();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getMethodName()
/*    */   {
/* 39 */     return this.name;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/TestName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */