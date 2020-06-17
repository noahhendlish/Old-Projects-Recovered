/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.junit.internal.Throwables;
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
/*    */ public class MultipleFailureException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final List<Throwable> fErrors;
/*    */   
/*    */   public MultipleFailureException(List<Throwable> errors)
/*    */   {
/* 25 */     this.fErrors = new ArrayList(errors);
/*    */   }
/*    */   
/*    */   public List<Throwable> getFailures() {
/* 29 */     return Collections.unmodifiableList(this.fErrors);
/*    */   }
/*    */   
/*    */   public String getMessage()
/*    */   {
/* 34 */     StringBuilder sb = new StringBuilder(String.format("There were %d errors:", new Object[] { Integer.valueOf(this.fErrors.size()) }));
/*    */     
/* 36 */     for (Throwable e : this.fErrors) {
/* 37 */       sb.append(String.format("\n  %s(%s)", new Object[] { e.getClass().getName(), e.getMessage() }));
/*    */     }
/* 39 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void assertEmpty(List<Throwable> errors)
/*    */     throws Exception
/*    */   {
/* 53 */     if (errors.isEmpty()) {
/* 54 */       return;
/*    */     }
/* 56 */     if (errors.size() == 1) {
/* 57 */       throw Throwables.rethrowAsException((Throwable)errors.get(0));
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 67 */     throw new org.junit.internal.runners.model.MultipleFailureException(errors);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/model/MultipleFailureException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */