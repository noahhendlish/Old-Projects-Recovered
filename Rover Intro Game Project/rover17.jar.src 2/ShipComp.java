/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */  enum ShipComp
/*    */ {
/* 57 */   CABIN(
/* 58 */     "cabin.jpg", "cabin2.jpg", "cabin"), 
/* 59 */   ENGINE("engine.jpg", "engine2.jpg", "engine"), 
/* 60 */   EXHAUST("exhaust.jpg", "exhaust2.jpg", "exhaust"), 
/* 61 */   RAMP("ramp.jpg", "ramp2.jpg", "ramp"), 
/* 62 */   WHEEL("wheel.jpg", "wheel2.jpg", "wheel");
/*    */   
/*    */   public String broken;
/*    */   public String working;
/*    */   public String type;
/*    */   
/*    */   private ShipComp(String brkn, String fixed, String type) {
/* 69 */     this.broken = brkn;
/* 70 */     this.working = fixed;
/* 71 */     this.type = type;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/ShipComp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */