/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import junit.framework.TestCase;
/*    */ import org.junit.runner.Runner;
/*    */ 
/*    */ public class JUnit3Builder extends org.junit.runners.model.RunnerBuilder
/*    */ {
/*    */   public Runner runnerForClass(Class<?> testClass) throws Throwable
/*    */   {
/* 10 */     if (isPre4Test(testClass)) {
/* 11 */       return new org.junit.internal.runners.JUnit38ClassRunner(testClass);
/*    */     }
/* 13 */     return null;
/*    */   }
/*    */   
/*    */   boolean isPre4Test(Class<?> testClass) {
/* 17 */     return TestCase.class.isAssignableFrom(testClass);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/builders/JUnit3Builder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */