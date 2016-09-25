public class Vector2D {
   private double x, y;  // we will use cartesian coordinates
   public Vector2D () {
      x = y = 0;
   }
   public Vector2D (double x, double y) {
      this.x = x;
      this.y = y;
   }
   public double getX(){
      return x;
   }
   public double getY(){
      return y;
   }
   public void moveTo(double x, double y){ // to set a new position
      this.x=x;
      this.y=y;
   }
   public Vector2D plus(Vector2D v) {
      if (v==null) return new Vector2D(x,y);
      else return new Vector2D(x+v.x, y+v.y);
   }
   public Vector2D times(double scalar) {
      return new Vector2D(x*scalar, y*scalar);
   }
   public Vector2D minus(Vector2D v) {
      if (v==null) return new Vector2D(x,y);
      else return new Vector2D(x-v.x, y-v.y);
   }
   public double module() {
      return Math.sqrt(x*x+y*y);
   }
   public double angle() {  // just in case we need it
      if (x==0){      // for x=0, we can't use arctang.
          if(y>0) return Math.PI/2;
          else return -Math.PI/2;
      }
      if (x > 0) return Math.atan(y/x);
      return Math.PI + Math.atan(y/x);
   }
   public Vector2D unitary() {
      return times(1/module());
   }
   public double dot(Vector2D v) {
      return x*v.x + y*v.y;
   }
   public static String getDescription() {
      return "(x,y)";
   }
   public String toString(){
      return  x + ", " + y;
   }
}
   
