/*     */ package junit.textui;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Enumeration;
/*     */ import junit.framework.AssertionFailedError;
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestFailure;
/*     */ import junit.framework.TestListener;
/*     */ import junit.framework.TestResult;
/*     */ import junit.runner.BaseTestRunner;
/*     */ 
/*     */ public class ResultPrinter implements TestListener
/*     */ {
/*     */   PrintStream fWriter;
/*  16 */   int fColumn = 0;
/*     */   
/*     */   public ResultPrinter(PrintStream writer) {
/*  19 */     this.fWriter = writer;
/*     */   }
/*     */   
/*     */ 
/*     */   synchronized void print(TestResult result, long runTime)
/*     */   {
/*  25 */     printHeader(runTime);
/*  26 */     printErrors(result);
/*  27 */     printFailures(result);
/*  28 */     printFooter(result);
/*     */   }
/*     */   
/*     */   void printWaitPrompt() {
/*  32 */     getWriter().println();
/*  33 */     getWriter().println("<RETURN> to continue");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void printHeader(long runTime)
/*     */   {
/*  39 */     getWriter().println();
/*  40 */     getWriter().println("Time: " + elapsedTimeAsString(runTime));
/*     */   }
/*     */   
/*     */   protected void printErrors(TestResult result) {
/*  44 */     printDefects(result.errors(), result.errorCount(), "error");
/*     */   }
/*     */   
/*     */   protected void printFailures(TestResult result) {
/*  48 */     printDefects(result.failures(), result.failureCount(), "failure");
/*     */   }
/*     */   
/*     */   protected void printDefects(Enumeration<TestFailure> booBoos, int count, String type) {
/*  52 */     if (count == 0) return;
/*  53 */     if (count == 1) {
/*  54 */       getWriter().println("There was " + count + " " + type + ":");
/*     */     } else {
/*  56 */       getWriter().println("There were " + count + " " + type + "s:");
/*     */     }
/*  58 */     for (int i = 1; booBoos.hasMoreElements(); i++) {
/*  59 */       printDefect((TestFailure)booBoos.nextElement(), i);
/*     */     }
/*     */   }
/*     */   
/*     */   public void printDefect(TestFailure booBoo, int count) {
/*  64 */     printDefectHeader(booBoo, count);
/*  65 */     printDefectTrace(booBoo);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void printDefectHeader(TestFailure booBoo, int count)
/*     */   {
/*  71 */     getWriter().print(count + ") " + booBoo.failedTest());
/*     */   }
/*     */   
/*     */   protected void printDefectTrace(TestFailure booBoo) {
/*  75 */     getWriter().print(BaseTestRunner.getFilteredTrace(booBoo.trace()));
/*     */   }
/*     */   
/*     */   protected void printFooter(TestResult result) {
/*  79 */     if (result.wasSuccessful()) {
/*  80 */       getWriter().println();
/*  81 */       getWriter().print("OK");
/*  82 */       getWriter().println(" (" + result.runCount() + " test" + (result.runCount() == 1 ? "" : "s") + ")");
/*     */     }
/*     */     else {
/*  85 */       getWriter().println();
/*  86 */       getWriter().println("FAILURES!!!");
/*  87 */       getWriter().println("Tests run: " + result.runCount() + ",  Failures: " + result.failureCount() + ",  Errors: " + result.errorCount());
/*     */     }
/*     */     
/*     */ 
/*  91 */     getWriter().println();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String elapsedTimeAsString(long runTime)
/*     */   {
/*  99 */     return NumberFormat.getInstance().format(runTime / 1000.0D);
/*     */   }
/*     */   
/*     */   public PrintStream getWriter() {
/* 103 */     return this.fWriter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addError(Test test, Throwable e)
/*     */   {
/* 110 */     getWriter().print("E");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addFailure(Test test, AssertionFailedError t)
/*     */   {
/* 117 */     getWriter().print("F");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void endTest(Test test) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void startTest(Test test)
/*     */   {
/* 130 */     getWriter().print(".");
/* 131 */     if (this.fColumn++ >= 40) {
/* 132 */       getWriter().println();
/* 133 */       this.fColumn = 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/textui/ResultPrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */