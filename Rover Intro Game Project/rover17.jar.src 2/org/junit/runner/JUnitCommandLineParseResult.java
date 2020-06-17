/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.internal.Classes;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ 
/*     */ 
/*     */ class JUnitCommandLineParseResult
/*     */ {
/*  13 */   private final List<String> filterSpecs = new ArrayList();
/*  14 */   private final List<Class<?>> classes = new ArrayList();
/*  15 */   private final List<Throwable> parserErrors = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> getFilterSpecs()
/*     */   {
/*  26 */     return Collections.unmodifiableList(this.filterSpecs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<Class<?>> getClasses()
/*     */   {
/*  33 */     return Collections.unmodifiableList(this.classes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JUnitCommandLineParseResult parse(String[] args)
/*     */   {
/*  42 */     JUnitCommandLineParseResult result = new JUnitCommandLineParseResult();
/*     */     
/*  44 */     result.parseArgs(args);
/*     */     
/*  46 */     return result;
/*     */   }
/*     */   
/*     */   private void parseArgs(String[] args) {
/*  50 */     parseParameters(parseOptions(args));
/*     */   }
/*     */   
/*     */   String[] parseOptions(String... args) {
/*  54 */     for (int i = 0; i != args.length; i++) {
/*  55 */       String arg = args[i];
/*     */       
/*  57 */       if (arg.equals("--"))
/*  58 */         return copyArray(args, i + 1, args.length);
/*  59 */       if (arg.startsWith("--")) {
/*  60 */         if ((arg.startsWith("--filter=")) || (arg.equals("--filter"))) {
/*     */           String filterSpec;
/*  62 */           if (arg.equals("--filter")) {
/*  63 */             i++;
/*     */             String filterSpec;
/*  65 */             if (i < args.length) {
/*  66 */               filterSpec = args[i];
/*     */             } else {
/*  68 */               this.parserErrors.add(new CommandLineParserError(arg + " value not specified"));
/*  69 */               break;
/*     */             }
/*     */           } else {
/*  72 */             filterSpec = arg.substring(arg.indexOf('=') + 1);
/*     */           }
/*     */           
/*  75 */           this.filterSpecs.add(filterSpec);
/*     */         } else {
/*  77 */           this.parserErrors.add(new CommandLineParserError("JUnit knows nothing about the " + arg + " option"));
/*     */         }
/*     */       } else {
/*  80 */         return copyArray(args, i, args.length);
/*     */       }
/*     */     }
/*     */     
/*  84 */     return new String[0];
/*     */   }
/*     */   
/*     */   private String[] copyArray(String[] args, int from, int to) {
/*  88 */     ArrayList<String> result = new ArrayList();
/*     */     
/*  90 */     for (int j = from; j != to; j++) {
/*  91 */       result.add(args[j]);
/*     */     }
/*     */     
/*  94 */     return (String[])result.toArray(new String[result.size()]);
/*     */   }
/*     */   
/*     */   void parseParameters(String[] args) {
/*  98 */     for (String arg : args) {
/*     */       try {
/* 100 */         this.classes.add(Classes.getClass(arg));
/*     */       } catch (ClassNotFoundException e) {
/* 102 */         this.parserErrors.add(new IllegalArgumentException("Could not find class [" + arg + "]", e));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private Request errorReport(Throwable cause) {
/* 108 */     return Request.errorReport(JUnitCommandLineParseResult.class, cause);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Request createRequest(Computer computer)
/*     */   {
/* 117 */     if (this.parserErrors.isEmpty()) {
/* 118 */       Request request = Request.classes(computer, (Class[])this.classes.toArray(new Class[this.classes.size()]));
/*     */       
/* 120 */       return applyFilterSpecs(request);
/*     */     }
/* 122 */     return errorReport(new InitializationError(this.parserErrors));
/*     */   }
/*     */   
/*     */   private Request applyFilterSpecs(Request request)
/*     */   {
/*     */     try {
/* 128 */       for (String filterSpec : this.filterSpecs) {
/* 129 */         Filter filter = FilterFactories.createFilterFromFilterSpec(request, filterSpec);
/*     */         
/* 131 */         request = request.filterWith(filter);
/*     */       }
/* 133 */       return request;
/*     */     } catch (FilterFactory.FilterNotCreatedException e) {
/* 135 */       return errorReport(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CommandLineParserError
/*     */     extends Exception
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public CommandLineParserError(String message)
/*     */     {
/* 146 */       super();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/runner/JUnitCommandLineParseResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */