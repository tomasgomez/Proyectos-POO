import java.awt.*;
public class Wall extends PhysicsElement {
        public static final int SIDEWAYS=0;
        public static final int STRAIGHT=1;
        private static int id=0;
        protected Vector2D anchor;
        protected double length;
        protected final int orientation;

        private Wall() {   // nobody can create a Wall without state
                super(id++);
                orientation=SIDEWAYS;
        }
        public Wall(Vector2D initPoint, int orient, double leng){
                super(id++);
                anchor = initPoint;
                orientation = orient;
                length=leng;
        }
        public Vector2D getPosition() {
                return anchor;
        }
        public PhysicsElement collide(Ball b){
          double b_x = b.getPosition().getX();
          double b_y = b.getPosition().getY();
          double r = b.getRadius();
          if (orientation == STRAIGHT) 
            if ((Math.abs(b_x-anchor.getX())< r) && // is touching wall?
                          (anchor.getY()< b_y) &&             // is withing range?
                           (b_y<anchor.getY()+length) &&      // is withing range?
                          ((b_x-anchor.getX())*b.getSpeed().getX() < 0))
                return this;
            else
                return null;
          else
            if ((Math.abs(b_y-anchor.getY())< r) &&
                          (anchor.getX()< b_x) &&
                          (b_x<anchor.getX()+length)  &&
                          ((b_y-anchor.getY())*b.getSpeed().getY() < 0))
                return this;
            else
                return null;
        }
        public double getX() {
                return anchor.getX();
        }
        public double getY() {
                return anchor.getY();
        }
        public double getLength(){
                return length;
        }
        public int getOrientation() {
                return orientation;
        }
        public void paintView (Graphics2D g) {
        }
        public String getDescription(){
          return "";
        }
        public String getState(){
          return "";
        }
        public boolean contains (double x, double y){
    return false;
   }
   public void dragDelta(double dx, double dy) {
   }
   public void setSelected() {
   }
   public void setReleased(){
   }


   public void dragbDelta(double dx, double dy){
      return;
   }
}
