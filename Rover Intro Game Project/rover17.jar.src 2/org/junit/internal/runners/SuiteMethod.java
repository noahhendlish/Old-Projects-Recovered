/*    */ package org.junit.internal.runners;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.lang.reflect.Modifier;
/*    */ import junit.framework.Test;
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
/*    */ public class SuiteMethod
/*    */   extends JUnit38ClassRunner
/*    */ {
/*    */   public SuiteMethod(Class<?> klass)
/*    */     throws Throwable
/*    */   {
/* 24 */     super(testFromSuiteMethod(klass));
/*    */   }
/*    */   
/*    */   public static Test testFromSuiteMethod(Class<?> klass) throws Throwable {
/* 28 */     Method suiteMethod = null;
/* 29 */     Test suite = null;
/*    */     try {
/* 31 */       suiteMethod = klass.getMethod("suite", new Class[0]);
/* 32 */       if (!Modifier.isStatic(suiteMethod.getModifiers())) {
/* 33 */         throw new Exception(klass.getName() + ".suite() must be static");
/*    */       }
/* 35 */       suite = (Test)suiteMethod.invoke(null, new Object[0]);
/*    */     } catch (InvocationTargetException e) {
/* 37 */       throw e.getCause();
/*    */     }
/* 39 */     return suite;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/SuiteMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */