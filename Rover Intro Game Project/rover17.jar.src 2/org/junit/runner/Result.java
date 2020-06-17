/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectInputStream.GetField;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectOutputStream.PutField;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.junit.runner.notification.Failure;
/*     */ import org.junit.runner.notification.RunListener;
/*     */ import org.junit.runner.notification.RunListener.ThreadSafe;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Result
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  27 */   private static final ObjectStreamField[] serialPersistentFields = ObjectStreamClass.lookup(SerializedForm.class).getFields();
/*     */   
/*     */   private final AtomicInteger count;
/*     */   
/*     */   private final AtomicInteger ignoreCount;
/*     */   private final CopyOnWriteArrayList<Failure> failures;
/*     */   private final AtomicLong runTime;
/*     */   private final AtomicLong startTime;
/*     */   private SerializedForm serializedForm;
/*     */   
/*     */   public Result()
/*     */   {
/*  39 */     this.count = new AtomicInteger();
/*  40 */     this.ignoreCount = new AtomicInteger();
/*  41 */     this.failures = new CopyOnWriteArrayList();
/*  42 */     this.runTime = new AtomicLong();
/*  43 */     this.startTime = new AtomicLong();
/*     */   }
/*     */   
/*     */   private Result(SerializedForm serializedForm) {
/*  47 */     this.count = serializedForm.fCount;
/*  48 */     this.ignoreCount = serializedForm.fIgnoreCount;
/*  49 */     this.failures = new CopyOnWriteArrayList(serializedForm.fFailures);
/*  50 */     this.runTime = new AtomicLong(serializedForm.fRunTime);
/*  51 */     this.startTime = new AtomicLong(serializedForm.fStartTime);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getRunCount()
/*     */   {
/*  58 */     return this.count.get();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getFailureCount()
/*     */   {
/*  65 */     return this.failures.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getRunTime()
/*     */   {
/*  72 */     return this.runTime.get();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<Failure> getFailures()
/*     */   {
/*  79 */     return this.failures;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getIgnoreCount()
/*     */   {
/*  86 */     return this.ignoreCount.get();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean wasSuccessful()
/*     */   {
/*  93 */     return getFailureCount() == 0;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/*  97 */     SerializedForm serializedForm = new SerializedForm(this);
/*  98 */     serializedForm.serialize(s);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException
/*     */   {
/* 103 */     this.serializedForm = SerializedForm.deserialize(s);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 107 */     return new Result(this.serializedForm);
/*     */   }
/*     */   
/*     */   @RunListener.ThreadSafe
/*     */   private class Listener extends RunListener {
/*     */     private Listener() {}
/*     */     
/* 114 */     public void testRunStarted(Description description) throws Exception { Result.this.startTime.set(System.currentTimeMillis()); }
/*     */     
/*     */     public void testRunFinished(Result result)
/*     */       throws Exception
/*     */     {
/* 119 */       long endTime = System.currentTimeMillis();
/* 120 */       Result.this.runTime.addAndGet(endTime - Result.this.startTime.get());
/*     */     }
/*     */     
/*     */     public void testFinished(Description description) throws Exception
/*     */     {
/* 125 */       Result.this.count.getAndIncrement();
/*     */     }
/*     */     
/*     */     public void testFailure(Failure failure) throws Exception
/*     */     {
/* 130 */       Result.this.failures.add(failure);
/*     */     }
/*     */     
/*     */     public void testIgnored(Description description) throws Exception
/*     */     {
/* 135 */       Result.this.ignoreCount.getAndIncrement();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void testAssumptionFailure(Failure failure) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RunListener createListener()
/*     */   {
/* 148 */     return new Listener(null);
/*     */   }
/*     */   
/*     */ 
/*     */   private static class SerializedForm
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     private final AtomicInteger fCount;
/*     */     private final AtomicInteger fIgnoreCount;
/*     */     private final List<Failure> fFailures;
/*     */     private final long fRunTime;
/*     */     private final long fStartTime;
/*     */     
/*     */     public SerializedForm(Result result)
/*     */     {
/* 164 */       this.fCount = result.count;
/* 165 */       this.fIgnoreCount = result.ignoreCount;
/* 166 */       this.fFailures = Collections.synchronizedList(new ArrayList(result.failures));
/* 167 */       this.fRunTime = result.runTime.longValue();
/* 168 */       this.fStartTime = result.startTime.longValue();
/*     */     }
/*     */     
/*     */     private SerializedForm(ObjectInputStream.GetField fields) throws IOException
/*     */     {
/* 173 */       this.fCount = ((AtomicInteger)fields.get("fCount", null));
/* 174 */       this.fIgnoreCount = ((AtomicInteger)fields.get("fIgnoreCount", null));
/* 175 */       this.fFailures = ((List)fields.get("fFailures", null));
/* 176 */       this.fRunTime = fields.get("fRunTime", 0L);
/* 177 */       this.fStartTime = fields.get("fStartTime", 0L);
/*     */     }
/*     */     
/*     */     public void serialize(ObjectOutputStream s) throws IOException {
/* 181 */       ObjectOutputStream.PutField fields = s.putFields();
/* 182 */       fields.put("fCount", this.fCount);
/* 183 */       fields.put("fIgnoreCount", this.fIgnoreCount);
/* 184 */       fields.put("fFailures", this.fFailures);
/* 185 */       fields.put("fRunTime", this.fRunTime);
/* 186 */       fields.put("fStartTime", this.fStartTime);
/* 187 */       s.writeFields();
/*     */     }
/*     */     
/*     */     public static SerializedForm deserialize(ObjectInputStream s) throws ClassNotFoundException, IOException
/*     */     {
/* 192 */       ObjectInputStream.GetField fields = s.readFields();
/* 193 */       return new SerializedForm(fields);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/Result.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */