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
/*    */  enum Parts
/*    */ {
/* 36 */   SCREW(
/* 37 */     "screw.jpg", "screw"), 
/* 38 */   GEAR("gear.jpg", "gear"), 
/* 39 */   CAKE("cake.jpg", "cake"), 
/* 40 */   CABBAGE("cabbage.jpg", "cabbage");
/*    */   
/*    */   public String img;
/*    */   public String type;
/*    */   
/*    */   private Parts(String img, String type) {
/* 46 */     this.img = img;
/* 47 */     this.type = type;
/*    */   }
/*    */ }


/* Location:              /Users/noahhendlish/Documents/workspace/Project1/togive/rover17.jar!/Parts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */