/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringContains
/*    */   extends SubstringMatcher
/*    */ {
/*    */   public StringContains(String substring)
/*    */   {
/* 13 */     super(substring);
/*    */   }
/*    */   
/*    */   protected boolean evalSubstringOf(String s)
/*    */   {
/* 18 */     return s.indexOf(this.substring) >= 0;
/*    */   }
/*    */   
/*    */   protected String relationship()
/*    */   {
/* 23 */     return "containing";
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
/*    */   @Factory
/*    */   public static Matcher<String> containsString(String substring)
/*    */   {
/* 39 */     return new StringContains(substring);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/StringContains.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */