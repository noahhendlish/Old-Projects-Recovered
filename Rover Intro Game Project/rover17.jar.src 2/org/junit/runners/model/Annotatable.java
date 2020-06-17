package org.junit.runners.model;

import java.lang.annotation.Annotation;

public abstract interface Annotatable
{
  public abstract Annotation[] getAnnotations();
  
  public abstract <T extends Annotation> T getAnnotation(Class<T> paramClass);
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/model/Annotatable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */