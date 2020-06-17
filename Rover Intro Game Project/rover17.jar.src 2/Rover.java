/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Queue;
/*     */ import java.util.Stack;
/*     */ import javax.imageio.ImageIO;
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
/*     */ public class Rover
/*     */   implements IRover
/*     */ {
/*     */   private Room room;
/*     */   private Point location;
/*     */   private Inventory inventory;
/*     */   private Stack<Portal> mapToBase;
/*     */   private Queue<Task> tasks;
/*     */   
/*     */   public Rover()
/*     */   {
/*  36 */     this.room = new Room();
/*  37 */     this.location = new Point(2, 2);
/*  38 */     this.inventory = new Inventory();
/*  39 */     this.tasks = new ArrayDeque();
/*  40 */     this.mapToBase = new Stack();
/*  41 */     generateTasks();
/*     */   }
/*     */   
/*     */   private void generateTasks()
/*     */   {
/*  46 */     for (int width = 0; width < 16; width++) {
/*  47 */       for (int height = 0; height < 16; height++) {
/*  48 */         if (this.room.isShip(width, height)) {
/*  49 */           System.out.println("ship at " + width + "," + height);
/*  50 */           Ship s = (Ship)this.room.getItem(width, height);
/*  51 */           if (s.isBroken()) {
/*  52 */             System.out.println("is broken");
/*  53 */             Task t = this.room.generateTask(width, height);
/*  54 */             this.tasks.add(t);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point getLocation()
/*     */   {
/*  66 */     return this.location;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTask()
/*     */   {
/*  75 */     if (this.tasks.isEmpty())
/*  76 */       return "You win!";
/*  77 */     return ((Task)this.tasks.peek()).toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getInventory()
/*     */   {
/*  86 */     return this.inventory.toString();
/*     */   }
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
/*     */   public void pickUp()
/*     */   {
/* 102 */     if ((this.room.isOccupied(getLocation())) && (this.room.isLiftable(getLocation()))) {
/* 103 */       Item i = this.room.removeItem(getLocation());
/* 104 */       this.inventory.add(i);
/*     */     }
/*     */   }
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
/*     */   public void goRight()
/*     */   {
/* 123 */     if (this.location.x < 15) {
/* 124 */       this.location.x += 1;
/* 125 */       checkForPortal();
/*     */     }
/*     */   }
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
/*     */   public void goLeft()
/*     */   {
/* 142 */     if (this.location.x > 0) {
/* 143 */       this.location.x -= 1;
/* 144 */       checkForPortal();
/*     */     }
/*     */   }
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
/*     */   public void goDown()
/*     */   {
/* 161 */     if (this.location.y < 15) {
/* 162 */       this.location.y += 1;
/* 163 */       checkForPortal();
/*     */     }
/*     */   }
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
/*     */   public void goUp()
/*     */   {
/* 179 */     if (this.location.y > 0) {
/* 180 */       this.location.y -= 1;
/* 181 */       checkForPortal();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkForPortal()
/*     */   {
/* 189 */     if (this.room.isPortal(this.location))
/*     */     {
/* 191 */       Portal p = (Portal)this.room.getItem(this.location);
/*     */       
/* 193 */       this.room.stopFlashingPortals();
/*     */       
/*     */ 
/* 196 */       if (p.getOtherEnd() == null)
/*     */       {
/*     */ 
/* 199 */         Room r = new Room(p);
/*     */         
/* 201 */         this.room = r;
/* 202 */         this.location = p.getOtherEnd().getLocation();
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 207 */         this.room = p.getOtherEnd().getRoom();
/* 208 */         this.location = p.getOtherEnd().getLocation();
/*     */       }
/*     */       
/*     */ 
/* 212 */       if ((!this.mapToBase.isEmpty()) && (this.mapToBase.peek() == p)) {
/* 213 */         this.mapToBase.pop();
/*     */       }
/*     */       else {
/* 216 */         this.mapToBase.push(p.getOtherEnd());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Room getRoom()
/*     */   {
/* 228 */     return this.room;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void showTheWayBack()
/*     */   {
/* 238 */     if (!this.mapToBase.isEmpty())
/*     */     {
/*     */ 
/* 241 */       Portal p = (Portal)this.mapToBase.peek();
/* 242 */       p.setImageFlashing(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public BufferedImage getImage()
/*     */   {
/*     */     try {
/* 249 */       return ImageIO.read(new File("rover.jpg"));
/*     */     }
/*     */     catch (IOException ie) {}
/* 252 */     return null;
/*     */   }
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
/*     */   public void performTask()
/*     */   {
/* 266 */     Item i = this.room.getItem(this.location);
/* 267 */     if ((i != null) && ((i instanceof Ship))) {
/* 268 */       Ship s = (Ship)i;
/* 269 */       Task t = (Task)this.tasks.peek();
/* 270 */       boolean success = t.fix(s, this.inventory);
/* 271 */       if (success) {
/* 272 */         this.tasks.poll();
/*     */       }
/*     */     }
/* 275 */     if (this.tasks.isEmpty()) {
/* 276 */       System.out.println("You Win!");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Rover.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */