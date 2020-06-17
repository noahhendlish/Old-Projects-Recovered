/*     */ package junit.runner;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Properties;
/*     */ import junit.framework.AssertionFailedError;
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestListener;
/*     */ import junit.framework.TestSuite;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseTestRunner
/*     */   implements TestListener
/*     */ {
/*  32 */   static boolean fgFilterStack = true;
/*  33 */   boolean fLoading = true;
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void startTest(Test test)
/*     */   {
/*  39 */     testStarted(test.toString());
/*     */   }
/*     */   
/*     */   protected static void setPreferences(Properties preferences) {
/*  43 */     fPreferences = preferences;
/*     */   }
/*     */   
/*     */   protected static Properties getPreferences() {
/*  47 */     if (fPreferences == null) {
/*  48 */       fPreferences = new Properties();
/*  49 */       fPreferences.put("loading", "true");
/*  50 */       fPreferences.put("filterstack", "true");
/*  51 */       readPreferences();
/*     */     }
/*  53 */     return fPreferences;
/*     */   }
/*     */   
/*     */   public static void savePreferences() throws IOException {
/*  57 */     FileOutputStream fos = new FileOutputStream(getPreferencesFile());
/*     */     try {
/*  59 */       getPreferences().store(fos, "");
/*     */     } finally {
/*  61 */       fos.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setPreference(String key, String value) {
/*  66 */     getPreferences().put(key, value);
/*     */   }
/*     */   
/*     */   public synchronized void endTest(Test test) {
/*  70 */     testEnded(test.toString());
/*     */   }
/*     */   
/*     */   public synchronized void addError(Test test, Throwable e) {
/*  74 */     testFailed(1, test, e);
/*     */   }
/*     */   
/*     */   public synchronized void addFailure(Test test, AssertionFailedError e) {
/*  78 */     testFailed(2, test, e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract void testStarted(String paramString);
/*     */   
/*     */ 
/*     */   public abstract void testEnded(String paramString);
/*     */   
/*     */ 
/*     */   public abstract void testFailed(int paramInt, Test paramTest, Throwable paramThrowable);
/*     */   
/*     */ 
/*     */   public Test getTest(String suiteClassName)
/*     */   {
/*  94 */     if (suiteClassName.length() <= 0) {
/*  95 */       clearStatus();
/*  96 */       return null;
/*     */     }
/*  98 */     Class<?> testClass = null;
/*     */     try {
/* 100 */       testClass = loadSuiteClass(suiteClassName);
/*     */     } catch (ClassNotFoundException e) {
/* 102 */       String clazz = e.getMessage();
/* 103 */       if (clazz == null) {
/* 104 */         clazz = suiteClassName;
/*     */       }
/* 106 */       runFailed("Class not found \"" + clazz + "\"");
/* 107 */       return null;
/*     */     } catch (Exception e) {
/* 109 */       runFailed("Error: " + e.toString());
/* 110 */       return null;
/*     */     }
/* 112 */     Method suiteMethod = null;
/*     */     try {
/* 114 */       suiteMethod = testClass.getMethod("suite", new Class[0]);
/*     */     }
/*     */     catch (Exception e) {
/* 117 */       clearStatus();
/* 118 */       return new TestSuite(testClass);
/*     */     }
/* 120 */     if (!Modifier.isStatic(suiteMethod.getModifiers())) {
/* 121 */       runFailed("Suite() method must be static");
/* 122 */       return null;
/*     */     }
/* 124 */     Test test = null;
/*     */     try {
/* 126 */       test = (Test)suiteMethod.invoke(null, new Object[0]);
/* 127 */       if (test == null) {
/* 128 */         return test;
/*     */       }
/*     */     } catch (InvocationTargetException e) {
/* 131 */       runFailed("Failed to invoke suite():" + e.getTargetException().toString());
/* 132 */       return null;
/*     */     } catch (IllegalAccessException e) {
/* 134 */       runFailed("Failed to invoke suite():" + e.toString());
/* 135 */       return null;
/*     */     }
/*     */     
/* 138 */     clearStatus();
/* 139 */     return test;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String elapsedTimeAsString(long runTime)
/*     */   {
/* 146 */     return NumberFormat.getInstance().format(runTime / 1000.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String processArguments(String[] args)
/*     */   {
/* 154 */     String suiteName = null;
/* 155 */     for (int i = 0; i < args.length; i++) {
/* 156 */       if (args[i].equals("-noloading")) {
/* 157 */         setLoading(false);
/* 158 */       } else if (args[i].equals("-nofilterstack")) {
/* 159 */         fgFilterStack = false;
/* 160 */       } else if (args[i].equals("-c")) {
/* 161 */         if (args.length > i + 1) {
/* 162 */           suiteName = extractClassName(args[(i + 1)]);
/*     */         } else {
/* 164 */           System.out.println("Missing Test class name");
/*     */         }
/* 166 */         i++;
/*     */       } else {
/* 168 */         suiteName = args[i];
/*     */       }
/*     */     }
/* 171 */     return suiteName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLoading(boolean enable)
/*     */   {
/* 178 */     this.fLoading = enable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String extractClassName(String className)
/*     */   {
/* 185 */     if (className.startsWith("Default package for")) {
/* 186 */       return className.substring(className.lastIndexOf(".") + 1);
/*     */     }
/* 188 */     return className;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String truncate(String s)
/*     */   {
/* 195 */     if ((fgMaxMessageLength != -1) && (s.length() > fgMaxMessageLength)) {
/* 196 */       s = s.substring(0, fgMaxMessageLength) + "...";
/*     */     }
/* 198 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void runFailed(String paramString);
/*     */   
/*     */ 
/*     */ 
/*     */   protected Class<?> loadSuiteClass(String suiteClassName)
/*     */     throws ClassNotFoundException
/*     */   {
/* 211 */     return Class.forName(suiteClassName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void clearStatus() {}
/*     */   
/*     */ 
/*     */   protected boolean useReloadingTestSuiteLoader()
/*     */   {
/* 221 */     return (getPreference("loading").equals("true")) && (this.fLoading);
/*     */   }
/*     */   
/*     */   private static File getPreferencesFile() {
/* 225 */     String home = System.getProperty("user.home");
/* 226 */     return new File(home, "junit.properties");
/*     */   }
/*     */   
/*     */   private static void readPreferences() {
/* 230 */     InputStream is = null;
/*     */     try {
/* 232 */       is = new FileInputStream(getPreferencesFile());
/* 233 */       setPreferences(new Properties(getPreferences()));
/* 234 */       getPreferences().load(is); return;
/*     */     }
/*     */     catch (IOException ignored) {}finally {
/*     */       try {
/* 238 */         if (is != null) {
/* 239 */           is.close();
/*     */         }
/*     */       }
/*     */       catch (IOException e1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getPreference(String key) {
/* 247 */     return getPreferences().getProperty(key);
/*     */   }
/*     */   
/*     */   public static int getPreference(String key, int dflt) {
/* 251 */     String value = getPreference(key);
/* 252 */     int intValue = dflt;
/* 253 */     if (value == null) {
/* 254 */       return intValue;
/*     */     }
/*     */     try {
/* 257 */       intValue = Integer.parseInt(value);
/*     */     }
/*     */     catch (NumberFormatException ne) {}
/* 260 */     return intValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String getFilteredTrace(Throwable e)
/*     */   {
/* 267 */     StringWriter stringWriter = new StringWriter();
/* 268 */     PrintWriter writer = new PrintWriter(stringWriter);
/* 269 */     e.printStackTrace(writer);
/* 270 */     String trace = stringWriter.toString();
/* 271 */     return getFilteredTrace(trace);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String getFilteredTrace(String stack)
/*     */   {
/* 278 */     if (showStackRaw()) {
/* 279 */       return stack;
/*     */     }
/*     */     
/* 282 */     StringWriter sw = new StringWriter();
/* 283 */     PrintWriter pw = new PrintWriter(sw);
/* 284 */     StringReader sr = new StringReader(stack);
/* 285 */     BufferedReader br = new BufferedReader(sr);
/*     */     try
/*     */     {
/*     */       String line;
/* 289 */       while ((line = br.readLine()) != null) {
/* 290 */         if (!filterLine(line)) {
/* 291 */           pw.println(line);
/*     */         }
/*     */       }
/*     */     } catch (Exception IOException) {
/* 295 */       return stack;
/*     */     }
/* 297 */     return sw.toString();
/*     */   }
/*     */   
/*     */   protected static boolean showStackRaw() {
/* 301 */     return (!getPreference("filterstack").equals("true")) || (!fgFilterStack);
/*     */   }
/*     */   
/*     */   static boolean filterLine(String line) {
/* 305 */     String[] patterns = { "junit.framework.TestCase", "junit.framework.TestResult", "junit.framework.TestSuite", "junit.framework.Assert.", "junit.swingui.TestRunner", "junit.awtui.TestRunner", "junit.textui.TestRunner", "java.lang.reflect.Method.invoke(" };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 315 */     for (int i = 0; i < patterns.length; i++) {
/* 316 */       if (line.indexOf(patterns[i]) > 0) {
/* 317 */         return true;
/*     */       }
/*     */     }
/* 320 */     return false;
/*     */   }
/*     */   
/*     */ 
/* 324 */   static int fgMaxMessageLength = getPreference("maxmessage", fgMaxMessageLength);
/*     */   public static final String SUITE_METHODNAME = "suite";
/*     */   private static Properties fPreferences;
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/runner/BaseTestRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */