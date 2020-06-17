/*     */ package org.junit.runners.model;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import org.junit.internal.runners.model.ReflectiveCallable;
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
/*     */ public class FrameworkMethod
/*     */   extends FrameworkMember<FrameworkMethod>
/*     */ {
/*     */   private final Method method;
/*     */   
/*     */   public FrameworkMethod(Method method)
/*     */   {
/*  26 */     if (method == null) {
/*  27 */       throw new NullPointerException("FrameworkMethod cannot be created without an underlying method.");
/*     */     }
/*     */     
/*  30 */     this.method = method;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Method getMethod()
/*     */   {
/*  37 */     return this.method;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object invokeExplosively(final Object target, final Object... params)
/*     */     throws Throwable
/*     */   {
/*  47 */     new ReflectiveCallable()
/*     */     {
/*     */       protected Object runReflectiveCall() throws Throwable {
/*  50 */         return FrameworkMethod.this.method.invoke(target, params);
/*     */       }
/*     */     }.run();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  60 */     return this.method.getName();
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
/*     */   public void validatePublicVoidNoArg(boolean isStatic, List<Throwable> errors)
/*     */   {
/*  74 */     validatePublicVoid(isStatic, errors);
/*  75 */     if (this.method.getParameterTypes().length != 0) {
/*  76 */       errors.add(new Exception("Method " + this.method.getName() + " should have no parameters"));
/*     */     }
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
/*     */   public void validatePublicVoid(boolean isStatic, List<Throwable> errors)
/*     */   {
/*  91 */     if (isStatic() != isStatic) {
/*  92 */       String state = isStatic ? "should" : "should not";
/*  93 */       errors.add(new Exception("Method " + this.method.getName() + "() " + state + " be static"));
/*     */     }
/*  95 */     if (!isPublic()) {
/*  96 */       errors.add(new Exception("Method " + this.method.getName() + "() should be public"));
/*     */     }
/*  98 */     if (this.method.getReturnType() != Void.TYPE) {
/*  99 */       errors.add(new Exception("Method " + this.method.getName() + "() should be void"));
/*     */     }
/*     */   }
/*     */   
/*     */   protected int getModifiers()
/*     */   {
/* 105 */     return this.method.getModifiers();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Class<?> getReturnType()
/*     */   {
/* 112 */     return this.method.getReturnType();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<?> getType()
/*     */   {
/* 120 */     return getReturnType();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<?> getDeclaringClass()
/*     */   {
/* 128 */     return this.method.getDeclaringClass();
/*     */   }
/*     */   
/*     */   public void validateNoTypeParametersOnArgs(List<Throwable> errors) {
/* 132 */     new NoGenericTypeParametersValidator(this.method).validate(errors);
/*     */   }
/*     */   
/*     */   public boolean isShadowedBy(FrameworkMethod other)
/*     */   {
/* 137 */     if (!other.getName().equals(getName())) {
/* 138 */       return false;
/*     */     }
/* 140 */     if (other.getParameterTypes().length != getParameterTypes().length) {
/* 141 */       return false;
/*     */     }
/* 143 */     for (int i = 0; i < other.getParameterTypes().length; i++) {
/* 144 */       if (!other.getParameterTypes()[i].equals(getParameterTypes()[i])) {
/* 145 */         return false;
/*     */       }
/*     */     }
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/* 153 */     if (!FrameworkMethod.class.isInstance(obj)) {
/* 154 */       return false;
/*     */     }
/* 156 */     return ((FrameworkMethod)obj).method.equals(this.method);
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 161 */     return this.method.hashCode();
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
/*     */   @Deprecated
/*     */   public boolean producesType(Type type)
/*     */   {
/* 175 */     return (getParameterTypes().length == 0) && ((type instanceof Class)) && (((Class)type).isAssignableFrom(this.method.getReturnType()));
/*     */   }
/*     */   
/*     */   private Class<?>[] getParameterTypes()
/*     */   {
/* 180 */     return this.method.getParameterTypes();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Annotation[] getAnnotations()
/*     */   {
/* 187 */     return this.method.getAnnotations();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends Annotation> T getAnnotation(Class<T> annotationType)
/*     */   {
/* 195 */     return this.method.getAnnotation(annotationType);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 200 */     return this.method.toString();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/model/FrameworkMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */