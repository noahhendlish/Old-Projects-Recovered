/*    */ package org.hamcrest.internal;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ 
/*    */ public class SelfDescribingValue<T> implements org.hamcrest.SelfDescribing
/*    */ {
/*    */   private T value;
/*    */   
/*    */   public SelfDescribingValue(T value) {
/* 10 */     this.value = value;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description)
/*    */   {
/* 15 */     description.appendValue(this.value);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/internal/SelfDescribingValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */