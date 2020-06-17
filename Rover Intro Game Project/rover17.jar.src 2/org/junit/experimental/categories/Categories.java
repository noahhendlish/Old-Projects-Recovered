/*     */ package org.junit.experimental.categories;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runner.manipulation.NoTestsRemainException;
/*     */ import org.junit.runners.Suite;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ import org.junit.runners.model.RunnerBuilder;
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
/*     */ public class Categories
/*     */   extends Suite
/*     */ {
/*     */   public static class CategoryFilter
/*     */     extends Filter
/*     */   {
/*     */     private final Set<Class<?>> included;
/*     */     private final Set<Class<?>> excluded;
/*     */     private final boolean includedAny;
/*     */     private final boolean excludedAny;
/*     */     
/*     */     public static CategoryFilter include(boolean matchAny, Class<?>... categories)
/*     */     {
/* 120 */       if (hasNull(categories)) {
/* 121 */         throw new NullPointerException("has null category");
/*     */       }
/* 123 */       return categoryFilter(matchAny, Categories.createSet(categories), true, null);
/*     */     }
/*     */     
/*     */     public static CategoryFilter include(Class<?> category) {
/* 127 */       return include(true, new Class[] { category });
/*     */     }
/*     */     
/*     */     public static CategoryFilter include(Class<?>... categories) {
/* 131 */       return include(true, categories);
/*     */     }
/*     */     
/*     */     public static CategoryFilter exclude(boolean matchAny, Class<?>... categories) {
/* 135 */       if (hasNull(categories)) {
/* 136 */         throw new NullPointerException("has null category");
/*     */       }
/* 138 */       return categoryFilter(true, null, matchAny, Categories.createSet(categories));
/*     */     }
/*     */     
/*     */     public static CategoryFilter exclude(Class<?> category) {
/* 142 */       return exclude(true, new Class[] { category });
/*     */     }
/*     */     
/*     */     public static CategoryFilter exclude(Class<?>... categories) {
/* 146 */       return exclude(true, categories);
/*     */     }
/*     */     
/*     */     public static CategoryFilter categoryFilter(boolean matchAnyInclusions, Set<Class<?>> inclusions, boolean matchAnyExclusions, Set<Class<?>> exclusions)
/*     */     {
/* 151 */       return new CategoryFilter(matchAnyInclusions, inclusions, matchAnyExclusions, exclusions);
/*     */     }
/*     */     
/*     */     protected CategoryFilter(boolean matchAnyIncludes, Set<Class<?>> includes, boolean matchAnyExcludes, Set<Class<?>> excludes)
/*     */     {
/* 156 */       this.includedAny = matchAnyIncludes;
/* 157 */       this.excludedAny = matchAnyExcludes;
/* 158 */       this.included = copyAndRefine(includes);
/* 159 */       this.excluded = copyAndRefine(excludes);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public String describe()
/*     */     {
/* 167 */       return toString();
/*     */     }
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
/*     */     public String toString()
/*     */     {
/* 184 */       StringBuilder description = new StringBuilder("categories ").append(this.included.isEmpty() ? "[all]" : this.included);
/*     */       
/* 186 */       if (!this.excluded.isEmpty()) {
/* 187 */         description.append(" - ").append(this.excluded);
/*     */       }
/* 189 */       return description.toString();
/*     */     }
/*     */     
/*     */     public boolean shouldRun(Description description)
/*     */     {
/* 194 */       if (hasCorrectCategoryAnnotation(description)) {
/* 195 */         return true;
/*     */       }
/*     */       
/* 198 */       for (Description each : description.getChildren()) {
/* 199 */         if (shouldRun(each)) {
/* 200 */           return true;
/*     */         }
/*     */       }
/*     */       
/* 204 */       return false;
/*     */     }
/*     */     
/*     */     private boolean hasCorrectCategoryAnnotation(Description description) {
/* 208 */       Set<Class<?>> childCategories = categories(description);
/*     */       
/*     */ 
/* 211 */       if (childCategories.isEmpty()) {
/* 212 */         return this.included.isEmpty();
/*     */       }
/*     */       
/* 215 */       if (!this.excluded.isEmpty()) {
/* 216 */         if (this.excludedAny) {
/* 217 */           if (matchesAnyParentCategories(childCategories, this.excluded)) {
/* 218 */             return false;
/*     */           }
/*     */         }
/* 221 */         else if (matchesAllParentCategories(childCategories, this.excluded)) {
/* 222 */           return false;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 227 */       if (this.included.isEmpty())
/*     */       {
/* 229 */         return true;
/*     */       }
/* 231 */       if (this.includedAny) {
/* 232 */         return matchesAnyParentCategories(childCategories, this.included);
/*     */       }
/* 234 */       return matchesAllParentCategories(childCategories, this.included);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private boolean matchesAnyParentCategories(Set<Class<?>> childCategories, Set<Class<?>> parentCategories)
/*     */     {
/* 244 */       for (Class<?> parentCategory : parentCategories) {
/* 245 */         if (Categories.hasAssignableTo(childCategories, parentCategory)) {
/* 246 */           return true;
/*     */         }
/*     */       }
/* 249 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private boolean matchesAllParentCategories(Set<Class<?>> childCategories, Set<Class<?>> parentCategories)
/*     */     {
/* 257 */       for (Class<?> parentCategory : parentCategories) {
/* 258 */         if (!Categories.hasAssignableTo(childCategories, parentCategory)) {
/* 259 */           return false;
/*     */         }
/*     */       }
/* 262 */       return true;
/*     */     }
/*     */     
/*     */     private static Set<Class<?>> categories(Description description) {
/* 266 */       Set<Class<?>> categories = new HashSet();
/* 267 */       Collections.addAll(categories, directCategories(description));
/* 268 */       Collections.addAll(categories, directCategories(parentDescription(description)));
/* 269 */       return categories;
/*     */     }
/*     */     
/*     */     private static Description parentDescription(Description description) {
/* 273 */       Class<?> testClass = description.getTestClass();
/* 274 */       return testClass == null ? null : Description.createSuiteDescription(testClass);
/*     */     }
/*     */     
/*     */     private static Class<?>[] directCategories(Description description) {
/* 278 */       if (description == null) {
/* 279 */         return new Class[0];
/*     */       }
/*     */       
/* 282 */       Category annotation = (Category)description.getAnnotation(Category.class);
/* 283 */       return annotation == null ? new Class[0] : annotation.value();
/*     */     }
/*     */     
/*     */     private static Set<Class<?>> copyAndRefine(Set<Class<?>> classes) {
/* 287 */       HashSet<Class<?>> c = new HashSet();
/* 288 */       if (classes != null) {
/* 289 */         c.addAll(classes);
/*     */       }
/* 291 */       c.remove(null);
/* 292 */       return c;
/*     */     }
/*     */     
/*     */     private static boolean hasNull(Class<?>... classes) {
/* 296 */       if (classes == null) return false;
/* 297 */       for (Class<?> clazz : classes) {
/* 298 */         if (clazz == null) {
/* 299 */           return true;
/*     */         }
/*     */       }
/* 302 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public Categories(Class<?> klass, RunnerBuilder builder) throws InitializationError {
/* 307 */     super(klass, builder);
/*     */     try {
/* 309 */       Set<Class<?>> included = getIncludedCategory(klass);
/* 310 */       Set<Class<?>> excluded = getExcludedCategory(klass);
/* 311 */       boolean isAnyIncluded = isAnyIncluded(klass);
/* 312 */       boolean isAnyExcluded = isAnyExcluded(klass);
/*     */       
/* 314 */       filter(CategoryFilter.categoryFilter(isAnyIncluded, included, isAnyExcluded, excluded));
/*     */     } catch (NoTestsRemainException e) {
/* 316 */       throw new InitializationError(e);
/*     */     }
/* 318 */     assertNoCategorizedDescendentsOfUncategorizeableParents(getDescription());
/*     */   }
/*     */   
/*     */   private static Set<Class<?>> getIncludedCategory(Class<?> klass) {
/* 322 */     IncludeCategory annotation = (IncludeCategory)klass.getAnnotation(IncludeCategory.class);
/* 323 */     return createSet(annotation == null ? null : annotation.value());
/*     */   }
/*     */   
/*     */   private static boolean isAnyIncluded(Class<?> klass) {
/* 327 */     IncludeCategory annotation = (IncludeCategory)klass.getAnnotation(IncludeCategory.class);
/* 328 */     return (annotation == null) || (annotation.matchAny());
/*     */   }
/*     */   
/*     */   private static Set<Class<?>> getExcludedCategory(Class<?> klass) {
/* 332 */     ExcludeCategory annotation = (ExcludeCategory)klass.getAnnotation(ExcludeCategory.class);
/* 333 */     return createSet(annotation == null ? null : annotation.value());
/*     */   }
/*     */   
/*     */   private static boolean isAnyExcluded(Class<?> klass) {
/* 337 */     ExcludeCategory annotation = (ExcludeCategory)klass.getAnnotation(ExcludeCategory.class);
/* 338 */     return (annotation == null) || (annotation.matchAny());
/*     */   }
/*     */   
/*     */   private static void assertNoCategorizedDescendentsOfUncategorizeableParents(Description description) throws InitializationError {
/* 342 */     if (!canHaveCategorizedChildren(description)) {
/* 343 */       assertNoDescendantsHaveCategoryAnnotations(description);
/*     */     }
/* 345 */     for (Description each : description.getChildren()) {
/* 346 */       assertNoCategorizedDescendentsOfUncategorizeableParents(each);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void assertNoDescendantsHaveCategoryAnnotations(Description description) throws InitializationError {
/* 351 */     for (Description each : description.getChildren()) {
/* 352 */       if (each.getAnnotation(Category.class) != null) {
/* 353 */         throw new InitializationError("Category annotations on Parameterized classes are not supported on individual methods.");
/*     */       }
/* 355 */       assertNoDescendantsHaveCategoryAnnotations(each);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean canHaveCategorizedChildren(Description description)
/*     */   {
/* 361 */     for (Description each : description.getChildren()) {
/* 362 */       if (each.getTestClass() == null) {
/* 363 */         return false;
/*     */       }
/*     */     }
/* 366 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean hasAssignableTo(Set<Class<?>> assigns, Class<?> to) {
/* 370 */     for (Class<?> from : assigns) {
/* 371 */       if (to.isAssignableFrom(from)) {
/* 372 */         return true;
/*     */       }
/*     */     }
/* 375 */     return false;
/*     */   }
/*     */   
/*     */   private static Set<Class<?>> createSet(Class<?>... t) {
/* 379 */     Set<Class<?>> set = new HashSet();
/* 380 */     if (t != null) {
/* 381 */       Collections.addAll(set, t);
/*     */     }
/* 383 */     return set;
/*     */   }
/*     */   
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   public static @interface ExcludeCategory
/*     */   {
/*     */     Class<?>[] value() default {};
/*     */     
/*     */     boolean matchAny() default true;
/*     */   }
/*     */   
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   public static @interface IncludeCategory
/*     */   {
/*     */     Class<?>[] value() default {};
/*     */     
/*     */     boolean matchAny() default true;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/categories/Categories.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */