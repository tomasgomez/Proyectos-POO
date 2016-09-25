import java.util.*;

public class Ball extends PhysicsElement  {
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private Vector2D pos_t;     // current position at time t
   private Vector2D pos_tPlusDelta;  // next position at t + delta
   private Vector2D speed_t;   // speed at time t
   private Vector2D speed_tPlusDelta;   // speed at t + delta
   
   private Ball(){   // nobody can create a ball without state
     super(id++);
     mass=0;
     radius=0;
   }

   public Ball(double mass, double r, Vector2D position , Vector2D speed, MyWorld world){
      super(id++);
      this.mass = mass;
      radius = r;
      pos_t = position;
      speed_t = speed;
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

   public boolean collide(Ball b){
   	  Vector2D diffPosition = pos_t.minus(b.getPosition());
   	  boolean areTouching = (diffPosition.size())<(b.getRadius()+radius);
   	  if(!areTouching) return false;
   	  Vector2D diffSpeed = speed_t.minus(b.getSpeed());
   	  boolean areApproaching=diffPosition.dot(diffSpeed)<0;
   	  return areApproaching;
   }

   public void computeNextState(double delta_t, MyWorld w) {
     Vector2D speed=speed_t;
     PhysicsElement pe;
     if ((pe=w.getCollidingElement(this))!= null){ /* elastic collision */
        if (pe instanceof Ball) {
           Ball b =(Ball) pe;
           Vector2D diffPosition = pos_t.minus(b.getPosition());
           Vector2D diffSpeed =speed_t.minus(b.getSpeed());
           speed=speed_t.minus(diffPosition.times((2*((b.getMass())/(mass+b.getMass())))*(diffPosition.dot(diffSpeed)/(diffPosition.size()*diffPosition.size()))));
        } else {
           // case Container, en step2
        }
     } 
    
     speed_tPlusDelta = speed;
     pos_tPlusDelta = pos_t.plus(speed.times(delta_t));
   }

   public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
   }

   public String getDescription() {
      return "Ball_" + getId()+":x\tBall_" + getId()+":y";
   }

   public String getState() {
      return getPosition().getX()+"\t"+getPosition().getY();
   }

}
