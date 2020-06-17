/*     */ package org.junit.internal.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runner.manipulation.Filterable;
/*     */ import org.junit.runner.manipulation.NoTestsRemainException;
/*     */ import org.junit.runner.manipulation.Sortable;
/*     */ import org.junit.runner.manipulation.Sorter;
/*     */ import org.junit.runner.notification.Failure;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class JUnit4ClassRunner
/*     */   extends Runner
/*     */   implements Filterable, Sortable
/*     */ {
/*     */   private final List<Method> testMethods;
/*     */   private TestClass testClass;
/*     */   
/*     */   public JUnit4ClassRunner(Class<?> klass)
/*     */     throws InitializationError
/*     */   {
/*  33 */     this.testClass = new TestClass(klass);
/*  34 */     this.testMethods = getTestMethods();
/*  35 */     validate();
/*     */   }
/*     */   
/*     */   protected List<Method> getTestMethods() {
/*  39 */     return this.testClass.getTestMethods();
/*     */   }
/*     */   
/*     */   protected void validate() throws InitializationError {
/*  43 */     MethodValidator methodValidator = new MethodValidator(this.testClass);
/*  44 */     methodValidator.validateMethodsForDefaultRunner();
/*  45 */     methodValidator.assertValid();
/*     */   }
/*     */   
/*     */   public void run(final RunNotifier notifier)
/*     */   {
/*  50 */     new ClassRoadie(notifier, this.testClass, getDescription(), new Runnable() {
/*     */       public void run() {
/*  52 */         JUnit4ClassRunner.this.runMethods(notifier);
/*     */       }
/*     */     }).runProtected();
/*     */   }
/*     */   
/*     */   protected void runMethods(RunNotifier notifier) {
/*  58 */     for (Method method : this.testMethods) {
/*  59 */       invokeTestMethod(method, notifier);
/*     */     }
/*     */   }
/*     */   
/*     */   public Description getDescription()
/*     */   {
/*  65 */     Description spec = Description.createSuiteDescription(getName(), classAnnotations());
/*  66 */     List<Method> testMethods = this.testMethods;
/*  67 */     for (Method method : testMethods) {
/*  68 */       spec.addChild(methodDescription(method));
/*     */     }
/*  70 */     return spec;
/*     */   }
/*     */   
/*     */   protected Annotation[] classAnnotations() {
/*  74 */     return this.testClass.getJavaClass().getAnnotations();
/*     */   }
/*     */   
/*     */   protected String getName() {
/*  78 */     return getTestClass().getName();
/*     */   }
/*     */   
/*     */   protected Object createTest() throws Exception {
/*  82 */     return getTestClass().getConstructor().newInstance(new Object[0]);
/*     */   }
/*     */   
/*     */   protected void invokeTestMethod(Method method, RunNotifier notifier) {
/*  86 */     Description description = methodDescription(method);
/*     */     Object test;
/*     */     try {
/*  89 */       test = createTest();
/*     */     } catch (InvocationTargetException e) {
/*  91 */       testAborted(notifier, description, e.getCause());
/*  92 */       return;
/*     */     } catch (Exception e) {
/*  94 */       testAborted(notifier, description, e);
/*  95 */       return;
/*     */     }
/*  97 */     TestMethod testMethod = wrapMethod(method);
/*  98 */     new MethodRoadie(test, testMethod, notifier, description).run();
/*     */   }
/*     */   
/*     */   private void testAborted(RunNotifier notifier, Description description, Throwable e)
/*     */   {
/* 103 */     notifier.fireTestStarted(description);
/* 104 */     notifier.fireTestFailure(new Failure(description, e));
/* 105 */     notifier.fireTestFinished(description);
/*     */   }
/*     */   
/*     */   protected TestMethod wrapMethod(Method method) {
/* 109 */     return new TestMethod(method, this.testClass);
/*     */   }
/*     */   
/*     */   protected String testName(Method method) {
/* 113 */     return method.getName();
/*     */   }
/*     */   
/*     */   protected Description methodDescription(Method method) {
/* 117 */     return Description.createTestDescription(getTestClass().getJavaClass(), testName(method), testAnnotations(method));
/*     */   }
/*     */   
/*     */   protected Annotation[] testAnnotations(Method method) {
/* 121 */     return method.getAnnotations();
/*     */   }
/*     */   
/*     */   public void filter(Filter filter) throws NoTestsRemainException {
/* 125 */     for (Iterator<Method> iter = this.testMethods.iterator(); iter.hasNext();) {
/* 126 */       Method method = (Method)iter.next();
/* 127 */       if (!filter.shouldRun(methodDescription(method))) {
/* 128 */         iter.remove();
/*     */       }
/*     */     }
/* 131 */     if (this.testMethods.isEmpty()) {
/* 132 */       throw new NoTestsRemainException();
/*     */     }
/*     */   }
/*     */   
/*     */   public void sort(final Sorter sorter) {
/* 137 */     Collections.sort(this.testMethods, new Comparator() {
/*     */       public int compare(Method o1, Method o2) {
/* 139 */         return sorter.compare(JUnit4ClassRunner.this.methodDescription(o1), JUnit4ClassRunner.this.methodDescription(o2));
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   protected TestClass getTestClass() {
/* 145 */     return this.testClass;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/JUnit4ClassRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */