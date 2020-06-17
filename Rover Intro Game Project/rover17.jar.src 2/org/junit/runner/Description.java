/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class Description
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  33 */   private static final Pattern METHOD_AND_CLASS_NAME_PATTERN = Pattern.compile("([\\s\\S]*)\\((.*)\\)");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Description createSuiteDescription(String name, Annotation... annotations)
/*     */   {
/*  45 */     return new Description(null, name, annotations);
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
/*     */   public static Description createSuiteDescription(String name, Serializable uniqueId, Annotation... annotations)
/*     */   {
/*  58 */     return new Description(null, name, uniqueId, annotations);
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
/*     */   public static Description createTestDescription(String className, String name, Annotation... annotations)
/*     */   {
/*  73 */     return new Description(null, formatDisplayName(name, className), annotations);
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
/*     */   public static Description createTestDescription(Class<?> clazz, String name, Annotation... annotations)
/*     */   {
/*  86 */     return new Description(clazz, formatDisplayName(name, clazz.getName()), annotations);
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
/*     */   public static Description createTestDescription(Class<?> clazz, String name)
/*     */   {
/*  99 */     return new Description(clazz, formatDisplayName(name, clazz.getName()), new Annotation[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Description createTestDescription(String className, String name, Serializable uniqueId)
/*     */   {
/* 110 */     return new Description(null, formatDisplayName(name, className), uniqueId, new Annotation[0]);
/*     */   }
/*     */   
/*     */   private static String formatDisplayName(String name, String className) {
/* 114 */     return String.format("%s(%s)", new Object[] { name, className });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Description createSuiteDescription(Class<?> testClass)
/*     */   {
/* 124 */     return new Description(testClass, testClass.getName(), testClass.getAnnotations());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 130 */   public static final Description EMPTY = new Description(null, "No Tests", new Annotation[0]);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 137 */   public static final Description TEST_MECHANISM = new Description(null, "Test mechanism", new Annotation[0]);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 144 */   private final Collection<Description> fChildren = new ConcurrentLinkedQueue();
/*     */   private final String fDisplayName;
/*     */   private final Serializable fUniqueId;
/*     */   private final Annotation[] fAnnotations;
/*     */   private volatile Class<?> fTestClass;
/*     */   
/*     */   private Description(Class<?> clazz, String displayName, Annotation... annotations) {
/* 151 */     this(clazz, displayName, displayName, annotations);
/*     */   }
/*     */   
/*     */   private Description(Class<?> testClass, String displayName, Serializable uniqueId, Annotation... annotations) {
/* 155 */     if ((displayName == null) || (displayName.length() == 0)) {
/* 156 */       throw new IllegalArgumentException("The display name must not be empty.");
/*     */     }
/*     */     
/* 159 */     if (uniqueId == null) {
/* 160 */       throw new IllegalArgumentException("The unique id must not be null.");
/*     */     }
/*     */     
/* 163 */     this.fTestClass = testClass;
/* 164 */     this.fDisplayName = displayName;
/* 165 */     this.fUniqueId = uniqueId;
/* 166 */     this.fAnnotations = annotations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getDisplayName()
/*     */   {
/* 173 */     return this.fDisplayName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChild(Description description)
/*     */   {
/* 182 */     this.fChildren.add(description);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList<Description> getChildren()
/*     */   {
/* 190 */     return new ArrayList(this.fChildren);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isSuite()
/*     */   {
/* 197 */     return !isTest();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isTest()
/*     */   {
/* 204 */     return this.fChildren.isEmpty();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int testCount()
/*     */   {
/* 211 */     if (isTest()) {
/* 212 */       return 1;
/*     */     }
/* 214 */     int result = 0;
/* 215 */     for (Description child : this.fChildren) {
/* 216 */       result += child.testCount();
/*     */     }
/* 218 */     return result;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 223 */     return this.fUniqueId.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/* 228 */     if (!(obj instanceof Description)) {
/* 229 */       return false;
/*     */     }
/* 231 */     Description d = (Description)obj;
/* 232 */     return this.fUniqueId.equals(d.fUniqueId);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 237 */     return getDisplayName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 244 */     return equals(EMPTY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Description childlessCopy()
/*     */   {
/* 252 */     return new Description(this.fTestClass, this.fDisplayName, this.fAnnotations);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends Annotation> T getAnnotation(Class<T> annotationType)
/*     */   {
/* 260 */     for (Annotation each : this.fAnnotations) {
/* 261 */       if (each.annotationType().equals(annotationType)) {
/* 262 */         return (Annotation)annotationType.cast(each);
/*     */       }
/*     */     }
/* 265 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<Annotation> getAnnotations()
/*     */   {
/* 272 */     return Arrays.asList(this.fAnnotations);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<?> getTestClass()
/*     */   {
/* 280 */     if (this.fTestClass != null) {
/* 281 */       return this.fTestClass;
/*     */     }
/* 283 */     String name = getClassName();
/* 284 */     if (name == null) {
/* 285 */       return null;
/*     */     }
/*     */     try {
/* 288 */       this.fTestClass = Class.forName(name, false, getClass().getClassLoader());
/* 289 */       return this.fTestClass;
/*     */     } catch (ClassNotFoundException e) {}
/* 291 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getClassName()
/*     */   {
/* 300 */     return this.fTestClass != null ? this.fTestClass.getName() : methodAndClassNamePatternGroupOrDefault(2, toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMethodName()
/*     */   {
/* 308 */     return methodAndClassNamePatternGroupOrDefault(1, null);
/*     */   }
/*     */   
/*     */   private String methodAndClassNamePatternGroupOrDefault(int group, String defaultString)
/*     */   {
/* 313 */     Matcher matcher = METHOD_AND_CLASS_NAME_PATTERN.matcher(toString());
/* 314 */     return matcher.matches() ? matcher.group(group) : defaultString;
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/Description.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */