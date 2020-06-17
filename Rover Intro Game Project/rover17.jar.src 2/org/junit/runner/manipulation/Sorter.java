/*    */ package org.junit.runner.manipulation;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.junit.runner.Description;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Sorter
/*    */   implements Comparator<Description>
/*    */ {
/* 17 */   public static final Sorter NULL = new Sorter(new Comparator() {
/*    */     public int compare(Description o1, Description o2) {
/* 19 */       return 0;
/*    */     }
/* 17 */   });
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private final Comparator<Description> comparator;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Sorter(Comparator<Description> comparator)
/*    */   {
/* 32 */     this.comparator = comparator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void apply(Object object)
/*    */   {
/* 39 */     if ((object instanceof Sortable)) {
/* 40 */       Sortable sortable = (Sortable)object;
/* 41 */       sortable.sort(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public int compare(Description o1, Description o2) {
/* 46 */     return this.comparator.compare(o1, o2);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/manipulation/Sorter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */