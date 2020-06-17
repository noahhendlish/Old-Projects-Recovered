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
/*    */ public final class ExcludeCategories
/*    */   extends CategoryFilterFactory
/*    */ {
/*    */   protected Filter createFilter(List<Class<?>> categories)
/*    */   {
/* 35 */     return new ExcludesAny(categories);
/*    */   }
/*    */   
/*    */   private static class ExcludesAny extends Categories.CategoryFilter {
/*    */     public ExcludesAny(List<Class<?>> categories) {
/* 40 */       this(new HashSet(categories));
/*    */     }
/*    */     
/*    */     public ExcludesAny(Set<Class<?>> categories) {
/* 44 */       super(null, true, categories);
/*    */     }
/*    */     
/*    */     public String describe()
/*    */     {
/* 49 */       return "excludes " + super.describe();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/categories/ExcludeCategories.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */