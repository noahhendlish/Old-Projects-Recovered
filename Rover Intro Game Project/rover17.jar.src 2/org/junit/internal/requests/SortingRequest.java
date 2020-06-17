/*    */ package org.junit.internal.requests;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.Request;
/*    */ import org.junit.runner.manipulation.Sorter;
/*    */ 
/*    */ public class SortingRequest extends Request
/*    */ {
/*    */   private final Request request;
/*    */   private final Comparator<Description> comparator;
/*    */   
/*    */   public SortingRequest(Request request, Comparator<Description> comparator)
/*    */   {
/* 15 */     this.request = request;
/* 16 */     this.comparator = comparator;
/*    */   }
/*    */   
/*    */   public org.junit.runner.Runner getRunner()
/*    */   {
/* 21 */     org.junit.runner.Runner runner = this.request.getRunner();
/* 22 */     new Sorter(this.comparator).apply(runner);
/* 23 */     return runner;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/requests/SortingRequest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */