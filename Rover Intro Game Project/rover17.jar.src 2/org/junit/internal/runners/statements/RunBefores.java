/*    */ package org.junit.internal.runners.statements;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ public class RunBefores
/*    */   extends Statement
/*    */ {
/*    */   private final Statement next;
/*    */   private final Object target;
/*    */   private final List<FrameworkMethod> befores;
/*    */   
/*    */   public RunBefores(Statement next, List<FrameworkMethod> befores, Object target)
/*    */   {
/* 16 */     this.next = next;
/* 17 */     this.befores = befores;
/* 18 */     this.target = target;
/*    */   }
/*    */   
/*    */   public void evaluate() throws Throwable
/*    */   {
/* 23 */     for (FrameworkMethod before : this.befores) {
/* 24 */       before.invokeExplosively(this.target, new Object[0]);
/*    */     }
/* 26 */     this.next.evaluate();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/statements/RunBefores.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */