/*     */ package junit.framework;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestResult
/*     */ {
/*     */   protected List<TestFailure> fFailures;
/*     */   protected List<TestFailure> fErrors;
/*     */   protected List<TestListener> fListeners;
/*     */   protected int fRunTests;
/*     */   private boolean fStop;
/*     */   
/*     */   public TestResult()
/*     */   {
/*  25 */     this.fFailures = new ArrayList();
/*  26 */     this.fErrors = new ArrayList();
/*  27 */     this.fListeners = new ArrayList();
/*  28 */     this.fRunTests = 0;
/*  29 */     this.fStop = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addError(Test test, Throwable e)
/*     */   {
/*  37 */     this.fErrors.add(new TestFailure(test, e));
/*  38 */     for (TestListener each : cloneListeners()) {
/*  39 */       each.addError(test, e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addFailure(Test test, AssertionFailedError e)
/*     */   {
/*  48 */     this.fFailures.add(new TestFailure(test, e));
/*  49 */     for (TestListener each : cloneListeners()) {
/*  50 */       each.addFailure(test, e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void addListener(TestListener listener)
/*     */   {
/*  58 */     this.fListeners.add(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void removeListener(TestListener listener)
/*     */   {
/*  65 */     this.fListeners.remove(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private synchronized List<TestListener> cloneListeners()
/*     */   {
/*  72 */     List<TestListener> result = new ArrayList();
/*  73 */     result.addAll(this.fListeners);
/*  74 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void endTest(Test test)
/*     */   {
/*  81 */     for (TestListener each : cloneListeners()) {
/*  82 */       each.endTest(test);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized int errorCount()
/*     */   {
/*  90 */     return this.fErrors.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized Enumeration<TestFailure> errors()
/*     */   {
/*  97 */     return Collections.enumeration(this.fErrors);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int failureCount()
/*     */   {
/* 105 */     return this.fFailures.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized Enumeration<TestFailure> failures()
/*     */   {
/* 112 */     return Collections.enumeration(this.fFailures);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void run(final TestCase test)
/*     */   {
/* 119 */     startTest(test);
/* 120 */     Protectable p = new Protectable() {
/*     */       public void protect() throws Throwable {
/* 122 */         test.runBare();
/*     */       }
/* 124 */     };
/* 125 */     runProtected(test, p);
/*     */     
/* 127 */     endTest(test);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized int runCount()
/*     */   {
/* 134 */     return this.fRunTests;
/*     */   }
/*     */   
/*     */ 
/*     */   public void runProtected(Test test, Protectable p)
/*     */   {
/*     */     try
/*     */     {
/* 142 */       p.protect();
/*     */     } catch (AssertionFailedError e) {
/* 144 */       addFailure(test, e);
/*     */     } catch (ThreadDeath e) {
/* 146 */       throw e;
/*     */     } catch (Throwable e) {
/* 148 */       addError(test, e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized boolean shouldStop()
/*     */   {
/* 156 */     return this.fStop;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startTest(Test test)
/*     */   {
/* 163 */     int count = test.countTestCases();
/* 164 */     synchronized (this) {
/* 165 */       this.fRunTests += count;
/*     */     }
/* 167 */     for (TestListener each : cloneListeners()) {
/* 168 */       each.startTest(test);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void stop()
/*     */   {
/* 176 */     this.fStop = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized boolean wasSuccessful()
/*     */   {
/* 183 */     return (failureCount() == 0) && (errorCount() == 0);
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/TestResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */