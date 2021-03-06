package org.junit.experimental.theories;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
public @interface DataPoints
{
  String[] value() default {};
  
  Class<? extends Throwable>[] ignoredExceptions() default {};
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/DataPoints.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */