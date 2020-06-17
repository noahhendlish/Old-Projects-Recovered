/*    */ package org.hamcrest.internal;
/*    */ 
/*    */ import java.lang.reflect.Array;
/*    */ 
/*    */ public class ArrayIterator implements java.util.Iterator<Object>
/*    */ {
/*    */   private final Object array;
/*  8 */   private int currentIndex = 0;
/*    */   
/*    */   public ArrayIterator(Object array) {
/* 11 */     if (!array.getClass().isArray()) {
/* 12 */       throw new IllegalArgumentException("not an array");
/*    */     }
/* 14 */     this.array = array;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 19 */     return this.currentIndex < Array.getLength(this.array);
/*    */   }
/*    */   
/*    */   public Object next()
/*    */   {
/* 24 */     return Array.get(this.array, this.currentIndex++);
/*    */   }
/*    */   
/*    */   public void remove()
/*    */   {
/* 29 */     throw new UnsupportedOperationException("cannot remove items from an array");
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/internal/ArrayIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */