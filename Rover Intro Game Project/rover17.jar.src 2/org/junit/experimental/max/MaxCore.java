/*     */ package org.junit.experimental.max;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import junit.framework.TestSuite;
/*     */ import org.junit.internal.requests.SortingRequest;
/*     */ import org.junit.internal.runners.ErrorReportingRunner;
/*     */ import org.junit.internal.runners.JUnit38ClassRunner;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.JUnitCore;
/*     */ import org.junit.runner.Request;
/*     */ import org.junit.runner.Result;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runners.Suite;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaxCore
/*     */ {
/*     */   private static final String MALFORMED_JUNIT_3_TEST_CLASS_PREFIX = "malformed JUnit 3 test class: ";
/*     */   private final MaxHistory history;
/*     */   
/*     */   @Deprecated
/*     */   public static MaxCore forFolder(String folderName)
/*     */   {
/*  42 */     return storedLocally(new File(folderName));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static MaxCore storedLocally(File storedResults)
/*     */   {
/*  49 */     return new MaxCore(storedResults);
/*     */   }
/*     */   
/*     */ 
/*     */   private MaxCore(File storedResults)
/*     */   {
/*  55 */     this.history = MaxHistory.forFolder(storedResults);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result run(Class<?> testClass)
/*     */   {
/*  64 */     return run(Request.aClass(testClass));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result run(Request request)
/*     */   {
/*  74 */     return run(request, new JUnitCore());
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
/*     */   public Result run(Request request, JUnitCore core)
/*     */   {
/*  88 */     core.addListener(this.history.listener());
/*  89 */     return core.run(sortRequest(request).getRunner());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Request sortRequest(Request request)
/*     */   {
/*  96 */     if ((request instanceof SortingRequest))
/*     */     {
/*  98 */       return request;
/*     */     }
/* 100 */     List<Description> leaves = findLeaves(request);
/* 101 */     Collections.sort(leaves, this.history.testComparator());
/* 102 */     return constructLeafRequest(leaves);
/*     */   }
/*     */   
/*     */   private Request constructLeafRequest(List<Description> leaves) {
/* 106 */     final List<Runner> runners = new ArrayList();
/* 107 */     for (Description each : leaves) {
/* 108 */       runners.add(buildRunner(each));
/*     */     }
/* 110 */     new Request()
/*     */     {
/*     */       public Runner getRunner() {
/*     */         try {
/* 114 */           new Suite((Class)null, runners) {};
/*     */         }
/*     */         catch (InitializationError e) {
/* 117 */           return new ErrorReportingRunner(null, e);
/*     */         }
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   private Runner buildRunner(Description each) {
/* 124 */     if (each.toString().equals("TestSuite with 0 tests")) {
/* 125 */       return Suite.emptySuite();
/*     */     }
/* 127 */     if (each.toString().startsWith("malformed JUnit 3 test class: "))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 132 */       return new JUnit38ClassRunner(new TestSuite(getMalformedTestClass(each)));
/*     */     }
/* 134 */     Class<?> type = each.getTestClass();
/* 135 */     if (type == null) {
/* 136 */       throw new RuntimeException("Can't build a runner from description [" + each + "]");
/*     */     }
/* 138 */     String methodName = each.getMethodName();
/* 139 */     if (methodName == null) {
/* 140 */       return Request.aClass(type).getRunner();
/*     */     }
/* 142 */     return Request.method(type, methodName).getRunner();
/*     */   }
/*     */   
/*     */   private Class<?> getMalformedTestClass(Description each) {
/*     */     try {
/* 147 */       return Class.forName(each.toString().replace("malformed JUnit 3 test class: ", ""));
/*     */     } catch (ClassNotFoundException e) {}
/* 149 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Description> sortedLeavesForTest(Request request)
/*     */   {
/* 159 */     return findLeaves(sortRequest(request));
/*     */   }
/*     */   
/*     */   private List<Description> findLeaves(Request request) {
/* 163 */     List<Description> results = new ArrayList();
/* 164 */     findLeaves(null, request.getRunner().getDescription(), results);
/* 165 */     return results;
/*     */   }
/*     */   
/*     */   private void findLeaves(Description parent, Description description, List<Description> results) {
/* 169 */     if (description.getChildren().isEmpty()) {
/* 170 */       if (description.toString().equals("warning(junit.framework.TestSuite$1)")) {
/* 171 */         results.add(Description.createSuiteDescription("malformed JUnit 3 test class: " + parent, new Annotation[0]));
/*     */       } else {
/* 173 */         results.add(description);
/*     */       }
/*     */     } else {
/* 176 */       for (Description each : description.getChildren()) {
/* 177 */         findLeaves(description, each, results);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/max/MaxCore.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */