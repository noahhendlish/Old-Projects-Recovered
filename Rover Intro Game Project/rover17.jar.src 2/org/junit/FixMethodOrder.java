package org.junit;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.runners.MethodSorters;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface FixMethodOrder
{
  MethodSorters value() default MethodSorters.DEFAULT;
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/FixMethodOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */