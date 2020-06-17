/*     */ package org.junit.runner.manipulation;
/*     */ 
/*     */ import org.junit.runner.Description;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Filter
/*     */ {
/*  21 */   public static final Filter ALL = new Filter()
/*     */   {
/*     */     public boolean shouldRun(Description description) {
/*  24 */       return true;
/*     */     }
/*     */     
/*     */     public String describe()
/*     */     {
/*  29 */       return "all tests";
/*     */     }
/*     */     
/*     */ 
/*     */     public void apply(Object child)
/*     */       throws NoTestsRemainException
/*     */     {}
/*     */     
/*     */     public Filter intersect(Filter second)
/*     */     {
/*  39 */       return second;
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Filter matchMethodDescription(Description desiredDescription)
/*     */   {
/*  48 */     new Filter()
/*     */     {
/*     */       public boolean shouldRun(Description description) {
/*  51 */         if (description.isTest()) {
/*  52 */           return this.val$desiredDescription.equals(description);
/*     */         }
/*     */         
/*     */ 
/*  56 */         for (Description each : description.getChildren()) {
/*  57 */           if (shouldRun(each)) {
/*  58 */             return true;
/*     */           }
/*     */         }
/*  61 */         return false;
/*     */       }
/*     */       
/*     */       public String describe()
/*     */       {
/*  66 */         return String.format("Method %s", new Object[] { this.val$desiredDescription.getDisplayName() });
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean shouldRun(Description paramDescription);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String describe();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void apply(Object child)
/*     */     throws NoTestsRemainException
/*     */   {
/*  93 */     if (!(child instanceof Filterable)) {
/*  94 */       return;
/*     */     }
/*  96 */     Filterable filterable = (Filterable)child;
/*  97 */     filterable.filter(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Filter intersect(final Filter second)
/*     */   {
/* 105 */     if ((second == this) || (second == ALL)) {
/* 106 */       return this;
/*     */     }
/* 108 */     final Filter first = this;
/* 109 */     new Filter()
/*     */     {
/*     */       public boolean shouldRun(Description description) {
/* 112 */         return (first.shouldRun(description)) && (second.shouldRun(description));
/*     */       }
/*     */       
/*     */ 
/*     */       public String describe()
/*     */       {
/* 118 */         return first.describe() + " and " + second.describe();
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/manipulation/Filter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */