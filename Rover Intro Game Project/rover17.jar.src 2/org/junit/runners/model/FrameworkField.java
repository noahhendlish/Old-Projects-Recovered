/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FrameworkField
/*    */   extends FrameworkMember<FrameworkField>
/*    */ {
/*    */   private final Field field;
/*    */   
/*    */   FrameworkField(Field field)
/*    */   {
/* 18 */     if (field == null) {
/* 19 */       throw new NullPointerException("FrameworkField cannot be created without an underlying field.");
/*    */     }
/*    */     
/* 22 */     this.field = field;
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 27 */     return getField().getName();
/*    */   }
/*    */   
/*    */   public Annotation[] getAnnotations() {
/* 31 */     return this.field.getAnnotations();
/*    */   }
/*    */   
/*    */   public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
/* 35 */     return this.field.getAnnotation(annotationType);
/*    */   }
/*    */   
/*    */   public boolean isShadowedBy(FrameworkField otherMember)
/*    */   {
/* 40 */     return otherMember.getName().equals(getName());
/*    */   }
/*    */   
/*    */   protected int getModifiers()
/*    */   {
/* 45 */     return this.field.getModifiers();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Field getField()
/*    */   {
/* 52 */     return this.field;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Class<?> getType()
/*    */   {
/* 61 */     return this.field.getType();
/*    */   }
/*    */   
/*    */   public Class<?> getDeclaringClass()
/*    */   {
/* 66 */     return this.field.getDeclaringClass();
/*    */   }
/*    */   
/*    */ 
/*    */   public Object get(Object target)
/*    */     throws IllegalArgumentException, IllegalAccessException
/*    */   {
/* 73 */     return this.field.get(target);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 78 */     return this.field.toString();
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/model/FrameworkField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */