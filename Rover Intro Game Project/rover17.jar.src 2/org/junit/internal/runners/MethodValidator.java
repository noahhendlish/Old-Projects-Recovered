/*    */ package org.junit.internal.runners;
/*    */ 
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.reflect.Method;
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.junit.After;
/*    */ import org.junit.AfterClass;
/*    */ import org.junit.Before;
/*    */ import org.junit.BeforeClass;
/*    */ import org.junit.Test;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class MethodValidator
/*    */ {
/* 24 */   private final List<Throwable> errors = new ArrayList();
/*    */   private TestClass testClass;
/*    */   
/*    */   public MethodValidator(TestClass testClass)
/*    */   {
/* 29 */     this.testClass = testClass;
/*    */   }
/*    */   
/*    */   public void validateInstanceMethods() {
/* 33 */     validateTestMethods(After.class, false);
/* 34 */     validateTestMethods(Before.class, false);
/* 35 */     validateTestMethods(Test.class, false);
/*    */     
/* 37 */     List<Method> methods = this.testClass.getAnnotatedMethods(Test.class);
/* 38 */     if (methods.size() == 0) {
/* 39 */       this.errors.add(new Exception("No runnable methods"));
/*    */     }
/*    */   }
/*    */   
/*    */   public void validateStaticMethods() {
/* 44 */     validateTestMethods(BeforeClass.class, true);
/* 45 */     validateTestMethods(AfterClass.class, true);
/*    */   }
/*    */   
/*    */   public List<Throwable> validateMethodsForDefaultRunner() {
/* 49 */     validateNoArgConstructor();
/* 50 */     validateStaticMethods();
/* 51 */     validateInstanceMethods();
/* 52 */     return this.errors;
/*    */   }
/*    */   
/*    */   public void assertValid() throws InitializationError {
/* 56 */     if (!this.errors.isEmpty()) {
/* 57 */       throw new InitializationError(this.errors);
/*    */     }
/*    */   }
/*    */   
/*    */   public void validateNoArgConstructor() {
/*    */     try {
/* 63 */       this.testClass.getConstructor();
/*    */     } catch (Exception e) {
/* 65 */       this.errors.add(new Exception("Test class should have public zero-argument constructor", e));
/*    */     }
/*    */   }
/*    */   
/*    */   private void validateTestMethods(Class<? extends Annotation> annotation, boolean isStatic)
/*    */   {
/* 71 */     List<Method> methods = this.testClass.getAnnotatedMethods(annotation);
/*    */     
/* 73 */     for (Method each : methods) {
/* 74 */       if (Modifier.isStatic(each.getModifiers()) != isStatic) {
/* 75 */         String state = isStatic ? "should" : "should not";
/* 76 */         this.errors.add(new Exception("Method " + each.getName() + "() " + state + " be static"));
/*    */       }
/*    */       
/* 79 */       if (!Modifier.isPublic(each.getDeclaringClass().getModifiers())) {
/* 80 */         this.errors.add(new Exception("Class " + each.getDeclaringClass().getName() + " should be public"));
/*    */       }
/*    */       
/* 83 */       if (!Modifier.isPublic(each.getModifiers())) {
/* 84 */         this.errors.add(new Exception("Method " + each.getName() + " should be public"));
/*    */       }
/*    */       
/* 87 */       if (each.getReturnType() != Void.TYPE) {
/* 88 */         this.errors.add(new Exception("Method " + each.getName() + " should be void"));
/*    */       }
/*    */       
/* 91 */       if (each.getParameterTypes().length != 0) {
/* 92 */         this.errors.add(new Exception("Method " + each.getName() + " should have no parameters"));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/MethodValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */