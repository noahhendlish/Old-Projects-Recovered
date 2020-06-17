/*    */ package org.junit.internal.runners.model;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ 
/*    */ public abstract class ReflectiveCallable
/*    */ {
/*    */   public Object run()
/*    */     throws Throwable
/*    */   {
/*    */     try
/*    */     {
/* 12 */       return runReflectiveCall();
/*    */     } catch (InvocationTargetException e) {
/* 14 */       throw e.getTargetException();
/*    */     }
/*    */   }
/*    */   
/*    */   protected abstract Object runReflectiveCall()
/*    */     throws Throwable;
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/model/ReflectiveCallable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */