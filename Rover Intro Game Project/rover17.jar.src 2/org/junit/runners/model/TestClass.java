/*     */ package org.junit.runners.model;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.junit.Assert;
/*     */ import org.junit.Before;
/*     */ import org.junit.BeforeClass;
/*     */ import org.junit.internal.MethodSorter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestClass
/*     */   implements Annotatable
/*     */ {
/*  32 */   private static final FieldComparator FIELD_COMPARATOR = new FieldComparator(null);
/*  33 */   private static final MethodComparator METHOD_COMPARATOR = new MethodComparator(null);
/*     */   
/*     */ 
/*     */   private final Class<?> clazz;
/*     */   
/*     */ 
/*     */   private final Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations;
/*     */   
/*     */   private final Map<Class<? extends Annotation>, List<FrameworkField>> fieldsForAnnotations;
/*     */   
/*     */ 
/*     */   public TestClass(Class<?> clazz)
/*     */   {
/*  46 */     this.clazz = clazz;
/*  47 */     if ((clazz != null) && (clazz.getConstructors().length > 1)) {
/*  48 */       throw new IllegalArgumentException("Test class can only have one constructor");
/*     */     }
/*     */     
/*     */ 
/*  52 */     Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations = new LinkedHashMap();
/*     */     
/*  54 */     Map<Class<? extends Annotation>, List<FrameworkField>> fieldsForAnnotations = new LinkedHashMap();
/*     */     
/*     */ 
/*  57 */     scanAnnotatedMembers(methodsForAnnotations, fieldsForAnnotations);
/*     */     
/*  59 */     this.methodsForAnnotations = makeDeeplyUnmodifiable(methodsForAnnotations);
/*  60 */     this.fieldsForAnnotations = makeDeeplyUnmodifiable(fieldsForAnnotations);
/*     */   }
/*     */   
/*     */   protected void scanAnnotatedMembers(Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations, Map<Class<? extends Annotation>, List<FrameworkField>> fieldsForAnnotations) {
/*  64 */     for (Class<?> eachClass : getSuperClasses(this.clazz)) {
/*  65 */       for (Method eachMethod : MethodSorter.getDeclaredMethods(eachClass)) {
/*  66 */         addToAnnotationLists(new FrameworkMethod(eachMethod), methodsForAnnotations);
/*     */       }
/*     */       
/*     */ 
/*  70 */       for (Field eachField : getSortedDeclaredFields(eachClass)) {
/*  71 */         addToAnnotationLists(new FrameworkField(eachField), fieldsForAnnotations);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static Field[] getSortedDeclaredFields(Class<?> clazz) {
/*  77 */     Field[] declaredFields = clazz.getDeclaredFields();
/*  78 */     Arrays.sort(declaredFields, FIELD_COMPARATOR);
/*  79 */     return declaredFields;
/*     */   }
/*     */   
/*     */   protected static <T extends FrameworkMember<T>> void addToAnnotationLists(T member, Map<Class<? extends Annotation>, List<T>> map)
/*     */   {
/*  84 */     for (Annotation each : member.getAnnotations()) {
/*  85 */       Class<? extends Annotation> type = each.annotationType();
/*  86 */       List<T> members = getAnnotatedMembers(map, type, true);
/*  87 */       if (member.isShadowedBy(members)) {
/*  88 */         return;
/*     */       }
/*  90 */       if (runsTopToBottom(type)) {
/*  91 */         members.add(0, member);
/*     */       } else {
/*  93 */         members.add(member);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static <T extends FrameworkMember<T>> Map<Class<? extends Annotation>, List<T>> makeDeeplyUnmodifiable(Map<Class<? extends Annotation>, List<T>> source)
/*     */   {
/* 100 */     LinkedHashMap<Class<? extends Annotation>, List<T>> copy = new LinkedHashMap();
/*     */     
/* 102 */     for (Map.Entry<Class<? extends Annotation>, List<T>> entry : source.entrySet()) {
/* 103 */       copy.put(entry.getKey(), Collections.unmodifiableList((List)entry.getValue()));
/*     */     }
/* 105 */     return Collections.unmodifiableMap(copy);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<FrameworkMethod> getAnnotatedMethods()
/*     */   {
/* 115 */     List<FrameworkMethod> methods = collectValues(this.methodsForAnnotations);
/* 116 */     Collections.sort(methods, METHOD_COMPARATOR);
/* 117 */     return methods;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<FrameworkMethod> getAnnotatedMethods(Class<? extends Annotation> annotationClass)
/*     */   {
/* 126 */     return Collections.unmodifiableList(getAnnotatedMembers(this.methodsForAnnotations, annotationClass, false));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<FrameworkField> getAnnotatedFields()
/*     */   {
/* 136 */     return collectValues(this.fieldsForAnnotations);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<FrameworkField> getAnnotatedFields(Class<? extends Annotation> annotationClass)
/*     */   {
/* 145 */     return Collections.unmodifiableList(getAnnotatedMembers(this.fieldsForAnnotations, annotationClass, false));
/*     */   }
/*     */   
/*     */   private <T> List<T> collectValues(Map<?, List<T>> map) {
/* 149 */     Set<T> values = new LinkedHashSet();
/* 150 */     for (List<T> additionalValues : map.values()) {
/* 151 */       values.addAll(additionalValues);
/*     */     }
/* 153 */     return new ArrayList(values);
/*     */   }
/*     */   
/*     */   private static <T> List<T> getAnnotatedMembers(Map<Class<? extends Annotation>, List<T>> map, Class<? extends Annotation> type, boolean fillIfAbsent)
/*     */   {
/* 158 */     if ((!map.containsKey(type)) && (fillIfAbsent)) {
/* 159 */       map.put(type, new ArrayList());
/*     */     }
/* 161 */     List<T> members = (List)map.get(type);
/* 162 */     return members == null ? Collections.emptyList() : members;
/*     */   }
/*     */   
/*     */   private static boolean runsTopToBottom(Class<? extends Annotation> annotation) {
/* 166 */     return (annotation.equals(Before.class)) || (annotation.equals(BeforeClass.class));
/*     */   }
/*     */   
/*     */   private static List<Class<?>> getSuperClasses(Class<?> testClass)
/*     */   {
/* 171 */     ArrayList<Class<?>> results = new ArrayList();
/* 172 */     Class<?> current = testClass;
/* 173 */     while (current != null) {
/* 174 */       results.add(current);
/* 175 */       current = current.getSuperclass();
/*     */     }
/* 177 */     return results;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Class<?> getJavaClass()
/*     */   {
/* 184 */     return this.clazz;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 191 */     if (this.clazz == null) {
/* 192 */       return "null";
/*     */     }
/* 194 */     return this.clazz.getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Constructor<?> getOnlyConstructor()
/*     */   {
/* 203 */     Constructor<?>[] constructors = this.clazz.getConstructors();
/* 204 */     Assert.assertEquals(1L, constructors.length);
/* 205 */     return constructors[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Annotation[] getAnnotations()
/*     */   {
/* 212 */     if (this.clazz == null) {
/* 213 */       return new Annotation[0];
/*     */     }
/* 215 */     return this.clazz.getAnnotations();
/*     */   }
/*     */   
/*     */   public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
/* 219 */     if (this.clazz == null) {
/* 220 */       return null;
/*     */     }
/* 222 */     return this.clazz.getAnnotation(annotationType);
/*     */   }
/*     */   
/*     */   public <T> List<T> getAnnotatedFieldValues(Object test, Class<? extends Annotation> annotationClass, Class<T> valueClass)
/*     */   {
/* 227 */     List<T> results = new ArrayList();
/* 228 */     for (FrameworkField each : getAnnotatedFields(annotationClass)) {
/*     */       try {
/* 230 */         Object fieldValue = each.get(test);
/* 231 */         if (valueClass.isInstance(fieldValue)) {
/* 232 */           results.add(valueClass.cast(fieldValue));
/*     */         }
/*     */       } catch (IllegalAccessException e) {
/* 235 */         throw new RuntimeException("How did getFields return a field we couldn't access?", e);
/*     */       }
/*     */     }
/*     */     
/* 239 */     return results;
/*     */   }
/*     */   
/*     */   public <T> List<T> getAnnotatedMethodValues(Object test, Class<? extends Annotation> annotationClass, Class<T> valueClass)
/*     */   {
/* 244 */     List<T> results = new ArrayList();
/* 245 */     for (FrameworkMethod each : getAnnotatedMethods(annotationClass))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 255 */         if (valueClass.isAssignableFrom(each.getReturnType())) {
/* 256 */           Object fieldValue = each.invokeExplosively(test, new Object[0]);
/* 257 */           results.add(valueClass.cast(fieldValue));
/*     */         }
/*     */       } catch (Throwable e) {
/* 260 */         throw new RuntimeException("Exception in " + each.getName(), e);
/*     */       }
/*     */     }
/*     */     
/* 264 */     return results;
/*     */   }
/*     */   
/*     */   public boolean isPublic() {
/* 268 */     return Modifier.isPublic(this.clazz.getModifiers());
/*     */   }
/*     */   
/*     */   public boolean isANonStaticInnerClass() {
/* 272 */     return (this.clazz.isMemberClass()) && (!Modifier.isStatic(this.clazz.getModifiers()));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 277 */     return this.clazz == null ? 0 : this.clazz.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/* 282 */     if (this == obj) {
/* 283 */       return true;
/*     */     }
/* 285 */     if (obj == null) {
/* 286 */       return false;
/*     */     }
/* 288 */     if (getClass() != obj.getClass()) {
/* 289 */       return false;
/*     */     }
/* 291 */     TestClass other = (TestClass)obj;
/* 292 */     return this.clazz == other.clazz;
/*     */   }
/*     */   
/*     */   private static class FieldComparator
/*     */     implements Comparator<Field>
/*     */   {
/*     */     public int compare(Field left, Field right)
/*     */     {
/* 300 */       return left.getName().compareTo(right.getName());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static class MethodComparator
/*     */     implements Comparator<FrameworkMethod>
/*     */   {
/*     */     public int compare(FrameworkMethod left, FrameworkMethod right)
/*     */     {
/* 310 */       return MethodSorter.NAME_ASCENDING.compare(left.getMethod(), right.getMethod());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/model/TestClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */