/*    */ package junit.framework;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.junit.Ignore;
/*    */ import org.junit.runner.Describable;
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.Request;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runner.manipulation.Filter;
/*    */ import org.junit.runner.manipulation.Filterable;
/*    */ import org.junit.runner.manipulation.NoTestsRemainException;
/*    */ import org.junit.runner.manipulation.Sortable;
/*    */ import org.junit.runner.manipulation.Sorter;
/*    */ 
/*    */ public class JUnit4TestAdapter
/*    */   implements Test, Filterable, Sortable, Describable
/*    */ {
/*    */   private final Class<?> fNewTestClass;
/*    */   private final Runner fRunner;
/*    */   private final JUnit4TestAdapterCache fCache;
/*    */   
/*    */   public JUnit4TestAdapter(Class<?> newTestClass)
/*    */   {
/* 24 */     this(newTestClass, JUnit4TestAdapterCache.getDefault());
/*    */   }
/*    */   
/*    */   public JUnit4TestAdapter(Class<?> newTestClass, JUnit4TestAdapterCache cache) {
/* 28 */     this.fCache = cache;
/* 29 */     this.fNewTestClass = newTestClass;
/* 30 */     this.fRunner = Request.classWithoutSuiteMethod(newTestClass).getRunner();
/*    */   }
/*    */   
/*    */   public int countTestCases() {
/* 34 */     return this.fRunner.testCount();
/*    */   }
/*    */   
/*    */   public void run(TestResult result) {
/* 38 */     this.fRunner.run(this.fCache.getNotifier(result, this));
/*    */   }
/*    */   
/*    */   public List<Test> getTests()
/*    */   {
/* 43 */     return this.fCache.asTestList(getDescription());
/*    */   }
/*    */   
/*    */   public Class<?> getTestClass()
/*    */   {
/* 48 */     return this.fNewTestClass;
/*    */   }
/*    */   
/*    */   public Description getDescription() {
/* 52 */     Description description = this.fRunner.getDescription();
/* 53 */     return removeIgnored(description);
/*    */   }
/*    */   
/*    */   private Description removeIgnored(Description description) {
/* 57 */     if (isIgnored(description)) {
/* 58 */       return Description.EMPTY;
/*    */     }
/* 60 */     Description result = description.childlessCopy();
/* 61 */     for (Description each : description.getChildren()) {
/* 62 */       Description child = removeIgnored(each);
/* 63 */       if (!child.isEmpty()) {
/* 64 */         result.addChild(child);
/*    */       }
/*    */     }
/* 67 */     return result;
/*    */   }
/*    */   
/*    */   private boolean isIgnored(Description description) {
/* 71 */     return description.getAnnotation(Ignore.class) != null;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 76 */     return this.fNewTestClass.getName();
/*    */   }
/*    */   
/*    */   public void filter(Filter filter) throws NoTestsRemainException {
/* 80 */     filter.apply(this.fRunner);
/*    */   }
/*    */   
/*    */   public void sort(Sorter sorter) {
/* 84 */     sorter.apply(this.fRunner);
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/junit/framework/JUnit4TestAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */