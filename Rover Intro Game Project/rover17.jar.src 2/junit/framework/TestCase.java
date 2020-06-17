/*     */ package junit.framework;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TestCase
/*     */   extends Assert
/*     */   implements Test
/*     */ {
/*     */   private String fName;
/*     */   
/*     */   public TestCase()
/*     */   {
/*  87 */     this.fName = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TestCase(String name)
/*     */   {
/*  94 */     this.fName = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int countTestCases()
/*     */   {
/* 101 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected TestResult createResult()
/*     */   {
/* 110 */     return new TestResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TestResult run()
/*     */   {
/* 120 */     TestResult result = createResult();
/* 121 */     run(result);
/* 122 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void run(TestResult result)
/*     */   {
/* 129 */     result.run(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void runBare()
/*     */     throws Throwable
/*     */   {
/* 138 */     Throwable exception = null;
/* 139 */     setUp();
/*     */     try {
/* 141 */       runTest();
/*     */       
/*     */ 
/*     */       try
/*     */       {
/* 146 */         tearDown();
/*     */       } catch (Throwable tearingDown) {
/* 148 */         if (exception == null) { exception = tearingDown;
/*     */         }
/*     */       }
/* 151 */       if (exception == null) {
/*     */         return;
/*     */       }
/*     */     }
/*     */     catch (Throwable running)
/*     */     {
/* 143 */       exception = running;
/*     */     } finally {
/*     */       try {
/* 146 */         tearDown();
/*     */       } catch (Throwable tearingDown) {
/* 148 */         if (exception == null) exception = tearingDown;
/*     */       }
/*     */     }
/* 151 */     throw exception;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void runTest()
/*     */     throws Throwable
/*     */   {
/* 160 */     assertNotNull("TestCase.fName cannot be null", this.fName);
/* 161 */     Method runMethod = null;
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 167 */       runMethod = getClass().getMethod(this.fName, (Class[])null);
/*     */     } catch (NoSuchMethodException e) {
/* 169 */       fail("Method \"" + this.fName + "\" not found");
/*     */     }
/* 171 */     if (!Modifier.isPublic(runMethod.getModifiers())) {
/* 172 */       fail("Method \"" + this.fName + "\" should be public");
/*     */     }
/*     */     try
/*     */     {
/* 176 */       runMethod.invoke(this, new Object[0]);
/*     */     } catch (InvocationTargetException e) {
/* 178 */       e.fillInStackTrace();
/* 179 */       throw e.getTargetException();
/*     */     } catch (IllegalAccessException e) {
/* 181 */       e.fillInStackTrace();
/* 182 */       throw e;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertTrue(String message, boolean condition)
/*     */   {
/* 192 */     Assert.assertTrue(message, condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertTrue(boolean condition)
/*     */   {
/* 201 */     Assert.assertTrue(condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertFalse(String message, boolean condition)
/*     */   {
/* 210 */     Assert.assertFalse(message, condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertFalse(boolean condition)
/*     */   {
/* 219 */     Assert.assertFalse(condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void fail(String message)
/*     */   {
/* 227 */     Assert.fail(message);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void fail() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, Object expected, Object actual)
/*     */   {
/* 244 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(Object expected, Object actual)
/*     */   {
/* 253 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, String expected, String actual)
/*     */   {
/* 261 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String expected, String actual)
/*     */   {
/* 269 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, double expected, double actual, double delta)
/*     */   {
/* 279 */     Assert.assertEquals(message, expected, actual, delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(double expected, double actual, double delta)
/*     */   {
/* 288 */     Assert.assertEquals(expected, actual, delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, float expected, float actual, float delta)
/*     */   {
/* 298 */     Assert.assertEquals(message, expected, actual, delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(float expected, float actual, float delta)
/*     */   {
/* 307 */     Assert.assertEquals(expected, actual, delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, long expected, long actual)
/*     */   {
/* 316 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(long expected, long actual)
/*     */   {
/* 324 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, boolean expected, boolean actual)
/*     */   {
/* 333 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(boolean expected, boolean actual)
/*     */   {
/* 341 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, byte expected, byte actual)
/*     */   {
/* 350 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(byte expected, byte actual)
/*     */   {
/* 358 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, char expected, char actual)
/*     */   {
/* 367 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(char expected, char actual)
/*     */   {
/* 375 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, short expected, short actual)
/*     */   {
/* 384 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(short expected, short actual)
/*     */   {
/* 392 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, int expected, int actual)
/*     */   {
/* 401 */     Assert.assertEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(int expected, int actual)
/*     */   {
/* 409 */     Assert.assertEquals(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotNull(Object object)
/*     */   {
/* 417 */     Assert.assertNotNull(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotNull(String message, Object object)
/*     */   {
/* 426 */     Assert.assertNotNull(message, object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNull(Object object)
/*     */   {
/* 438 */     Assert.assertNull(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNull(String message, Object object)
/*     */   {
/* 447 */     Assert.assertNull(message, object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertSame(String message, Object expected, Object actual)
/*     */   {
/* 456 */     Assert.assertSame(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertSame(Object expected, Object actual)
/*     */   {
/* 465 */     Assert.assertSame(expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotSame(String message, Object expected, Object actual)
/*     */   {
/* 475 */     Assert.assertNotSame(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotSame(Object expected, Object actual)
/*     */   {
/* 484 */     Assert.assertNotSame(expected, actual);
/*     */   }
/*     */   
/*     */   public static void failSame(String message)
/*     */   {
/* 489 */     Assert.failSame(message);
/*     */   }
/*     */   
/*     */   public static void failNotSame(String message, Object expected, Object actual)
/*     */   {
/* 494 */     Assert.failNotSame(message, expected, actual);
/*     */   }
/*     */   
/*     */   public static void failNotEquals(String message, Object expected, Object actual)
/*     */   {
/* 499 */     Assert.failNotEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */   public static String format(String message, Object expected, Object actual)
/*     */   {
/* 504 */     return Assert.format(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp()
/*     */     throws Exception
/*     */   {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void tearDown()
/*     */     throws Exception
/*     */   {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 526 */     return getName() + "(" + getClass().getName() + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 535 */     return this.fName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 544 */     this.fName = name;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/TestCase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */