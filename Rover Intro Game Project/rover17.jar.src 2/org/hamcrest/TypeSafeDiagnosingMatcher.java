/*    */ package org.hamcrest;
/*    */ 
/*    */ import org.hamcrest.internal.ReflectiveTypeFinder;
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
/*    */ public abstract class TypeSafeDiagnosingMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/* 18 */   private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 2, 0);
/*    */   
/*    */ 
/*    */ 
/*    */   private final Class<?> expectedType;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected abstract boolean matchesSafely(T paramT, Description paramDescription);
/*    */   
/*    */ 
/*    */ 
/*    */   protected TypeSafeDiagnosingMatcher(Class<?> expectedType)
/*    */   {
/* 33 */     this.expectedType = expectedType;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected TypeSafeDiagnosingMatcher(ReflectiveTypeFinder typeFinder)
/*    */   {
/* 42 */     this.expectedType = typeFinder.findExpectedType(getClass());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected TypeSafeDiagnosingMatcher()
/*    */   {
/* 49 */     this(TYPE_FINDER);
/*    */   }
/*    */   
/*    */ 
/*    */   public final boolean matches(Object item)
/*    */   {
/* 55 */     return (item != null) && (this.expectedType.isInstance(item)) && (matchesSafely(item, new Description.NullDescription()));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public final void describeMismatch(Object item, Description mismatchDescription)
/*    */   {
/* 63 */     if ((item == null) || (!this.expectedType.isInstance(item))) {
/* 64 */       super.describeMismatch(item, mismatchDescription);
/*    */     } else {
/* 66 */       matchesSafely(item, mismatchDescription);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/TypeSafeDiagnosingMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */