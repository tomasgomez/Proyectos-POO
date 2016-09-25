import java.awt.*;

public class Spring extends PhysicsElement  {
   private static int id=0;  // Spring identification
   protected final double restLength;
   private final double stiffness;
   protected Ball aBall, bBall;
   private SpringView view;
   private boolean aContains,bContains;

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
   public Vector2D getbBallPosition(){
     if (bBall==null) return null;
     return bBall.getPosition();
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
  public boolean contains (double x, double y){
	   Vector2D vector = new Vector2D(x,y);
		if(aBall.getPosition().minus(vector).module()< 0.1){
			aContains = true;
         bContains = false;
			return true;
		}
		if(bBall.getPosition().minus(vector).module() < 0.1){
			bContains = true;
         aContains = false;
			return true;
		}
		return false;
	}
   public void dragDelta(double dx, double dy) { // se modifican las posiciones de las bolas a las que el elastico esta unido.
   	aBall.dragDelta(dx,dy);
   }

   public void dragbDelta(double dx, double dy) { // se modifican las posiciones de las bolas a las que el elastico esta unido.
   	bBall.dragDelta(dx,dy);
   }
   
   public void unAttachaBall(){
      aBall.unAttach(this);
      aContains = false;
      aBall = null;
   }
   
   public void setSelected() {
      if (aBall != null) {		   //Primero se libera la bola del elastico, luego se crea una bola que define la posicion del elastico
      	aBall.unAttach(this);	//haciendo como que este quedara libre, sin ese extremo adjunto a alguna bola
      	aBall = new Ball(1.0, 0.1, aBall.getPosition(), aBall.getSpeed());
      }
      if (bBall != null){ 
      	bBall.unAttach(this);
      	bBall = new Ball(1.0, 0.1, bBall.getPosition(), bBall.getSpeed());
      }
      view.isSelected();
   }
   public void setReleased(){
      view.isReleased();
   }
   
   public boolean isaBallTouching(){      // retorna si hay o no bola agarrada en el extremo a
      return aContains;
    }
    
   public boolean isbBallTouching(){   // retorna si hay o no bola agarrada en el extremo b
      return bContains;
   }
   public void setaContains(boolean b){
      aContains = b;
   }
   public void setbContains(boolean b){
      bContains = b;
   }
   
}
