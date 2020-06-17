/*    */ package org.junit.internal.runners.statements;
/*    */ 
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ 
/*    */ public class InvokeMethod extends org.junit.runners.model.Statement
/*    */ {
/*    */   private final FrameworkMethod testMethod;
/*    */   private final Object target;
/*    */   
/*    */   public InvokeMethod(FrameworkMethod testMethod, Object target) {
/* 11 */     this.testMethod = testMethod;
/* 12 */     this.target = target;
/*    */   }
/*    */   
/*    */   public void evaluate() throws Throwable
/*    */   {
/* 17 */     this.testMethod.invokeExplosively(this.target, new Object[0]);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/statements/InvokeMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */