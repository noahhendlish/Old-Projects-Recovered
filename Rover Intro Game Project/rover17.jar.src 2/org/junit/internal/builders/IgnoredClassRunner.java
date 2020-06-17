/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import org.junit.runner.notification.RunNotifier;
/*    */ 
/*    */ public class IgnoredClassRunner extends org.junit.runner.Runner
/*    */ {
/*    */   private final Class<?> clazz;
/*    */   
/*    */   public IgnoredClassRunner(Class<?> testClass)
/*    */   {
/* 11 */     this.clazz = testClass;
/*    */   }
/*    */   
/*    */   public void run(RunNotifier notifier)
/*    */   {
/* 16 */     notifier.fireTestIgnored(getDescription());
/*    */   }
/*    */   
/*    */   public org.junit.runner.Description getDescription()
/*    */   {
/* 21 */     return org.junit.runner.Description.createSuiteDescription(this.clazz);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/builders/IgnoredClassRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */