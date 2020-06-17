/*    */ package org.junit.rules;
/*    */ 
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunRules
/*    */   extends Statement
/*    */ {
/*    */   private final Statement statement;
/*    */   
/*    */   public RunRules(Statement base, Iterable<TestRule> rules, Description description)
/*    */   {
/* 15 */     this.statement = applyAll(base, rules, description);
/*    */   }
/*    */   
/*    */   public void evaluate() throws Throwable
/*    */   {
/* 20 */     this.statement.evaluate();
/*    */   }
/*    */   
/*    */   private static Statement applyAll(Statement result, Iterable<TestRule> rules, Description description)
/*    */   {
/* 25 */     for (TestRule each : rules) {
/* 26 */       result = each.apply(result, description);
/*    */     }
/* 28 */     return result;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/RunRules.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */