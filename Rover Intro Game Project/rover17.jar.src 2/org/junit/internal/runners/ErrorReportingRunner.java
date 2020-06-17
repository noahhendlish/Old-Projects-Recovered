/*    */ package org.junit.internal.runners;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runner.notification.Failure;
/*    */ import org.junit.runner.notification.RunNotifier;
/*    */ 
/*    */ public class ErrorReportingRunner
/*    */   extends Runner
/*    */ {
/*    */   private final List<Throwable> causes;
/*    */   private final Class<?> testClass;
/*    */   
/*    */   public ErrorReportingRunner(Class<?> testClass, Throwable cause)
/*    */   {
/* 19 */     if (testClass == null) {
/* 20 */       throw new NullPointerException("Test class cannot be null");
/*    */     }
/* 22 */     this.testClass = testClass;
/* 23 */     this.causes = getCauses(cause);
/*    */   }
/*    */   
/*    */   public Description getDescription()
/*    */   {
/* 28 */     Description description = Description.createSuiteDescription(this.testClass);
/* 29 */     for (Throwable each : this.causes) {
/* 30 */       description.addChild(describeCause(each));
/*    */     }
/* 32 */     return description;
/*    */   }
/*    */   
/*    */   public void run(RunNotifier notifier)
/*    */   {
/* 37 */     for (Throwable each : this.causes) {
/* 38 */       runCause(each, notifier);
/*    */     }
/*    */   }
/*    */   
/*    */   private List<Throwable> getCauses(Throwable cause)
/*    */   {
/* 44 */     if ((cause instanceof InvocationTargetException)) {
/* 45 */       return getCauses(cause.getCause());
/*    */     }
/* 47 */     if ((cause instanceof org.junit.runners.model.InitializationError)) {
/* 48 */       return ((org.junit.runners.model.InitializationError)cause).getCauses();
/*    */     }
/* 50 */     if ((cause instanceof InitializationError)) {
/* 51 */       return ((InitializationError)cause).getCauses();
/*    */     }
/*    */     
/* 54 */     return Arrays.asList(new Throwable[] { cause });
/*    */   }
/*    */   
/*    */   private Description describeCause(Throwable child) {
/* 58 */     return Description.createTestDescription(this.testClass, "initializationError");
/*    */   }
/*    */   
/*    */   private void runCause(Throwable child, RunNotifier notifier)
/*    */   {
/* 63 */     Description description = describeCause(child);
/* 64 */     notifier.fireTestStarted(description);
/* 65 */     notifier.fireTestFailure(new Failure(description, child));
/* 66 */     notifier.fireTestFinished(description);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/ErrorReportingRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */