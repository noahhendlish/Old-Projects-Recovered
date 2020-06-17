package org.junit.rules;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public abstract interface TestRule
{
  public abstract Statement apply(Statement paramStatement, Description paramDescription);
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/TestRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */