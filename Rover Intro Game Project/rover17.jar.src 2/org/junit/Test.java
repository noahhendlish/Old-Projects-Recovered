package org.junit;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface Test
{
  Class<? extends Throwable> expected() default None.class;
  
  long timeout() default 0L;
  
  public static class None
    extends Throwable
  {
    private static final long serialVersionUID = 1L;
  }
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/Test.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */