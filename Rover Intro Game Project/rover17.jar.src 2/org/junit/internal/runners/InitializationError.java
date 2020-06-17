/*    */ package org.junit.internal.runners;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class InitializationError
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final List<Throwable> fErrors;
/*    */   
/*    */   public InitializationError(List<Throwable> errors)
/*    */   {
/* 23 */     this.fErrors = errors;
/*    */   }
/*    */   
/*    */   public InitializationError(Throwable... errors) {
/* 27 */     this(Arrays.asList(errors));
/*    */   }
/*    */   
/*    */   public InitializationError(String string) {
/* 31 */     this(new Throwable[] { new Exception(string) });
/*    */   }
/*    */   
/*    */   public List<Throwable> getCauses() {
/* 35 */     return this.fErrors;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/InitializationError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */