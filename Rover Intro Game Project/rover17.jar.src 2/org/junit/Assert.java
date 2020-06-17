/*     */ package org.junit;
/*     */ 
/*     */ import org.hamcrest.Matcher;
/*     */ import org.hamcrest.MatcherAssert;
/*     */ import org.junit.internal.ArrayComparisonFailure;
/*     */ import org.junit.internal.ExactComparisonCriteria;
/*     */ import org.junit.internal.InexactComparisonCriteria;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Assert
/*     */ {
/*     */   public static void assertTrue(String message, boolean condition)
/*     */   {
/*  40 */     if (!condition) {
/*  41 */       fail(message);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertTrue(boolean condition)
/*     */   {
/*  52 */     assertTrue(null, condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertFalse(String message, boolean condition)
/*     */   {
/*  64 */     assertTrue(message, !condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertFalse(boolean condition)
/*     */   {
/*  74 */     assertFalse(null, condition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void fail(String message)
/*     */   {
/*  85 */     if (message == null) {
/*  86 */       throw new AssertionError();
/*     */     }
/*  88 */     throw new AssertionError(message);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void fail()
/*     */   {
/*  95 */     fail(null);
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
/*     */   public static void assertEquals(String message, Object expected, Object actual)
/*     */   {
/* 111 */     if (equalsRegardingNull(expected, actual))
/* 112 */       return;
/* 113 */     if (((expected instanceof String)) && ((actual instanceof String))) {
/* 114 */       String cleanMessage = message == null ? "" : message;
/* 115 */       throw new ComparisonFailure(cleanMessage, (String)expected, (String)actual);
/*     */     }
/*     */     
/* 118 */     failNotEquals(message, expected, actual);
/*     */   }
/*     */   
/*     */   private static boolean equalsRegardingNull(Object expected, Object actual)
/*     */   {
/* 123 */     if (expected == null) {
/* 124 */       return actual == null;
/*     */     }
/*     */     
/* 127 */     return isEquals(expected, actual);
/*     */   }
/*     */   
/*     */   private static boolean isEquals(Object expected, Object actual) {
/* 131 */     return expected.equals(actual);
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
/*     */   public static void assertEquals(Object expected, Object actual)
/*     */   {
/* 144 */     assertEquals(null, expected, actual);
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
/*     */   public static void assertNotEquals(String message, Object unexpected, Object actual)
/*     */   {
/* 160 */     if (equalsRegardingNull(unexpected, actual)) {
/* 161 */       failEquals(message, actual);
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
/*     */   public static void assertNotEquals(Object unexpected, Object actual)
/*     */   {
/* 175 */     assertNotEquals(null, unexpected, actual);
/*     */   }
/*     */   
/*     */   private static void failEquals(String message, Object actual) {
/* 179 */     String formatted = "Values should be different. ";
/* 180 */     if (message != null) {
/* 181 */       formatted = message + ". ";
/*     */     }
/*     */     
/* 184 */     formatted = formatted + "Actual: " + actual;
/* 185 */     fail(formatted);
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
/*     */   public static void assertNotEquals(String message, long unexpected, long actual)
/*     */   {
/* 198 */     if (unexpected == actual) {
/* 199 */       failEquals(message, Long.valueOf(actual));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotEquals(long unexpected, long actual)
/*     */   {
/* 211 */     assertNotEquals(null, unexpected, actual);
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
/*     */   public static void assertNotEquals(String message, double unexpected, double actual, double delta)
/*     */   {
/* 231 */     if (!doubleIsDifferent(unexpected, actual, delta)) {
/* 232 */       failEquals(message, Double.valueOf(actual));
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
/*     */   public static void assertNotEquals(double unexpected, double actual, double delta)
/*     */   {
/* 249 */     assertNotEquals(null, unexpected, actual, delta);
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
/*     */   public static void assertNotEquals(float unexpected, float actual, float delta)
/*     */   {
/* 265 */     assertNotEquals(null, unexpected, actual, delta);
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
/*     */   public static void assertArrayEquals(String message, Object[] expecteds, Object[] actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 283 */     internalArrayEquals(message, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(Object[] expecteds, Object[] actuals)
/*     */   {
/* 298 */     assertArrayEquals(null, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(String message, boolean[] expecteds, boolean[] actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 314 */     internalArrayEquals(message, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(boolean[] expecteds, boolean[] actuals)
/*     */   {
/* 327 */     assertArrayEquals(null, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(String message, byte[] expecteds, byte[] actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 341 */     internalArrayEquals(message, expecteds, actuals);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertArrayEquals(byte[] expecteds, byte[] actuals)
/*     */   {
/* 352 */     assertArrayEquals(null, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(String message, char[] expecteds, char[] actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 366 */     internalArrayEquals(message, expecteds, actuals);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertArrayEquals(char[] expecteds, char[] actuals)
/*     */   {
/* 377 */     assertArrayEquals(null, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(String message, short[] expecteds, short[] actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 391 */     internalArrayEquals(message, expecteds, actuals);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertArrayEquals(short[] expecteds, short[] actuals)
/*     */   {
/* 402 */     assertArrayEquals(null, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(String message, int[] expecteds, int[] actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 416 */     internalArrayEquals(message, expecteds, actuals);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertArrayEquals(int[] expecteds, int[] actuals)
/*     */   {
/* 427 */     assertArrayEquals(null, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(String message, long[] expecteds, long[] actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 441 */     internalArrayEquals(message, expecteds, actuals);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertArrayEquals(long[] expecteds, long[] actuals)
/*     */   {
/* 452 */     assertArrayEquals(null, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(String message, double[] expecteds, double[] actuals, double delta)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 469 */     new InexactComparisonCriteria(delta).arrayEquals(message, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(double[] expecteds, double[] actuals, double delta)
/*     */   {
/* 483 */     assertArrayEquals(null, expecteds, actuals, delta);
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
/*     */   public static void assertArrayEquals(String message, float[] expecteds, float[] actuals, float delta)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 500 */     new InexactComparisonCriteria(delta).arrayEquals(message, expecteds, actuals);
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
/*     */   public static void assertArrayEquals(float[] expecteds, float[] actuals, float delta)
/*     */   {
/* 514 */     assertArrayEquals(null, expecteds, actuals, delta);
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
/*     */   private static void internalArrayEquals(String message, Object expecteds, Object actuals)
/*     */     throws ArrayComparisonFailure
/*     */   {
/* 532 */     new ExactComparisonCriteria().arrayEquals(message, expecteds, actuals);
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
/*     */   public static void assertEquals(String message, double expected, double actual, double delta)
/*     */   {
/* 552 */     if (doubleIsDifferent(expected, actual, delta)) {
/* 553 */       failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(String message, float expected, float actual, float delta)
/*     */   {
/* 574 */     if (floatIsDifferent(expected, actual, delta)) {
/* 575 */       failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotEquals(String message, float unexpected, float actual, float delta)
/*     */   {
/* 596 */     if (!floatIsDifferent(unexpected, actual, delta)) {
/* 597 */       failEquals(message, Float.valueOf(actual));
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean doubleIsDifferent(double d1, double d2, double delta) {
/* 602 */     if (Double.compare(d1, d2) == 0) {
/* 603 */       return false;
/*     */     }
/* 605 */     if (Math.abs(d1 - d2) <= delta) {
/* 606 */       return false;
/*     */     }
/*     */     
/* 609 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean floatIsDifferent(float f1, float f2, float delta) {
/* 613 */     if (Float.compare(f1, f2) == 0) {
/* 614 */       return false;
/*     */     }
/* 616 */     if (Math.abs(f1 - f2) <= delta) {
/* 617 */       return false;
/*     */     }
/*     */     
/* 620 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertEquals(long expected, long actual)
/*     */   {
/* 631 */     assertEquals(null, expected, actual);
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
/*     */   public static void assertEquals(String message, long expected, long actual)
/*     */   {
/* 644 */     if (expected != actual) {
/* 645 */       failNotEquals(message, Long.valueOf(expected), Long.valueOf(actual));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static void assertEquals(double expected, double actual)
/*     */   {
/* 656 */     assertEquals(null, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static void assertEquals(String message, double expected, double actual)
/*     */   {
/* 667 */     fail("Use assertEquals(expected, actual, delta) to compare floating-point numbers");
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
/*     */   public static void assertEquals(double expected, double actual, double delta)
/*     */   {
/* 683 */     assertEquals(null, expected, actual, delta);
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
/*     */   public static void assertEquals(float expected, float actual, float delta)
/*     */   {
/* 700 */     assertEquals(null, expected, actual, delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotNull(String message, Object object)
/*     */   {
/* 712 */     assertTrue(message, object != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNotNull(Object object)
/*     */   {
/* 722 */     assertNotNull(null, object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNull(String message, Object object)
/*     */   {
/* 734 */     if (object == null) {
/* 735 */       return;
/*     */     }
/* 737 */     failNotNull(message, object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertNull(Object object)
/*     */   {
/* 747 */     assertNull(null, object);
/*     */   }
/*     */   
/*     */   private static void failNotNull(String message, Object actual) {
/* 751 */     String formatted = "";
/* 752 */     if (message != null) {
/* 753 */       formatted = message + " ";
/*     */     }
/* 755 */     fail(formatted + "expected null, but was:<" + actual + ">");
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
/*     */   public static void assertSame(String message, Object expected, Object actual)
/*     */   {
/* 768 */     if (expected == actual) {
/* 769 */       return;
/*     */     }
/* 771 */     failNotSame(message, expected, actual);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void assertSame(Object expected, Object actual)
/*     */   {
/* 782 */     assertSame(null, expected, actual);
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
/*     */   public static void assertNotSame(String message, Object unexpected, Object actual)
/*     */   {
/* 797 */     if (unexpected == actual) {
/* 798 */       failSame(message);
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
/*     */   public static void assertNotSame(Object unexpected, Object actual)
/*     */   {
/* 811 */     assertNotSame(null, unexpected, actual);
/*     */   }
/*     */   
/*     */   private static void failSame(String message) {
/* 815 */     String formatted = "";
/* 816 */     if (message != null) {
/* 817 */       formatted = message + " ";
/*     */     }
/* 819 */     fail(formatted + "expected not same");
/*     */   }
/*     */   
/*     */   private static void failNotSame(String message, Object expected, Object actual)
/*     */   {
/* 824 */     String formatted = "";
/* 825 */     if (message != null) {
/* 826 */       formatted = message + " ";
/*     */     }
/* 828 */     fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
/*     */   }
/*     */   
/*     */ 
/*     */   private static void failNotEquals(String message, Object expected, Object actual)
/*     */   {
/* 834 */     fail(format(message, expected, actual));
/*     */   }
/*     */   
/*     */   static String format(String message, Object expected, Object actual) {
/* 838 */     String formatted = "";
/* 839 */     if ((message != null) && (!message.equals(""))) {
/* 840 */       formatted = message + " ";
/*     */     }
/* 842 */     String expectedString = String.valueOf(expected);
/* 843 */     String actualString = String.valueOf(actual);
/* 844 */     if (expectedString.equals(actualString)) {
/* 845 */       return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: " + formatClassAndValue(actual, actualString);
/*     */     }
/*     */     
/*     */ 
/* 849 */     return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
/*     */   }
/*     */   
/*     */ 
/*     */   private static String formatClassAndValue(Object value, String valueString)
/*     */   {
/* 855 */     String className = value == null ? "null" : value.getClass().getName();
/* 856 */     return className + "<" + valueString + ">";
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
/*     */   @Deprecated
/*     */   public static void assertEquals(String message, Object[] expecteds, Object[] actuals)
/*     */   {
/* 876 */     assertArrayEquals(message, expecteds, actuals);
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
/*     */   @Deprecated
/*     */   public static void assertEquals(Object[] expecteds, Object[] actuals)
/*     */   {
/* 893 */     assertArrayEquals(expecteds, actuals);
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
/*     */ 
/*     */   public static <T> void assertThat(T actual, Matcher<? super T> matcher)
/*     */   {
/* 923 */     assertThat("", actual, matcher);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher)
/*     */   {
/* 956 */     MatcherAssert.assertThat(reason, actual, matcher);
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/Assert.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */