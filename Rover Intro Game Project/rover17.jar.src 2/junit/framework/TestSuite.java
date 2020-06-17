/*     */ package junit.framework;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import org.junit.internal.MethodSorter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestSuite
/*     */   implements Test
/*     */ {
/*     */   private String fName;
/*     */   
/*     */   public static Test createTest(Class<?> theClass, String name)
/*     */   {
/*     */     Constructor<?> constructor;
/*     */     try
/*     */     {
/*  53 */       constructor = getTestConstructor(theClass);
/*     */     } catch (NoSuchMethodException e) {
/*  55 */       return warning("Class " + theClass.getName() + " has no public constructor TestCase(String name) or TestCase()");
/*     */     }
/*     */     Object test;
/*     */     try {
/*  59 */       if (constructor.getParameterTypes().length == 0) {
/*  60 */         Object test = constructor.newInstance(new Object[0]);
/*  61 */         if ((test instanceof TestCase)) {
/*  62 */           ((TestCase)test).setName(name);
/*     */         }
/*     */       } else {
/*  65 */         test = constructor.newInstance(new Object[] { name });
/*     */       }
/*     */     } catch (InstantiationException e) {
/*  68 */       return warning("Cannot instantiate test case: " + name + " (" + exceptionToString(e) + ")");
/*     */     } catch (InvocationTargetException e) {
/*  70 */       return warning("Exception in constructor: " + name + " (" + exceptionToString(e.getTargetException()) + ")");
/*     */     } catch (IllegalAccessException e) {
/*  72 */       return warning("Cannot access test case: " + name + " (" + exceptionToString(e) + ")");
/*     */     }
/*  74 */     return (Test)test;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Constructor<?> getTestConstructor(Class<?> theClass)
/*     */     throws NoSuchMethodException
/*     */   {
/*     */     try
/*     */     {
/*  83 */       return theClass.getConstructor(new Class[] { String.class });
/*     */     }
/*     */     catch (NoSuchMethodException e) {}
/*     */     
/*  87 */     return theClass.getConstructor(new Class[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Test warning(final String message)
/*     */   {
/*  94 */     new TestCase("warning")
/*     */     {
/*     */       protected void runTest() {
/*  97 */         fail(message);
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static String exceptionToString(Throwable e)
/*     */   {
/* 106 */     StringWriter stringWriter = new StringWriter();
/* 107 */     PrintWriter writer = new PrintWriter(stringWriter);
/* 108 */     e.printStackTrace(writer);
/* 109 */     return stringWriter.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 114 */   private Vector<Test> fTests = new Vector(10);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TestSuite() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TestSuite(Class<?> theClass)
/*     */   {
/* 129 */     addTestsFromTestCase(theClass);
/*     */   }
/*     */   
/*     */   private void addTestsFromTestCase(Class<?> theClass) {
/* 133 */     this.fName = theClass.getName();
/*     */     try {
/* 135 */       getTestConstructor(theClass);
/*     */     } catch (NoSuchMethodException e) {
/* 137 */       addTest(warning("Class " + theClass.getName() + " has no public constructor TestCase(String name) or TestCase()"));
/* 138 */       return;
/*     */     }
/*     */     
/* 141 */     if (!Modifier.isPublic(theClass.getModifiers())) {
/* 142 */       addTest(warning("Class " + theClass.getName() + " is not public"));
/* 143 */       return;
/*     */     }
/*     */     
/* 146 */     Class<?> superClass = theClass;
/* 147 */     List<String> names = new ArrayList();
/* 148 */     while (Test.class.isAssignableFrom(superClass)) {
/* 149 */       for (Method each : MethodSorter.getDeclaredMethods(superClass)) {
/* 150 */         addTestMethod(each, names, theClass);
/*     */       }
/* 152 */       superClass = superClass.getSuperclass();
/*     */     }
/* 154 */     if (this.fTests.size() == 0) {
/* 155 */       addTest(warning("No tests found in " + theClass.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TestSuite(Class<? extends TestCase> theClass, String name)
/*     */   {
/* 165 */     this(theClass);
/* 166 */     setName(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TestSuite(String name)
/*     */   {
/* 173 */     setName(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TestSuite(Class<?>... classes)
/*     */   {
/* 182 */     for (Class<?> each : classes) {
/* 183 */       addTest(testCaseForClass(each));
/*     */     }
/*     */   }
/*     */   
/*     */   private Test testCaseForClass(Class<?> each) {
/* 188 */     if (TestCase.class.isAssignableFrom(each)) {
/* 189 */       return new TestSuite(each.asSubclass(TestCase.class));
/*     */     }
/* 191 */     return warning(each.getCanonicalName() + " does not extend TestCase");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TestSuite(Class<? extends TestCase>[] classes, String name)
/*     */   {
/* 201 */     this(classes);
/* 202 */     setName(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addTest(Test test)
/*     */   {
/* 209 */     this.fTests.add(test);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addTestSuite(Class<? extends TestCase> testClass)
/*     */   {
/* 216 */     addTest(new TestSuite(testClass));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int countTestCases()
/*     */   {
/* 223 */     int count = 0;
/* 224 */     for (Test each : this.fTests) {
/* 225 */       count += each.countTestCases();
/*     */     }
/* 227 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 236 */     return this.fName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void run(TestResult result)
/*     */   {
/* 243 */     for (Test each : this.fTests) {
/* 244 */       if (result.shouldStop()) {
/*     */         break;
/*     */       }
/* 247 */       runTest(each, result);
/*     */     }
/*     */   }
/*     */   
/*     */   public void runTest(Test test, TestResult result) {
/* 252 */     test.run(result);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 261 */     this.fName = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Test testAt(int index)
/*     */   {
/* 268 */     return (Test)this.fTests.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int testCount()
/*     */   {
/* 275 */     return this.fTests.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Enumeration<Test> tests()
/*     */   {
/* 282 */     return this.fTests.elements();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 289 */     if (getName() != null) {
/* 290 */       return getName();
/*     */     }
/* 292 */     return super.toString();
/*     */   }
/*     */   
/*     */   private void addTestMethod(Method m, List<String> names, Class<?> theClass) {
/* 296 */     String name = m.getName();
/* 297 */     if (names.contains(name)) {
/* 298 */       return;
/*     */     }
/* 300 */     if (!isPublicTestMethod(m)) {
/* 301 */       if (isTestMethod(m)) {
/* 302 */         addTest(warning("Test method isn't public: " + m.getName() + "(" + theClass.getCanonicalName() + ")"));
/*     */       }
/* 304 */       return;
/*     */     }
/* 306 */     names.add(name);
/* 307 */     addTest(createTest(theClass, name));
/*     */   }
/*     */   
/*     */   private boolean isPublicTestMethod(Method m) {
/* 311 */     return (isTestMethod(m)) && (Modifier.isPublic(m.getModifiers()));
/*     */   }
/*     */   
/*     */   private boolean isTestMethod(Method m) {
/* 315 */     return (m.getParameterTypes().length == 0) && (m.getName().startsWith("test")) && (m.getReturnType().equals(Void.TYPE));
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/TestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */