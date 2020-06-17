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
/*    */ public class IsAnything<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final String message;
/*    */   
/*    */   public IsAnything()
/*    */   {
/* 19 */     this("ANYTHING");
/*    */   }
/*    */   
/*    */   public IsAnything(String message) {
/* 23 */     this.message = message;
/*    */   }
/*    */   
/*    */   public boolean matches(Object o)
/*    */   {
/* 28 */     return true;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description)
/*    */   {
/* 33 */     description.appendText(this.message);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static Matcher<Object> anything()
/*    */   {
/* 41 */     return new IsAnything();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static Matcher<Object> anything(String description)
/*    */   {
/* 53 */     return new IsAnything(description);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/IsAnything.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */