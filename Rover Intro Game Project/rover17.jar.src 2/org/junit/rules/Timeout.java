/*     */ package org.junit.rules;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.junit.internal.runners.statements.FailOnTimeout;
/*     */ import org.junit.internal.runners.statements.FailOnTimeout.Builder;
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
/*     */ public class Timeout
/*     */   implements TestRule
/*     */ {
/*     */   private final long timeout;
/*     */   private final TimeUnit timeUnit;
/*     */   private final boolean lookForStuckThread;
/*     */   
/*     */   public static Builder builder()
/*     */   {
/*  51 */     return new Builder();
/*     */   }
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
/*     */   @Deprecated
/*     */   public Timeout(int millis)
/*     */   {
/*  68 */     this(millis, TimeUnit.MILLISECONDS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Timeout(long timeout, TimeUnit timeUnit)
/*     */   {
/*  81 */     this.timeout = timeout;
/*  82 */     this.timeUnit = timeUnit;
/*  83 */     this.lookForStuckThread = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Timeout(Builder builder)
/*     */   {
/*  93 */     this.timeout = builder.getTimeout();
/*  94 */     this.timeUnit = builder.getTimeUnit();
/*  95 */     this.lookForStuckThread = builder.getLookingForStuckThread();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Timeout millis(long millis)
/*     */   {
/* 105 */     return new Timeout(millis, TimeUnit.MILLISECONDS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Timeout seconds(long seconds)
/*     */   {
/* 115 */     return new Timeout(seconds, TimeUnit.SECONDS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final long getTimeout(TimeUnit unit)
/*     */   {
/* 124 */     return unit.convert(this.timeout, this.timeUnit);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final boolean getLookingForStuckThread()
/*     */   {
/* 134 */     return this.lookForStuckThread;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement createFailOnTimeoutStatement(Statement statement)
/*     */     throws Exception
/*     */   {
/* 147 */     return FailOnTimeout.builder().withTimeout(this.timeout, this.timeUnit).withLookingForStuckThread(this.lookForStuckThread).build(statement);
/*     */   }
/*     */   
/*     */ 
/*     */   public Statement apply(Statement base, Description description)
/*     */   {
/*     */     try
/*     */     {
/* 155 */       return createFailOnTimeoutStatement(base);
/*     */     } catch (Exception e) {
/* 157 */       new Statement() {
/*     */         public void evaluate() throws Throwable {
/* 159 */           throw new RuntimeException("Invalid parameters for Timeout", e);
/*     */         }
/*     */       };
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Builder
/*     */   {
/* 171 */     private boolean lookForStuckThread = false;
/* 172 */     private long timeout = 0L;
/* 173 */     private TimeUnit timeUnit = TimeUnit.SECONDS;
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
/*     */     public Builder withTimeout(long timeout, TimeUnit unit)
/*     */     {
/* 194 */       this.timeout = timeout;
/* 195 */       this.timeUnit = unit;
/* 196 */       return this;
/*     */     }
/*     */     
/*     */     protected long getTimeout() {
/* 200 */       return this.timeout;
/*     */     }
/*     */     
/*     */     protected TimeUnit getTimeUnit() {
/* 204 */       return this.timeUnit;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Builder withLookingForStuckThread(boolean enable)
/*     */     {
/* 217 */       this.lookForStuckThread = enable;
/* 218 */       return this;
/*     */     }
/*     */     
/*     */     protected boolean getLookingForStuckThread() {
/* 222 */       return this.lookForStuckThread;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Timeout build()
/*     */     {
/* 230 */       return new Timeout(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/Timeout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */