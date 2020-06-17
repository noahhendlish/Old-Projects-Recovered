package org.junit.experimental.theories;

import java.util.List;

public abstract class ParameterSupplier
{
  public abstract List<PotentialAssignment> getValueSources(ParameterSignature paramParameterSignature)
    throws Throwable;
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/experimental/theories/ParameterSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */