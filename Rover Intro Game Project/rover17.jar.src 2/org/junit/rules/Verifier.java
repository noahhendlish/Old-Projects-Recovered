/*    */ package org.junit.rules;
/*    */ 
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runners.model.Statement;
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
/*    */ public abstract class Verifier
/*    */   implements TestRule
/*    */ {
/*    */   public Statement apply(final Statement base, Description description)
/*    */   {
/* 32 */     new Statement()
/*    */     {
/*    */       public void evaluate() throws Throwable {
/* 35 */         base.evaluate();
/* 36 */         Verifier.this.verify();
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */   protected void verify()
/*    */     throws Throwable
/*    */   {}
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/Verifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */