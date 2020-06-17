/*     */ package org.junit.validator;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.runners.model.Annotatable;
/*     */ import org.junit.runners.model.FrameworkField;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.TestClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AnnotationsValidator
/*     */   implements TestClassValidator
/*     */ {
/*  22 */   private static final List<AnnotatableValidator<?>> VALIDATORS = Arrays.asList(new AnnotatableValidator[] { new ClassValidator(null), new MethodValidator(null), new FieldValidator(null) });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Exception> validateTestClass(TestClass testClass)
/*     */   {
/*  34 */     List<Exception> validationErrors = new ArrayList();
/*  35 */     for (AnnotatableValidator<?> validator : VALIDATORS) {
/*  36 */       List<Exception> additionalErrors = validator.validateTestClass(testClass);
/*     */       
/*  38 */       validationErrors.addAll(additionalErrors);
/*     */     }
/*  40 */     return validationErrors;
/*     */   }
/*     */   
/*     */   private static abstract class AnnotatableValidator<T extends Annotatable> {
/*  44 */     private static final AnnotationValidatorFactory ANNOTATION_VALIDATOR_FACTORY = new AnnotationValidatorFactory();
/*     */     
/*     */     abstract Iterable<T> getAnnotatablesForTestClass(TestClass paramTestClass);
/*     */     
/*     */     abstract List<Exception> validateAnnotatable(AnnotationValidator paramAnnotationValidator, T paramT);
/*     */     
/*     */     public List<Exception> validateTestClass(TestClass testClass)
/*     */     {
/*  52 */       List<Exception> validationErrors = new ArrayList();
/*  53 */       for (T annotatable : getAnnotatablesForTestClass(testClass)) {
/*  54 */         List<Exception> additionalErrors = validateAnnotatable(annotatable);
/*  55 */         validationErrors.addAll(additionalErrors);
/*     */       }
/*  57 */       return validationErrors;
/*     */     }
/*     */     
/*     */     private List<Exception> validateAnnotatable(T annotatable) {
/*  61 */       List<Exception> validationErrors = new ArrayList();
/*  62 */       for (Annotation annotation : annotatable.getAnnotations()) {
/*  63 */         Class<? extends Annotation> annotationType = annotation.annotationType();
/*     */         
/*  65 */         ValidateWith validateWith = (ValidateWith)annotationType.getAnnotation(ValidateWith.class);
/*     */         
/*  67 */         if (validateWith != null) {
/*  68 */           AnnotationValidator annotationValidator = ANNOTATION_VALIDATOR_FACTORY.createAnnotationValidator(validateWith);
/*     */           
/*  70 */           List<Exception> errors = validateAnnotatable(annotationValidator, annotatable);
/*     */           
/*  72 */           validationErrors.addAll(errors);
/*     */         }
/*     */       }
/*  75 */       return validationErrors;
/*     */     }
/*     */   }
/*     */   
/*  79 */   private static class ClassValidator extends AnnotationsValidator.AnnotatableValidator<TestClass> { private ClassValidator() { super(); }
/*     */     
/*     */     Iterable<TestClass> getAnnotatablesForTestClass(TestClass testClass) {
/*  82 */       return Collections.singletonList(testClass);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  88 */     List<Exception> validateAnnotatable(AnnotationValidator validator, TestClass testClass) { return validator.validateAnnotatedClass(testClass); }
/*     */   }
/*     */   
/*     */   private static class MethodValidator extends AnnotationsValidator.AnnotatableValidator<FrameworkMethod> {
/*  92 */     private MethodValidator() { super(); }
/*     */     
/*     */ 
/*     */     Iterable<FrameworkMethod> getAnnotatablesForTestClass(TestClass testClass)
/*     */     {
/*  97 */       return testClass.getAnnotatedMethods();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 103 */     List<Exception> validateAnnotatable(AnnotationValidator validator, FrameworkMethod method) { return validator.validateAnnotatedMethod(method); }
/*     */   }
/*     */   
/*     */   private static class FieldValidator extends AnnotationsValidator.AnnotatableValidator<FrameworkField> {
/* 107 */     private FieldValidator() { super(); }
/*     */     
/*     */     Iterable<FrameworkField> getAnnotatablesForTestClass(TestClass testClass)
/*     */     {
/* 111 */       return testClass.getAnnotatedFields();
/*     */     }
/*     */     
/*     */ 
/*     */     List<Exception> validateAnnotatable(AnnotationValidator validator, FrameworkField field)
/*     */     {
/* 117 */       return validator.validateAnnotatedField(field);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/validator/AnnotationsValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */