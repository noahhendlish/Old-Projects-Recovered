/*     */ package org.hamcrest.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.hamcrest.Description;
/*     */ import org.hamcrest.Factory;
/*     */ import org.hamcrest.Matcher;
/*     */ import org.hamcrest.TypeSafeDiagnosingMatcher;
/*     */ 
/*     */ 
/*     */ public class IsCollectionContaining<T>
/*     */   extends TypeSafeDiagnosingMatcher<Iterable<? super T>>
/*     */ {
/*     */   private final Matcher<? super T> elementMatcher;
/*     */   
/*     */   public IsCollectionContaining(Matcher<? super T> elementMatcher)
/*     */   {
/*  18 */     this.elementMatcher = elementMatcher;
/*     */   }
/*     */   
/*     */   protected boolean matchesSafely(Iterable<? super T> collection, Description mismatchDescription)
/*     */   {
/*  23 */     boolean isPastFirst = false;
/*  24 */     for (Object item : collection) {
/*  25 */       if (this.elementMatcher.matches(item)) {
/*  26 */         return true;
/*     */       }
/*  28 */       if (isPastFirst) {
/*  29 */         mismatchDescription.appendText(", ");
/*     */       }
/*  31 */       this.elementMatcher.describeMismatch(item, mismatchDescription);
/*  32 */       isPastFirst = true;
/*     */     }
/*  34 */     return false;
/*     */   }
/*     */   
/*     */   public void describeTo(Description description)
/*     */   {
/*  39 */     description.appendText("a collection containing ").appendDescriptionOf(this.elementMatcher);
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
/*     */   @Factory
/*     */   public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> itemMatcher)
/*     */   {
/*  59 */     return new IsCollectionContaining(itemMatcher);
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
/*     */   @Factory
/*     */   public static <T> Matcher<Iterable<? super T>> hasItem(T item)
/*     */   {
/*  77 */     return new IsCollectionContaining(IsEqual.equalTo(item));
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
/*     */   @Factory
/*     */   public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... itemMatchers)
/*     */   {
/*  94 */     List<Matcher<? super Iterable<T>>> all = new ArrayList(itemMatchers.length);
/*     */     
/*  96 */     for (Matcher<? super T> elementMatcher : itemMatchers)
/*     */     {
/*  98 */       all.add(new IsCollectionContaining(elementMatcher));
/*     */     }
/*     */     
/* 101 */     return AllOf.allOf(all);
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
/*     */   @Factory
/*     */   public static <T> Matcher<Iterable<T>> hasItems(T... items)
/*     */   {
/* 118 */     List<Matcher<? super Iterable<T>>> all = new ArrayList(items.length);
/* 119 */     for (T element : items) {
/* 120 */       all.add(hasItem(element));
/*     */     }
/*     */     
/* 123 */     return AllOf.allOf(all);
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/core/IsCollectionContaining.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */