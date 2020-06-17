/*     */ package org.junit.rules;
/*     */ 
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.util.List;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runners.model.Statement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DisableOnDebug
/*     */   implements TestRule
/*     */ {
/*     */   private final TestRule rule;
/*     */   private final boolean debugging;
/*     */   
/*     */   public DisableOnDebug(TestRule rule)
/*     */   {
/*  57 */     this(rule, ManagementFactory.getRuntimeMXBean().getInputArguments());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   DisableOnDebug(TestRule rule, List<String> inputArguments)
/*     */   {
/*  69 */     this.rule = rule;
/*  70 */     this.debugging = isDebugging(inputArguments);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Statement apply(Statement base, Description description)
/*     */   {
/*  77 */     if (this.debugging) {
/*  78 */       return base;
/*     */     }
/*  80 */     return this.rule.apply(base, description);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isDebugging(List<String> arguments)
/*     */   {
/* 105 */     for (String argument : arguments) {
/* 106 */       if ("-Xdebug".equals(argument))
/* 107 */         return true;
/* 108 */       if (argument.startsWith("-agentlib:jdwp")) {
/* 109 */         return true;
/*     */       }
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDebugging()
/*     */   {
/* 124 */     return this.debugging;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/DisableOnDebug.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */