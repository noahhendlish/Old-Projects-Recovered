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
/*    */ public abstract class CustomMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final String fixedDescription;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CustomMatcher(String description)
/*    */   {
/* 27 */     if (description == null) {
/* 28 */       throw new IllegalArgumentException("Description should be non null!");
/*    */     }
/* 30 */     this.fixedDescription = description;
/*    */   }
/*    */   
/*    */   public final void describeTo(Description description)
/*    */   {
/* 35 */     description.appendText(this.fixedDescription);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/CustomMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */