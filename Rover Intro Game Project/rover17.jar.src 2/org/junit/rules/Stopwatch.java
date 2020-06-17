/*     */ package org.junit.rules;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.junit.AssumptionViolatedException;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runners.model.Statement;
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
/*     */ public abstract class Stopwatch
/*     */   implements TestRule
/*     */ {
/*     */   private final Clock clock;
/*     */   private volatile long startNanos;
/*     */   private volatile long endNanos;
/*     */   
/*     */   public Stopwatch()
/*     */   {
/*  85 */     this(new Clock());
/*     */   }
/*     */   
/*     */   Stopwatch(Clock clock) {
/*  89 */     this.clock = clock;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long runtime(TimeUnit unit)
/*     */   {
/*  99 */     return unit.convert(getNanos(), TimeUnit.NANOSECONDS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void succeeded(long nanos, Description description) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void failed(long nanos, Throwable e, Description description) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void skipped(long nanos, AssumptionViolatedException e, Description description) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void finished(long nanos, Description description) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private long getNanos()
/*     */   {
/* 127 */     if (this.startNanos == 0L) {
/* 128 */       throw new IllegalStateException("Test has not started");
/*     */     }
/* 130 */     long currentEndNanos = this.endNanos;
/* 131 */     if (currentEndNanos == 0L) {
/* 132 */       currentEndNanos = this.clock.nanoTime();
/*     */     }
/*     */     
/* 135 */     return currentEndNanos - this.startNanos;
/*     */   }
/*     */   
/*     */   private void starting() {
/* 139 */     this.startNanos = this.clock.nanoTime();
/* 140 */     this.endNanos = 0L;
/*     */   }
/*     */   
/*     */   private void stopping() {
/* 144 */     this.endNanos = this.clock.nanoTime();
/*     */   }
/*     */   
/*     */   public final Statement apply(Statement base, Description description) {
/* 148 */     return new InternalWatcher(null).apply(base, description);
/*     */   }
/*     */   
/*     */   private class InternalWatcher extends TestWatcher {
/*     */     private InternalWatcher() {}
/*     */     
/* 154 */     protected void starting(Description description) { Stopwatch.this.starting(); }
/*     */     
/*     */     protected void finished(Description description)
/*     */     {
/* 158 */       Stopwatch.this.finished(Stopwatch.this.getNanos(), description);
/*     */     }
/*     */     
/*     */     protected void succeeded(Description description) {
/* 162 */       Stopwatch.this.stopping();
/* 163 */       Stopwatch.this.succeeded(Stopwatch.this.getNanos(), description);
/*     */     }
/*     */     
/*     */     protected void failed(Throwable e, Description description) {
/* 167 */       Stopwatch.this.stopping();
/* 168 */       Stopwatch.this.failed(Stopwatch.this.getNanos(), e, description);
/*     */     }
/*     */     
/*     */     protected void skipped(AssumptionViolatedException e, Description description) {
/* 172 */       Stopwatch.this.stopping();
/* 173 */       Stopwatch.this.skipped(Stopwatch.this.getNanos(), e, description);
/*     */     }
/*     */   }
/*     */   
/*     */   static class Clock
/*     */   {
/*     */     public long nanoTime() {
/* 180 */       return System.nanoTime();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/Stopwatch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */