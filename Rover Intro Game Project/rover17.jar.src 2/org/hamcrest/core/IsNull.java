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
/*    */ public class IsNull<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   public boolean matches(Object o)
/*    */   {
/* 17 */     return o == null;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description)
/*    */   {
/* 22 */     description.appendText("null");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static Matcher<Object> nullValue()
/*    */   {
/* 34 */     return new IsNull();
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
/*    */   public static Matcher<Object> notNullValue()
/*    */   {
/* 48 */     return IsNot.not(nullValue());
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
/*    */   public static <T> Matcher<T> nullValue(Class<T> type)
/*    */   {
/* 63 */     return new IsNull();
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
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static <T> Matcher<T> notNullValue(Class<T> type)
/*    */   {
/* 81 */     return IsNot.not(nullValue(type));
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/IsNull.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */