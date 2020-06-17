package org.junit.experimental.categories;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.validator.ValidateWith;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ValidateWith(CategoryValidator.class)
public @interface Category
{
  Class<?>[] value();
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/categories/Category.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */