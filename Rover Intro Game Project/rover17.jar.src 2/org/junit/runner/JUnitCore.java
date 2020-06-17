/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import junit.framework.Test;
/*     */ import junit.runner.Version;
/*     */ import org.junit.internal.JUnitSystem;
/*     */ import org.junit.internal.RealSystem;
/*     */ import org.junit.internal.TextListener;
/*     */ import org.junit.internal.runners.JUnit38ClassRunner;
/*     */ import org.junit.runner.notification.RunListener;
/*     */ import org.junit.runner.notification.RunNotifier;
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
/*     */ public class JUnitCore
/*     */ {
/*  25 */   private final RunNotifier notifier = new RunNotifier();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String... args)
/*     */   {
/*  36 */     Result result = new JUnitCore().runMain(new RealSystem(), args);
/*  37 */     System.exit(result.wasSuccessful() ? 0 : 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Result runClasses(Class<?>... classes)
/*     */   {
/*  49 */     return runClasses(defaultComputer(), classes);
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
/*     */   public static Result runClasses(Computer computer, Class<?>... classes)
/*     */   {
/*  62 */     return new JUnitCore().run(computer, classes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   Result runMain(JUnitSystem system, String... args)
/*     */   {
/*  70 */     system.out().println("JUnit version " + Version.id());
/*     */     
/*  72 */     JUnitCommandLineParseResult jUnitCommandLineParseResult = JUnitCommandLineParseResult.parse(args);
/*     */     
/*  74 */     RunListener listener = new TextListener(system);
/*  75 */     addListener(listener);
/*     */     
/*  77 */     return run(jUnitCommandLineParseResult.createRequest(defaultComputer()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getVersion()
/*     */   {
/*  84 */     return Version.id();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result run(Class<?>... classes)
/*     */   {
/*  94 */     return run(defaultComputer(), classes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result run(Computer computer, Class<?>... classes)
/*     */   {
/* 105 */     return run(Request.classes(computer, classes));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result run(Request request)
/*     */   {
/* 115 */     return run(request.getRunner());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result run(Test test)
/*     */   {
/* 125 */     return run(new JUnit38ClassRunner(test));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Result run(Runner runner)
/*     */   {
/* 132 */     Result result = new Result();
/* 133 */     RunListener listener = result.createListener();
/* 134 */     this.notifier.addFirstListener(listener);
/*     */     try {
/* 136 */       this.notifier.fireTestRunStarted(runner.getDescription());
/* 137 */       runner.run(this.notifier);
/* 138 */       this.notifier.fireTestRunFinished(result);
/*     */     } finally {
/* 140 */       removeListener(listener);
/*     */     }
/* 142 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addListener(RunListener listener)
/*     */   {
/* 152 */     this.notifier.addListener(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeListener(RunListener listener)
/*     */   {
/* 161 */     this.notifier.removeListener(listener);
/*     */   }
/*     */   
/*     */   static Computer defaultComputer() {
/* 165 */     return new Computer();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/JUnitCore.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */