/*    */ package junit.extensions;
/*    */ 
/*    */ import junit.framework.Test;
/*    */ import junit.framework.TestResult;
/*    */ 
/*    */ public class RepeatedTest
/*    */   extends TestDecorator
/*    */ {
/*    */   private int fTimesRepeat;
/*    */   
/*    */   public RepeatedTest(Test test, int repeat)
/*    */   {
/* 13 */     super(test);
/* 14 */     if (repeat < 0) {
/* 15 */       throw new IllegalArgumentException("Repetition count must be >= 0");
/*    */     }
/* 17 */     this.fTimesRepeat = repeat;
/*    */   }
/*    */   
/*    */   public int countTestCases()
/*    */   {
/* 22 */     return super.countTestCases() * this.fTimesRepeat;
/*    */   }
/*    */   
/*    */   public void run(TestResult result)
/*    */   {
/* 27 */     for (int i = 0; i < this.fTimesRepeat; i++) {
/* 28 */       if (result.shouldStop()) {
/*    */         break;
/*    */       }
/* 31 */       super.run(result);
/*    */     }
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 37 */     return super.toString() + "(repeated)";
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/extensions/RepeatedTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */