/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SwingUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Gui
/*     */   implements ActionListener, KeyListener
/*     */ {
/*     */   private IRover rover;
/*     */   private JFrame frame;
/*     */   private JTextArea taskField;
/*     */   private JTextArea inventoryField;
/*     */   private JPanel mapPanel;
/*     */   private GuiPanel roomPanel;
/*     */   private JButton returnButton;
/*     */   private JButton leftButton;
/*     */   private JButton rightButton;
/*     */   private JButton upButton;
/*     */   private JButton downButton;
/*     */   private JButton pickUpButton;
/*     */   private JButton performButton;
/*     */   private JButton quitButton;
/*     */   private JButton helpButton;
/*     */   
/*     */   private class FixedHeightTextField
/*     */     extends JTextField
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public FixedHeightTextField(String s, int chars)
/*     */     {
/*  76 */       super(chars);
/*     */     }
/*     */     
/*     */     public Dimension getMinimumSize() {
/*  80 */       return getPreferredSize();
/*     */     }
/*     */     
/*     */     public Dimension getMaximumSize() {
/*  84 */       return getPreferredSize();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Gui()
/*     */   {
/* 155 */     this.rover = new Rover();
/*     */     
/*     */ 
/* 158 */     JPanel taskPanel = new JPanel(new BorderLayout());
/* 159 */     taskPanel.setPreferredSize(new Dimension(100, 100));
/* 160 */     taskPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
/* 161 */     taskPanel.setAlignmentX(0.5F);
/* 162 */     taskPanel.setAlignmentY(0.5F);
/* 163 */     this.taskField = new JTextArea();
/* 164 */     this.taskField.setText("Task: ");
/* 165 */     this.taskField.setEditable(false);
/* 166 */     taskPanel.add(this.taskField, "Center");
/* 167 */     taskPanel.add(new JLabel("Task"), "North");
/*     */     
/*     */ 
/* 170 */     this.roomPanel = new GuiPanel(this.rover);
/*     */     
/*     */ 
/* 173 */     this.mapPanel = new JPanel(new BorderLayout());
/* 174 */     this.mapPanel.setPreferredSize(new Dimension(GuiPanel.GRIDSIZE + 20, 
/* 175 */       GuiPanel.GRIDSIZE + 35));
/* 176 */     this.mapPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/* 177 */     this.mapPanel.add(this.roomPanel, "Center");
/* 178 */     this.mapPanel.add(new JLabel("Map of room"), "North");
/* 179 */     this.mapPanel.setAlignmentX(0.5F);
/* 180 */     this.mapPanel.setAlignmentY(0.5F);
/*     */     
/*     */ 
/* 183 */     JPanel leftPanel = new JPanel();
/* 184 */     leftPanel.setLayout(new BoxLayout(leftPanel, 1));
/* 185 */     leftPanel.add(taskPanel);
/* 186 */     leftPanel.add(this.mapPanel);
/*     */     
/*     */ 
/* 189 */     this.inventoryField = new JTextArea();
/* 190 */     this.inventoryField.setText("Inventory: ");
/* 191 */     this.inventoryField.setEditable(false);
/*     */     
/* 193 */     JPanel invPanel = new JPanel(new BorderLayout());
/* 194 */     invPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/* 195 */     invPanel.setAlignmentX(0.5F);
/* 196 */     invPanel.setAlignmentY(0.5F);
/* 197 */     invPanel.add(this.inventoryField, "Center");
/* 198 */     invPanel.add(new JLabel("Inventory"), "North");
/*     */     
/*     */ 
/*     */ 
/* 202 */     this.rightButton = new JButton(">");
/* 203 */     this.leftButton = new JButton("<");
/* 204 */     this.upButton = new JButton("^");
/* 205 */     this.downButton = new JButton("V");
/* 206 */     this.pickUpButton = new JButton("Pick Up");
/* 207 */     this.performButton = new JButton("Perform Task");
/* 208 */     this.quitButton = new JButton("Quit");
/* 209 */     this.helpButton = new JButton("Help (none)");
/* 210 */     this.returnButton = new JButton("Show the way back");
/*     */     
/*     */ 
/* 213 */     this.rightButton.addActionListener(this);
/* 214 */     this.leftButton.addActionListener(this);
/* 215 */     this.downButton.addActionListener(this);
/* 216 */     this.upButton.addActionListener(this);
/* 217 */     this.pickUpButton.addActionListener(this);
/* 218 */     this.performButton.addActionListener(this);
/* 219 */     this.quitButton.addActionListener(this);
/* 220 */     this.helpButton.addActionListener(this);
/* 221 */     this.returnButton.addActionListener(this);
/*     */     
/*     */ 
/* 224 */     JPanel upPanel = new JPanel();
/* 225 */     upPanel.setLayout(new FlowLayout(1));
/* 226 */     upPanel.add(this.upButton);
/* 227 */     this.upButton.setAlignmentX(0.5F);
/*     */     
/*     */ 
/* 230 */     JPanel leftRightButtonsPanel = new JPanel();
/* 231 */     leftRightButtonsPanel.setLayout(new FlowLayout(1));
/* 232 */     leftRightButtonsPanel.add(this.leftButton);
/* 233 */     leftRightButtonsPanel.add(this.rightButton);
/*     */     
/*     */ 
/* 236 */     JPanel returnPanel = new JPanel();
/* 237 */     returnPanel.setLayout(new FlowLayout(1));
/* 238 */     returnPanel.add(this.returnButton);
/* 239 */     this.returnButton.setAlignmentX(0.5F);
/*     */     
/*     */ 
/* 242 */     JPanel otherButtonsPanel_1 = new JPanel();
/* 243 */     otherButtonsPanel_1.setLayout(new FlowLayout(1));
/* 244 */     otherButtonsPanel_1.add(this.pickUpButton);
/* 245 */     otherButtonsPanel_1.add(this.performButton);
/*     */     
/*     */ 
/* 248 */     JPanel otherButtonsPanel_2 = new JPanel();
/* 249 */     otherButtonsPanel_2.setLayout(new FlowLayout(1));
/* 250 */     otherButtonsPanel_2.add(this.quitButton);
/* 251 */     otherButtonsPanel_2.add(this.helpButton);
/*     */     
/*     */ 
/* 254 */     JPanel buttonPanel = new JPanel();
/* 255 */     buttonPanel.setLayout(new BoxLayout(buttonPanel, 1));
/* 256 */     buttonPanel.setAlignmentX(0.5F);
/* 257 */     buttonPanel.add(upPanel);
/* 258 */     buttonPanel.add(leftRightButtonsPanel);
/* 259 */     buttonPanel.add(this.downButton);
/* 260 */     this.downButton.setAlignmentX(0.5F);
/* 261 */     buttonPanel.add(returnPanel);
/* 262 */     buttonPanel.add(otherButtonsPanel_1);
/* 263 */     buttonPanel.add(otherButtonsPanel_2);
/*     */     
/*     */ 
/* 266 */     JPanel rightPanel = new JPanel();
/* 267 */     rightPanel.setLayout(new BoxLayout(rightPanel, 1));
/* 268 */     rightPanel.add(invPanel);
/* 269 */     rightPanel.add(buttonPanel);
/*     */     
/*     */ 
/* 272 */     JPanel mainPanel = new JPanel();
/* 273 */     mainPanel.setLayout(new BoxLayout(mainPanel, 0));
/* 274 */     mainPanel.add(leftPanel);
/* 275 */     mainPanel.add(rightPanel);
/* 276 */     mainPanel.setOpaque(true);
/*     */     
/*     */ 
/* 279 */     this.frame = new JFrame("Lost Rovers");
/* 280 */     this.frame.setDefaultCloseOperation(3);
/* 281 */     this.frame.setResizable(false);
/* 282 */     this.frame.setContentPane(mainPanel);
/* 283 */     this.mapPanel.addKeyListener(this);
/* 284 */     this.frame.pack();
/*     */     
/* 286 */     updateGui();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Component getSeparator()
/*     */   {
/* 295 */     return Box.createRigidArea(new Dimension(10, 10));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void show()
/*     */   {
/* 302 */     this.frame.setVisible(true);
/*     */     try {
/* 304 */       Thread.sleep(500L);
/*     */     } catch (InterruptedException e) {
/* 306 */       System.out.println("Sleeping Interrupted.");
/*     */     }
/* 308 */     this.mapPanel.requestFocusInWindow();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void createAndShowGUI()
/*     */   {
/* 316 */     Gui mazeViz = new Gui();
/* 317 */     mazeViz.show();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void run()
/*     */   {
/* 338 */     SwingUtilities.invokeLater(new Runnable()
/*     */     {
/*     */       public void run() {}
/*     */     });
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
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 354 */     String command = e.getActionCommand();
/* 355 */     if (command == null) {
/* 356 */       return;
/*     */     }
/* 358 */     if ("Quit".equals(command)) {
/* 359 */       System.exit(0);
/* 360 */     } else if ("Pick Up".equals(command)) {
/* 361 */       this.rover.pickUp();
/* 362 */     } else if ("Perform Task".equals(command)) {
/* 363 */       this.rover.performTask();
/* 364 */     } else if ("Show the way back".equals(command)) {
/* 365 */       this.rover.showTheWayBack();
/* 366 */     } else if (!"Help".equals(command))
/*     */     {
/* 368 */       if (">".equals(command)) {
/* 369 */         this.rover.goRight();
/* 370 */       } else if ("<".equals(command)) {
/* 371 */         this.rover.goLeft();
/* 372 */       } else if ("V".equals(command)) {
/* 373 */         this.rover.goDown();
/* 374 */       } else if ("^".equals(command)) {
/* 375 */         this.rover.goUp();
/*     */       }
/*     */     }
/* 378 */     updateGui();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 386 */     int action = e.getKeyCode();
/*     */     
/* 388 */     if (action == 38) {
/* 389 */       this.rover.goUp();
/* 390 */     } else if (action == 40) {
/* 391 */       this.rover.goDown();
/* 392 */     } else if (action == 37) {
/* 393 */       this.rover.goLeft();
/* 394 */     } else if (action == 39) {
/* 395 */       this.rover.goRight();
/* 396 */     } else if (action == 32) {
/* 397 */       this.rover.pickUp();
/*     */     }
/*     */     
/* 400 */     updateGui();
/*     */   }
/*     */   
/*     */ 
/*     */   public void keyReleased(KeyEvent e) {}
/*     */   
/*     */ 
/*     */   public void keyTyped(KeyEvent e) {}
/*     */   
/*     */   private void updateGui()
/*     */   {
/* 411 */     this.inventoryField.setText(this.rover.getInventory());
/* 412 */     this.taskField.setText(this.rover.getTask());
/* 413 */     this.roomPanel.repaint();
/* 414 */     this.mapPanel.requestFocusInWindow();
/*     */   }
/*     */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Gui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */