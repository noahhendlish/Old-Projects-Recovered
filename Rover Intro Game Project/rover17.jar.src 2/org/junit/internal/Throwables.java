/*    */ package org.junit.internal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Throwables
/*    */ {
/*    */   public static Exception rethrowAsException(Throwable e)
/*    */     throws Exception
/*    */   {
/* 34 */     rethrow(e);
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   private static <T extends Throwable> void rethrow(Throwable e) throws Throwable
/*    */   {
/* 40 */     throw e;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/Throwables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */