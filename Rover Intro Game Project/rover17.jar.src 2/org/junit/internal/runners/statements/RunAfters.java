/*    */ package org.junit.internal.runners.statements;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.MultipleFailureException;
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ public class RunAfters extends Statement
/*    */ {
/*    */   private final Statement next;
/*    */   private final Object target;
/*    */   private final List<FrameworkMethod> afters;
/*    */   
/*    */   public RunAfters(Statement next, List<FrameworkMethod> afters, Object target)
/*    */   {
/* 18 */     this.next = next;
/* 19 */     this.afters = afters;
/* 20 */     this.target = target;
/*    */   }
/*    */   
/*    */   public void evaluate() throws Throwable
/*    */   {
/* 25 */     List<Throwable> errors = new ArrayList();
/*    */     try {
/* 27 */       this.next.evaluate(); } catch (Throwable e) { Iterator i$;
/*    */       FrameworkMethod each;
/* 29 */       errors.add(e); } finally { Iterator i$;
/*    */       FrameworkMethod each;
/* 31 */       for (FrameworkMethod each : this.afters) {
/*    */         try {
/* 33 */           each.invokeExplosively(this.target, new Object[0]);
/*    */         } catch (Throwable e) {
/* 35 */           errors.add(e);
/*    */         }
/*    */       }
/*    */     }
/* 39 */     MultipleFailureException.assertEmpty(errors);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/statements/RunAfters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */