/*    */ package org.junit.rules;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.hamcrest.CoreMatchers;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.junit.matchers.JUnitMatchers;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ExpectedExceptionMatcherBuilder
/*    */ {
/* 16 */   private final List<Matcher<?>> matchers = new ArrayList();
/*    */   
/*    */   void add(Matcher<?> matcher) {
/* 19 */     this.matchers.add(matcher);
/*    */   }
/*    */   
/*    */   boolean expectsThrowable() {
/* 23 */     return !this.matchers.isEmpty();
/*    */   }
/*    */   
/*    */   Matcher<Throwable> build() {
/* 27 */     return JUnitMatchers.isThrowable(allOfTheMatchers());
/*    */   }
/*    */   
/*    */   private Matcher<Throwable> allOfTheMatchers() {
/* 31 */     if (this.matchers.size() == 1) {
/* 32 */       return cast((Matcher)this.matchers.get(0));
/*    */     }
/* 34 */     return CoreMatchers.allOf(castedMatchers());
/*    */   }
/*    */   
/*    */   private List<Matcher<? super Throwable>> castedMatchers()
/*    */   {
/* 39 */     return new ArrayList(this.matchers);
/*    */   }
/*    */   
/*    */   private Matcher<Throwable> cast(Matcher<?> singleMatcher)
/*    */   {
/* 44 */     return singleMatcher;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/ExpectedExceptionMatcherBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */