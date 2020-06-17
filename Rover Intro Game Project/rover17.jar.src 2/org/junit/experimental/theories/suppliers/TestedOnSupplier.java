/*    */ package org.junit.experimental.theories.suppliers;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.junit.experimental.theories.ParameterSignature;
/*    */ import org.junit.experimental.theories.ParameterSupplier;
/*    */ import org.junit.experimental.theories.PotentialAssignment;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestedOnSupplier
/*    */   extends ParameterSupplier
/*    */ {
/*    */   public List<PotentialAssignment> getValueSources(ParameterSignature sig)
/*    */   {
/* 17 */     List<PotentialAssignment> list = new ArrayList();
/* 18 */     TestedOn testedOn = (TestedOn)sig.getAnnotation(TestedOn.class);
/* 19 */     int[] ints = testedOn.ints();
/* 20 */     for (int i : ints) {
/* 21 */       list.add(PotentialAssignment.forValue("ints", Integer.valueOf(i)));
/*    */     }
/* 23 */     return list;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/suppliers/TestedOnSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */