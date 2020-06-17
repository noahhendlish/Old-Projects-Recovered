/*    */ package org.junit.internal.matchers;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.TypeSafeMatcher;
/*    */ 
/*    */ public class ThrowableMessageMatcher<T extends Throwable> extends TypeSafeMatcher<T>
/*    */ {
/*    */   private final Matcher<String> matcher;
/*    */   
/*    */   public ThrowableMessageMatcher(Matcher<String> matcher)
/*    */   {
/* 14 */     this.matcher = matcher;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description) {
/* 18 */     description.appendText("exception with message ");
/* 19 */     description.appendDescriptionOf(this.matcher);
/*    */   }
/*    */   
/*    */   protected boolean matchesSafely(T item)
/*    */   {
/* 24 */     return this.matcher.matches(item.getMessage());
/*    */   }
/*    */   
/*    */   protected void describeMismatchSafely(T item, Description description)
/*    */   {
/* 29 */     description.appendText("message ");
/* 30 */     this.matcher.describeMismatch(item.getMessage(), description);
/*    */   }
/*    */   
/*    */   @Factory
/*    */   public static <T extends Throwable> Matcher<T> hasMessage(Matcher<String> matcher) {
/* 35 */     return new ThrowableMessageMatcher(matcher);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/matchers/ThrowableMessageMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */