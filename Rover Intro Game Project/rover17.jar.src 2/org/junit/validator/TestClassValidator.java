package org.junit.validator;

import java.util.List;
import org.junit.runners.model.TestClass;

public abstract interface TestClassValidator
{
  public abstract List<Exception> validateTestClass(TestClass paramTestClass);
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/validator/TestClassValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */