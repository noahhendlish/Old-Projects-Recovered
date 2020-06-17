/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.BlockJUnit4ClassRunner;
/*    */ 
/*    */ public class JUnit4Builder extends org.junit.runners.model.RunnerBuilder
/*    */ {
/*    */   public Runner runnerForClass(Class<?> testClass) throws Throwable
/*    */   {
/* 10 */     return new BlockJUnit4ClassRunner(testClass);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/builders/JUnit4Builder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */