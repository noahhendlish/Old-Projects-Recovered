/*     */ package org.junit.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.annotation.Inherited;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.lang.annotation.Target;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ import org.junit.runners.model.RunnerBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Suite
/*     */   extends ParentRunner<Runner>
/*     */ {
/*     */   private final List<Runner> runners;
/*     */   
/*     */   public static Runner emptySuite()
/*     */   {
/*     */     try
/*     */     {
/*  33 */       return new Suite((Class)null, new Class[0]);
/*     */     } catch (InitializationError e) {
/*  35 */       throw new RuntimeException("This shouldn't be possible");
/*     */     }
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
/*     */   private static Class<?>[] getAnnotatedClasses(Class<?> klass)
/*     */     throws InitializationError
/*     */   {
/*  54 */     SuiteClasses annotation = (SuiteClasses)klass.getAnnotation(SuiteClasses.class);
/*  55 */     if (annotation == null) {
/*  56 */       throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", new Object[] { klass.getName() }));
/*     */     }
/*  58 */     return annotation.value();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Suite(Class<?> klass, RunnerBuilder builder)
/*     */     throws InitializationError
/*     */   {
/*  70 */     this(builder, klass, getAnnotatedClasses(klass));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Suite(RunnerBuilder builder, Class<?>[] classes)
/*     */     throws InitializationError
/*     */   {
/*  81 */     this(null, builder.runners(null, classes));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Suite(Class<?> klass, Class<?>[] suiteClasses)
/*     */     throws InitializationError
/*     */   {
/*  91 */     this(new AllDefaultPossibilitiesBuilder(true), klass, suiteClasses);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Suite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses)
/*     */     throws InitializationError
/*     */   {
/* 102 */     this(klass, builder.runners(klass, suiteClasses));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Suite(Class<?> klass, List<Runner> runners)
/*     */     throws InitializationError
/*     */   {
/* 112 */     super(klass);
/* 113 */     this.runners = Collections.unmodifiableList(runners);
/*     */   }
/*     */   
/*     */   protected List<Runner> getChildren()
/*     */   {
/* 118 */     return this.runners;
/*     */   }
/*     */   
/*     */   protected Description describeChild(Runner child)
/*     */   {
/* 123 */     return child.getDescription();
/*     */   }
/*     */   
/*     */   protected void runChild(Runner runner, RunNotifier notifier)
/*     */   {
/* 128 */     runner.run(notifier);
/*     */   }
/*     */   
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   @Target({java.lang.annotation.ElementType.TYPE})
/*     */   @Inherited
/*     */   public static @interface SuiteClasses
/*     */   {
/*     */     Class<?>[] value();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/Suite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */