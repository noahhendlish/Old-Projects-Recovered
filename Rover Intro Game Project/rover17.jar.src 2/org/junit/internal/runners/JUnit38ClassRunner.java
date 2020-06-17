/*     */ package org.junit.internal.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import junit.extensions.TestDecorator;
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestCase;
/*     */ import junit.framework.TestListener;
/*     */ import junit.framework.TestResult;
/*     */ import junit.framework.TestSuite;
/*     */ import org.junit.runner.Describable;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runner.manipulation.Filterable;
/*     */ import org.junit.runner.manipulation.NoTestsRemainException;
/*     */ import org.junit.runner.manipulation.Sortable;
/*     */ import org.junit.runner.notification.Failure;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ 
/*     */ public class JUnit38ClassRunner extends org.junit.runner.Runner implements Filterable, Sortable
/*     */ {
/*     */   private volatile Test test;
/*     */   
/*     */   private static final class OldTestClassAdaptingListener implements TestListener
/*     */   {
/*     */     private final RunNotifier notifier;
/*     */     
/*     */     private OldTestClassAdaptingListener(RunNotifier notifier)
/*     */     {
/*  29 */       this.notifier = notifier;
/*     */     }
/*     */     
/*     */     public void endTest(Test test) {
/*  33 */       this.notifier.fireTestFinished(asDescription(test));
/*     */     }
/*     */     
/*     */     public void startTest(Test test) {
/*  37 */       this.notifier.fireTestStarted(asDescription(test));
/*     */     }
/*     */     
/*     */     public void addError(Test test, Throwable e)
/*     */     {
/*  42 */       Failure failure = new Failure(asDescription(test), e);
/*  43 */       this.notifier.fireTestFailure(failure);
/*     */     }
/*     */     
/*     */     private Description asDescription(Test test) {
/*  47 */       if ((test instanceof Describable)) {
/*  48 */         Describable facade = (Describable)test;
/*  49 */         return facade.getDescription();
/*     */       }
/*  51 */       return Description.createTestDescription(getEffectiveClass(test), getName(test));
/*     */     }
/*     */     
/*     */     private Class<? extends Test> getEffectiveClass(Test test) {
/*  55 */       return test.getClass();
/*     */     }
/*     */     
/*     */     private String getName(Test test) {
/*  59 */       if ((test instanceof TestCase)) {
/*  60 */         return ((TestCase)test).getName();
/*     */       }
/*  62 */       return test.toString();
/*     */     }
/*     */     
/*     */     public void addFailure(Test test, junit.framework.AssertionFailedError t)
/*     */     {
/*  67 */       addError(test, t);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public JUnit38ClassRunner(Class<?> klass)
/*     */   {
/*  74 */     this(new TestSuite(klass.asSubclass(TestCase.class)));
/*     */   }
/*     */   
/*     */   public JUnit38ClassRunner(Test test)
/*     */   {
/*  79 */     setTest(test);
/*     */   }
/*     */   
/*     */   public void run(RunNotifier notifier)
/*     */   {
/*  84 */     TestResult result = new TestResult();
/*  85 */     result.addListener(createAdaptingListener(notifier));
/*  86 */     getTest().run(result);
/*     */   }
/*     */   
/*     */   public TestListener createAdaptingListener(RunNotifier notifier) {
/*  90 */     return new OldTestClassAdaptingListener(notifier, null);
/*     */   }
/*     */   
/*     */   public Description getDescription()
/*     */   {
/*  95 */     return makeDescription(getTest());
/*     */   }
/*     */   
/*     */   private static Description makeDescription(Test test) {
/*  99 */     if ((test instanceof TestCase)) {
/* 100 */       TestCase tc = (TestCase)test;
/* 101 */       return Description.createTestDescription(tc.getClass(), tc.getName(), getAnnotations(tc));
/*     */     }
/* 103 */     if ((test instanceof TestSuite)) {
/* 104 */       TestSuite ts = (TestSuite)test;
/* 105 */       String name = ts.getName() == null ? createSuiteDescription(ts) : ts.getName();
/* 106 */       Description description = Description.createSuiteDescription(name, new Annotation[0]);
/* 107 */       int n = ts.testCount();
/* 108 */       for (int i = 0; i < n; i++) {
/* 109 */         Description made = makeDescription(ts.testAt(i));
/* 110 */         description.addChild(made);
/*     */       }
/* 112 */       return description; }
/* 113 */     if ((test instanceof Describable)) {
/* 114 */       Describable adapter = (Describable)test;
/* 115 */       return adapter.getDescription(); }
/* 116 */     if ((test instanceof TestDecorator)) {
/* 117 */       TestDecorator decorator = (TestDecorator)test;
/* 118 */       return makeDescription(decorator.getTest());
/*     */     }
/*     */     
/* 121 */     return Description.createSuiteDescription(test.getClass());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Annotation[] getAnnotations(TestCase test)
/*     */   {
/*     */     try
/*     */     {
/* 131 */       java.lang.reflect.Method m = test.getClass().getMethod(test.getName(), new Class[0]);
/* 132 */       return m.getDeclaredAnnotations();
/*     */     }
/*     */     catch (SecurityException e) {}catch (NoSuchMethodException e) {}
/*     */     
/* 136 */     return new Annotation[0];
/*     */   }
/*     */   
/*     */   private static String createSuiteDescription(TestSuite ts) {
/* 140 */     int count = ts.countTestCases();
/* 141 */     String example = count == 0 ? "" : String.format(" [example: %s]", new Object[] { ts.testAt(0) });
/* 142 */     return String.format("TestSuite with %s tests%s", new Object[] { Integer.valueOf(count), example });
/*     */   }
/*     */   
/*     */   public void filter(Filter filter) throws NoTestsRemainException {
/* 146 */     if ((getTest() instanceof Filterable)) {
/* 147 */       Filterable adapter = (Filterable)getTest();
/* 148 */       adapter.filter(filter);
/* 149 */     } else if ((getTest() instanceof TestSuite)) {
/* 150 */       TestSuite suite = (TestSuite)getTest();
/* 151 */       TestSuite filtered = new TestSuite(suite.getName());
/* 152 */       int n = suite.testCount();
/* 153 */       for (int i = 0; i < n; i++) {
/* 154 */         Test test = suite.testAt(i);
/* 155 */         if (filter.shouldRun(makeDescription(test))) {
/* 156 */           filtered.addTest(test);
/*     */         }
/*     */       }
/* 159 */       setTest(filtered);
/* 160 */       if (filtered.testCount() == 0) {
/* 161 */         throw new NoTestsRemainException();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void sort(org.junit.runner.manipulation.Sorter sorter) {
/* 167 */     if ((getTest() instanceof Sortable)) {
/* 168 */       Sortable adapter = (Sortable)getTest();
/* 169 */       adapter.sort(sorter);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setTest(Test test) {
/* 174 */     this.test = test;
/*     */   }
/*     */   
/*     */   private Test getTest() {
/* 178 */     return this.test;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/JUnit38ClassRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */