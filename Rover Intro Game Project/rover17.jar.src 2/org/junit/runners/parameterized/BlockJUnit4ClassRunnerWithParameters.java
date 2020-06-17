/*     */ package org.junit.runners.parameterized;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.List;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ import org.junit.runners.BlockJUnit4ClassRunner;
/*     */ import org.junit.runners.Parameterized.Parameter;
/*     */ import org.junit.runners.model.FrameworkField;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ import org.junit.runners.model.Statement;
/*     */ import org.junit.runners.model.TestClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockJUnit4ClassRunnerWithParameters
/*     */   extends BlockJUnit4ClassRunner
/*     */ {
/*     */   private final Object[] parameters;
/*     */   private final String name;
/*     */   
/*     */   public BlockJUnit4ClassRunnerWithParameters(TestWithParameters test)
/*     */     throws InitializationError
/*     */   {
/*  27 */     super(test.getTestClass().getJavaClass());
/*  28 */     this.parameters = test.getParameters().toArray(new Object[test.getParameters().size()]);
/*     */     
/*  30 */     this.name = test.getName();
/*     */   }
/*     */   
/*     */   public Object createTest() throws Exception
/*     */   {
/*  35 */     if (fieldsAreAnnotated()) {
/*  36 */       return createTestUsingFieldInjection();
/*     */     }
/*  38 */     return createTestUsingConstructorInjection();
/*     */   }
/*     */   
/*     */   private Object createTestUsingConstructorInjection() throws Exception
/*     */   {
/*  43 */     return getTestClass().getOnlyConstructor().newInstance(this.parameters);
/*     */   }
/*     */   
/*     */   private Object createTestUsingFieldInjection() throws Exception {
/*  47 */     List<FrameworkField> annotatedFieldsByParameter = getAnnotatedFieldsByParameter();
/*  48 */     if (annotatedFieldsByParameter.size() != this.parameters.length) {
/*  49 */       throw new Exception("Wrong number of parameters and @Parameter fields. @Parameter fields counted: " + annotatedFieldsByParameter.size() + ", available parameters: " + this.parameters.length + ".");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */     Object testClassInstance = getTestClass().getJavaClass().newInstance();
/*  57 */     for (FrameworkField each : annotatedFieldsByParameter) {
/*  58 */       Field field = each.getField();
/*  59 */       Parameterized.Parameter annotation = (Parameterized.Parameter)field.getAnnotation(Parameterized.Parameter.class);
/*  60 */       int index = annotation.value();
/*     */       try {
/*  62 */         field.set(testClassInstance, this.parameters[index]);
/*     */       } catch (IllegalArgumentException iare) {
/*  64 */         throw new Exception(getTestClass().getName() + ": Trying to set " + field.getName() + " with the value " + this.parameters[index] + " that is not the right type (" + this.parameters[index].getClass().getSimpleName() + " instead of " + field.getType().getSimpleName() + ").", iare);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  73 */     return testClassInstance;
/*     */   }
/*     */   
/*     */   protected String getName()
/*     */   {
/*  78 */     return this.name;
/*     */   }
/*     */   
/*     */   protected String testName(FrameworkMethod method)
/*     */   {
/*  83 */     return method.getName() + getName();
/*     */   }
/*     */   
/*     */   protected void validateConstructor(List<Throwable> errors)
/*     */   {
/*  88 */     validateOnlyOneConstructor(errors);
/*  89 */     if (fieldsAreAnnotated()) {
/*  90 */       validateZeroArgConstructor(errors);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void validateFields(List<Throwable> errors)
/*     */   {
/*  96 */     super.validateFields(errors);
/*  97 */     if (fieldsAreAnnotated()) {
/*  98 */       List<FrameworkField> annotatedFieldsByParameter = getAnnotatedFieldsByParameter();
/*  99 */       int[] usedIndices = new int[annotatedFieldsByParameter.size()];
/* 100 */       for (FrameworkField each : annotatedFieldsByParameter) {
/* 101 */         int index = ((Parameterized.Parameter)each.getField().getAnnotation(Parameterized.Parameter.class)).value();
/*     */         
/* 103 */         if ((index < 0) || (index > annotatedFieldsByParameter.size() - 1)) {
/* 104 */           errors.add(new Exception("Invalid @Parameter value: " + index + ". @Parameter fields counted: " + annotatedFieldsByParameter.size() + ". Please use an index between 0 and " + (annotatedFieldsByParameter.size() - 1) + "."));
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 110 */           usedIndices[index] += 1;
/*     */         }
/*     */       }
/* 113 */       for (int index = 0; index < usedIndices.length; index++) {
/* 114 */         int numberOfUse = usedIndices[index];
/* 115 */         if (numberOfUse == 0) {
/* 116 */           errors.add(new Exception("@Parameter(" + index + ") is never used."));
/*     */         }
/* 118 */         else if (numberOfUse > 1) {
/* 119 */           errors.add(new Exception("@Parameter(" + index + ") is used more than once (" + numberOfUse + ")."));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected Statement classBlock(RunNotifier notifier)
/*     */   {
/* 128 */     return childrenInvoker(notifier);
/*     */   }
/*     */   
/*     */   protected Annotation[] getRunnerAnnotations()
/*     */   {
/* 133 */     return new Annotation[0];
/*     */   }
/*     */   
/*     */   private List<FrameworkField> getAnnotatedFieldsByParameter() {
/* 137 */     return getTestClass().getAnnotatedFields(Parameterized.Parameter.class);
/*     */   }
/*     */   
/*     */   private boolean fieldsAreAnnotated() {
/* 141 */     return !getAnnotatedFieldsByParameter().isEmpty();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/parameterized/BlockJUnit4ClassRunnerWithParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */