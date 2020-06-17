/*    */ package org.junit.experimental.runners;
/*    */ 
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.junit.runners.Suite;
/*    */ import org.junit.runners.model.RunnerBuilder;
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
/*    */ public class Enclosed
/*    */   extends Suite
/*    */ {
/*    */   public Enclosed(Class<?> klass, RunnerBuilder builder)
/*    */     throws Throwable
/*    */   {
/* 31 */     super(builder, klass, filterAbstractClasses(klass.getClasses()));
/*    */   }
/*    */   
/*    */   private static Class<?>[] filterAbstractClasses(Class<?>[] classes) {
/* 35 */     List<Class<?>> filteredList = new ArrayList(classes.length);
/*    */     
/* 37 */     for (Class<?> clazz : classes) {
/* 38 */       if (!Modifier.isAbstract(clazz.getModifiers())) {
/* 39 */         filteredList.add(clazz);
/*    */       }
/*    */     }
/*    */     
/* 43 */     return (Class[])filteredList.toArray(new Class[filteredList.size()]);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/runners/Enclosed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */