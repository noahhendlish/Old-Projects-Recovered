/*     */ package org.junit.rules;
/*     */ 
/*     */ import org.hamcrest.CoreMatchers;
/*     */ import org.hamcrest.Matcher;
/*     */ import org.hamcrest.StringDescription;
/*     */ import org.junit.Assert;
/*     */ import org.junit.internal.matchers.ThrowableCauseMatcher;
/*     */ import org.junit.internal.matchers.ThrowableMessageMatcher;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runners.model.Statement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExpectedException
/*     */   implements TestRule
/*     */ {
/*     */   public static ExpectedException none()
/*     */   {
/* 112 */     return new ExpectedException();
/*     */   }
/*     */   
/* 115 */   private final ExpectedExceptionMatcherBuilder matcherBuilder = new ExpectedExceptionMatcherBuilder();
/*     */   
/* 117 */   private String missingExceptionMessage = "Expected test to throw %s";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public ExpectedException handleAssertionErrors()
/*     */   {
/* 129 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public ExpectedException handleAssumptionViolatedExceptions()
/*     */   {
/* 139 */     return this;
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
/*     */   public ExpectedException reportMissingExceptionWithMessage(String message)
/*     */   {
/* 153 */     this.missingExceptionMessage = message;
/* 154 */     return this;
/*     */   }
/*     */   
/*     */   public Statement apply(Statement base, Description description)
/*     */   {
/* 159 */     return new ExpectedExceptionStatement(base);
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
/*     */   public void expect(Matcher<?> matcher)
/*     */   {
/* 173 */     this.matcherBuilder.add(matcher);
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
/*     */   public void expect(Class<? extends Throwable> type)
/*     */   {
/* 186 */     expect(CoreMatchers.instanceOf(type));
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
/*     */   public void expectMessage(String substring)
/*     */   {
/* 199 */     expectMessage(CoreMatchers.containsString(substring));
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
/*     */   public void expectMessage(Matcher<String> matcher)
/*     */   {
/* 212 */     expect(ThrowableMessageMatcher.hasMessage(matcher));
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
/*     */   public void expectCause(Matcher<? extends Throwable> expectedCause)
/*     */   {
/* 226 */     expect(ThrowableCauseMatcher.hasCause(expectedCause));
/*     */   }
/*     */   
/*     */   private class ExpectedExceptionStatement extends Statement {
/*     */     private final Statement next;
/*     */     
/*     */     public ExpectedExceptionStatement(Statement base) {
/* 233 */       this.next = base;
/*     */     }
/*     */     
/*     */     public void evaluate() throws Throwable
/*     */     {
/*     */       try {
/* 239 */         this.next.evaluate();
/*     */       } catch (Throwable e) {
/* 241 */         ExpectedException.this.handleException(e);
/* 242 */         return;
/*     */       }
/* 244 */       if (ExpectedException.this.isAnyExceptionExpected()) {
/* 245 */         ExpectedException.this.failDueToMissingException();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void handleException(Throwable e) throws Throwable {
/* 251 */     if (isAnyExceptionExpected()) {
/* 252 */       Assert.assertThat(e, this.matcherBuilder.build());
/*     */     } else {
/* 254 */       throw e;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isAnyExceptionExpected() {
/* 259 */     return this.matcherBuilder.expectsThrowable();
/*     */   }
/*     */   
/*     */   private void failDueToMissingException() throws AssertionError {
/* 263 */     Assert.fail(missingExceptionMessage());
/*     */   }
/*     */   
/*     */   private String missingExceptionMessage() {
/* 267 */     String expectation = StringDescription.toString(this.matcherBuilder.build());
/* 268 */     return String.format(this.missingExceptionMessage, new Object[] { expectation });
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/ExpectedException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */