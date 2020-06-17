/*    */ package org.junit.internal.runners.statements;
/*    */ 
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ public class Fail extends Statement {
/*    */   private final Throwable error;
/*    */   
/*    */   public Fail(Throwable e) {
/*  9 */     this.error = e;
/*    */   }
/*    */   
/*    */   public void evaluate() throws Throwable
/*    */   {
/* 14 */     throw this.error;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/statements/Fail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */