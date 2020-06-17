/*     */ package junit.framework;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Assert
/*     */ {
/*     */   public static void assertTrue(String message, boolean condition)
/*     */   {
/*  21 */     if (!condition) {
/*  22 */       fail(message);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertTrue(boolean condition)
/*     */   {
/*  31 */     assertTrue(null, condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertFalse(String message, boolean condition)
/*     */   {
/*  39 */     assertTrue(message, !condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertFalse(boolean condition)
/*     */   {
/*  47 */     assertFalse(null, condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void fail(String message)
/*     */   {
/*  54 */     if (message == null) {
/*  55 */       throw new AssertionFailedError();
/*     */     }
/*  57 */     throw new AssertionFailedError(message);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void fail()
/*     */   {
/*  64 */     fail(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, Object expected, Object actual)
/*     */   {
/*  72 */     if ((expected == null) && (actual == null)) {
/*  73 */       return;
/*     */     }
/*  75 */     if ((expected != null) && (expected.equals(actual))) {
/*  76 */       return;
/*     */     }
/*  78 */     failNotEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(Object expected, Object actual)
/*     */   {
/*  86 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, String expected, String actual)
/*     */   {
/*  93 */     if ((expected == null) && (actual == null)) {
/*  94 */       return;
/*     */     }
/*  96 */     if ((expected != null) && (expected.equals(actual))) {
/*  97 */       return;
/*     */     }
/*  99 */     String cleanMessage = message == null ? "" : message;
/* 100 */     throw new ComparisonFailure(cleanMessage, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String expected, String actual)
/*     */   {
/* 107 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, double expected, double actual, double delta)
/*     */   {
/* 116 */     if (Double.compare(expected, actual) == 0) {
/* 117 */       return;
/*     */     }
/* 119 */     if (Math.abs(expected - actual) > delta) {
/* 120 */       failNotEquals(message, new Double(expected), new Double(actual));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(double expected, double actual, double delta)
/*     */   {
/* 129 */     assertEquals(null, expected, actual, delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, float expected, float actual, float delta)
/*     */   {
/* 138 */     if (Float.compare(expected, actual) == 0) {
/* 139 */       return;
/*     */     }
/* 141 */     if (Math.abs(expected - actual) > delta) {
/* 142 */       failNotEquals(message, new Float(expected), new Float(actual));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(float expected, float actual, float delta)
/*     */   {
/* 151 */     assertEquals(null, expected, actual, delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, long expected, long actual)
/*     */   {
/* 159 */     assertEquals(message, Long.valueOf(expected), Long.valueOf(actual));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(long expected, long actual)
/*     */   {
/* 166 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, boolean expected, boolean actual)
/*     */   {
/* 174 */     assertEquals(message, Boolean.valueOf(expected), Boolean.valueOf(actual));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(boolean expected, boolean actual)
/*     */   {
/* 181 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, byte expected, byte actual)
/*     */   {
/* 189 */     assertEquals(message, Byte.valueOf(expected), Byte.valueOf(actual));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(byte expected, byte actual)
/*     */   {
/* 196 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, char expected, char actual)
/*     */   {
/* 204 */     assertEquals(message, Character.valueOf(expected), Character.valueOf(actual));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(char expected, char actual)
/*     */   {
/* 211 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, short expected, short actual)
/*     */   {
/* 219 */     assertEquals(message, Short.valueOf(expected), Short.valueOf(actual));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(short expected, short actual)
/*     */   {
/* 226 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, int expected, int actual)
/*     */   {
/* 234 */     assertEquals(message, Integer.valueOf(expected), Integer.valueOf(actual));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertEquals(int expected, int actual)
/*     */   {
/* 241 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void assertNotNull(Object object)
/*     */   {
/* 248 */     assertNotNull(null, object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotNull(String message, Object object)
/*     */   {
/* 256 */     assertTrue(message, object != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNull(Object object)
/*     */   {
/* 267 */     if (object != null) {
/* 268 */       assertNull("Expected: <null> but was: " + object.toString(), object);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNull(String message, Object object)
/*     */   {
/* 277 */     assertTrue(message, object == null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertSame(String message, Object expected, Object actual)
/*     */   {
/* 285 */     if (expected == actual) {
/* 286 */       return;
/*     */     }
/* 288 */     failNotSame(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertSame(Object expected, Object actual)
/*     */   {
/* 296 */     assertSame(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotSame(String message, Object expected, Object actual)
/*     */   {
/* 305 */     if (expected == actual) {
/* 306 */       failSame(message);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotSame(Object expected, Object actual)
/*     */   {
/* 315 */     assertNotSame(null, expected, actual);
/*     */   }
/*     */   
/*     */   public static void failSame(String message) {
/* 319 */     String formatted = message != null ? message + " " : "";
/* 320 */     fail(formatted + "expected not same");
/*     */   }
/*     */   
/*     */   public static void failNotSame(String message, Object expected, Object actual) {
/* 324 */     String formatted = message != null ? message + " " : "";
/* 325 */     fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
/*     */   }
/*     */   
/*     */   public static void failNotEquals(String message, Object expected, Object actual) {
/* 329 */     fail(format(message, expected, actual));
/*     */   }
/*     */   
/*     */   public static String format(String message, Object expected, Object actual) {
/* 333 */     String formatted = "";
/* 334 */     if ((message != null) && (message.length() > 0)) {
/* 335 */       formatted = message + " ";
/*     */     }
/* 337 */     return formatted + "expected:<" + expected + "> but was:<" + actual + ">";
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/Assert.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */