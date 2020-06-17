/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.lang.reflect.GenericArrayType;
/*    */ import java.lang.reflect.ParameterizedType;
/*    */ import java.lang.reflect.Type;
/*    */ import java.lang.reflect.WildcardType;
/*    */ import java.util.List;
/*    */ 
/*    */ class NoGenericTypeParametersValidator
/*    */ {
/*    */   private final java.lang.reflect.Method method;
/*    */   
/*    */   NoGenericTypeParametersValidator(java.lang.reflect.Method method)
/*    */   {
/* 15 */     this.method = method;
/*    */   }
/*    */   
/*    */   void validate(List<Throwable> errors) {
/* 19 */     for (Type each : this.method.getGenericParameterTypes()) {
/* 20 */       validateNoTypeParameterOnType(each, errors);
/*    */     }
/*    */   }
/*    */   
/*    */   private void validateNoTypeParameterOnType(Type type, List<Throwable> errors) {
/* 25 */     if ((type instanceof java.lang.reflect.TypeVariable)) {
/* 26 */       errors.add(new Exception("Method " + this.method.getName() + "() contains unresolved type variable " + type));
/*    */     }
/* 28 */     else if ((type instanceof ParameterizedType)) {
/* 29 */       validateNoTypeParameterOnParameterizedType((ParameterizedType)type, errors);
/* 30 */     } else if ((type instanceof WildcardType)) {
/* 31 */       validateNoTypeParameterOnWildcardType((WildcardType)type, errors);
/* 32 */     } else if ((type instanceof GenericArrayType)) {
/* 33 */       validateNoTypeParameterOnGenericArrayType((GenericArrayType)type, errors);
/*    */     }
/*    */   }
/*    */   
/*    */   private void validateNoTypeParameterOnParameterizedType(ParameterizedType parameterized, List<Throwable> errors)
/*    */   {
/* 39 */     for (Type each : parameterized.getActualTypeArguments()) {
/* 40 */       validateNoTypeParameterOnType(each, errors);
/*    */     }
/*    */   }
/*    */   
/*    */   private void validateNoTypeParameterOnWildcardType(WildcardType wildcard, List<Throwable> errors)
/*    */   {
/* 46 */     for (Type each : wildcard.getUpperBounds()) {
/* 47 */       validateNoTypeParameterOnType(each, errors);
/*    */     }
/* 49 */     for (Type each : wildcard.getLowerBounds()) {
/* 50 */       validateNoTypeParameterOnType(each, errors);
/*    */     }
/*    */   }
/*    */   
/*    */   private void validateNoTypeParameterOnGenericArrayType(GenericArrayType arrayType, List<Throwable> errors)
/*    */   {
/* 56 */     validateNoTypeParameterOnType(arrayType.getGenericComponentType(), errors);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/model/NoGenericTypeParametersValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */