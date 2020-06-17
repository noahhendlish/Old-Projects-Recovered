/*    */ package org.junit.runner;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import org.junit.internal.Classes;
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
/*    */ class FilterFactories
/*    */ {
/*    */   public static Filter createFilterFromFilterSpec(Request request, String filterSpec)
/*    */     throws FilterFactory.FilterNotCreatedException
/*    */   {
/* 23 */     Description topLevelDescription = request.getRunner().getDescription();
/*    */     String[] tuple;
/*    */     String[] tuple;
/* 26 */     if (filterSpec.contains("=")) {
/* 27 */       tuple = filterSpec.split("=", 2);
/*    */     } else {
/* 29 */       tuple = new String[] { filterSpec, "" };
/*    */     }
/*    */     
/* 32 */     return createFilter(tuple[0], new FilterFactoryParams(topLevelDescription, tuple[1]));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Filter createFilter(String filterFactoryFqcn, FilterFactoryParams params)
/*    */     throws FilterFactory.FilterNotCreatedException
/*    */   {
/* 43 */     FilterFactory filterFactory = createFilterFactory(filterFactoryFqcn);
/*    */     
/* 45 */     return filterFactory.createFilter(params);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Filter createFilter(Class<? extends FilterFactory> filterFactoryClass, FilterFactoryParams params)
/*    */     throws FilterFactory.FilterNotCreatedException
/*    */   {
/* 57 */     FilterFactory filterFactory = createFilterFactory(filterFactoryClass);
/*    */     
/* 59 */     return filterFactory.createFilter(params);
/*    */   }
/*    */   
/*    */   static FilterFactory createFilterFactory(String filterFactoryFqcn) throws FilterFactory.FilterNotCreatedException
/*    */   {
/*    */     Class<? extends FilterFactory> filterFactoryClass;
/*    */     try {
/* 66 */       filterFactoryClass = Classes.getClass(filterFactoryFqcn).asSubclass(FilterFactory.class);
/*    */     } catch (Exception e) {
/* 68 */       throw new FilterFactory.FilterNotCreatedException(e);
/*    */     }
/*    */     
/* 71 */     return createFilterFactory(filterFactoryClass);
/*    */   }
/*    */   
/*    */   static FilterFactory createFilterFactory(Class<? extends FilterFactory> filterFactoryClass) throws FilterFactory.FilterNotCreatedException
/*    */   {
/*    */     try {
/* 77 */       return (FilterFactory)filterFactoryClass.getConstructor(new Class[0]).newInstance(new Object[0]);
/*    */     } catch (Exception e) {
/* 79 */       throw new FilterFactory.FilterNotCreatedException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/FilterFactories.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */