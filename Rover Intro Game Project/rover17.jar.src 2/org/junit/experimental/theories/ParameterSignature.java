/*     */ package org.junit.experimental.theories;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ParameterSignature
/*     */ {
/*  15 */   private static final Map<Class<?>, Class<?>> CONVERTABLE_TYPES_MAP = ;
/*     */   private final Class<?> type;
/*     */   
/*  18 */   private static Map<Class<?>, Class<?>> buildConvertableTypesMap() { Map<Class<?>, Class<?>> map = new HashMap();
/*     */     
/*  20 */     putSymmetrically(map, Boolean.TYPE, Boolean.class);
/*  21 */     putSymmetrically(map, Byte.TYPE, Byte.class);
/*  22 */     putSymmetrically(map, Short.TYPE, Short.class);
/*  23 */     putSymmetrically(map, Character.TYPE, Character.class);
/*  24 */     putSymmetrically(map, Integer.TYPE, Integer.class);
/*  25 */     putSymmetrically(map, Long.TYPE, Long.class);
/*  26 */     putSymmetrically(map, Float.TYPE, Float.class);
/*  27 */     putSymmetrically(map, Double.TYPE, Double.class);
/*     */     
/*  29 */     return Collections.unmodifiableMap(map);
/*     */   }
/*     */   
/*     */   private static <T> void putSymmetrically(Map<T, T> map, T a, T b) {
/*  33 */     map.put(a, b);
/*  34 */     map.put(b, a);
/*     */   }
/*     */   
/*     */   public static ArrayList<ParameterSignature> signatures(Method method) {
/*  38 */     return signatures(method.getParameterTypes(), method.getParameterAnnotations());
/*     */   }
/*     */   
/*     */   public static List<ParameterSignature> signatures(Constructor<?> constructor)
/*     */   {
/*  43 */     return signatures(constructor.getParameterTypes(), constructor.getParameterAnnotations());
/*     */   }
/*     */   
/*     */ 
/*     */   private static ArrayList<ParameterSignature> signatures(Class<?>[] parameterTypes, Annotation[][] parameterAnnotations)
/*     */   {
/*  49 */     ArrayList<ParameterSignature> sigs = new ArrayList();
/*  50 */     for (int i = 0; i < parameterTypes.length; i++) {
/*  51 */       sigs.add(new ParameterSignature(parameterTypes[i], parameterAnnotations[i]));
/*     */     }
/*     */     
/*  54 */     return sigs;
/*     */   }
/*     */   
/*     */ 
/*     */   private final Annotation[] annotations;
/*     */   
/*     */   private ParameterSignature(Class<?> type, Annotation[] annotations)
/*     */   {
/*  62 */     this.type = type;
/*  63 */     this.annotations = annotations;
/*     */   }
/*     */   
/*     */   public boolean canAcceptValue(Object candidate) {
/*  67 */     return candidate == null ? false : !this.type.isPrimitive() ? true : canAcceptType(candidate.getClass());
/*     */   }
/*     */   
/*     */   public boolean canAcceptType(Class<?> candidate) {
/*  71 */     return (this.type.isAssignableFrom(candidate)) || (isAssignableViaTypeConversion(this.type, candidate));
/*     */   }
/*     */   
/*     */   public boolean canPotentiallyAcceptType(Class<?> candidate)
/*     */   {
/*  76 */     return (candidate.isAssignableFrom(this.type)) || (isAssignableViaTypeConversion(candidate, this.type)) || (canAcceptType(candidate));
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isAssignableViaTypeConversion(Class<?> targetType, Class<?> candidate)
/*     */   {
/*  82 */     if (CONVERTABLE_TYPES_MAP.containsKey(candidate)) {
/*  83 */       Class<?> wrapperClass = (Class)CONVERTABLE_TYPES_MAP.get(candidate);
/*  84 */       return targetType.isAssignableFrom(wrapperClass);
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public Class<?> getType()
/*     */   {
/*  91 */     return this.type;
/*     */   }
/*     */   
/*     */   public List<Annotation> getAnnotations() {
/*  95 */     return Arrays.asList(this.annotations);
/*     */   }
/*     */   
/*     */   public boolean hasAnnotation(Class<? extends Annotation> type) {
/*  99 */     return getAnnotation(type) != null;
/*     */   }
/*     */   
/*     */   public <T extends Annotation> T findDeepAnnotation(Class<T> annotationType) {
/* 103 */     Annotation[] annotations2 = this.annotations;
/* 104 */     return findDeepAnnotation(annotations2, annotationType, 3);
/*     */   }
/*     */   
/*     */   private <T extends Annotation> T findDeepAnnotation(Annotation[] annotations, Class<T> annotationType, int depth)
/*     */   {
/* 109 */     if (depth == 0) {
/* 110 */       return null;
/*     */     }
/* 112 */     for (Annotation each : annotations) {
/* 113 */       if (annotationType.isInstance(each)) {
/* 114 */         return (Annotation)annotationType.cast(each);
/*     */       }
/* 116 */       Annotation candidate = findDeepAnnotation(each.annotationType().getAnnotations(), annotationType, depth - 1);
/*     */       
/* 118 */       if (candidate != null) {
/* 119 */         return (Annotation)annotationType.cast(candidate);
/*     */       }
/*     */     }
/*     */     
/* 123 */     return null;
/*     */   }
/*     */   
/*     */   public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
/* 127 */     for (Annotation each : getAnnotations()) {
/* 128 */       if (annotationType.isInstance(each)) {
/* 129 */         return (Annotation)annotationType.cast(each);
/*     */       }
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/ParameterSignature.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */