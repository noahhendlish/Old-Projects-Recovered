/*    */ package org.junit.runner;
/*    */ 
/*    */ public final class FilterFactoryParams {
/*    */   private final Description topLevelDescription;
/*    */   private final String args;
/*    */   
/*    */   public FilterFactoryParams(Description topLevelDescription, String args) {
/*  8 */     if ((args == null) || (topLevelDescription == null)) {
/*  9 */       throw new NullPointerException();
/*    */     }
/*    */     
/* 12 */     this.topLevelDescription = topLevelDescription;
/* 13 */     this.args = args;
/*    */   }
/*    */   
/*    */   public String getArgs() {
/* 17 */     return this.args;
/*    */   }
/*    */   
/*    */   public Description getTopLevelDescription() {
/* 21 */     return this.topLevelDescription;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/FilterFactoryParams.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */