/*    */ package org.junit.rules;
/*    */ 
/*    */ import org.junit.internal.AssumptionViolatedException;
/*    */ import org.junit.runners.model.FrameworkMethod;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class TestWatchman
/*    */   implements MethodRule
/*    */ {
/*    */   public Statement apply(final Statement base, final FrameworkMethod method, Object target)
/*    */   {
/* 48 */     new Statement()
/*    */     {
/*    */       public void evaluate() throws Throwable {
/* 51 */         TestWatchman.this.starting(method);
/*    */         try {
/* 53 */           base.evaluate();
/* 54 */           TestWatchman.this.succeeded(method);
/*    */         } catch (AssumptionViolatedException e) {
/* 56 */           throw e;
/*    */         } catch (Throwable e) {
/* 58 */           TestWatchman.this.failed(e, method);
/* 59 */           throw e;
/*    */         } finally {
/* 61 */           TestWatchman.this.finished(method);
/*    */         }
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */   public void succeeded(FrameworkMethod method) {}
/*    */   
/*    */   public void failed(Throwable e, FrameworkMethod method) {}
/*    */   
/*    */   public void starting(FrameworkMethod method) {}
/*    */   
/*    */   public void finished(FrameworkMethod method) {}
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/TestWatchman.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */