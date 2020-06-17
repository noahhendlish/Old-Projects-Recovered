/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ abstract class ShortcutCombination<T> extends org.hamcrest.BaseMatcher<T>
/*    */ {
/*    */   private final Iterable<Matcher<? super T>> matchers;
/*    */   
/*    */   public ShortcutCombination(Iterable<Matcher<? super T>> matchers)
/*    */   {
/* 12 */     this.matchers = matchers;
/*    */   }
/*    */   
/*    */ 
/*    */   public abstract boolean matches(Object paramObject);
/*    */   
/*    */   public abstract void describeTo(Description paramDescription);
/*    */   
/*    */   protected boolean matches(Object o, boolean shortcut)
/*    */   {
/* 22 */     for (Matcher<? super T> matcher : this.matchers) {
/* 23 */       if (matcher.matches(o) == shortcut) {
/* 24 */         return shortcut;
/*    */       }
/*    */     }
/* 27 */     return !shortcut;
/*    */   }
/*    */   
/*    */   public void describeTo(Description description, String operator) {
/* 31 */     description.appendList("(", " " + operator + " ", ")", this.matchers);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/ShortcutCombination.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */