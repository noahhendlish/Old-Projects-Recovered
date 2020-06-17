/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ public class Every<T> extends org.hamcrest.TypeSafeDiagnosingMatcher<Iterable<T>>
/*    */ {
/*    */   private final Matcher<? super T> matcher;
/*    */   
/*    */   public Every(Matcher<? super T> matcher)
/*    */   {
/* 12 */     this.matcher = matcher;
/*    */   }
/*    */   
/*    */   public boolean matchesSafely(Iterable<T> collection, Description mismatchDescription)
/*    */   {
/* 17 */     for (T t : collection) {
/* 18 */       if (!this.matcher.matches(t)) {
/* 19 */         mismatchDescription.appendText("an item ");
/* 20 */         this.matcher.describeMismatch(t, mismatchDescription);
/* 21 */         return false;
/*    */       }
/*    */     }
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description)
/*    */   {
/* 29 */     description.appendText("every item is ").appendDescriptionOf(this.matcher);
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
/*    */ 
/*    */   @org.hamcrest.Factory
/*    */   public static <U> Matcher<Iterable<U>> everyItem(Matcher<U> itemMatcher)
/*    */   {
/* 45 */     return new Every(itemMatcher);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/Every.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */