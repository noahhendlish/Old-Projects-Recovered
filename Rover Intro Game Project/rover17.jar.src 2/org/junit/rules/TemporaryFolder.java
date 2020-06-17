/*     */ package org.junit.rules;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TemporaryFolder
/*     */   extends ExternalResource
/*     */ {
/*     */   private final File parentFolder;
/*     */   private File folder;
/*     */   
/*     */   public TemporaryFolder()
/*     */   {
/*  36 */     this(null);
/*     */   }
/*     */   
/*     */   public TemporaryFolder(File parentFolder) {
/*  40 */     this.parentFolder = parentFolder;
/*     */   }
/*     */   
/*     */   protected void before() throws Throwable
/*     */   {
/*  45 */     create();
/*     */   }
/*     */   
/*     */   protected void after()
/*     */   {
/*  50 */     delete();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void create()
/*     */     throws IOException
/*     */   {
/*  59 */     this.folder = createTemporaryFolderIn(this.parentFolder);
/*     */   }
/*     */   
/*     */ 
/*     */   public File newFile(String fileName)
/*     */     throws IOException
/*     */   {
/*  66 */     File file = new File(getRoot(), fileName);
/*  67 */     if (!file.createNewFile()) {
/*  68 */       throw new IOException("a file with the name '" + fileName + "' already exists in the test folder");
/*     */     }
/*     */     
/*  71 */     return file;
/*     */   }
/*     */   
/*     */ 
/*     */   public File newFile()
/*     */     throws IOException
/*     */   {
/*  78 */     return File.createTempFile("junit", null, getRoot());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public File newFolder(String folder)
/*     */     throws IOException
/*     */   {
/*  86 */     return newFolder(new String[] { folder });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public File newFolder(String... folderNames)
/*     */     throws IOException
/*     */   {
/*  94 */     File file = getRoot();
/*  95 */     for (int i = 0; i < folderNames.length; i++) {
/*  96 */       String folderName = folderNames[i];
/*  97 */       validateFolderName(folderName);
/*  98 */       file = new File(file, folderName);
/*  99 */       if ((!file.mkdir()) && (isLastElementInArray(i, folderNames))) {
/* 100 */         throw new IOException("a folder with the name '" + folderName + "' already exists");
/*     */       }
/*     */     }
/*     */     
/* 104 */     return file;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void validateFolderName(String folderName)
/*     */     throws IOException
/*     */   {
/* 114 */     File tempFile = new File(folderName);
/* 115 */     if (tempFile.getParent() != null) {
/* 116 */       String errorMsg = "Folder name cannot consist of multiple path components separated by a file separator. Please use newFolder('MyParentFolder','MyFolder') to create hierarchies of folders";
/*     */       
/* 118 */       throw new IOException(errorMsg);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isLastElementInArray(int index, String[] array) {
/* 123 */     return index == array.length - 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public File newFolder()
/*     */     throws IOException
/*     */   {
/* 130 */     return createTemporaryFolderIn(getRoot());
/*     */   }
/*     */   
/*     */   private File createTemporaryFolderIn(File parentFolder) throws IOException {
/* 134 */     File createdFolder = File.createTempFile("junit", "", parentFolder);
/* 135 */     createdFolder.delete();
/* 136 */     createdFolder.mkdir();
/* 137 */     return createdFolder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public File getRoot()
/*     */   {
/* 144 */     if (this.folder == null) {
/* 145 */       throw new IllegalStateException("the temporary folder has not yet been created");
/*     */     }
/*     */     
/* 148 */     return this.folder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delete()
/*     */   {
/* 156 */     if (this.folder != null) {
/* 157 */       recursiveDelete(this.folder);
/*     */     }
/*     */   }
/*     */   
/*     */   private void recursiveDelete(File file) {
/* 162 */     File[] files = file.listFiles();
/* 163 */     if (files != null) {
/* 164 */       for (File each : files) {
/* 165 */         recursiveDelete(each);
/*     */       }
/*     */     }
/* 168 */     file.delete();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/org/junit/rules/TemporaryFolder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */