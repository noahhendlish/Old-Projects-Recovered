/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import org.junit.internal.runners.SuiteMethod;
/*    */ import org.junit.runner.Runner;
/*    */ 
/*    */ public class SuiteMethodBuilder extends org.junit.runners.model.RunnerBuilder
/*    */ {
/*    */   public Runner runnerForClass(Class<?> each) throws Throwable
/*    */   {
/* 10 */     if (hasSuiteMethod(each)) {
/* 11 */       return new SuiteMethod(each);
/*    */     }
/* 13 */     return null;
/*    */   }
/*    */   
/*    */   public boolean hasSuiteMethod(Class<?> testClass) {
/*    */     try {
/* 18 */       testClass.getMethod("suite", new Class[0]);
/*    */     } catch (NoSuchMethodException e) {
/* 20 */       return false;
/*    */     }
/* 22 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/builders/SuiteMethodBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */