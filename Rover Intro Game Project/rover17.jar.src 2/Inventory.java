/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
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
/*     */ public class Inventory
/*     */ {
/*     */   private LinkedList<Inventory.Box> inventory;
/*     */   
/*     */   public Inventory()
/*     */   {
/*  28 */     this.inventory = new LinkedList();
/*     */   }
/*     */   
/*     */   public void add(Item i) {
/*  32 */     boolean found = false;
/*     */     
/*     */ 
/*  35 */     for (Inventory.Box b : this.inventory) {
/*  36 */       if (b.item.equals(i))
/*     */       {
/*  38 */         b.count += 1;
/*  39 */         found = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  44 */     if (!found) {
/*  45 */       Inventory.Box b = new Inventory.Box(null);
/*  46 */       b.item = i;
/*  47 */       b.count = 1;
/*     */       
/*     */ 
/*  50 */       if (this.inventory.isEmpty())
/*     */       {
/*  52 */         this.inventory.add(0, b);
/*     */ 
/*     */       }
/*  55 */       else if (((Inventory.Box)this.inventory.get(0)).item.compareTo(i) > 0)
/*     */       {
/*  57 */         this.inventory.add(0, b);
/*     */       }
/*     */       else
/*     */       {
/*  61 */         for (Inventory.Box box : this.inventory)
/*     */         {
/*  63 */           if (box.item.compareTo(i) > 0)
/*     */           {
/*  65 */             int index = this.inventory.indexOf(box);
/*     */             
/*     */ 
/*  68 */             this.inventory.add(index, b);
/*  69 */             return;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  75 */         this.inventory.addLast(b);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  82 */     String s = "";
/*  83 */     for (Inventory.Box b : this.inventory)
/*  84 */       s = s + b.count + " " + b.item.toString() + "\n";
/*  85 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */   public void remove(Inventory supplies)
/*     */   {
/*     */     Iterator localIterator2;
/*     */     Inventory.Box b;
/*  93 */     for (Iterator localIterator1 = supplies.inventory.iterator(); localIterator1.hasNext(); 
/*     */         
/*  95 */         localIterator2.hasNext())
/*     */     {
/*  93 */       Inventory.Box a = (Inventory.Box)localIterator1.next();
/*     */       
/*  95 */       localIterator2 = this.inventory.iterator(); continue;b = (Inventory.Box)localIterator2.next();
/*  96 */       if (b.item.compareTo(a.item) == 0) {
/*  97 */         b.count -= a.count;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     Inventory.Box bToRemove;
/*     */     
/*     */     do
/*     */     {
/* 106 */       bToRemove = null;
/* 107 */       for (Inventory.Box b : this.inventory) {
/* 108 */         if (b.count < 1)
/*     */         {
/*     */ 
/* 111 */           bToRemove = b;
/*     */         }
/*     */       }
/* 114 */       if (bToRemove != null) {
/* 115 */         this.inventory.remove(bToRemove);
/*     */       }
/*     */       
/* 118 */     } while (bToRemove != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(Inventory supplies)
/*     */   {
/* 129 */     for (Inventory.Box need : supplies.inventory) {
/* 130 */       boolean found = false;
/*     */       
/* 132 */       for (Inventory.Box have : this.inventory) {
/* 133 */         if ((have.item.equals(need.item)) && (have.count >= need.count))
/* 134 */           found = true;
/*     */       }
/* 136 */       if (!found) {
/* 137 */         return false;
/*     */       }
/*     */     }
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   private class Box
/*     */   {
/*     */     public Item item;
/*     */     public int count;
/*     */     
/*     */     private Box() {}
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Inventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */