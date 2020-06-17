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
/*    */ public class Classes
/*    */ {
/*    */   public static Class<?> getClass(String className)
/*    */     throws ClassNotFoundException
/*    */   {
/* 16 */     return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/Classes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */