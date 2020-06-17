package org.junit.runners.parameterized;

import org.junit.runner.Runner;
import org.junit.runners.model.InitializationError;

public abstract interface ParametersRunnerFactory
{
  public abstract Runner createRunnerForTestWithParameters(TestWithParameters paramTestWithParameters)
    throws InitializationError;
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runners/parameterized/ParametersRunnerFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */