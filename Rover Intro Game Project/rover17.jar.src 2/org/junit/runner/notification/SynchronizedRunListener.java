/*     */ package org.junit.runner.notification;
/*     */ 
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @RunListener.ThreadSafe
/*     */ final class SynchronizedRunListener
/*     */   extends RunListener
/*     */ {
/*     */   private final RunListener listener;
/*     */   private final Object monitor;
/*     */   
/*     */   SynchronizedRunListener(RunListener listener, Object monitor)
/*     */   {
/*  28 */     this.listener = listener;
/*  29 */     this.monitor = monitor;
/*     */   }
/*     */   
/*     */   public void testRunStarted(Description description) throws Exception
/*     */   {
/*  34 */     synchronized (this.monitor) {
/*  35 */       this.listener.testRunStarted(description);
/*     */     }
/*     */   }
/*     */   
/*     */   public void testRunFinished(Result result) throws Exception
/*     */   {
/*  41 */     synchronized (this.monitor) {
/*  42 */       this.listener.testRunFinished(result);
/*     */     }
/*     */   }
/*     */   
/*     */   public void testStarted(Description description) throws Exception
/*     */   {
/*  48 */     synchronized (this.monitor) {
/*  49 */       this.listener.testStarted(description);
/*     */     }
/*     */   }
/*     */   
/*     */   public void testFinished(Description description) throws Exception
/*     */   {
/*  55 */     synchronized (this.monitor) {
/*  56 */       this.listener.testFinished(description);
/*     */     }
/*     */   }
/*     */   
/*     */   public void testFailure(Failure failure) throws Exception
/*     */   {
/*  62 */     synchronized (this.monitor) {
/*  63 */       this.listener.testFailure(failure);
/*     */     }
/*     */   }
/*     */   
/*     */   public void testAssumptionFailure(Failure failure)
/*     */   {
/*  69 */     synchronized (this.monitor) {
/*  70 */       this.listener.testAssumptionFailure(failure);
/*     */     }
/*     */   }
/*     */   
/*     */   public void testIgnored(Description description) throws Exception
/*     */   {
/*  76 */     synchronized (this.monitor) {
/*  77 */       this.listener.testIgnored(description);
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  83 */     return this.listener.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object other)
/*     */   {
/*  88 */     if (this == other) {
/*  89 */       return true;
/*     */     }
/*  91 */     if (!(other instanceof SynchronizedRunListener)) {
/*  92 */       return false;
/*     */     }
/*  94 */     SynchronizedRunListener that = (SynchronizedRunListener)other;
/*     */     
/*  96 */     return this.listener.equals(that.listener);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 101 */     return this.listener.toString() + " (with synchronization wrapper)";
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/notification/SynchronizedRunListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */