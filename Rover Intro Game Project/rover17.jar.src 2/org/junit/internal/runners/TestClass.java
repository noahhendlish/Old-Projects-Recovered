/*     */ package org.junit.internal.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.AfterClass;
/*     */ import org.junit.Before;
/*     */ import org.junit.BeforeClass;
/*     */ import org.junit.Test;
/*     */ import org.junit.internal.MethodSorter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class TestClass
/*     */ {
/*     */   private final Class<?> klass;
/*     */   
/*     */   public TestClass(Class<?> klass)
/*     */   {
/*  27 */     this.klass = klass;
/*     */   }
/*     */   
/*     */   public List<Method> getTestMethods() {
/*  31 */     return getAnnotatedMethods(Test.class);
/*     */   }
/*     */   
/*     */   List<Method> getBefores() {
/*  35 */     return getAnnotatedMethods(BeforeClass.class);
/*     */   }
/*     */   
/*     */   List<Method> getAfters() {
/*  39 */     return getAnnotatedMethods(AfterClass.class);
/*     */   }
/*     */   
/*     */   public List<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass) {
/*  43 */     List<Method> results = new ArrayList();
/*  44 */     for (Class<?> eachClass : getSuperClasses(this.klass)) {
/*  45 */       Method[] methods = MethodSorter.getDeclaredMethods(eachClass);
/*  46 */       for (Method eachMethod : methods) {
/*  47 */         Annotation annotation = eachMethod.getAnnotation(annotationClass);
/*  48 */         if ((annotation != null) && (!isShadowed(eachMethod, results))) {
/*  49 */           results.add(eachMethod);
/*     */         }
/*     */       }
/*     */     }
/*  53 */     if (runsTopToBottom(annotationClass)) {
/*  54 */       Collections.reverse(results);
/*     */     }
/*  56 */     return results;
/*     */   }
/*     */   
/*     */   private boolean runsTopToBottom(Class<? extends Annotation> annotation) {
/*  60 */     return (annotation.equals(Before.class)) || (annotation.equals(BeforeClass.class));
/*     */   }
/*     */   
/*     */   private boolean isShadowed(Method method, List<Method> results) {
/*  64 */     for (Method each : results) {
/*  65 */       if (isShadowed(method, each)) {
/*  66 */         return true;
/*     */       }
/*     */     }
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isShadowed(Method current, Method previous) {
/*  73 */     if (!previous.getName().equals(current.getName())) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (previous.getParameterTypes().length != current.getParameterTypes().length) {
/*  77 */       return false;
/*     */     }
/*  79 */     for (int i = 0; i < previous.getParameterTypes().length; i++) {
/*  80 */       if (!previous.getParameterTypes()[i].equals(current.getParameterTypes()[i])) {
/*  81 */         return false;
/*     */       }
/*     */     }
/*  84 */     return true;
/*     */   }
/*     */   
/*     */   private List<Class<?>> getSuperClasses(Class<?> testClass) {
/*  88 */     ArrayList<Class<?>> results = new ArrayList();
/*  89 */     Class<?> current = testClass;
/*  90 */     while (current != null) {
/*  91 */       results.add(current);
/*  92 */       current = current.getSuperclass();
/*     */     }
/*  94 */     return results;
/*     */   }
/*     */   
/*     */   public Constructor<?> getConstructor() throws SecurityException, NoSuchMethodException {
/*  98 */     return this.klass.getConstructor(new Class[0]);
/*     */   }
/*     */   
/*     */   public Class<?> getJavaClass() {
/* 102 */     return this.klass;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 106 */     return this.klass.getName();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/TestClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */