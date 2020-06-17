package org.junit;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
public @interface Ignore
{
  String value() default "";
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/Ignore.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */