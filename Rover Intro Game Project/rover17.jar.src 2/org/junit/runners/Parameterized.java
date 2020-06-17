/*     */ package org.junit.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.annotation.Inherited;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.lang.annotation.Target;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ import org.junit.runners.model.TestClass;
/*     */ import org.junit.runners.parameterized.BlockJUnit4ClassRunnerWithParametersFactory;
/*     */ import org.junit.runners.parameterized.ParametersRunnerFactory;
/*     */ import org.junit.runners.parameterized.TestWithParameters;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Parameterized
/*     */   extends Suite
/*     */ {
/* 233 */   private static final ParametersRunnerFactory DEFAULT_FACTORY = new BlockJUnit4ClassRunnerWithParametersFactory();
/*     */   
/* 235 */   private static final List<Runner> NO_RUNNERS = Collections.emptyList();
/*     */   
/*     */   private final List<Runner> runners;
/*     */   
/*     */ 
/*     */   public Parameterized(Class<?> klass)
/*     */     throws Throwable
/*     */   {
/* 243 */     super(klass, NO_RUNNERS);
/* 244 */     ParametersRunnerFactory runnerFactory = getParametersRunnerFactory(klass);
/*     */     
/* 246 */     Parameters parameters = (Parameters)getParametersMethod().getAnnotation(Parameters.class);
/*     */     
/* 248 */     this.runners = Collections.unmodifiableList(createRunnersForParameters(allParameters(), parameters.name(), runnerFactory));
/*     */   }
/*     */   
/*     */   private ParametersRunnerFactory getParametersRunnerFactory(Class<?> klass)
/*     */     throws InstantiationException, IllegalAccessException
/*     */   {
/* 254 */     UseParametersRunnerFactory annotation = (UseParametersRunnerFactory)klass.getAnnotation(UseParametersRunnerFactory.class);
/*     */     
/* 256 */     if (annotation == null) {
/* 257 */       return DEFAULT_FACTORY;
/*     */     }
/* 259 */     Class<? extends ParametersRunnerFactory> factoryClass = annotation.value();
/*     */     
/* 261 */     return (ParametersRunnerFactory)factoryClass.newInstance();
/*     */   }
/*     */   
/*     */ 
/*     */   protected List<Runner> getChildren()
/*     */   {
/* 267 */     return this.runners;
/*     */   }
/*     */   
/*     */   private TestWithParameters createTestWithNotNormalizedParameters(String pattern, int index, Object parametersOrSingleParameter)
/*     */   {
/* 272 */     Object[] parameters = { (parametersOrSingleParameter instanceof Object[]) ? (Object[])parametersOrSingleParameter : parametersOrSingleParameter };
/*     */     
/* 274 */     return createTestWithParameters(getTestClass(), pattern, index, parameters);
/*     */   }
/*     */   
/*     */   private Iterable<Object> allParameters()
/*     */     throws Throwable
/*     */   {
/* 280 */     Object parameters = getParametersMethod().invokeExplosively(null, new Object[0]);
/* 281 */     if ((parameters instanceof Iterable))
/* 282 */       return (Iterable)parameters;
/* 283 */     if ((parameters instanceof Object[])) {
/* 284 */       return Arrays.asList((Object[])parameters);
/*     */     }
/* 286 */     throw parametersMethodReturnedWrongType();
/*     */   }
/*     */   
/*     */   private FrameworkMethod getParametersMethod() throws Exception
/*     */   {
/* 291 */     List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(Parameters.class);
/*     */     
/* 293 */     for (FrameworkMethod each : methods) {
/* 294 */       if ((each.isStatic()) && (each.isPublic())) {
/* 295 */         return each;
/*     */       }
/*     */     }
/*     */     
/* 299 */     throw new Exception("No public static parameters method on class " + getTestClass().getName());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private List<Runner> createRunnersForParameters(Iterable<Object> allParameters, String namePattern, ParametersRunnerFactory runnerFactory)
/*     */     throws InitializationError, Exception
/*     */   {
/*     */     try
/*     */     {
/* 309 */       List<TestWithParameters> tests = createTestsForParameters(allParameters, namePattern);
/*     */       
/* 311 */       List<Runner> runners = new ArrayList();
/* 312 */       for (TestWithParameters test : tests) {
/* 313 */         runners.add(runnerFactory.createRunnerForTestWithParameters(test));
/*     */       }
/*     */       
/* 316 */       return runners;
/*     */     } catch (ClassCastException e) {
/* 318 */       throw parametersMethodReturnedWrongType();
/*     */     }
/*     */   }
/*     */   
/*     */   private List<TestWithParameters> createTestsForParameters(Iterable<Object> allParameters, String namePattern)
/*     */     throws Exception
/*     */   {
/* 325 */     int i = 0;
/* 326 */     List<TestWithParameters> children = new ArrayList();
/* 327 */     for (Object parametersOfSingleTest : allParameters) {
/* 328 */       children.add(createTestWithNotNormalizedParameters(namePattern, i++, parametersOfSingleTest));
/*     */     }
/*     */     
/* 331 */     return children;
/*     */   }
/*     */   
/*     */   private Exception parametersMethodReturnedWrongType() throws Exception {
/* 335 */     String className = getTestClass().getName();
/* 336 */     String methodName = getParametersMethod().getName();
/* 337 */     String message = MessageFormat.format("{0}.{1}() must return an Iterable of arrays.", new Object[] { className, methodName });
/*     */     
/*     */ 
/* 340 */     return new Exception(message);
/*     */   }
/*     */   
/*     */   private static TestWithParameters createTestWithParameters(TestClass testClass, String pattern, int index, Object[] parameters)
/*     */   {
/* 345 */     String finalPattern = pattern.replaceAll("\\{index\\}", Integer.toString(index));
/*     */     
/* 347 */     String name = MessageFormat.format(finalPattern, parameters);
/* 348 */     return new TestWithParameters("[" + name + "]", testClass, Arrays.asList(parameters));
/*     */   }
/*     */   
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   @Inherited
/*     */   @Target({java.lang.annotation.ElementType.TYPE})
/*     */   public static @interface UseParametersRunnerFactory
/*     */   {
/*     */     Class<? extends ParametersRunnerFactory> value() default BlockJUnit4ClassRunnerWithParametersFactory.class;
/*     */   }
/*     */   
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   @Target({java.lang.annotation.ElementType.FIELD})
/*     */   public static @interface Parameter
/*     */   {
/*     */     int value() default 0;
/*     */   }
/*     */   
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   @Target({java.lang.annotation.ElementType.METHOD})
/*     */   public static @interface Parameters
/*     */   {
/*     */     String name() default "{index}";
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/Parameterized.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */