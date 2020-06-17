/*     */ package org.junit.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.junit.AfterClass;
/*     */ import org.junit.BeforeClass;
/*     */ import org.junit.ClassRule;
/*     */ import org.junit.internal.AssumptionViolatedException;
/*     */ import org.junit.internal.runners.model.EachTestNotifier;
/*     */ import org.junit.internal.runners.rules.RuleMemberValidator;
/*     */ import org.junit.internal.runners.statements.RunAfters;
/*     */ import org.junit.internal.runners.statements.RunBefores;
/*     */ import org.junit.rules.RunRules;
/*     */ import org.junit.rules.TestRule;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runner.manipulation.Filterable;
/*     */ import org.junit.runner.manipulation.NoTestsRemainException;
/*     */ import org.junit.runner.manipulation.Sortable;
/*     */ import org.junit.runner.manipulation.Sorter;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ import org.junit.runner.notification.StoppedByUserException;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ import org.junit.runners.model.RunnerScheduler;
/*     */ import org.junit.runners.model.Statement;
/*     */ import org.junit.runners.model.TestClass;
/*     */ import org.junit.validator.AnnotationsValidator;
/*     */ import org.junit.validator.PublicClassValidator;
/*     */ import org.junit.validator.TestClassValidator;
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
/*     */ public abstract class ParentRunner<T>
/*     */   extends Runner
/*     */   implements Filterable, Sortable
/*     */ {
/*  60 */   private static final List<TestClassValidator> VALIDATORS = Arrays.asList(new TestClassValidator[] { new AnnotationsValidator(), new PublicClassValidator() });
/*     */   
/*     */ 
/*  63 */   private final Object childrenLock = new Object();
/*     */   
/*     */   private final TestClass testClass;
/*     */   
/*  67 */   private volatile Collection<T> filteredChildren = null;
/*     */   
/*  69 */   private volatile RunnerScheduler scheduler = new RunnerScheduler() {
/*     */     public void schedule(Runnable childStatement) {
/*  71 */       childStatement.run();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void finished() {}
/*     */   };
/*     */   
/*     */ 
/*     */   protected ParentRunner(Class<?> testClass)
/*     */     throws InitializationError
/*     */   {
/*  83 */     this.testClass = createTestClass(testClass);
/*  84 */     validate();
/*     */   }
/*     */   
/*     */   protected TestClass createTestClass(Class<?> testClass) {
/*  88 */     return new TestClass(testClass);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract List<T> getChildren();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract Description describeChild(T paramT);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void runChild(T paramT, RunNotifier paramRunNotifier);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void collectInitializationErrors(List<Throwable> errors)
/*     */   {
/* 125 */     validatePublicVoidNoArgMethods(BeforeClass.class, true, errors);
/* 126 */     validatePublicVoidNoArgMethods(AfterClass.class, true, errors);
/* 127 */     validateClassRules(errors);
/* 128 */     applyValidators(errors);
/*     */   }
/*     */   
/*     */   private void applyValidators(List<Throwable> errors) {
/* 132 */     if (getTestClass().getJavaClass() != null) {
/* 133 */       for (TestClassValidator each : VALIDATORS) {
/* 134 */         errors.addAll(each.validateTestClass(getTestClass()));
/*     */       }
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
/*     */   protected void validatePublicVoidNoArgMethods(Class<? extends Annotation> annotation, boolean isStatic, List<Throwable> errors)
/*     */   {
/* 152 */     List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(annotation);
/*     */     
/* 154 */     for (FrameworkMethod eachTestMethod : methods) {
/* 155 */       eachTestMethod.validatePublicVoidNoArg(isStatic, errors);
/*     */     }
/*     */   }
/*     */   
/*     */   private void validateClassRules(List<Throwable> errors) {
/* 160 */     RuleMemberValidator.CLASS_RULE_VALIDATOR.validate(getTestClass(), errors);
/* 161 */     RuleMemberValidator.CLASS_RULE_METHOD_VALIDATOR.validate(getTestClass(), errors);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement classBlock(RunNotifier notifier)
/*     */   {
/* 190 */     Statement statement = childrenInvoker(notifier);
/* 191 */     if (!areAllChildrenIgnored()) {
/* 192 */       statement = withBeforeClasses(statement);
/* 193 */       statement = withAfterClasses(statement);
/* 194 */       statement = withClassRules(statement);
/*     */     }
/* 196 */     return statement;
/*     */   }
/*     */   
/*     */   private boolean areAllChildrenIgnored() {
/* 200 */     for (T child : getFilteredChildren()) {
/* 201 */       if (!isIgnored(child)) {
/* 202 */         return false;
/*     */       }
/*     */     }
/* 205 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement withBeforeClasses(Statement statement)
/*     */   {
/* 214 */     List<FrameworkMethod> befores = this.testClass.getAnnotatedMethods(BeforeClass.class);
/*     */     
/* 216 */     return befores.isEmpty() ? statement : new RunBefores(statement, befores, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement withAfterClasses(Statement statement)
/*     */   {
/* 228 */     List<FrameworkMethod> afters = this.testClass.getAnnotatedMethods(AfterClass.class);
/*     */     
/* 230 */     return afters.isEmpty() ? statement : new RunAfters(statement, afters, null);
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
/*     */   private Statement withClassRules(Statement statement)
/*     */   {
/* 244 */     List<TestRule> classRules = classRules();
/* 245 */     return classRules.isEmpty() ? statement : new RunRules(statement, classRules, getDescription());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<TestRule> classRules()
/*     */   {
/* 254 */     List<TestRule> result = this.testClass.getAnnotatedMethodValues(null, ClassRule.class, TestRule.class);
/* 255 */     result.addAll(this.testClass.getAnnotatedFieldValues(null, ClassRule.class, TestRule.class));
/* 256 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement childrenInvoker(final RunNotifier notifier)
/*     */   {
/* 265 */     new Statement()
/*     */     {
/*     */       public void evaluate() {
/* 268 */         ParentRunner.this.runChildren(notifier);
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isIgnored(T child)
/*     */   {
/* 281 */     return false;
/*     */   }
/*     */   
/*     */   private void runChildren(final RunNotifier notifier) {
/* 285 */     RunnerScheduler currentScheduler = this.scheduler;
/*     */     try {
/* 287 */       for (final T each : getFilteredChildren()) {
/* 288 */         currentScheduler.schedule(new Runnable() {
/*     */           public void run() {
/* 290 */             ParentRunner.this.runChild(each, notifier);
/*     */           }
/*     */         });
/*     */       }
/*     */     } finally {
/* 295 */       currentScheduler.finished();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String getName()
/*     */   {
/* 303 */     return this.testClass.getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final TestClass getTestClass()
/*     */   {
/* 314 */     return this.testClass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void runLeaf(Statement statement, Description description, RunNotifier notifier)
/*     */   {
/* 322 */     EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
/* 323 */     eachNotifier.fireTestStarted();
/*     */     try {
/* 325 */       statement.evaluate();
/*     */     } catch (AssumptionViolatedException e) {
/* 327 */       eachNotifier.addFailedAssumption(e);
/*     */     } catch (Throwable e) {
/* 329 */       eachNotifier.addFailure(e);
/*     */     } finally {
/* 331 */       eachNotifier.fireTestFinished();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Annotation[] getRunnerAnnotations()
/*     */   {
/* 340 */     return this.testClass.getAnnotations();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Description getDescription()
/*     */   {
/* 349 */     Description description = Description.createSuiteDescription(getName(), getRunnerAnnotations());
/*     */     
/* 351 */     for (T child : getFilteredChildren()) {
/* 352 */       description.addChild(describeChild(child));
/*     */     }
/* 354 */     return description;
/*     */   }
/*     */   
/*     */   public void run(RunNotifier notifier)
/*     */   {
/* 359 */     EachTestNotifier testNotifier = new EachTestNotifier(notifier, getDescription());
/*     */     try
/*     */     {
/* 362 */       Statement statement = classBlock(notifier);
/* 363 */       statement.evaluate();
/*     */     } catch (AssumptionViolatedException e) {
/* 365 */       testNotifier.addFailedAssumption(e);
/*     */     } catch (StoppedByUserException e) {
/* 367 */       throw e;
/*     */     } catch (Throwable e) {
/* 369 */       testNotifier.addFailure(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void filter(Filter filter)
/*     */     throws NoTestsRemainException
/*     */   {
/* 378 */     synchronized (this.childrenLock) {
/* 379 */       List<T> children = new ArrayList(getFilteredChildren());
/* 380 */       for (Iterator<T> iter = children.iterator(); iter.hasNext();) {
/* 381 */         T each = iter.next();
/* 382 */         if (shouldRun(filter, each)) {
/*     */           try {
/* 384 */             filter.apply(each);
/*     */           } catch (NoTestsRemainException e) {
/* 386 */             iter.remove();
/*     */           }
/*     */         } else {
/* 389 */           iter.remove();
/*     */         }
/*     */       }
/* 392 */       this.filteredChildren = Collections.unmodifiableCollection(children);
/* 393 */       if (this.filteredChildren.isEmpty()) {
/* 394 */         throw new NoTestsRemainException();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void sort(Sorter sorter) {
/* 400 */     synchronized (this.childrenLock) {
/* 401 */       for (T each : getFilteredChildren()) {
/* 402 */         sorter.apply(each);
/*     */       }
/* 404 */       List<T> sortedChildren = new ArrayList(getFilteredChildren());
/* 405 */       Collections.sort(sortedChildren, comparator(sorter));
/* 406 */       this.filteredChildren = Collections.unmodifiableCollection(sortedChildren);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void validate()
/*     */     throws InitializationError
/*     */   {
/* 415 */     List<Throwable> errors = new ArrayList();
/* 416 */     collectInitializationErrors(errors);
/* 417 */     if (!errors.isEmpty()) {
/* 418 */       throw new InitializationError(errors);
/*     */     }
/*     */   }
/*     */   
/*     */   private Collection<T> getFilteredChildren() {
/* 423 */     if (this.filteredChildren == null) {
/* 424 */       synchronized (this.childrenLock) {
/* 425 */         if (this.filteredChildren == null) {
/* 426 */           this.filteredChildren = Collections.unmodifiableCollection(getChildren());
/*     */         }
/*     */       }
/*     */     }
/* 430 */     return this.filteredChildren;
/*     */   }
/*     */   
/*     */   private boolean shouldRun(Filter filter, T each) {
/* 434 */     return filter.shouldRun(describeChild(each));
/*     */   }
/*     */   
/*     */   private Comparator<? super T> comparator(final Sorter sorter) {
/* 438 */     new Comparator() {
/*     */       public int compare(T o1, T o2) {
/* 440 */         return sorter.compare(ParentRunner.this.describeChild(o1), ParentRunner.this.describeChild(o2));
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setScheduler(RunnerScheduler scheduler)
/*     */   {
/* 450 */     this.scheduler = scheduler;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/ParentRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */