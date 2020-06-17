/*    */ package org.junit.internal;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.List;
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.Result;
/*    */ import org.junit.runner.notification.Failure;
/*    */ import org.junit.runner.notification.RunListener;
/*    */ 
/*    */ public class TextListener extends RunListener
/*    */ {
/*    */   private final PrintStream writer;
/*    */   
/*    */   public TextListener(JUnitSystem system)
/*    */   {
/* 17 */     this(system.out());
/*    */   }
/*    */   
/*    */   public TextListener(PrintStream writer) {
/* 21 */     this.writer = writer;
/*    */   }
/*    */   
/*    */   public void testRunFinished(Result result)
/*    */   {
/* 26 */     printHeader(result.getRunTime());
/* 27 */     printFailures(result);
/* 28 */     printFooter(result);
/*    */   }
/*    */   
/*    */   public void testStarted(Description description)
/*    */   {
/* 33 */     this.writer.append('.');
/*    */   }
/*    */   
/*    */   public void testFailure(Failure failure)
/*    */   {
/* 38 */     this.writer.append('E');
/*    */   }
/*    */   
/*    */   public void testIgnored(Description description)
/*    */   {
/* 43 */     this.writer.append('I');
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private PrintStream getWriter()
/*    */   {
/* 51 */     return this.writer;
/*    */   }
/*    */   
/*    */   protected void printHeader(long runTime) {
/* 55 */     getWriter().println();
/* 56 */     getWriter().println("Time: " + elapsedTimeAsString(runTime));
/*    */   }
/*    */   
/*    */   protected void printFailures(Result result) {
/* 60 */     List<Failure> failures = result.getFailures();
/* 61 */     if (failures.size() == 0) {
/* 62 */       return;
/*    */     }
/* 64 */     if (failures.size() == 1) {
/* 65 */       getWriter().println("There was " + failures.size() + " failure:");
/*    */     } else {
/* 67 */       getWriter().println("There were " + failures.size() + " failures:");
/*    */     }
/* 69 */     int i = 1;
/* 70 */     for (Failure each : failures) {
/* 71 */       printFailure(each, "" + i++);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void printFailure(Failure each, String prefix) {
/* 76 */     getWriter().println(prefix + ") " + each.getTestHeader());
/* 77 */     getWriter().print(each.getTrace());
/*    */   }
/*    */   
/*    */   protected void printFooter(Result result) {
/* 81 */     if (result.wasSuccessful()) {
/* 82 */       getWriter().println();
/* 83 */       getWriter().print("OK");
/* 84 */       getWriter().println(" (" + result.getRunCount() + " test" + (result.getRunCount() == 1 ? "" : "s") + ")");
/*    */     }
/*    */     else {
/* 87 */       getWriter().println();
/* 88 */       getWriter().println("FAILURES!!!");
/* 89 */       getWriter().println("Tests run: " + result.getRunCount() + ",  Failures: " + result.getFailureCount());
/*    */     }
/* 91 */     getWriter().println();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected String elapsedTimeAsString(long runTime)
/*    */   {
/* 99 */     return NumberFormat.getInstance().format(runTime / 1000.0D);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/TextListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */