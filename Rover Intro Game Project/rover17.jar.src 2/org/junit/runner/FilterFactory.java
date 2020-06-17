/*    */ package org.junit.runner;
/*    */ 
/*    */ import org.junit.runner.manipulation.Filter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface FilterFactory
/*    */ {
/*    */   public abstract Filter createFilter(FilterFactoryParams paramFilterFactoryParams)
/*    */     throws FilterFactory.FilterNotCreatedException;
/*    */   
/*    */   public static class FilterNotCreatedException
/*    */     extends Exception
/*    */   {
/*    */     public FilterNotCreatedException(Exception exception)
/*    */     {
/* 21 */       super(exception);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/FilterFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */