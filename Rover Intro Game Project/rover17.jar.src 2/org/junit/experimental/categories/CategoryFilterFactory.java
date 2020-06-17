/*    */ package org.junit.experimental.categories;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.junit.internal.Classes;
/*    */ import org.junit.runner.FilterFactory;
/*    */ import org.junit.runner.FilterFactory.FilterNotCreatedException;
/*    */ import org.junit.runner.FilterFactoryParams;
/*    */ import org.junit.runner.manipulation.Filter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class CategoryFilterFactory
/*    */   implements FilterFactory
/*    */ {
/*    */   public Filter createFilter(FilterFactoryParams params)
/*    */     throws FilterFactory.FilterNotCreatedException
/*    */   {
/*    */     try
/*    */     {
/* 23 */       return createFilter(parseCategories(params.getArgs()));
/*    */     } catch (ClassNotFoundException e) {
/* 25 */       throw new FilterFactory.FilterNotCreatedException(e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected abstract Filter createFilter(List<Class<?>> paramList);
/*    */   
/*    */ 
/*    */   private List<Class<?>> parseCategories(String categories)
/*    */     throws ClassNotFoundException
/*    */   {
/* 37 */     List<Class<?>> categoryClasses = new ArrayList();
/*    */     
/* 39 */     for (String category : categories.split(",")) {
/* 40 */       Class<?> categoryClass = Classes.getClass(category);
/*    */       
/* 42 */       categoryClasses.add(categoryClass);
/*    */     }
/*    */     
/* 45 */     return categoryClasses;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/categories/CategoryFilterFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */