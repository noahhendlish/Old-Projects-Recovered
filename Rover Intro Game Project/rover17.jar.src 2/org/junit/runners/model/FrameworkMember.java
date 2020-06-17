/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FrameworkMember<T extends FrameworkMember<T>>
/*    */   implements Annotatable
/*    */ {
/*    */   abstract boolean isShadowedBy(T paramT);
/*    */   
/*    */   boolean isShadowedBy(List<T> members)
/*    */   {
/* 16 */     for (T each : members) {
/* 17 */       if (isShadowedBy(each)) {
/* 18 */         return true;
/*    */       }
/*    */     }
/* 21 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   protected abstract int getModifiers();
/*    */   
/*    */ 
/*    */   public boolean isStatic()
/*    */   {
/* 30 */     return Modifier.isStatic(getModifiers());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean isPublic()
/*    */   {
/* 37 */     return Modifier.isPublic(getModifiers());
/*    */   }
/*    */   
/*    */   public abstract String getName();
/*    */   
/*    */   public abstract Class<?> getType();
/*    */   
/*    */   public abstract Class<?> getDeclaringClass();
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/model/FrameworkMember.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */