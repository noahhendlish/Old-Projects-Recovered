/*    */ package org.hamcrest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DiagnosingMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   public final boolean matches(Object item)
/*    */   {
/* 12 */     return matches(item, Description.NONE);
/*    */   }
/*    */   
/*    */   public final void describeMismatch(Object item, Description mismatchDescription)
/*    */   {
/* 17 */     matches(item, mismatchDescription);
/*    */   }
/*    */   
/*    */   protected abstract boolean matches(Object paramObject, Description paramDescription);
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/DiagnosingMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */