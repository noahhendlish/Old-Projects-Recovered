/*    */ package org.junit.experimental.theories.internal;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import org.junit.experimental.theories.DataPoint;
/*    */ import org.junit.experimental.theories.DataPoints;
/*    */ import org.junit.experimental.theories.FromDataPoints;
/*    */ import org.junit.experimental.theories.ParameterSignature;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.TestClass;
/*    */ 
/*    */ public class SpecificDataPointsSupplier extends AllMembersSupplier
/*    */ {
/*    */   public SpecificDataPointsSupplier(TestClass testClass)
/*    */   {
/* 19 */     super(testClass);
/*    */   }
/*    */   
/*    */   protected Collection<Field> getSingleDataPointFields(ParameterSignature sig)
/*    */   {
/* 24 */     Collection<Field> fields = super.getSingleDataPointFields(sig);
/* 25 */     String requestedName = ((FromDataPoints)sig.getAnnotation(FromDataPoints.class)).value();
/*    */     
/* 27 */     List<Field> fieldsWithMatchingNames = new ArrayList();
/*    */     
/* 29 */     for (Field field : fields) {
/* 30 */       String[] fieldNames = ((DataPoint)field.getAnnotation(DataPoint.class)).value();
/* 31 */       if (Arrays.asList(fieldNames).contains(requestedName)) {
/* 32 */         fieldsWithMatchingNames.add(field);
/*    */       }
/*    */     }
/*    */     
/* 36 */     return fieldsWithMatchingNames;
/*    */   }
/*    */   
/*    */   protected Collection<Field> getDataPointsFields(ParameterSignature sig)
/*    */   {
/* 41 */     Collection<Field> fields = super.getDataPointsFields(sig);
/* 42 */     String requestedName = ((FromDataPoints)sig.getAnnotation(FromDataPoints.class)).value();
/*    */     
/* 44 */     List<Field> fieldsWithMatchingNames = new ArrayList();
/*    */     
/* 46 */     for (Field field : fields) {
/* 47 */       String[] fieldNames = ((DataPoints)field.getAnnotation(DataPoints.class)).value();
/* 48 */       if (Arrays.asList(fieldNames).contains(requestedName)) {
/* 49 */         fieldsWithMatchingNames.add(field);
/*    */       }
/*    */     }
/*    */     
/* 53 */     return fieldsWithMatchingNames;
/*    */   }
/*    */   
/*    */   protected Collection<FrameworkMethod> getSingleDataPointMethods(ParameterSignature sig)
/*    */   {
/* 58 */     Collection<FrameworkMethod> methods = super.getSingleDataPointMethods(sig);
/* 59 */     String requestedName = ((FromDataPoints)sig.getAnnotation(FromDataPoints.class)).value();
/*    */     
/* 61 */     List<FrameworkMethod> methodsWithMatchingNames = new ArrayList();
/*    */     
/* 63 */     for (FrameworkMethod method : methods) {
/* 64 */       String[] methodNames = ((DataPoint)method.getAnnotation(DataPoint.class)).value();
/* 65 */       if (Arrays.asList(methodNames).contains(requestedName)) {
/* 66 */         methodsWithMatchingNames.add(method);
/*    */       }
/*    */     }
/*    */     
/* 70 */     return methodsWithMatchingNames;
/*    */   }
/*    */   
/*    */   protected Collection<FrameworkMethod> getDataPointsMethods(ParameterSignature sig)
/*    */   {
/* 75 */     Collection<FrameworkMethod> methods = super.getDataPointsMethods(sig);
/* 76 */     String requestedName = ((FromDataPoints)sig.getAnnotation(FromDataPoints.class)).value();
/*    */     
/* 78 */     List<FrameworkMethod> methodsWithMatchingNames = new ArrayList();
/*    */     
/* 80 */     for (FrameworkMethod method : methods) {
/* 81 */       String[] methodNames = ((DataPoints)method.getAnnotation(DataPoints.class)).value();
/* 82 */       if (Arrays.asList(methodNames).contains(requestedName)) {
/* 83 */         methodsWithMatchingNames.add(method);
/*    */       }
/*    */     }
/*    */     
/* 87 */     return methodsWithMatchingNames;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/internal/SpecificDataPointsSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */