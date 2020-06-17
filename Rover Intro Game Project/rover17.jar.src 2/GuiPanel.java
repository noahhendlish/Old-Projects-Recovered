/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import javax.swing.JPanel;
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
/*     */ public class GuiPanel
/*     */   extends JPanel
/*     */ {
/*  23 */   public static int GRIDSIZE = 400;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  28 */   public static int CELLSIZE = 25;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  33 */   private Dimension canvasSize = new Dimension(GRIDSIZE, GRIDSIZE);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  38 */   private Dimension cellSize = new Dimension(CELLSIZE, CELLSIZE);
/*     */   private IRover rover;
/*     */   
/*  41 */   public GuiPanel(IRover rover) { this.rover = rover; }
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
/*     */   public void paint(Graphics g)
/*     */   {
/*  56 */     Graphics2D g2 = (Graphics2D)g;
/*     */     
/*  58 */     IRoom room = this.rover.getRoom();
/*     */     
/*     */ 
/*  61 */     synchronized (room)
/*     */     {
/*  63 */       for (int i = 0; i < 16; i++) {
/*  64 */         for (int j = 0; j < 16; j++)
/*     */         {
/*  66 */           g2.setColor(Color.WHITE);
/*  67 */           g2.fillRect(i * this.cellSize.width, j * this.cellSize.height, 
/*  68 */             this.cellSize.width, this.cellSize.height);
/*     */           
/*     */ 
/*  71 */           if (room.isOccupied(i, j)) {
/*  72 */             Image image = room.getImage(i, j);
/*     */             
/*  74 */             if (image != null) {
/*  75 */               g2.drawImage(image, i * this.cellSize.width, j * 
/*  76 */                 this.cellSize.height, this);
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/*  81 */               g2.setColor(Color.ORANGE);
/*  82 */               g2.fillOval(i * this.cellSize.width, j * this.cellSize.width, 
/*  83 */                 this.cellSize.width, this.cellSize.height);
/*  84 */               g2.setColor(Color.BLACK);
/*  85 */               g2.drawOval(i * this.cellSize.width, j * this.cellSize.width, 
/*  86 */                 this.cellSize.width, this.cellSize.height);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  94 */     int roverX = this.rover.getLocation().x * this.cellSize.width;
/*  95 */     int roverY = this.rover.getLocation().y * this.cellSize.height;
/*     */     
/*  97 */     Image image = this.rover.getImage();
/*  98 */     if (image != null) {
/*  99 */       g2.drawImage(image, roverX, roverY, this);
/*     */     } else {
/* 101 */       g2.setColor(Color.BLUE);
/* 102 */       g2.fillOval(roverX, roverY, this.cellSize.width, this.cellSize.height);
/* 103 */       g2.setColor(Color.BLACK);
/* 104 */       g2.drawOval(roverX, roverY, this.cellSize.width, this.cellSize.height);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/GuiPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */