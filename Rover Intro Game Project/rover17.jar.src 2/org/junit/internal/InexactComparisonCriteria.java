/*    */ package org.junit.internal;
/*    */ 
/*    */ import org.junit.Assert;
/*    */ 
/*    */ public class InexactComparisonCriteria extends ComparisonCriteria {
/*    */   public Object fDelta;
/*    */   
/*    */   public InexactComparisonCriteria(double delta) {
/*  9 */     this.fDelta = Double.valueOf(delta);
/*    */   }
/*    */   
/*    */   public InexactComparisonCriteria(float delta) {
/* 13 */     this.fDelta = Float.valueOf(delta);
/*    */   }
/*    */   
/*    */   protected void assertElementsEqual(Object expected, Object actual)
/*    */   {
/* 18 */     if ((expected instanceof Double)) {
/* 19 */       Assert.assertEquals(((Double)expected).doubleValue(), ((Double)actual).doubleValue(), ((Double)this.fDelta).doubleValue());
/*    */     } else {
/* 21 */       Assert.assertEquals(((Float)expected).floatValue(), ((Float)actual).floatValue(), ((Float)this.fDelta).floatValue());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/InexactComparisonCriteria.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */