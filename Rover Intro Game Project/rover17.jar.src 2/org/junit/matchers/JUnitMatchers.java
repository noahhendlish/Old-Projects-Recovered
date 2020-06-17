/*     */ package org.junit.matchers;
/*     */ 
/*     */ import org.hamcrest.CoreMatchers;
/*     */ import org.hamcrest.Matcher;
/*     */ import org.hamcrest.core.CombinableMatcher.CombinableBothMatcher;
/*     */ import org.hamcrest.core.CombinableMatcher.CombinableEitherMatcher;
/*     */ import org.junit.internal.matchers.StacktracePrintingMatcher;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JUnitMatchers
/*     */ {
/*     */   @Deprecated
/*     */   public static <T> Matcher<Iterable<? super T>> hasItem(T element)
/*     */   {
/*  22 */     return CoreMatchers.hasItem(element);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> elementMatcher)
/*     */   {
/*  31 */     return CoreMatchers.hasItem(elementMatcher);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static <T> Matcher<Iterable<T>> hasItems(T... elements)
/*     */   {
/*  40 */     return CoreMatchers.hasItems(elements);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... elementMatchers)
/*     */   {
/*  51 */     return CoreMatchers.hasItems(elementMatchers);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static <T> Matcher<Iterable<T>> everyItem(Matcher<T> elementMatcher)
/*     */   {
/*  60 */     return CoreMatchers.everyItem(elementMatcher);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static Matcher<String> containsString(String substring)
/*     */   {
/*  69 */     return CoreMatchers.containsString(substring);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static <T> CombinableMatcher.CombinableBothMatcher<T> both(Matcher<? super T> matcher)
/*     */   {
/*  82 */     return CoreMatchers.both(matcher);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static <T> CombinableMatcher.CombinableEitherMatcher<T> either(Matcher<? super T> matcher)
/*     */   {
/*  95 */     return CoreMatchers.either(matcher);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T extends Throwable> Matcher<T> isThrowable(Matcher<T> throwableMatcher)
/*     */   {
/* 103 */     return StacktracePrintingMatcher.isThrowable(throwableMatcher);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T extends Exception> Matcher<T> isException(Matcher<T> exceptionMatcher)
/*     */   {
/* 111 */     return StacktracePrintingMatcher.isException(exceptionMatcher);
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/matchers/JUnitMatchers.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */