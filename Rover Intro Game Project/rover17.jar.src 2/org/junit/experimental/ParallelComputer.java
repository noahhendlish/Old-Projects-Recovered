/*    */ package org.junit.experimental;
/*    */ 
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.junit.runner.Computer;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.ParentRunner;
/*    */ import org.junit.runners.model.InitializationError;
/*    */ import org.junit.runners.model.RunnerBuilder;
/*    */ import org.junit.runners.model.RunnerScheduler;
/*    */ 
/*    */ public class ParallelComputer extends Computer
/*    */ {
/*    */   private final boolean classes;
/*    */   private final boolean methods;
/*    */   
/*    */   public ParallelComputer(boolean classes, boolean methods)
/*    */   {
/* 20 */     this.classes = classes;
/* 21 */     this.methods = methods;
/*    */   }
/*    */   
/*    */   public static Computer classes() {
/* 25 */     return new ParallelComputer(true, false);
/*    */   }
/*    */   
/*    */   public static Computer methods() {
/* 29 */     return new ParallelComputer(false, true);
/*    */   }
/*    */   
/*    */   private static Runner parallelize(Runner runner) {
/* 33 */     if ((runner instanceof ParentRunner)) {
/* 34 */       ((ParentRunner)runner).setScheduler(new RunnerScheduler() {
/* 35 */         private final ExecutorService fService = Executors.newCachedThreadPool();
/*    */         
/*    */         public void schedule(Runnable childStatement) {
/* 38 */           this.fService.submit(childStatement);
/*    */         }
/*    */         
/*    */         public void finished() {
/*    */           try {
/* 43 */             this.fService.shutdown();
/* 44 */             this.fService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
/*    */           } catch (InterruptedException e) {
/* 46 */             e.printStackTrace(System.err);
/*    */           }
/*    */         }
/*    */       });
/*    */     }
/* 51 */     return runner;
/*    */   }
/*    */   
/*    */   public Runner getSuite(RunnerBuilder builder, Class<?>[] classes)
/*    */     throws InitializationError
/*    */   {
/* 57 */     Runner suite = super.getSuite(builder, classes);
/* 58 */     return this.classes ? parallelize(suite) : suite;
/*    */   }
/*    */   
/*    */   protected Runner getRunner(RunnerBuilder builder, Class<?> testClass)
/*    */     throws Throwable
/*    */   {
/* 64 */     Runner runner = super.getRunner(builder, testClass);
/* 65 */     return this.methods ? parallelize(runner) : runner;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/ParallelComputer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */