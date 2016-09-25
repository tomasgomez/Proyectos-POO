import java.awt.*;

public class Spring extends PhysicsElement  {
   private static int id=0;  // Spring identification
   protected final double restLength;
   private final double stiffness;
   protected Ball aBall, bBall;
   private SpringView view;

   private Spring(){   // nobody can create a block without state
      super(id++);
      stiffness = restLength = 0;
   }
   public Spring(double restLength, double stiffness){
      super(id++);
      this.restLength = restLength;
      this.stiffness = stiffness;
      aBall = bBall = null;
      view = new SpringView(this);
   }
   public void attachBall (Ball b) {  // note: we attach a spring to a ball, 
      if(aBall==null)                 //       not the other way around.
         aBall = b;                     
      else 
         bBall = b;                            
      b.attach(this);
   }
   public PhysicsElement collide(Ball b){
     return null; // Springs do not collide in this model, they are transparent
   }
   public double getRestLength() {
      return restLength;
   }
   public Vector2D getaBallPosition(){
     if (aBall==null) return null;
     return aBall.getPosition();
   }
   public Vector2D getVector() { // vector from aball to bBall
      return bBall.getPosition().minus(aBall.getPosition());
   }
   public Vector2D getForce(Ball b) {
      Vector2D force = new Vector2D();
      if ((aBall == null) || (bBall == null))
         return force;
      if ((b != aBall) && (b != bBall))
         return force;
      Vector2D springVector = getVector();
      double stretch = springVector.module() - restLength;
      force = springVector.unitary().times(stretch*stiffness);
      if (b == aBall) return force;
      return force.times(-1.0);
   }
   public String getDescription() {
      return "Spring_"+ getId()+": "+ Vector2D.getDescription()+" ,"+ Vector2D.getDescription();
   }
   public String getState() {
      String s = aBall.getPosition() + "," + bBall.getPosition();
      return s;
   } 
   public void paintView(Graphics2D g){
      view.draw(g);
   }
}
