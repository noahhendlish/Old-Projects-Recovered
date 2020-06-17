/*   */ package org.junit.internal.runners.rules;
/*   */ 
/*   */ import org.junit.runners.model.FrameworkMember;
/*   */ 
/*   */ class ValidationError extends Exception
/*   */ {
/*   */   public ValidationError(FrameworkMember<?> member, Class<? extends java.lang.annotation.Annotation> annotation, String suffix)
/*   */   {
/* 9 */     super(String.format("The @%s '%s' %s", new Object[] { annotation.getSimpleName(), member.getName(), suffix }));
/*   */   }
/*   */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/internal/runners/rules/ValidationError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */