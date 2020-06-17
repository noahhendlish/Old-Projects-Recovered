/*     */ package org.junit.runners;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.junit.After;
/*     */ import org.junit.Before;
/*     */ import org.junit.Ignore;
/*     */ import org.junit.Rule;
/*     */ import org.junit.Test;
/*     */ import org.junit.Test.None;
/*     */ import org.junit.internal.runners.model.ReflectiveCallable;
/*     */ import org.junit.internal.runners.rules.RuleMemberValidator;
/*     */ import org.junit.internal.runners.statements.ExpectException;
/*     */ import org.junit.internal.runners.statements.Fail;
/*     */ import org.junit.internal.runners.statements.FailOnTimeout;
/*     */ import org.junit.internal.runners.statements.FailOnTimeout.Builder;
/*     */ import org.junit.internal.runners.statements.InvokeMethod;
/*     */ import org.junit.internal.runners.statements.RunAfters;
/*     */ import org.junit.internal.runners.statements.RunBefores;
/*     */ import org.junit.rules.MethodRule;
/*     */ import org.junit.rules.RunRules;
/*     */ import org.junit.rules.TestRule;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ import org.junit.runners.model.Statement;
/*     */ import org.junit.runners.model.TestClass;
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
/*     */ public class BlockJUnit4ClassRunner
/*     */   extends ParentRunner<FrameworkMethod>
/*     */ {
/*  58 */   private final ConcurrentHashMap<FrameworkMethod, Description> methodDescriptions = new ConcurrentHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */   public BlockJUnit4ClassRunner(Class<?> klass)
/*     */     throws InitializationError
/*     */   {
/*  65 */     super(klass);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void runChild(FrameworkMethod method, RunNotifier notifier)
/*     */   {
/*  74 */     Description description = describeChild(method);
/*  75 */     if (isIgnored(method)) {
/*  76 */       notifier.fireTestIgnored(description);
/*     */     } else {
/*  78 */       runLeaf(methodBlock(method), description, notifier);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isIgnored(FrameworkMethod child)
/*     */   {
/*  88 */     return child.getAnnotation(Ignore.class) != null;
/*     */   }
/*     */   
/*     */   protected Description describeChild(FrameworkMethod method)
/*     */   {
/*  93 */     Description description = (Description)this.methodDescriptions.get(method);
/*     */     
/*  95 */     if (description == null) {
/*  96 */       description = Description.createTestDescription(getTestClass().getJavaClass(), testName(method), method.getAnnotations());
/*     */       
/*  98 */       this.methodDescriptions.putIfAbsent(method, description);
/*     */     }
/*     */     
/* 101 */     return description;
/*     */   }
/*     */   
/*     */   protected List<FrameworkMethod> getChildren()
/*     */   {
/* 106 */     return computeTestMethods();
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
/*     */   protected List<FrameworkMethod> computeTestMethods()
/*     */   {
/* 119 */     return getTestClass().getAnnotatedMethods(Test.class);
/*     */   }
/*     */   
/*     */   protected void collectInitializationErrors(List<Throwable> errors)
/*     */   {
/* 124 */     super.collectInitializationErrors(errors);
/*     */     
/* 126 */     validateNoNonStaticInnerClass(errors);
/* 127 */     validateConstructor(errors);
/* 128 */     validateInstanceMethods(errors);
/* 129 */     validateFields(errors);
/* 130 */     validateMethods(errors);
/*     */   }
/*     */   
/*     */   protected void validateNoNonStaticInnerClass(List<Throwable> errors) {
/* 134 */     if (getTestClass().isANonStaticInnerClass()) {
/* 135 */       String gripe = "The inner class " + getTestClass().getName() + " is not static.";
/*     */       
/* 137 */       errors.add(new Exception(gripe));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void validateConstructor(List<Throwable> errors)
/*     */   {
/* 147 */     validateOnlyOneConstructor(errors);
/* 148 */     validateZeroArgConstructor(errors);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void validateOnlyOneConstructor(List<Throwable> errors)
/*     */   {
/* 156 */     if (!hasOneConstructor()) {
/* 157 */       String gripe = "Test class should have exactly one public constructor";
/* 158 */       errors.add(new Exception(gripe));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void validateZeroArgConstructor(List<Throwable> errors)
/*     */   {
/* 167 */     if ((!getTestClass().isANonStaticInnerClass()) && (hasOneConstructor()) && (getTestClass().getOnlyConstructor().getParameterTypes().length != 0))
/*     */     {
/*     */ 
/* 170 */       String gripe = "Test class should have exactly one public zero-argument constructor";
/* 171 */       errors.add(new Exception(gripe));
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean hasOneConstructor() {
/* 176 */     return getTestClass().getJavaClass().getConstructors().length == 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   protected void validateInstanceMethods(List<Throwable> errors)
/*     */   {
/* 186 */     validatePublicVoidNoArgMethods(After.class, false, errors);
/* 187 */     validatePublicVoidNoArgMethods(Before.class, false, errors);
/* 188 */     validateTestMethods(errors);
/*     */     
/* 190 */     if (computeTestMethods().size() == 0) {
/* 191 */       errors.add(new Exception("No runnable methods"));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void validateFields(List<Throwable> errors) {
/* 196 */     RuleMemberValidator.RULE_VALIDATOR.validate(getTestClass(), errors);
/*     */   }
/*     */   
/*     */   private void validateMethods(List<Throwable> errors) {
/* 200 */     RuleMemberValidator.RULE_METHOD_VALIDATOR.validate(getTestClass(), errors);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void validateTestMethods(List<Throwable> errors)
/*     */   {
/* 208 */     validatePublicVoidNoArgMethods(Test.class, false, errors);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object createTest()
/*     */     throws Exception
/*     */   {
/* 217 */     return getTestClass().getOnlyConstructor().newInstance(new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String testName(FrameworkMethod method)
/*     */   {
/* 225 */     return method.getName();
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
/*     */   protected Statement methodBlock(FrameworkMethod method)
/*     */   {
/*     */     Object test;
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
/*     */     try
/*     */     {
/* 263 */       test = new ReflectiveCallable()
/*     */       {
/*     */         protected Object runReflectiveCall() throws Throwable {
/* 266 */           return BlockJUnit4ClassRunner.this.createTest();
/*     */         }
/*     */       }.run();
/*     */     } catch (Throwable e) {
/* 270 */       return new Fail(e);
/*     */     }
/*     */     
/* 273 */     Statement statement = methodInvoker(method, test);
/* 274 */     statement = possiblyExpectingExceptions(method, test, statement);
/* 275 */     statement = withPotentialTimeout(method, test, statement);
/* 276 */     statement = withBefores(method, test, statement);
/* 277 */     statement = withAfters(method, test, statement);
/* 278 */     statement = withRules(method, test, statement);
/* 279 */     return statement;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement methodInvoker(FrameworkMethod method, Object test)
/*     */   {
/* 290 */     return new InvokeMethod(method, test);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement possiblyExpectingExceptions(FrameworkMethod method, Object test, Statement next)
/*     */   {
/* 301 */     Test annotation = (Test)method.getAnnotation(Test.class);
/* 302 */     return expectsException(annotation) ? new ExpectException(next, getExpectedException(annotation)) : next;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   protected Statement withPotentialTimeout(FrameworkMethod method, Object test, Statement next)
/*     */   {
/* 314 */     long timeout = getTimeout((Test)method.getAnnotation(Test.class));
/* 315 */     if (timeout <= 0L) {
/* 316 */       return next;
/*     */     }
/* 318 */     return FailOnTimeout.builder().withTimeout(timeout, TimeUnit.MILLISECONDS).build(next);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Statement withBefores(FrameworkMethod method, Object target, Statement statement)
/*     */   {
/* 330 */     List<FrameworkMethod> befores = getTestClass().getAnnotatedMethods(Before.class);
/*     */     
/* 332 */     return befores.isEmpty() ? statement : new RunBefores(statement, befores, target);
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
/*     */   protected Statement withAfters(FrameworkMethod method, Object target, Statement statement)
/*     */   {
/* 345 */     List<FrameworkMethod> afters = getTestClass().getAnnotatedMethods(After.class);
/*     */     
/* 347 */     return afters.isEmpty() ? statement : new RunAfters(statement, afters, target);
/*     */   }
/*     */   
/*     */ 
/*     */   private Statement withRules(FrameworkMethod method, Object target, Statement statement)
/*     */   {
/* 353 */     List<TestRule> testRules = getTestRules(target);
/* 354 */     Statement result = statement;
/* 355 */     result = withMethodRules(method, testRules, target, result);
/* 356 */     result = withTestRules(method, testRules, result);
/*     */     
/* 358 */     return result;
/*     */   }
/*     */   
/*     */   private Statement withMethodRules(FrameworkMethod method, List<TestRule> testRules, Object target, Statement result)
/*     */   {
/* 363 */     for (MethodRule each : getMethodRules(target)) {
/* 364 */       if (!testRules.contains(each)) {
/* 365 */         result = each.apply(result, method, target);
/*     */       }
/*     */     }
/* 368 */     return result;
/*     */   }
/*     */   
/*     */   private List<MethodRule> getMethodRules(Object target) {
/* 372 */     return rules(target);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<MethodRule> rules(Object target)
/*     */   {
/* 381 */     List<MethodRule> rules = getTestClass().getAnnotatedMethodValues(target, Rule.class, MethodRule.class);
/*     */     
/*     */ 
/* 384 */     rules.addAll(getTestClass().getAnnotatedFieldValues(target, Rule.class, MethodRule.class));
/*     */     
/*     */ 
/* 387 */     return rules;
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
/*     */   private Statement withTestRules(FrameworkMethod method, List<TestRule> testRules, Statement statement)
/*     */   {
/* 400 */     return testRules.isEmpty() ? statement : new RunRules(statement, testRules, describeChild(method));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<TestRule> getTestRules(Object target)
/*     */   {
/* 410 */     List<TestRule> result = getTestClass().getAnnotatedMethodValues(target, Rule.class, TestRule.class);
/*     */     
/*     */ 
/* 413 */     result.addAll(getTestClass().getAnnotatedFieldValues(target, Rule.class, TestRule.class));
/*     */     
/*     */ 
/* 416 */     return result;
/*     */   }
/*     */   
/*     */   private Class<? extends Throwable> getExpectedException(Test annotation) {
/* 420 */     if ((annotation == null) || (annotation.expected() == Test.None.class)) {
/* 421 */       return null;
/*     */     }
/* 423 */     return annotation.expected();
/*     */   }
/*     */   
/*     */   private boolean expectsException(Test annotation)
/*     */   {
/* 428 */     return getExpectedException(annotation) != null;
/*     */   }
/*     */   
/*     */   private long getTimeout(Test annotation) {
/* 432 */     if (annotation == null) {
/* 433 */       return 0L;
/*     */     }
/* 435 */     return annotation.timeout();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/BlockJUnit4ClassRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */