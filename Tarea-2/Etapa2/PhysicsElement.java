import java.awt.*;
public abstract class PhysicsElement {
   private final int myId; /* to identify each element within its category */
   
   protected PhysicsElement( int id){
      myId = id;
   }
   protected int getId() {
      return myId;
   }
   public abstract String getDescription();
   public abstract String getState();
   public abstract PhysicsElement collide(Ball b);
   public abstract void paintView(Graphics2D g);
 }
