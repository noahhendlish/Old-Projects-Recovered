/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.model.RunnerBuilder;
/*    */ 
/*    */ public class AllDefaultPossibilitiesBuilder extends RunnerBuilder
/*    */ {
/*    */   private final boolean canUseSuiteMethod;
/*    */   
/*    */   public AllDefaultPossibilitiesBuilder(boolean canUseSuiteMethod)
/*    */   {
/* 13 */     this.canUseSuiteMethod = canUseSuiteMethod;
/*    */   }
/*    */   
/*    */   public Runner runnerForClass(Class<?> testClass) throws Throwable
/*    */   {
/* 18 */     java.util.List<RunnerBuilder> builders = Arrays.asList(new RunnerBuilder[] { ignoredBuilder(), annotatedBuilder(), suiteMethodBuilder(), junit3Builder(), junit4Builder() });
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 25 */     for (RunnerBuilder each : builders) {
/* 26 */       Runner runner = each.safeRunnerForClass(testClass);
/* 27 */       if (runner != null) {
/* 28 */         return runner;
/*    */       }
/*    */     }
/* 31 */     return null;
/*    */   }
/*    */   
/*    */   protected JUnit4Builder junit4Builder() {
/* 35 */     return new JUnit4Builder();
/*    */   }
/*    */   
/*    */   protected JUnit3Builder junit3Builder() {
/* 39 */     return new JUnit3Builder();
/*    */   }
/*    */   
/*    */   protected AnnotatedBuilder annotatedBuilder() {
/* 43 */     return new AnnotatedBuilder(this);
/*    */   }
/*    */   
/*    */   protected IgnoredBuilder ignoredBuilder() {
/* 47 */     return new IgnoredBuilder();
/*    */   }
/*    */   
/*    */   protected RunnerBuilder suiteMethodBuilder() {
/* 51 */     if (this.canUseSuiteMethod) {
/* 52 */       return new SuiteMethodBuilder();
/*    */     }
/* 54 */     return new NullBuilder();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/builders/AllDefaultPossibilitiesBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */