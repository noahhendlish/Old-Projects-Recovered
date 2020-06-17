/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringEndsWith
/*    */   extends SubstringMatcher
/*    */ {
/*    */   public StringEndsWith(String substring)
/*    */   {
/* 13 */     super(substring);
/*    */   }
/*    */   
/*    */   protected boolean evalSubstringOf(String s)
/*    */   {
/* 18 */     return s.endsWith(this.substring);
/*    */   }
/*    */   
/*    */   protected String relationship()
/*    */   {
/* 23 */     return "ending with";
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
/*    */   public static Matcher<String> endsWith(String suffix)
/*    */   {
/* 38 */     return new StringEndsWith(suffix);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/StringEndsWith.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */