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
/*    */ public class IsSame<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final T object;
/*    */   
/*    */   public IsSame(T object)
/*    */   {
/* 18 */     this.object = object;
/*    */   }
/*    */   
/*    */   public boolean matches(Object arg)
/*    */   {
/* 23 */     return arg == this.object;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description)
/*    */   {
/* 28 */     description.appendText("sameInstance(").appendValue(this.object).appendText(")");
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
/*    */   public static <T> Matcher<T> sameInstance(T target)
/*    */   {
/* 42 */     return new IsSame(target);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @Factory
/*    */   public static <T> Matcher<T> theInstance(T target)
/*    */   {
/* 54 */     return new IsSame(target);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/IsSame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */