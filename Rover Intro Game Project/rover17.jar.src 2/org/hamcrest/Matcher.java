package org.hamcrest;

public abstract interface Matcher<T>
  extends SelfDescribing
{
  public abstract boolean matches(Object paramObject);
  
  public abstract void describeMismatch(Object paramObject, Description paramDescription);
  
  @Deprecated
  public abstract void _dont_implement_Matcher___instead_extend_BaseMatcher_();
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/hamcrest/Matcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */