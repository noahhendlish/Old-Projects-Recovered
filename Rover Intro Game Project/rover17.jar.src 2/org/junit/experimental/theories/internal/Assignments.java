/*     */ package org.junit.experimental.theories.internal;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.experimental.theories.ParameterSignature;
/*     */ import org.junit.experimental.theories.ParameterSupplier;
/*     */ import org.junit.experimental.theories.ParametersSuppliedBy;
/*     */ import org.junit.experimental.theories.PotentialAssignment;
/*     */ import org.junit.experimental.theories.PotentialAssignment.CouldNotGenerateValueException;
/*     */ import org.junit.runners.model.TestClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Assignments
/*     */ {
/*     */   private final List<PotentialAssignment> assigned;
/*     */   private final List<ParameterSignature> unassigned;
/*     */   private final TestClass clazz;
/*     */   
/*     */   private Assignments(List<PotentialAssignment> assigned, List<ParameterSignature> unassigned, TestClass clazz)
/*     */   {
/*  30 */     this.unassigned = unassigned;
/*  31 */     this.assigned = assigned;
/*  32 */     this.clazz = clazz;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Assignments allUnassigned(Method testMethod, TestClass testClass)
/*     */   {
/*  42 */     List<ParameterSignature> signatures = ParameterSignature.signatures(testClass.getOnlyConstructor());
/*     */     
/*  44 */     signatures.addAll(ParameterSignature.signatures(testMethod));
/*  45 */     return new Assignments(new ArrayList(), signatures, testClass);
/*     */   }
/*     */   
/*     */   public boolean isComplete()
/*     */   {
/*  50 */     return this.unassigned.size() == 0;
/*     */   }
/*     */   
/*     */   public ParameterSignature nextUnassigned() {
/*  54 */     return (ParameterSignature)this.unassigned.get(0);
/*     */   }
/*     */   
/*     */   public Assignments assignNext(PotentialAssignment source) {
/*  58 */     List<PotentialAssignment> assigned = new ArrayList(this.assigned);
/*     */     
/*  60 */     assigned.add(source);
/*     */     
/*  62 */     return new Assignments(assigned, this.unassigned.subList(1, this.unassigned.size()), this.clazz);
/*     */   }
/*     */   
/*     */   public Object[] getActualValues(int start, int stop)
/*     */     throws PotentialAssignment.CouldNotGenerateValueException
/*     */   {
/*  68 */     Object[] values = new Object[stop - start];
/*  69 */     for (int i = start; i < stop; i++) {
/*  70 */       values[(i - start)] = ((PotentialAssignment)this.assigned.get(i)).getValue();
/*     */     }
/*  72 */     return values;
/*     */   }
/*     */   
/*     */   public List<PotentialAssignment> potentialsForNextUnassigned() throws Throwable
/*     */   {
/*  77 */     ParameterSignature unassigned = nextUnassigned();
/*  78 */     List<PotentialAssignment> assignments = getSupplier(unassigned).getValueSources(unassigned);
/*     */     
/*  80 */     if (assignments.size() == 0) {
/*  81 */       assignments = generateAssignmentsFromTypeAlone(unassigned);
/*     */     }
/*     */     
/*  84 */     return assignments;
/*     */   }
/*     */   
/*     */   private List<PotentialAssignment> generateAssignmentsFromTypeAlone(ParameterSignature unassigned) {
/*  88 */     Class<?> paramType = unassigned.getType();
/*     */     
/*  90 */     if (paramType.isEnum())
/*  91 */       return new EnumSupplier(paramType).getValueSources(unassigned);
/*  92 */     if ((paramType.equals(Boolean.class)) || (paramType.equals(Boolean.TYPE))) {
/*  93 */       return new BooleanSupplier().getValueSources(unassigned);
/*     */     }
/*  95 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   private ParameterSupplier getSupplier(ParameterSignature unassigned)
/*     */     throws Exception
/*     */   {
/* 101 */     ParametersSuppliedBy annotation = (ParametersSuppliedBy)unassigned.findDeepAnnotation(ParametersSuppliedBy.class);
/*     */     
/*     */ 
/* 104 */     if (annotation != null) {
/* 105 */       return buildParameterSupplierFromClass(annotation.value());
/*     */     }
/* 107 */     return new AllMembersSupplier(this.clazz);
/*     */   }
/*     */   
/*     */   private ParameterSupplier buildParameterSupplierFromClass(Class<? extends ParameterSupplier> cls)
/*     */     throws Exception
/*     */   {
/* 113 */     Constructor<?>[] supplierConstructors = cls.getConstructors();
/*     */     
/* 115 */     for (Constructor<?> constructor : supplierConstructors) {
/* 116 */       Class<?>[] parameterTypes = constructor.getParameterTypes();
/* 117 */       if ((parameterTypes.length == 1) && (parameterTypes[0].equals(TestClass.class)))
/*     */       {
/* 119 */         return (ParameterSupplier)constructor.newInstance(new Object[] { this.clazz });
/*     */       }
/*     */     }
/*     */     
/* 123 */     return (ParameterSupplier)cls.newInstance();
/*     */   }
/*     */   
/*     */   public Object[] getConstructorArguments() throws PotentialAssignment.CouldNotGenerateValueException
/*     */   {
/* 128 */     return getActualValues(0, getConstructorParameterCount());
/*     */   }
/*     */   
/*     */   public Object[] getMethodArguments() throws PotentialAssignment.CouldNotGenerateValueException {
/* 132 */     return getActualValues(getConstructorParameterCount(), this.assigned.size());
/*     */   }
/*     */   
/*     */   public Object[] getAllArguments() throws PotentialAssignment.CouldNotGenerateValueException {
/* 136 */     return getActualValues(0, this.assigned.size());
/*     */   }
/*     */   
/*     */   private int getConstructorParameterCount() {
/* 140 */     List<ParameterSignature> signatures = ParameterSignature.signatures(this.clazz.getOnlyConstructor());
/*     */     
/* 142 */     int constructorParameterCount = signatures.size();
/* 143 */     return constructorParameterCount;
/*     */   }
/*     */   
/*     */   public Object[] getArgumentStrings(boolean nullsOk) throws PotentialAssignment.CouldNotGenerateValueException
/*     */   {
/* 148 */     Object[] values = new Object[this.assigned.size()];
/* 149 */     for (int i = 0; i < values.length; i++) {
/* 150 */       values[i] = ((PotentialAssignment)this.assigned.get(i)).getDescription();
/*     */     }
/* 152 */     return values;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/internal/Assignments.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */