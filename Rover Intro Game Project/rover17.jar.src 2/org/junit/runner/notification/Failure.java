/*    */ package org.junit.runner.notification;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.Serializable;
/*    */ import java.io.StringWriter;
/*    */ import org.junit.runner.Description;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Failure
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final Description fDescription;
/*    */   private final Throwable fThrownException;
/*    */   
/*    */   public Failure(Description description, Throwable thrownException)
/*    */   {
/* 36 */     this.fThrownException = thrownException;
/* 37 */     this.fDescription = description;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getTestHeader()
/*    */   {
/* 44 */     return this.fDescription.getDisplayName();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Description getDescription()
/*    */   {
/* 51 */     return this.fDescription;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Throwable getException()
/*    */   {
/* 59 */     return this.fThrownException;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 64 */     return getTestHeader() + ": " + this.fThrownException.getMessage();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getTrace()
/*    */   {
/* 73 */     StringWriter stringWriter = new StringWriter();
/* 74 */     PrintWriter writer = new PrintWriter(stringWriter);
/* 75 */     getException().printStackTrace(writer);
/* 76 */     return stringWriter.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 85 */     return getException().getMessage();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/notification/Failure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */