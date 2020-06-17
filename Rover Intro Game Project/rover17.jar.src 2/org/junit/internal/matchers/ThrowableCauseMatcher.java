/*    */ package org.junit.internal.matchers;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.TypeSafeMatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThrowableCauseMatcher<T extends Throwable>
/*    */   extends TypeSafeMatcher<T>
/*    */ {
/*    */   private final Matcher<? extends Throwable> causeMatcher;
/*    */   
/*    */   public ThrowableCauseMatcher(Matcher<? extends Throwable> causeMatcher)
/*    */   {
/* 20 */     this.causeMatcher = causeMatcher;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description) {
/* 24 */     description.appendText("exception with cause ");
/* 25 */     description.appendDescriptionOf(this.causeMatcher);
/*    */   }
/*    */   
/*    */   protected boolean matchesSafely(T item)
/*    */   {
/* 30 */     return this.causeMatcher.matches(item.getCause());
/*    */   }
/*    */   
/*    */   protected void describeMismatchSafely(T item, Description description)
/*    */   {
/* 35 */     description.appendText("cause ");
/* 36 */     this.causeMatcher.describeMismatch(item.getCause(), description);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static <T extends Throwable> Matcher<T> hasCause(Matcher<? extends Throwable> matcher)
/*    */   {
/* 48 */     return new ThrowableCauseMatcher(matcher);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/matchers/ThrowableCauseMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */