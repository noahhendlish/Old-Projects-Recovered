/*     */ package org.junit;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.hamcrest.CoreMatchers;
/*     */ import org.hamcrest.Matcher;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Assume
/*     */ {
/*     */   public static void assumeTrue(boolean b)
/*     */   {
/*  41 */     assumeThat(Boolean.valueOf(b), CoreMatchers.is(Boolean.valueOf(true)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assumeFalse(boolean b)
/*     */   {
/*  48 */     assumeTrue(!b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assumeTrue(String message, boolean b)
/*     */   {
/*  59 */     if (!b) { throw new AssumptionViolatedException(message);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void assumeFalse(String message, boolean b)
/*     */   {
/*  66 */     assumeTrue(message, !b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assumeNotNull(Object... objects)
/*     */   {
/*  73 */     assumeThat(Arrays.asList(objects), CoreMatchers.everyItem(CoreMatchers.notNullValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> void assumeThat(T actual, Matcher<T> matcher)
/*     */   {
/*  94 */     if (!matcher.matches(actual)) {
/*  95 */       throw new AssumptionViolatedException(actual, matcher);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> void assumeThat(String message, T actual, Matcher<T> matcher)
/*     */   {
/* 117 */     if (!matcher.matches(actual)) {
/* 118 */       throw new AssumptionViolatedException(message, actual, matcher);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assumeNoException(Throwable e)
/*     */   {
/* 142 */     assumeThat(e, CoreMatchers.nullValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assumeNoException(String message, Throwable e)
/*     */   {
/* 156 */     assumeThat(message, e, CoreMatchers.nullValue());
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/Assume.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */