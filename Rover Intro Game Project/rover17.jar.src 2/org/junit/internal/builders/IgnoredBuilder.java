/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.model.RunnerBuilder;
/*    */ 
/*    */ public class IgnoredBuilder extends RunnerBuilder
/*    */ {
/*    */   public Runner runnerForClass(Class<?> testClass)
/*    */   {
/* 10 */     if (testClass.getAnnotation(org.junit.Ignore.class) != null) {
/* 11 */       return new IgnoredClassRunner(testClass);
/*    */     }
/* 13 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/builders/IgnoredBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */