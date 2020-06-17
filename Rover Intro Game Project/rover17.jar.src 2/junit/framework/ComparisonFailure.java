/*    */ package junit.framework;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComparisonFailure
/*    */   extends AssertionFailedError
/*    */ {
/*    */   private static final int MAX_CONTEXT_LENGTH = 20;
/*    */   
/*    */ 
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */ 
/*    */   private String fExpected;
/*    */   
/*    */ 
/*    */   private String fActual;
/*    */   
/*    */ 
/*    */ 
/*    */   public ComparisonFailure(String message, String expected, String actual)
/*    */   {
/* 23 */     super(message);
/* 24 */     this.fExpected = expected;
/* 25 */     this.fActual = actual;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 36 */     return new ComparisonCompactor(20, this.fExpected, this.fActual).compact(super.getMessage());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getActual()
/*    */   {
/* 45 */     return this.fActual;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getExpected()
/*    */   {
/* 54 */     return this.fExpected;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/ComparisonFailure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */