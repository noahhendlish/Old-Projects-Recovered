/*    */ package org.junit.validator;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.junit.runners.model.FrameworkField;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.TestClass;
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
/*    */ public abstract class AnnotationValidator
/*    */ {
/* 22 */   private static final List<Exception> NO_VALIDATION_ERRORS = ;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public List<Exception> validateAnnotatedClass(TestClass testClass)
/*    */   {
/* 33 */     return NO_VALIDATION_ERRORS;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public List<Exception> validateAnnotatedField(FrameworkField field)
/*    */   {
/* 45 */     return NO_VALIDATION_ERRORS;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public List<Exception> validateAnnotatedMethod(FrameworkMethod method)
/*    */   {
/* 58 */     return NO_VALIDATION_ERRORS;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/validator/AnnotationValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */