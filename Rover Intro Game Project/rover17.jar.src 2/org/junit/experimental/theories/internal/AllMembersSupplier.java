/*     */ package org.junit.experimental.theories.internal;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.junit.experimental.theories.DataPoint;
/*     */ import org.junit.experimental.theories.DataPoints;
/*     */ import org.junit.experimental.theories.ParameterSignature;
/*     */ import org.junit.experimental.theories.PotentialAssignment;
/*     */ import org.junit.experimental.theories.PotentialAssignment.CouldNotGenerateValueException;
/*     */ import org.junit.runners.model.FrameworkField;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.TestClass;
/*     */ 
/*     */ public class AllMembersSupplier extends org.junit.experimental.theories.ParameterSupplier
/*     */ {
/*     */   private final TestClass clazz;
/*     */   
/*     */   static class MethodParameterValue extends PotentialAssignment
/*     */   {
/*     */     private final FrameworkMethod method;
/*     */     
/*     */     private MethodParameterValue(FrameworkMethod dataPointMethod)
/*     */     {
/*  28 */       this.method = dataPointMethod;
/*     */     }
/*     */     
/*     */     public Object getValue() throws PotentialAssignment.CouldNotGenerateValueException
/*     */     {
/*     */       try {
/*  34 */         return this.method.invokeExplosively(null, new Object[0]);
/*     */       } catch (IllegalArgumentException e) {
/*  36 */         throw new RuntimeException("unexpected: argument length is checked");
/*     */       }
/*     */       catch (IllegalAccessException e) {
/*  39 */         throw new RuntimeException("unexpected: getMethods returned an inaccessible method");
/*     */       }
/*     */       catch (Throwable throwable) {
/*  42 */         DataPoint annotation = (DataPoint)this.method.getAnnotation(DataPoint.class);
/*  43 */         org.junit.Assume.assumeTrue((annotation == null) || (!AllMembersSupplier.isAssignableToAnyOf(annotation.ignoredExceptions(), throwable)));
/*     */         
/*  45 */         throw new PotentialAssignment.CouldNotGenerateValueException(throwable);
/*     */       }
/*     */     }
/*     */     
/*     */     public String getDescription() throws PotentialAssignment.CouldNotGenerateValueException
/*     */     {
/*  51 */       return this.method.getName();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AllMembersSupplier(TestClass type)
/*     */   {
/*  61 */     this.clazz = type;
/*     */   }
/*     */   
/*     */   public List<PotentialAssignment> getValueSources(ParameterSignature sig) throws Throwable
/*     */   {
/*  66 */     List<PotentialAssignment> list = new ArrayList();
/*     */     
/*  68 */     addSinglePointFields(sig, list);
/*  69 */     addMultiPointFields(sig, list);
/*  70 */     addSinglePointMethods(sig, list);
/*  71 */     addMultiPointMethods(sig, list);
/*     */     
/*  73 */     return list;
/*     */   }
/*     */   
/*     */   private void addMultiPointMethods(ParameterSignature sig, List<PotentialAssignment> list) throws Throwable {
/*  77 */     for (FrameworkMethod dataPointsMethod : getDataPointsMethods(sig)) {
/*  78 */       Class<?> returnType = dataPointsMethod.getReturnType();
/*     */       
/*  80 */       if (((returnType.isArray()) && (sig.canPotentiallyAcceptType(returnType.getComponentType()))) || (Iterable.class.isAssignableFrom(returnType))) {
/*     */         try
/*     */         {
/*  83 */           addDataPointsValues(returnType, sig, dataPointsMethod.getName(), list, dataPointsMethod.invokeExplosively(null, new Object[0]));
/*     */         }
/*     */         catch (Throwable throwable) {
/*  86 */           DataPoints annotation = (DataPoints)dataPointsMethod.getAnnotation(DataPoints.class);
/*  87 */           if ((annotation != null) && (isAssignableToAnyOf(annotation.ignoredExceptions(), throwable))) {
/*  88 */             return;
/*     */           }
/*  90 */           throw throwable;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addSinglePointMethods(ParameterSignature sig, List<PotentialAssignment> list)
/*     */   {
/*  98 */     for (FrameworkMethod dataPointMethod : getSingleDataPointMethods(sig)) {
/*  99 */       if (sig.canAcceptType(dataPointMethod.getType())) {
/* 100 */         list.add(new MethodParameterValue(dataPointMethod, null));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addMultiPointFields(ParameterSignature sig, List<PotentialAssignment> list) {
/* 106 */     for (Field field : getDataPointsFields(sig)) {
/* 107 */       Class<?> type = field.getType();
/* 108 */       addDataPointsValues(type, sig, field.getName(), list, getStaticFieldValue(field));
/*     */     }
/*     */   }
/*     */   
/*     */   private void addSinglePointFields(ParameterSignature sig, List<PotentialAssignment> list) {
/* 113 */     for (Field field : getSingleDataPointFields(sig)) {
/* 114 */       Object value = getStaticFieldValue(field);
/*     */       
/* 116 */       if (sig.canAcceptValue(value)) {
/* 117 */         list.add(PotentialAssignment.forValue(field.getName(), value));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addDataPointsValues(Class<?> type, ParameterSignature sig, String name, List<PotentialAssignment> list, Object value)
/*     */   {
/* 124 */     if (type.isArray()) {
/* 125 */       addArrayValues(sig, name, list, value);
/*     */     }
/* 127 */     else if (Iterable.class.isAssignableFrom(type)) {
/* 128 */       addIterableValues(sig, name, list, (Iterable)value);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addArrayValues(ParameterSignature sig, String name, List<PotentialAssignment> list, Object array) {
/* 133 */     for (int i = 0; i < Array.getLength(array); i++) {
/* 134 */       Object value = Array.get(array, i);
/* 135 */       if (sig.canAcceptValue(value)) {
/* 136 */         list.add(PotentialAssignment.forValue(name + "[" + i + "]", value));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addIterableValues(ParameterSignature sig, String name, List<PotentialAssignment> list, Iterable<?> iterable) {
/* 142 */     Iterator<?> iterator = iterable.iterator();
/* 143 */     int i = 0;
/* 144 */     while (iterator.hasNext()) {
/* 145 */       Object value = iterator.next();
/* 146 */       if (sig.canAcceptValue(value)) {
/* 147 */         list.add(PotentialAssignment.forValue(name + "[" + i + "]", value));
/*     */       }
/* 149 */       i++;
/*     */     }
/*     */   }
/*     */   
/*     */   private Object getStaticFieldValue(Field field) {
/*     */     try {
/* 155 */       return field.get(null);
/*     */     } catch (IllegalArgumentException e) {
/* 157 */       throw new RuntimeException("unexpected: field from getClass doesn't exist on object");
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 160 */       throw new RuntimeException("unexpected: getFields returned an inaccessible field");
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isAssignableToAnyOf(Class<?>[] typeArray, Object target)
/*     */   {
/* 166 */     for (Class<?> type : typeArray) {
/* 167 */       if (type.isAssignableFrom(target.getClass())) {
/* 168 */         return true;
/*     */       }
/*     */     }
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   protected Collection<FrameworkMethod> getDataPointsMethods(ParameterSignature sig) {
/* 175 */     return this.clazz.getAnnotatedMethods(DataPoints.class);
/*     */   }
/*     */   
/*     */   protected Collection<Field> getSingleDataPointFields(ParameterSignature sig) {
/* 179 */     List<FrameworkField> fields = this.clazz.getAnnotatedFields(DataPoint.class);
/* 180 */     Collection<Field> validFields = new ArrayList();
/*     */     
/* 182 */     for (FrameworkField frameworkField : fields) {
/* 183 */       validFields.add(frameworkField.getField());
/*     */     }
/*     */     
/* 186 */     return validFields;
/*     */   }
/*     */   
/*     */   protected Collection<Field> getDataPointsFields(ParameterSignature sig) {
/* 190 */     List<FrameworkField> fields = this.clazz.getAnnotatedFields(DataPoints.class);
/* 191 */     Collection<Field> validFields = new ArrayList();
/*     */     
/* 193 */     for (FrameworkField frameworkField : fields) {
/* 194 */       validFields.add(frameworkField.getField());
/*     */     }
/*     */     
/* 197 */     return validFields;
/*     */   }
/*     */   
/*     */   protected Collection<FrameworkMethod> getSingleDataPointMethods(ParameterSignature sig) {
/* 201 */     return this.clazz.getAnnotatedMethods(DataPoint.class);
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/internal/AllMembersSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */