package org.junit;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface After {}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/After.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */