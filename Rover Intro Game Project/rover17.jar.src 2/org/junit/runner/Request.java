/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
/*     */ import org.junit.internal.requests.ClassRequest;
/*     */ import org.junit.internal.requests.FilterRequest;
/*     */ import org.junit.internal.requests.SortingRequest;
/*     */ import org.junit.internal.runners.ErrorReportingRunner;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Request
/*     */ {
/*     */   public static Request method(Class<?> clazz, String methodName)
/*     */   {
/*  38 */     Description method = Description.createTestDescription(clazz, methodName);
/*  39 */     return aClass(clazz).filterWith(method);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Request aClass(Class<?> clazz)
/*     */   {
/*  50 */     return new ClassRequest(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Request classWithoutSuiteMethod(Class<?> clazz)
/*     */   {
/*  61 */     return new ClassRequest(clazz, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Request classes(Computer computer, Class<?>... classes)
/*     */   {
/*     */     try
/*     */     {
/*  74 */       AllDefaultPossibilitiesBuilder builder = new AllDefaultPossibilitiesBuilder(true);
/*  75 */       Runner suite = computer.getSuite(builder, classes);
/*  76 */       return runner(suite);
/*     */     } catch (InitializationError e) {
/*  78 */       throw new RuntimeException("Bug in saff's brain: Suite constructor, called as above, should always complete");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Request classes(Class<?>... classes)
/*     */   {
/*  91 */     return classes(JUnitCore.defaultComputer(), classes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Request errorReport(Class<?> klass, Throwable cause)
/*     */   {
/* 100 */     return runner(new ErrorReportingRunner(klass, cause));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Request runner(Runner runner)
/*     */   {
/* 108 */     new Request()
/*     */     {
/*     */       public Runner getRunner() {
/* 111 */         return this.val$runner;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract Runner getRunner();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Request filterWith(Filter filter)
/*     */   {
/* 131 */     return new FilterRequest(this, filter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Request filterWith(Description desiredDescription)
/*     */   {
/* 142 */     return filterWith(Filter.matchMethodDescription(desiredDescription));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Request sortWith(Comparator<Description> comparator)
/*     */   {
/* 168 */     return new SortingRequest(this, comparator);
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/Request.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */