/*   */ package org.junit.internal.builders;
/*   */ 
/*   */ import org.junit.runner.Runner;
/*   */ 
/*   */ public class NullBuilder extends org.junit.runners.model.RunnerBuilder
/*   */ {
/*   */   public Runner runnerForClass(Class<?> each) throws Throwable
/*   */   {
/* 9 */     return null;
/*   */   }
/*   */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/builders/NullBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */