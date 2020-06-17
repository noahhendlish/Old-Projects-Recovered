/*    */ package org.junit.internal.matchers;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.junit.internal.MethodSorter;
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
/*    */ @Deprecated
/*    */ public abstract class TypeSafeMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private Class<?> expectedType;
/*    */   
/*    */   public abstract boolean matchesSafely(T paramT);
/*    */   
/*    */   protected TypeSafeMatcher()
/*    */   {
/* 27 */     this.expectedType = findExpectedType(getClass());
/*    */   }
/*    */   
/*    */   private static Class<?> findExpectedType(Class<?> fromClass) {
/* 31 */     for (Class<?> c = fromClass; c != Object.class; c = c.getSuperclass()) {
/* 32 */       for (Method method : MethodSorter.getDeclaredMethods(c)) {
/* 33 */         if (isMatchesSafelyMethod(method)) {
/* 34 */           return method.getParameterTypes()[0];
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 39 */     throw new Error("Cannot determine correct type for matchesSafely() method.");
/*    */   }
/*    */   
/*    */   private static boolean isMatchesSafelyMethod(Method method) {
/* 43 */     return (method.getName().equals("matchesSafely")) && (method.getParameterTypes().length == 1) && (!method.isSynthetic());
/*    */   }
/*    */   
/*    */ 
/*    */   protected TypeSafeMatcher(Class<T> expectedType)
/*    */   {
/* 49 */     this.expectedType = expectedType;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final boolean matches(Object item)
/*    */   {
/* 59 */     return (item != null) && (this.expectedType.isInstance(item)) && (matchesSafely(item));
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/matchers/TypeSafeMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */