/*    */ package org.junit.experimental.results;
/*    */ 
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.TypeSafeMatcher;
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
/*    */ public class ResultMatchers
/*    */ {
/*    */   public static Matcher<PrintableResult> isSuccessful()
/*    */   {
/* 21 */     return failureCountIs(0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static Matcher<PrintableResult> failureCountIs(int count)
/*    */   {
/* 28 */     new TypeSafeMatcher() {
/*    */       public void describeTo(Description description) {
/* 30 */         description.appendText("has " + this.val$count + " failures");
/*    */       }
/*    */       
/*    */       public boolean matchesSafely(PrintableResult item)
/*    */       {
/* 35 */         return item.failureCount() == this.val$count;
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static Matcher<Object> hasSingleFailureContaining(String string)
/*    */   {
/* 44 */     new BaseMatcher() {
/*    */       public boolean matches(Object item) {
/* 46 */         return (item.toString().contains(this.val$string)) && (ResultMatchers.failureCountIs(1).matches(item));
/*    */       }
/*    */       
/*    */       public void describeTo(Description description) {
/* 50 */         description.appendText("has single failure containing " + this.val$string);
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Matcher<PrintableResult> hasFailureContaining(String string)
/*    */   {
/* 60 */     new BaseMatcher() {
/*    */       public boolean matches(Object item) {
/* 62 */         return item.toString().contains(this.val$string);
/*    */       }
/*    */       
/*    */       public void describeTo(Description description) {
/* 66 */         description.appendText("has failure containing " + this.val$string);
/*    */       }
/*    */     };
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/results/ResultMatchers.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */