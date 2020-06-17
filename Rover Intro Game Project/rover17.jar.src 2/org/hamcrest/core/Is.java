/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Is<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final Matcher<T> matcher;
/*    */   
/*    */   public Is(Matcher<T> matcher)
/*    */   {
/* 22 */     this.matcher = matcher;
/*    */   }
/*    */   
/*    */   public boolean matches(Object arg)
/*    */   {
/* 27 */     return this.matcher.matches(arg);
/*    */   }
/*    */   
/*    */   public void describeTo(Description description)
/*    */   {
/* 32 */     description.appendText("is ").appendDescriptionOf(this.matcher);
/*    */   }
/*    */   
/*    */   public void describeMismatch(Object item, Description mismatchDescription)
/*    */   {
/* 37 */     this.matcher.describeMismatch(item, mismatchDescription);
/*    */   }
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
/*    */   @Factory
/*    */   public static <T> Matcher<T> is(Matcher<T> matcher)
/*    */   {
/* 52 */     return new Is(matcher);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static <T> Matcher<T> is(T value)
/*    */   {
/* 66 */     return is(IsEqual.equalTo(value));
/*    */   }
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
/*    */   @Factory
/*    */   @Deprecated
/*    */   public static <T> Matcher<T> is(Class<T> type)
/*    */   {
/* 82 */     Matcher<T> typeMatcher = IsInstanceOf.instanceOf(type);
/* 83 */     return is(typeMatcher);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static <T> Matcher<T> isA(Class<T> type)
/*    */   {
/* 97 */     Matcher<T> typeMatcher = IsInstanceOf.instanceOf(type);
/* 98 */     return is(typeMatcher);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/Is.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */