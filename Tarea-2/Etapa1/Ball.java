import java.util.*;
import java.awt.*;

public class Ball extends PhysicsElement implements Simulateable  {
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private Vector2D pos_t;     // current position at time t
   private Vector2D pos_tPlusDelta;  // next position at t + delta
   private Vector2D speed_t;   // speed at time t
   private Vector2D speed_tPlusDelta;   // speed at t + delta
   private Vector2D a_t;    // acceleration at time t
   private Vector2D a_tMinusDelta; 
   private ArrayList<Spring> springs;
   private BallView view;
   
   private Ball(){   // nobody can create a ball without state
     super(id++);
     mass=0;
     radius=0;
   }
   public Ball(double mass, double r, Vector2D position, Vector2D speed){
      super(id++);
      this.mass = mass;
      radius = r;
      pos_t = position;
      speed_t = speed;
      a_t=a_tMinusDelta= new Vector2D();
      springs = new ArrayList<Spring>();
      view = new BallView(this);
   }
   public void attach(Spring s){
     springs.add(s);
   }
   public double getMass() {
      return mass;
   }
   public double getRadius() {
      return radius;
   }
   public Vector2D getPosition() {
      return pos_t;
   }
   public Vector2D getSpeed() {
      return speed_t;
   }
   private Vector2D getNetForce(){
     Vector2D force = new Vector2D();
     for (Spring s:springs)
       force=force.plus(s.getForce(this));
     return force;
   }
   public PhysicsElement collide(Ball b){
      if (this == b) return null;
      Vector2D vectorBetweenCenters=getPosition().minus(b.getPosition());
      boolean areTouching = vectorBetweenCenters.module() < radius+b.getRadius();
      Vector2D diffSpeed = getSpeed().minus(b.getSpeed());
      boolean areApproaching = diffSpeed.dot(vectorBetweenCenters) < 0;
      return (areTouching && areApproaching?this: null);
   }
   
   public void computeNextState(double delta_t, MyWorld w) {
     Vector2D speed=speed_t;
     PhysicsElement pe;
     if ((pe=w.getCollidingElement(this))!= null){ /* elastic collision */
        if (pe instanceof Ball) {
           Ball b =(Ball) pe;
         //speed=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());  // one dimession
           Vector2D dpos = getPosition().minus(b.getPosition()).unitary();
           Vector2D dv = getSpeed().minus(b.getSpeed());
           speed=speed_t.minus( dpos.times( dv.dot(dpos)*2*b.getMass()/(mass+b.getMass()) ) );
        } else { 
            if( pe instanceof Wall)
              if (((Wall)pe).getOrientation()==Wall.SIDEWAYS)
                 speed = new Vector2D(speed_t.getX(),-speed_t.getY());
              else
                 speed = new Vector2D(-speed_t.getX(),speed_t.getY());
        }
     } 
     
     a_t = getNetForce().times(1/mass);
     speed_tPlusDelta = speed.plus(a_t.times(3).minus(a_tMinusDelta).times(delta_t/2));
     pos_tPlusDelta = pos_t.plus(speed.times(delta_t)).plus(a_t.times(4).minus(a_tMinusDelta).times(delta_t*delta_t/6));
    
  //   speed_tPlusDelta = speed;
  //   pos_tPlusDelta = pos_t.plus(speed.times(delta_t));
   }
   public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
     a_tMinusDelta = a_t;
   }
   public void paintView(Graphics2D g){
      view.draw(g);
   }
   public String getDescription() {
      return "Ball_" + getId()+Vector2D.getDescription();
   }
   public String getState() {
      return getPosition()+" ";
   }
}
