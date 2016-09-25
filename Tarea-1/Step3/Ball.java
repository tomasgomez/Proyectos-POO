import java.util.*;

public class Ball extends PhysicsElement  {
   private boolean joined;
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private Vector2D pos_t;     // current position at time t
   private Vector2D pos_tPlusDelta;  // next position at t + delta
   private Vector2D speed_t;   // speed at time t
   private Vector2D speed_tPlusDelta;   // speed at t + delta
   private Vector2D accel_t;
   private Vector2D accel_tPlusDelta;
   private ArrayList<Integer> joinedSprings;
   
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
      joined=false;
      accel_t=new Vector2D(0,0);
      accel_tPlusDelta=new Vector2D(0,0);
      joinedSprings = new ArrayList<Integer>();
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

   public void setSpring(int springId){
   	  joinedSprings.add(springId);
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

     if(joinedSprings.size()>0){
     	 accel_tPlusDelta=new Vector2D(0,0);
	     for(PhysicsElement e : w.getElements()){
	     	for(int s : joinedSprings){
	     		if(s==e.getId()){
	     			if (e instanceof Spring){
		     			Spring spring = (Spring) e;
		     			accel_tPlusDelta=accel_tPlusDelta.plus(spring.getForce(this.getId()).times(1/mass));
	     			}
	     		}
	     	}
	     }
	     
     }

     if ((pe=w.getCollidingElement(this))!= null){ /* elastic collision */
        if (pe instanceof Ball) {
           Ball b =(Ball) pe;
           Vector2D diffPosition = pos_t.minus(b.getPosition());
           Vector2D diffSpeed =speed.minus(b.getSpeed());
           speed=speed.minus(diffPosition.times((2*((b.getMass())/(mass+b.getMass())))*(diffPosition.dot(diffSpeed)/(diffPosition.size()*diffPosition.size()))));
        } 
        else{
           Container c = (Container) pe;
           double diffR = pos_t.getX()-c.WallR();
           double diffL = pos_t.getX()-c.WallL();
           double diffU = pos_t.getY()-c.WallU();
           double diffD = pos_t.getY()-c.WallD();      
           boolean [] WallTouch = {(Math.abs(diffR)<=radius),(Math.abs(diffL)<=radius),(Math.abs(diffU)<=radius),(Math.abs(diffD)<=radius)};
           boolean [] Aproaching = {(diffR*speed.getX())<0,(diffL*speed.getX())<0,(diffU*speed.getY())<0,(diffD*speed.getY())<0};
           ENDCOLLITION:
           for(int i=0;i<=3;i++){
            if(WallTouch[i]&&Aproaching[i]){
              switch(i){
                case 0: case 1: speed=new Vector2D(speed.getX()*-1,speed.getY());break ENDCOLLITION;
                case 2: case 3: speed=new Vector2D(speed.getX(),speed.getY()*-1);break ENDCOLLITION;
                default: speed=speed;
              }
            } 
           }
        }
      }
     
     
	 speed_tPlusDelta=speed.plus(accel_tPlusDelta.times(3.0).minus(accel_t).times(delta_t/2));
	 pos_tPlusDelta=pos_t.plus(speed.times(delta_t)).plus(accel_tPlusDelta.times(4.0).minus(accel_t).times((delta_t*delta_t)/6));
   }

   public void updateState(){
   	 accel_t=accel_tPlusDelta;
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
   }

   public String getDescription() {
      return "Ball_" + getId()+":x\ty";
   }

   public String getState() {
      return pos_t.getX()+"\t"+pos_t.getY();
   }
}
