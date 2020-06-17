package junit.framework;

public abstract interface TestListener
{
  public abstract void addError(Test paramTest, Throwable paramThrowable);
  
  public abstract void addFailure(Test paramTest, AssertionFailedError paramAssertionFailedError);
  
  public abstract void endTest(Test paramTest);
  
  public abstract void startTest(Test paramTest);
}


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/TestListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */