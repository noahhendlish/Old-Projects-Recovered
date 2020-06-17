/*    */ package junit.framework;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.notification.Failure;
/*    */ import org.junit.runner.notification.RunListener;
/*    */ import org.junit.runner.notification.RunNotifier;
/*    */ 
/*    */ public class JUnit4TestAdapterCache extends HashMap<Description, Test>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   private static final JUnit4TestAdapterCache fInstance = new JUnit4TestAdapterCache();
/*    */   
/*    */   public static JUnit4TestAdapterCache getDefault() {
/* 18 */     return fInstance;
/*    */   }
/*    */   
/*    */   public Test asTest(Description description) {
/* 22 */     if (description.isSuite()) {
/* 23 */       return createTest(description);
/*    */     }
/* 25 */     if (!containsKey(description)) {
/* 26 */       put(description, createTest(description));
/*    */     }
/* 28 */     return (Test)get(description);
/*    */   }
/*    */   
/*    */   Test createTest(Description description)
/*    */   {
/* 33 */     if (description.isTest()) {
/* 34 */       return new JUnit4TestCaseFacade(description);
/*    */     }
/* 36 */     TestSuite suite = new TestSuite(description.getDisplayName());
/* 37 */     for (Description child : description.getChildren()) {
/* 38 */       suite.addTest(asTest(child));
/*    */     }
/* 40 */     return suite;
/*    */   }
/*    */   
/*    */   public RunNotifier getNotifier(final TestResult result, JUnit4TestAdapter adapter)
/*    */   {
/* 45 */     RunNotifier notifier = new RunNotifier();
/* 46 */     notifier.addListener(new RunListener()
/*    */     {
/*    */       public void testFailure(Failure failure) throws Exception {
/* 49 */         result.addError(JUnit4TestAdapterCache.this.asTest(failure.getDescription()), failure.getException());
/*    */       }
/*    */       
/*    */       public void testFinished(Description description) throws Exception
/*    */       {
/* 54 */         result.endTest(JUnit4TestAdapterCache.this.asTest(description));
/*    */       }
/*    */       
/*    */       public void testStarted(Description description) throws Exception
/*    */       {
/* 59 */         result.startTest(JUnit4TestAdapterCache.this.asTest(description));
/*    */       }
/* 61 */     });
/* 62 */     return notifier;
/*    */   }
/*    */   
/*    */   public List<Test> asTestList(Description description) {
/* 66 */     if (description.isTest()) {
/* 67 */       return Arrays.asList(new Test[] { asTest(description) });
/*    */     }
/* 69 */     List<Test> returnThis = new ArrayList();
/* 70 */     for (Description child : description.getChildren()) {
/* 71 */       returnThis.add(asTest(child));
/*    */     }
/* 73 */     return returnThis;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/JUnit4TestAdapterCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */