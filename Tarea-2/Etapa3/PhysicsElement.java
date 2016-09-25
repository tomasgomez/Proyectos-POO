import java.awt.*;
import java.io.*;

public abstract class PhysicsElement implements Serializable{
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
   public abstract boolean contains (double x, double y);
   public abstract void dragDelta(double dx, double dy);
   public abstract void dragbDelta(double dx, double dy);
   public abstract void setSelected();
   public abstract void setReleased();
 }
