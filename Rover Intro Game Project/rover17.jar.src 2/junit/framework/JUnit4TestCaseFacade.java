/*    */ package junit.framework;
/*    */ 
/*    */ import org.junit.runner.Description;
/*    */ 
/*    */ public class JUnit4TestCaseFacade implements Test, org.junit.runner.Describable
/*    */ {
/*    */   private final Description fDescription;
/*    */   
/*    */   JUnit4TestCaseFacade(Description description) {
/* 10 */     this.fDescription = description;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 15 */     return getDescription().toString();
/*    */   }
/*    */   
/*    */   public int countTestCases() {
/* 19 */     return 1;
/*    */   }
/*    */   
/*    */   public void run(TestResult result) {
/* 23 */     throw new RuntimeException("This test stub created only for informational purposes.");
/*    */   }
/*    */   
/*    */   public Description getDescription()
/*    */   {
/* 28 */     return this.fDescription;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/JUnit4TestCaseFacade.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */