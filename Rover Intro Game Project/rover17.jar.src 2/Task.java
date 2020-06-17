/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Task
/*    */ {
/*    */   public static final double MAX = 3.0D;
/*    */   private String name;
/*    */   private Inventory supplies;
/*    */   
/*    */   public Task(String string)
/*    */   {
/* 18 */     this.name = string;
/* 19 */     this.supplies = new Inventory();
/*    */     
/*    */ 
/* 22 */     int p = (int)Math.round(Math.random() * 3.0D);
/* 23 */     int q = (int)Math.round(Math.random() * 3.0D);
/* 24 */     int r = (int)Math.round(Math.random() * 3.0D);
/* 25 */     while (p == q) {
/* 26 */       q = (int)Math.round(Math.random() * 3.0D);
/*    */     }
/* 28 */     while ((r == q) || (r == p)) {
/* 29 */       r = (int)Math.round(Math.random() * 3.0D);
/*    */     }
/* 31 */     generate(p);
/* 32 */     generate(q);
/* 33 */     generate(r);
/*    */   }
/*    */   
/*    */   private void generate(int what) {
/* 37 */     int num = (int)Math.round(Math.random() * 3.0D) + 1;
/*    */     
/*    */ 
/* 40 */     for (int i = 0; i < num; i++) {
/* 41 */       switch (what) {
/*    */       case 1: 
/* 43 */         this.supplies.add(new Part(Parts.SCREW));
/* 44 */         break;
/*    */       case 2: 
/* 46 */         this.supplies.add(new Part(Parts.CAKE));
/* 47 */         break;
/*    */       
/*    */       case 3: 
/* 50 */         this.supplies.add(new Part(Parts.CABBAGE));
/* 51 */         break;
/*    */       
/*    */       default: 
/* 54 */         this.supplies.add(new Part(Parts.GEAR));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public String toString() {
/* 60 */     return "Fix the " + this.name + "\n" + this.supplies.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean fix(Ship s, Inventory inventory)
/*    */   {
/* 72 */     System.out.println("attempting fix");
/*    */     
/* 74 */     if (!s.toString().equals(this.name))
/* 75 */       return false;
/* 76 */     System.out.println("same ship");
/*    */     
/* 78 */     if (!s.isBroken())
/* 79 */       return false;
/* 80 */     System.out.println("broken");
/*    */     
/*    */ 
/* 83 */     if (!inventory.contains(this.supplies))
/* 84 */       return false;
/* 85 */     System.out.println("supplies ok");
/*    */     
/* 87 */     inventory.remove(this.supplies);
/* 88 */     s.becomeFixed();
/* 89 */     System.out.println("done");
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Task.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */