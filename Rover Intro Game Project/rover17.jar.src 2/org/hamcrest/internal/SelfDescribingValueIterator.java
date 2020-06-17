/*    */ package org.hamcrest.internal;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class SelfDescribingValueIterator<T> implements Iterator<org.hamcrest.SelfDescribing>
/*    */ {
/*    */   private Iterator<T> values;
/*    */   
/*    */   public SelfDescribingValueIterator(Iterator<T> values)
/*    */   {
/* 11 */     this.values = values;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 16 */     return this.values.hasNext();
/*    */   }
/*    */   
/*    */   public org.hamcrest.SelfDescribing next()
/*    */   {
/* 21 */     return new SelfDescribingValue(this.values.next());
/*    */   }
/*    */   
/*    */   public void remove()
/*    */   {
/* 26 */     this.values.remove();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/internal/SelfDescribingValueIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */