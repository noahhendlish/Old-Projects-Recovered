/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringStartsWith
/*    */   extends SubstringMatcher
/*    */ {
/*    */   public StringStartsWith(String substring)
/*    */   {
/* 13 */     super(substring);
/*    */   }
/*    */   
/*    */   protected boolean evalSubstringOf(String s)
/*    */   {
/* 18 */     return s.startsWith(this.substring);
/*    */   }
/*    */   
/*    */   protected String relationship()
/*    */   {
/* 23 */     return "starting with";
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
/*    */   public static Matcher<String> startsWith(String prefix)
/*    */   {
/* 38 */     return new StringStartsWith(prefix);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/StringStartsWith.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */