/*     */ package org.junit.experimental.max;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Result;
/*     */ import org.junit.runner.notification.Failure;
/*     */ import org.junit.runner.notification.RunListener;
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
/*     */ public class MaxHistory
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static MaxHistory forFolder(File file)
/*     */   {
/*  34 */     if (file.exists()) {
/*     */       try {
/*  36 */         return readHistory(file);
/*     */       } catch (CouldNotReadCoreException e) {
/*  38 */         e.printStackTrace();
/*  39 */         file.delete();
/*     */       }
/*     */     }
/*  42 */     return new MaxHistory(file);
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
/*  69 */   private final Map<String, Long> fDurations = new HashMap();
/*  70 */   private final Map<String, Long> fFailureTimestamps = new HashMap();
/*     */   private final File fHistoryStore;
/*     */   
/*     */   /* Error */
/*     */   private static MaxHistory readHistory(File storedResults)
/*     */     throws CouldNotReadCoreException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: new 9	java/io/FileInputStream
/*     */     //   3: dup
/*     */     //   4: aload_0
/*     */     //   5: invokespecial 10	java/io/FileInputStream:<init>	(Ljava/io/File;)V
/*     */     //   8: astore_1
/*     */     //   9: new 11	java/io/ObjectInputStream
/*     */     //   12: dup
/*     */     //   13: aload_1
/*     */     //   14: invokespecial 12	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
/*     */     //   17: astore_2
/*     */     //   18: aload_2
/*     */     //   19: invokevirtual 13	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
/*     */     //   22: checkcast 7	org/junit/experimental/max/MaxHistory
/*     */     //   25: astore_3
/*     */     //   26: aload_2
/*     */     //   27: invokevirtual 14	java/io/ObjectInputStream:close	()V
/*     */     //   30: aload_1
/*     */     //   31: invokevirtual 15	java/io/FileInputStream:close	()V
/*     */     //   34: aload_3
/*     */     //   35: areturn
/*     */     //   36: astore 4
/*     */     //   38: aload_2
/*     */     //   39: invokevirtual 14	java/io/ObjectInputStream:close	()V
/*     */     //   42: aload 4
/*     */     //   44: athrow
/*     */     //   45: astore 5
/*     */     //   47: aload_1
/*     */     //   48: invokevirtual 15	java/io/FileInputStream:close	()V
/*     */     //   51: aload 5
/*     */     //   53: athrow
/*     */     //   54: astore_1
/*     */     //   55: new 4	org/junit/experimental/max/CouldNotReadCoreException
/*     */     //   58: dup
/*     */     //   59: aload_1
/*     */     //   60: invokespecial 17	org/junit/experimental/max/CouldNotReadCoreException:<init>	(Ljava/lang/Throwable;)V
/*     */     //   63: athrow
/*     */     // Line number table:
/*     */     //   Java source line #48	-> byte code offset #0
/*     */     //   Java source line #50	-> byte code offset #9
/*     */     //   Java source line #52	-> byte code offset #18
/*     */     //   Java source line #54	-> byte code offset #26
/*     */     //   Java source line #57	-> byte code offset #30
/*     */     //   Java source line #54	-> byte code offset #36
/*     */     //   Java source line #57	-> byte code offset #45
/*     */     //   Java source line #59	-> byte code offset #54
/*     */     //   Java source line #60	-> byte code offset #55
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	64	0	storedResults	File
/*     */     //   8	40	1	file	java.io.FileInputStream
/*     */     //   54	6	1	e	Exception
/*     */     //   17	22	2	stream	java.io.ObjectInputStream
/*     */     //   36	7	4	localObject1	Object
/*     */     //   45	7	5	localObject2	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   18	26	36	finally
/*     */     //   36	38	36	finally
/*     */     //   9	30	45	finally
/*     */     //   36	47	45	finally
/*     */     //   0	34	54	java/lang/Exception
/*     */     //   36	54	54	java/lang/Exception
/*     */   }
/*     */   
/*     */   private MaxHistory(File storedResults)
/*     */   {
/*  74 */     this.fHistoryStore = storedResults;
/*     */   }
/*     */   
/*     */   private void save() throws IOException {
/*  78 */     ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.fHistoryStore));
/*     */     
/*  80 */     stream.writeObject(this);
/*  81 */     stream.close();
/*     */   }
/*     */   
/*     */   Long getFailureTimestamp(Description key) {
/*  85 */     return (Long)this.fFailureTimestamps.get(key.toString());
/*     */   }
/*     */   
/*     */   void putTestFailureTimestamp(Description key, long end) {
/*  89 */     this.fFailureTimestamps.put(key.toString(), Long.valueOf(end));
/*     */   }
/*     */   
/*     */   boolean isNewTest(Description key) {
/*  93 */     return !this.fDurations.containsKey(key.toString());
/*     */   }
/*     */   
/*     */   Long getTestDuration(Description key) {
/*  97 */     return (Long)this.fDurations.get(key.toString());
/*     */   }
/*     */   
/*     */   void putTestDuration(Description description, long duration) {
/* 101 */     this.fDurations.put(description.toString(), Long.valueOf(duration));
/*     */   }
/*     */   
/*     */   private final class RememberingListener extends RunListener {
/* 105 */     private long overallStart = System.currentTimeMillis();
/*     */     
/* 107 */     private Map<Description, Long> starts = new HashMap();
/*     */     
/*     */     private RememberingListener() {}
/*     */     
/* 111 */     public void testStarted(Description description) throws Exception { this.starts.put(description, Long.valueOf(System.nanoTime())); }
/*     */     
/*     */ 
/*     */     public void testFinished(Description description)
/*     */       throws Exception
/*     */     {
/* 117 */       long end = System.nanoTime();
/* 118 */       long start = ((Long)this.starts.get(description)).longValue();
/* 119 */       MaxHistory.this.putTestDuration(description, end - start);
/*     */     }
/*     */     
/*     */     public void testFailure(Failure failure) throws Exception
/*     */     {
/* 124 */       MaxHistory.this.putTestFailureTimestamp(failure.getDescription(), this.overallStart);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 129 */     public void testRunFinished(Result result) throws Exception { MaxHistory.this.save(); }
/*     */   }
/*     */   
/*     */   private class TestComparator implements Comparator<Description> {
/*     */     private TestComparator() {}
/*     */     
/*     */     public int compare(Description o1, Description o2) {
/* 136 */       if (MaxHistory.this.isNewTest(o1)) {
/* 137 */         return -1;
/*     */       }
/* 139 */       if (MaxHistory.this.isNewTest(o2)) {
/* 140 */         return 1;
/*     */       }
/*     */       
/* 143 */       int result = getFailure(o2).compareTo(getFailure(o1));
/* 144 */       return result != 0 ? result : MaxHistory.this.getTestDuration(o1).compareTo(MaxHistory.this.getTestDuration(o2));
/*     */     }
/*     */     
/*     */ 
/*     */     private Long getFailure(Description key)
/*     */     {
/* 150 */       Long result = MaxHistory.this.getFailureTimestamp(key);
/* 151 */       if (result == null) {
/* 152 */         return Long.valueOf(0L);
/*     */       }
/* 154 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RunListener listener()
/*     */   {
/* 163 */     return new RememberingListener(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparator<Description> testComparator()
/*     */   {
/* 171 */     return new TestComparator(null);
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/max/MaxHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */