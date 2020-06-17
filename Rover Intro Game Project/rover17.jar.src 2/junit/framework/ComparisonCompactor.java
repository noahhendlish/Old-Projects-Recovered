/*    */ package junit.framework;
/*    */ 
/*    */ public class ComparisonCompactor
/*    */ {
/*    */   private static final String ELLIPSIS = "...";
/*    */   private static final String DELTA_END = "]";
/*    */   private static final String DELTA_START = "[";
/*    */   private int fContextLength;
/*    */   private String fExpected;
/*    */   private String fActual;
/*    */   private int fPrefix;
/*    */   private int fSuffix;
/*    */   
/*    */   public ComparisonCompactor(int contextLength, String expected, String actual)
/*    */   {
/* 16 */     this.fContextLength = contextLength;
/* 17 */     this.fExpected = expected;
/* 18 */     this.fActual = actual;
/*    */   }
/*    */   
/*    */   public String compact(String message) {
/* 22 */     if ((this.fExpected == null) || (this.fActual == null) || (areStringsEqual())) {
/* 23 */       return Assert.format(message, this.fExpected, this.fActual);
/*    */     }
/*    */     
/* 26 */     findCommonPrefix();
/* 27 */     findCommonSuffix();
/* 28 */     String expected = compactString(this.fExpected);
/* 29 */     String actual = compactString(this.fActual);
/* 30 */     return Assert.format(message, expected, actual);
/*    */   }
/*    */   
/*    */   private String compactString(String source) {
/* 34 */     String result = "[" + source.substring(this.fPrefix, source.length() - this.fSuffix + 1) + "]";
/* 35 */     if (this.fPrefix > 0) {
/* 36 */       result = computeCommonPrefix() + result;
/*    */     }
/* 38 */     if (this.fSuffix > 0) {
/* 39 */       result = result + computeCommonSuffix();
/*    */     }
/* 41 */     return result;
/*    */   }
/*    */   
/*    */   private void findCommonPrefix() {
/* 45 */     this.fPrefix = 0;
/* 46 */     int end = Math.min(this.fExpected.length(), this.fActual.length());
/* 47 */     while ((this.fPrefix < end) && 
/* 48 */       (this.fExpected.charAt(this.fPrefix) == this.fActual.charAt(this.fPrefix))) {
/* 47 */       this.fPrefix += 1;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void findCommonSuffix()
/*    */   {
/* 55 */     int expectedSuffix = this.fExpected.length() - 1;
/* 56 */     int actualSuffix = this.fActual.length() - 1;
/* 57 */     for (; (actualSuffix >= this.fPrefix) && (expectedSuffix >= this.fPrefix); expectedSuffix--) {
/* 58 */       if (this.fExpected.charAt(expectedSuffix) != this.fActual.charAt(actualSuffix)) {
/*    */         break;
/*    */       }
/* 57 */       actualSuffix--;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 62 */     this.fSuffix = (this.fExpected.length() - expectedSuffix);
/*    */   }
/*    */   
/*    */   private String computeCommonPrefix() {
/* 66 */     return (this.fPrefix > this.fContextLength ? "..." : "") + this.fExpected.substring(Math.max(0, this.fPrefix - this.fContextLength), this.fPrefix);
/*    */   }
/*    */   
/*    */   private String computeCommonSuffix() {
/* 70 */     int end = Math.min(this.fExpected.length() - this.fSuffix + 1 + this.fContextLength, this.fExpected.length());
/* 71 */     return this.fExpected.substring(this.fExpected.length() - this.fSuffix + 1, end) + (this.fExpected.length() - this.fSuffix + 1 < this.fExpected.length() - this.fContextLength ? "..." : "");
/*    */   }
/*    */   
/*    */   private boolean areStringsEqual() {
/* 75 */     return this.fExpected.equals(this.fActual);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/ComparisonCompactor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */