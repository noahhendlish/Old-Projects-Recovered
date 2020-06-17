/*     */ package org.junit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComparisonFailure
/*     */   extends AssertionError
/*     */ {
/*     */   private static final int MAX_CONTEXT_LENGTH = 20;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String fExpected;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String fActual;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparisonFailure(String message, String expected, String actual)
/*     */   {
/*  37 */     super(message);
/*  38 */     this.fExpected = expected;
/*  39 */     this.fActual = actual;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMessage()
/*     */   {
/*  49 */     return new ComparisonCompactor(20, this.fExpected, this.fActual).compact(super.getMessage());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActual()
/*     */   {
/*  58 */     return this.fActual;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getExpected()
/*     */   {
/*  67 */     return this.fExpected;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static class ComparisonCompactor
/*     */   {
/*     */     private static final String ELLIPSIS = "...";
/*     */     
/*     */ 
/*     */     private static final String DIFF_END = "]";
/*     */     
/*     */     private static final String DIFF_START = "[";
/*     */     
/*     */     private final int contextLength;
/*     */     
/*     */     private final String expected;
/*     */     
/*     */     private final String actual;
/*     */     
/*     */ 
/*     */     public ComparisonCompactor(int contextLength, String expected, String actual)
/*     */     {
/*  90 */       this.contextLength = contextLength;
/*  91 */       this.expected = expected;
/*  92 */       this.actual = actual;
/*     */     }
/*     */     
/*     */     public String compact(String message) {
/*  96 */       if ((this.expected == null) || (this.actual == null) || (this.expected.equals(this.actual))) {
/*  97 */         return Assert.format(message, this.expected, this.actual);
/*     */       }
/*  99 */       DiffExtractor extractor = new DiffExtractor(null);
/* 100 */       String compactedPrefix = extractor.compactPrefix();
/* 101 */       String compactedSuffix = extractor.compactSuffix();
/* 102 */       return Assert.format(message, compactedPrefix + extractor.expectedDiff() + compactedSuffix, compactedPrefix + extractor.actualDiff() + compactedSuffix);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private String sharedPrefix()
/*     */     {
/* 109 */       int end = Math.min(this.expected.length(), this.actual.length());
/* 110 */       for (int i = 0; i < end; i++) {
/* 111 */         if (this.expected.charAt(i) != this.actual.charAt(i)) {
/* 112 */           return this.expected.substring(0, i);
/*     */         }
/*     */       }
/* 115 */       return this.expected.substring(0, end);
/*     */     }
/*     */     
/*     */     private String sharedSuffix(String prefix) {
/* 119 */       int suffixLength = 0;
/* 120 */       int maxSuffixLength = Math.min(this.expected.length() - prefix.length(), this.actual.length() - prefix.length()) - 1;
/* 122 */       for (; 
/* 122 */           suffixLength <= maxSuffixLength; suffixLength++) {
/* 123 */         if (this.expected.charAt(this.expected.length() - 1 - suffixLength) != this.actual.charAt(this.actual.length() - 1 - suffixLength)) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       
/* 128 */       return this.expected.substring(this.expected.length() - suffixLength);
/*     */     }
/*     */     
/*     */ 
/*     */     private class DiffExtractor
/*     */     {
/*     */       private final String sharedPrefix;
/*     */       private final String sharedSuffix;
/*     */       
/*     */       private DiffExtractor()
/*     */       {
/* 139 */         this.sharedPrefix = ComparisonFailure.ComparisonCompactor.this.sharedPrefix();
/* 140 */         this.sharedSuffix = ComparisonFailure.ComparisonCompactor.this.sharedSuffix(this.sharedPrefix);
/*     */       }
/*     */       
/*     */       public String expectedDiff() {
/* 144 */         return extractDiff(ComparisonFailure.ComparisonCompactor.this.expected);
/*     */       }
/*     */       
/*     */       public String actualDiff() {
/* 148 */         return extractDiff(ComparisonFailure.ComparisonCompactor.this.actual);
/*     */       }
/*     */       
/*     */       public String compactPrefix() {
/* 152 */         if (this.sharedPrefix.length() <= ComparisonFailure.ComparisonCompactor.this.contextLength) {
/* 153 */           return this.sharedPrefix;
/*     */         }
/* 155 */         return "..." + this.sharedPrefix.substring(this.sharedPrefix.length() - ComparisonFailure.ComparisonCompactor.this.contextLength);
/*     */       }
/*     */       
/*     */       public String compactSuffix() {
/* 159 */         if (this.sharedSuffix.length() <= ComparisonFailure.ComparisonCompactor.this.contextLength) {
/* 160 */           return this.sharedSuffix;
/*     */         }
/* 162 */         return this.sharedSuffix.substring(0, ComparisonFailure.ComparisonCompactor.this.contextLength) + "...";
/*     */       }
/*     */       
/*     */       private String extractDiff(String source) {
/* 166 */         return "[" + source.substring(this.sharedPrefix.length(), source.length() - this.sharedSuffix.length()) + "]";
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/ComparisonFailure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */