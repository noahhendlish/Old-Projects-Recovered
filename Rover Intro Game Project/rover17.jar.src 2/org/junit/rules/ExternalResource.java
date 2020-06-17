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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ExternalResource
/*    */   implements TestRule
/*    */ {
/*    */   public Statement apply(Statement base, Description description)
/*    */   {
/* 39 */     return statement(base);
/*    */   }
/*    */   
/*    */   private Statement statement(final Statement base) {
/* 43 */     new Statement()
/*    */     {
/*    */       public void evaluate() throws Throwable {
/* 46 */         ExternalResource.this.before();
/*    */         try {
/* 48 */           base.evaluate();
/*    */         } finally {
/* 50 */           ExternalResource.this.after();
/*    */         }
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */   protected void before()
/*    */     throws Throwable
/*    */   {}
/*    */   
/*    */   protected void after() {}
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/ExternalResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */