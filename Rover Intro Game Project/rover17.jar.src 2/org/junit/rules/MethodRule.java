package org.junit.rules;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public abstract interface MethodRule
{
  public abstract Statement apply(Statement paramStatement, FrameworkMethod paramFrameworkMethod, Object paramObject);
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/MethodRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */