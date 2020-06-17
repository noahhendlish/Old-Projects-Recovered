/*    */ package org.junit.internal;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ import org.junit.FixMethodOrder;
/*    */ import org.junit.runners.MethodSorters;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodSorter
/*    */ {
/* 13 */   public static final Comparator<Method> DEFAULT = new Comparator() {
/*    */     public int compare(Method m1, Method m2) {
/* 15 */       int i1 = m1.getName().hashCode();
/* 16 */       int i2 = m2.getName().hashCode();
/* 17 */       if (i1 != i2) {
/* 18 */         return i1 < i2 ? -1 : 1;
/*    */       }
/* 20 */       return MethodSorter.NAME_ASCENDING.compare(m1, m2);
/*    */     }
/*    */   };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 27 */   public static final Comparator<Method> NAME_ASCENDING = new Comparator() {
/*    */     public int compare(Method m1, Method m2) {
/* 29 */       int comparison = m1.getName().compareTo(m2.getName());
/* 30 */       if (comparison != 0) {
/* 31 */         return comparison;
/*    */       }
/* 33 */       return m1.toString().compareTo(m2.toString());
/*    */     }
/*    */   };
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
/*    */ 
/*    */ 
/*    */   public static Method[] getDeclaredMethods(Class<?> clazz)
/*    */   {
/* 52 */     Comparator<Method> comparator = getSorter((FixMethodOrder)clazz.getAnnotation(FixMethodOrder.class));
/*    */     
/* 54 */     Method[] methods = clazz.getDeclaredMethods();
/* 55 */     if (comparator != null) {
/* 56 */       Arrays.sort(methods, comparator);
/*    */     }
/*    */     
/* 59 */     return methods;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private static Comparator<Method> getSorter(FixMethodOrder fixMethodOrder)
/*    */   {
/* 66 */     if (fixMethodOrder == null) {
/* 67 */       return DEFAULT;
/*    */     }
/*    */     
/* 70 */     return fixMethodOrder.value().getComparator();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/MethodSorter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */