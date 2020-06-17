/*    */ package org.junit.internal.runners.model;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ @Deprecated
/*    */ public class MultipleFailureException extends org.junit.runners.model.MultipleFailureException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public MultipleFailureException(List<Throwable> errors) {
/* 10 */     super(errors);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/model/MultipleFailureException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */