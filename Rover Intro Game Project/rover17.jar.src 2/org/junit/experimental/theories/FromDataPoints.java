package org.junit.experimental.theories;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.experimental.theories.internal.SpecificDataPointsSupplier;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.PARAMETER})
@ParametersSuppliedBy(SpecificDataPointsSupplier.class)
public @interface FromDataPoints
{
  String value();
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/FromDataPoints.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */