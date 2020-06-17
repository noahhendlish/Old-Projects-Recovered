/*    */ package org.hamcrest;
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
/*    */ public abstract class Condition<T>
/*    */ {
/* 14 */   public static final NotMatched<Object> NOT_MATCHED = new NotMatched(null);
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract boolean matching(Matcher<T> paramMatcher, String paramString);
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract <U> Condition<U> and(Step<? super T, U> paramStep);
/*    */   
/*    */ 
/* 25 */   public final boolean matching(Matcher<T> match) { return matching(match, ""); }
/* 26 */   public final <U> Condition<U> then(Step<? super T, U> mapping) { return and(mapping); }
/*    */   
/*    */   public static <T> Condition<T> notMatched()
/*    */   {
/* 30 */     return NOT_MATCHED;
/*    */   }
/*    */   
/*    */ 
/* 34 */   public static <T> Condition<T> matched(T theValue, Description mismatch) { return new Matched(theValue, mismatch, null); }
/*    */   
/*    */   private static final class Matched<T> extends Condition<T> {
/*    */     private final T theValue;
/*    */     private final Description mismatch;
/*    */     
/*    */     private Matched(T theValue, Description mismatch) {
/* 41 */       super();
/* 42 */       this.theValue = theValue;
/* 43 */       this.mismatch = mismatch;
/*    */     }
/*    */     
/*    */     public boolean matching(Matcher<T> matcher, String message)
/*    */     {
/* 48 */       if (matcher.matches(this.theValue)) {
/* 49 */         return true;
/*    */       }
/* 51 */       this.mismatch.appendText(message);
/* 52 */       matcher.describeMismatch(this.theValue, this.mismatch);
/* 53 */       return false;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 58 */     public <U> Condition<U> and(Condition.Step<? super T, U> next) { return next.apply(this.theValue, this.mismatch); }
/*    */   }
/*    */   
/*    */   private static final class NotMatched<T> extends Condition<T> {
/* 62 */     private NotMatched() { super(); }
/* 63 */     public boolean matching(Matcher<T> match, String message) { return false; }
/*    */     
/*    */     public <U> Condition<U> and(Condition.Step<? super T, U> mapping) {
/* 66 */       return notMatched();
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract interface Step<I, O>
/*    */   {
/*    */     public abstract Condition<O> apply(I paramI, Description paramDescription);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/Condition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */