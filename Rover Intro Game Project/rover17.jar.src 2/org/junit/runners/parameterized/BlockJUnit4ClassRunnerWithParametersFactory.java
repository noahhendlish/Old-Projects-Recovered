/*    */ package org.junit.runners.parameterized;
/*    */ 
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.model.InitializationError;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockJUnit4ClassRunnerWithParametersFactory
/*    */   implements ParametersRunnerFactory
/*    */ {
/*    */   public Runner createRunnerForTestWithParameters(TestWithParameters test)
/*    */     throws InitializationError
/*    */   {
/* 16 */     return new BlockJUnit4ClassRunnerWithParameters(test);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/parameterized/BlockJUnit4ClassRunnerWithParametersFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */