package org.junit.validator;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ValidateWith
{
  Class<? extends AnnotationValidator> value();
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/validator/ValidateWith.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */