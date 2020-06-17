/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ public class CombinableMatcher<T> extends org.hamcrest.TypeSafeDiagnosingMatcher<T>
/*    */ {
/*    */   private final Matcher<? super T> matcher;
/*    */   
/*    */   public CombinableMatcher(Matcher<? super T> matcher)
/*    */   {
/* 11 */     this.matcher = matcher;
/*    */   }
/*    */   
/*    */   protected boolean matchesSafely(T item, org.hamcrest.Description mismatch)
/*    */   {
/* 16 */     if (!this.matcher.matches(item)) {
/* 17 */       this.matcher.describeMismatch(item, mismatch);
/* 18 */       return false;
/*    */     }
/* 20 */     return true;
/*    */   }
/*    */   
/*    */   public void describeTo(org.hamcrest.Description description)
/*    */   {
/* 25 */     description.appendDescriptionOf(this.matcher);
/*    */   }
/*    */   
/*    */   public CombinableMatcher<T> and(Matcher<? super T> other) {
/* 29 */     return new CombinableMatcher(new AllOf(templatedListWith(other)));
/*    */   }
/*    */   
/*    */   public CombinableMatcher<T> or(Matcher<? super T> other) {
/* 33 */     return new CombinableMatcher(new AnyOf(templatedListWith(other)));
/*    */   }
/*    */   
/*    */   private java.util.ArrayList<Matcher<? super T>> templatedListWith(Matcher<? super T> other) {
/* 37 */     java.util.ArrayList<Matcher<? super T>> matchers = new java.util.ArrayList();
/* 38 */     matchers.add(this.matcher);
/* 39 */     matchers.add(other);
/* 40 */     return matchers;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @org.hamcrest.Factory
/*    */   public static <LHS> CombinableBothMatcher<LHS> both(Matcher<? super LHS> matcher)
/*    */   {
/* 51 */     return new CombinableBothMatcher(matcher);
/*    */   }
/*    */   
/*    */   public static final class CombinableBothMatcher<X> {
/*    */     private final Matcher<? super X> first;
/*    */     
/* 57 */     public CombinableBothMatcher(Matcher<? super X> matcher) { this.first = matcher; }
/*    */     
/*    */     public CombinableMatcher<X> and(Matcher<? super X> other) {
/* 60 */       return new CombinableMatcher(this.first).and(other);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @org.hamcrest.Factory
/*    */   public static <LHS> CombinableEitherMatcher<LHS> either(Matcher<? super LHS> matcher)
/*    */   {
/* 72 */     return new CombinableEitherMatcher(matcher);
/*    */   }
/*    */   
/*    */   public static final class CombinableEitherMatcher<X> {
/*    */     private final Matcher<? super X> first;
/*    */     
/* 78 */     public CombinableEitherMatcher(Matcher<? super X> matcher) { this.first = matcher; }
/*    */     
/*    */     public CombinableMatcher<X> or(Matcher<? super X> other) {
/* 81 */       return new CombinableMatcher(this.first).or(other);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/CombinableMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */