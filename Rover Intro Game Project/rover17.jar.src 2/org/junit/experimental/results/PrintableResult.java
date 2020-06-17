/*    */ package org.junit.experimental.results;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import org.junit.internal.TextListener;
/*    */ import org.junit.runner.JUnitCore;
/*    */ import org.junit.runner.Request;
/*    */ import org.junit.runner.Result;
/*    */ import org.junit.runner.notification.Failure;
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
/*    */ public class PrintableResult
/*    */ {
/*    */   private Result result;
/*    */   
/*    */   public static PrintableResult testResult(Class<?> type)
/*    */   {
/* 29 */     return testResult(Request.aClass(type));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static PrintableResult testResult(Request request)
/*    */   {
/* 36 */     return new PrintableResult(new JUnitCore().run(request));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public PrintableResult(List<Failure> failures)
/*    */   {
/* 43 */     this(new FailureList(failures).result());
/*    */   }
/*    */   
/*    */   private PrintableResult(Result result) {
/* 47 */     this.result = result;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int failureCount()
/*    */   {
/* 54 */     return this.result.getFailures().size();
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 59 */     ByteArrayOutputStream stream = new ByteArrayOutputStream();
/* 60 */     new TextListener(new PrintStream(stream)).testRunFinished(this.result);
/* 61 */     return stream.toString();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/results/PrintableResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */