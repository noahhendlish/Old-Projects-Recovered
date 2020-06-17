/*     */ package junit.textui;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestCase;
/*     */ import junit.framework.TestResult;
/*     */ import junit.framework.TestSuite;
/*     */ import junit.runner.BaseTestRunner;
/*     */ import junit.runner.Version;
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
/*     */ public class TestRunner
/*     */   extends BaseTestRunner
/*     */ {
/*     */   private ResultPrinter fPrinter;
/*     */   public static final int SUCCESS_EXIT = 0;
/*     */   public static final int FAILURE_EXIT = 1;
/*     */   public static final int EXCEPTION_EXIT = 2;
/*     */   
/*     */   public TestRunner()
/*     */   {
/*  41 */     this(System.out);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TestRunner(PrintStream writer)
/*     */   {
/*  48 */     this(new ResultPrinter(writer));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TestRunner(ResultPrinter printer)
/*     */   {
/*  55 */     this.fPrinter = printer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void run(Class<? extends TestCase> testClass)
/*     */   {
/*  62 */     run(new TestSuite(testClass));
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
/*     */   public static TestResult run(Test test)
/*     */   {
/*  76 */     TestRunner runner = new TestRunner();
/*  77 */     return runner.doRun(test);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void runAndWait(Test suite)
/*     */   {
/*  85 */     TestRunner aTestRunner = new TestRunner();
/*  86 */     aTestRunner.doRun(suite, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testFailed(int status, Test test, Throwable e) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void testStarted(String testName) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void testEnded(String testName) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected TestResult createTestResult()
/*     */   {
/* 105 */     return new TestResult();
/*     */   }
/*     */   
/*     */   public TestResult doRun(Test test) {
/* 109 */     return doRun(test, false);
/*     */   }
/*     */   
/*     */   public TestResult doRun(Test suite, boolean wait) {
/* 113 */     TestResult result = createTestResult();
/* 114 */     result.addListener(this.fPrinter);
/* 115 */     long startTime = System.currentTimeMillis();
/* 116 */     suite.run(result);
/* 117 */     long endTime = System.currentTimeMillis();
/* 118 */     long runTime = endTime - startTime;
/* 119 */     this.fPrinter.print(result, runTime);
/*     */     
/* 121 */     pause(wait);
/* 122 */     return result;
/*     */   }
/*     */   
/*     */   protected void pause(boolean wait) {
/* 126 */     if (!wait) return;
/* 127 */     this.fPrinter.printWaitPrompt();
/*     */     try {
/* 129 */       System.in.read();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 135 */     TestRunner aTestRunner = new TestRunner();
/*     */     try {
/* 137 */       TestResult r = aTestRunner.start(args);
/* 138 */       if (!r.wasSuccessful()) {
/* 139 */         System.exit(1);
/*     */       }
/* 141 */       System.exit(0);
/*     */     } catch (Exception e) {
/* 143 */       System.err.println(e.getMessage());
/* 144 */       System.exit(2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TestResult start(String[] args)
/*     */     throws Exception
/*     */   {
/* 153 */     String testCase = "";
/* 154 */     String method = "";
/* 155 */     boolean wait = false;
/*     */     
/* 157 */     for (int i = 0; i < args.length; i++) {
/* 158 */       if (args[i].equals("-wait")) {
/* 159 */         wait = true;
/* 160 */       } else if (args[i].equals("-c")) {
/* 161 */         testCase = extractClassName(args[(++i)]);
/* 162 */       } else if (args[i].equals("-m")) {
/* 163 */         String arg = args[(++i)];
/* 164 */         int lastIndex = arg.lastIndexOf('.');
/* 165 */         testCase = arg.substring(0, lastIndex);
/* 166 */         method = arg.substring(lastIndex + 1);
/* 167 */       } else if (args[i].equals("-v")) {
/* 168 */         System.err.println("JUnit " + Version.id() + " by Kent Beck and Erich Gamma");
/*     */       } else {
/* 170 */         testCase = args[i];
/*     */       }
/*     */     }
/*     */     
/* 174 */     if (testCase.equals("")) {
/* 175 */       throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
/*     */     }
/*     */     try
/*     */     {
/* 179 */       if (!method.equals("")) {
/* 180 */         return runSingleMethod(testCase, method, wait);
/*     */       }
/* 182 */       Test suite = getTest(testCase);
/* 183 */       return doRun(suite, wait);
/*     */     } catch (Exception e) {
/* 185 */       throw new Exception("Could not create and run test suite: " + e);
/*     */     }
/*     */   }
/*     */   
/*     */   protected TestResult runSingleMethod(String testCase, String method, boolean wait) throws Exception {
/* 190 */     Class<? extends TestCase> testClass = loadSuiteClass(testCase).asSubclass(TestCase.class);
/* 191 */     Test test = TestSuite.createTest(testClass, method);
/* 192 */     return doRun(test, wait);
/*     */   }
/*     */   
/*     */   protected void runFailed(String message)
/*     */   {
/* 197 */     System.err.println(message);
/* 198 */     System.exit(1);
/*     */   }
/*     */   
/*     */   public void setPrinter(ResultPrinter printer) {
/* 202 */     this.fPrinter = printer;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/textui/TestRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */