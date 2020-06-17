/*    */ package org.junit.experimental.categories;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.junit.runner.manipulation.Filter;
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
/*    */ public final class IncludeCategories
/*    */   extends CategoryFilterFactory
/*    */ {
/*    */   protected Filter createFilter(List<Class<?>> categories)
/*    */   {
/* 35 */     return new IncludesAny(categories);
/*    */   }
/*    */   
/*    */   private static class IncludesAny extends Categories.CategoryFilter {
/*    */     public IncludesAny(List<Class<?>> categories) {
/* 40 */       this(new HashSet(categories));
/*    */     }
/*    */     
/*    */     public IncludesAny(Set<Class<?>> categories) {
/* 44 */       super(categories, true, null);
/*    */     }
/*    */     
/*    */     public String describe()
/*    */     {
/* 49 */       return "includes " + super.describe();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/categories/IncludeCategories.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */