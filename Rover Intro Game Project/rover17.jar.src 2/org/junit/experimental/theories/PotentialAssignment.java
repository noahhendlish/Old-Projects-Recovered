/*    */ package org.junit.experimental.theories;
/*    */ 
/*    */ public abstract class PotentialAssignment
/*    */ {
/*    */   public static class CouldNotGenerateValueException extends Exception
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public CouldNotGenerateValueException() {}
/*    */     
/*    */     public CouldNotGenerateValueException(Throwable e)
/*    */     {
/* 13 */       super();
/*    */     }
/*    */   }
/*    */   
/*    */   public static PotentialAssignment forValue(final String name, Object value) {
/* 18 */     new PotentialAssignment()
/*    */     {
/*    */       public Object getValue() {
/* 21 */         return this.val$value;
/*    */       }
/*    */       
/*    */       public String toString()
/*    */       {
/* 26 */         return String.format("[%s]", new Object[] { this.val$value });
/*    */       }
/*    */       
/*    */       public String getDescription()
/*    */       {
/*    */         String valueString;
/*    */         String valueString;
/* 33 */         if (this.val$value == null) {
/* 34 */           valueString = "null";
/*    */         } else {
/*    */           try {
/* 37 */             valueString = String.format("\"%s\"", new Object[] { this.val$value });
/*    */           } catch (Throwable e) {
/* 39 */             valueString = String.format("[toString() threw %s: %s]", new Object[] { e.getClass().getSimpleName(), e.getMessage() });
/*    */           }
/*    */         }
/*    */         
/*    */ 
/* 44 */         return String.format("%s <from %s>", new Object[] { valueString, name });
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */   public abstract Object getValue()
/*    */     throws PotentialAssignment.CouldNotGenerateValueException;
/*    */   
/*    */   public abstract String getDescription()
/*    */     throws PotentialAssignment.CouldNotGenerateValueException;
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/PotentialAssignment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */