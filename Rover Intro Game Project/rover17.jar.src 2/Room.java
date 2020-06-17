/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
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
/*     */ public class Room
/*     */   implements IRoom
/*     */ {
/*     */   private static final double DENSITY = 0.05D;
/*     */   public static final int SIZE = 16;
/*     */   private Item[][] grid;
/*     */   
/*     */   public Room()
/*     */   {
/*  35 */     this.grid = new Item[16][16];
/*     */     
/*  37 */     this.grid[0][0] = new Portal(this, new Point(0, 0));
/*  38 */     this.grid[15][15] = new Portal(this, new Point(15, 
/*  39 */       15));
/*  40 */     this.grid[0][15] = new Portal(this, new Point(0, 15));
/*  41 */     this.grid[15][0] = new Portal(this, new Point(15, 0));
/*     */     
/*  43 */     this.grid[8][8] = new Ship(true, ShipComp.CABIN);
/*  44 */     this.grid[8][7] = new Ship(true, ShipComp.ENGINE);
/*  45 */     this.grid[7][7] = new Ship(false, ShipComp.ENGINE);
/*  46 */     this.grid[9][7] = new Ship(false, ShipComp.ENGINE);
/*  47 */     this.grid[7][9] = new Ship(true, ShipComp.WHEEL);
/*  48 */     this.grid[9][9] = new Ship(false, ShipComp.WHEEL);
/*  49 */     this.grid[8][9] = new Ship(false, ShipComp.EXHAUST);
/*  50 */     this.grid[8][6] = new Ship(false, ShipComp.RAMP);
/*     */     
/*     */ 
/*  53 */     int num = (int)Math.round(Math.random() * 12.8D) + 1;
/*     */     
/*  55 */     for (int i = 0; i < num; i++) {
/*  56 */       addItem();
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
/*     */   public Room(Portal connection)
/*     */   {
/*  69 */     this.grid = new Item[16][16];
/*     */     
/*     */ 
/*  72 */     Point pt = getNewLocation();
/*  73 */     Portal portal = new Portal(this, pt, connection);
/*  74 */     this.grid[pt.x][pt.y] = portal;
/*  75 */     connection.setOtherEnd(portal);
/*     */     
/*     */ 
/*  78 */     int num = (int)Math.round(Math.random() * 2.0D + 1.0D);
/*     */     
/*  80 */     for (int i = 0; i < num; i++) {
/*  81 */       pt = getNewLocation();
/*  82 */       this.grid[pt.x][pt.y] = new Portal(this, pt);
/*     */     }
/*     */     
/*     */ 
/*  86 */     num = (int)Math.round(Math.random() * 12.8D);
/*     */     
/*  88 */     for (int i = 0; i < num; i++) {
/*  89 */       addItem();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void addItem()
/*     */   {
/*  97 */     int num = (int)Math.round(Math.random() * 3.0D);
/*     */     
/*  99 */     switch (num) {
/*     */     case 1: 
/* 101 */       Point pt = getNewLocation();
/* 102 */       this.grid[pt.x][pt.y] = new Part(Parts.SCREW);
/* 103 */       break;
/*     */     case 2: 
/* 105 */       Point pt = getNewLocation();
/* 106 */       this.grid[pt.x][pt.y] = new Part(Parts.CAKE);
/* 107 */       break;
/*     */     
/*     */     case 3: 
/* 110 */       Point pt = getNewLocation();
/* 111 */       this.grid[pt.x][pt.y] = new Part(Parts.CABBAGE);
/* 112 */       break;
/*     */     
/*     */     default: 
/* 115 */       Point pt = getNewLocation();
/* 116 */       this.grid[pt.x][pt.y] = new Part(Parts.GEAR);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   private Point getNewLocation()
/*     */   {
/*     */     int x;
/*     */     int y;
/*     */     do
/*     */     {
/* 128 */       x = (int)Math.round(Math.random() * 15.0D);
/* 129 */       y = (int)Math.round(Math.random() * 15.0D);
/* 130 */     } while (isOccupied(x, y));
/* 131 */     return new Point(x, y);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isOccupied(Point location)
/*     */   {
/* 137 */     return isOccupied(location.x, location.y);
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
/*     */   public boolean isOccupied(int x, int y)
/*     */   {
/* 150 */     return this.grid[x][y] != null;
/*     */   }
/*     */   
/*     */   public Item removeItem(Point location) {
/* 154 */     if (isOccupied(location))
/*     */     {
/* 156 */       Item item = this.grid[location.x][location.y];
/* 157 */       this.grid[location.x][location.y] = null;
/* 158 */       return item;
/*     */     }
/*     */     
/* 161 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isLiftable(Point location)
/*     */   {
/* 166 */     return (!isPortal(location)) && (!isShip(location));
/*     */   }
/*     */   
/*     */   public boolean isShip(Point location) {
/* 170 */     return (isOccupied(location.x, location.y)) && ((this.grid[location.x][location.y] instanceof Ship));
/*     */   }
/*     */   
/*     */   public boolean isPortal(int x, int y) {
/* 174 */     return (isOccupied(x, y)) && ((this.grid[x][y] instanceof Portal));
/*     */   }
/*     */   
/*     */   public boolean isPortal(Point location) {
/* 178 */     return isPortal(location.x, location.y);
/*     */   }
/*     */   
/*     */   public Item getItem(Point location) {
/* 182 */     if (isOccupied(location))
/* 183 */       return this.grid[location.x][location.y];
/* 184 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Image getImage(int i, int j)
/*     */   {
/* 195 */     return this.grid[i][j].getImage();
/*     */   }
/*     */   
/*     */   public boolean isShip(int width, int height) {
/* 199 */     return isShip(new Point(width, height));
/*     */   }
/*     */   
/*     */   public Item getItem(int width, int height) {
/* 203 */     return getItem(new Point(width, height));
/*     */   }
/*     */   
/*     */ 
/*     */   public Task generateTask(int width, int height)
/*     */   {
/* 209 */     Ship s = (Ship)getItem(width, height);
/* 210 */     Task t = new Task(s.toString());
/* 211 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */   public void stopFlashingPortals()
/*     */   {
/*     */     Item[][] arrayOfItem;
/* 218 */     int j = (arrayOfItem = this.grid).length; for (int i = 0; i < j; i++) { Item[] row = arrayOfItem[i];
/* 219 */       Item[] arrayOfItem1; int m = (arrayOfItem1 = row).length; for (int k = 0; k < m; k++) { Item i = arrayOfItem1[k];
/* 220 */         if ((i instanceof Portal)) {
/* 221 */           Portal p = (Portal)i;
/* 222 */           p.setImageFlashing(false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Room.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */