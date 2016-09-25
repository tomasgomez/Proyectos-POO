import java.util.*;
import java.awt.*;

public class Receptacle extends PhysicsElement  {
   private static int id=0;  // Receptacle identification number
   private Vector2D dlCorner;
   private Vector2D urCorner;
   private ReceptacleView view;
   
   private Receptacle(){   // nobody can create a Receptacle without state
     super(id++);
   }
   public Receptacle(Vector2D dl, Vector2D ur){
      super(id++);
      dlCorner = dl;
      urCorner = ur;
      view = new ReceptacleView(this);
   }
   public Vector2D getDLcorner() {
      return dlCorner;
   }
   public Vector2D getURcorner() {
      return urCorner;
   }
   public PhysicsElement collide(Ball b){
      if (b == null) return null;
      // test collision with left wall
      Wall wall = new Wall(dlCorner, Wall.STRAIGHT, urCorner.getY()-dlCorner.getY());
      if (wall.collide(b)!=null)
         return wall;
      // test collision with right wall
      wall = new Wall(new Vector2D(urCorner.getX(),dlCorner.getY()), Wall.STRAIGHT, urCorner.getY()-dlCorner.getY());
      if (wall.collide(b)!=null)
         return wall;
         // test collision with up wall
      wall = new Wall(new Vector2D(dlCorner.getX(),urCorner.getY()), Wall.SIDEWAYS, urCorner.getX()-dlCorner.getX());
      if (wall.collide(b)!=null)
         return wall;
         // test collision with down wall
      wall =  new Wall(dlCorner, Wall.SIDEWAYS, urCorner.getX()-dlCorner.getX());
      return wall.collide(b);
   }
   public void paintView(Graphics2D g){
      view.draw(g);
   }
   public String getDescription() {
      return "Receptacle_" + getId()+": DL.x, DL.y, UR.x, UR.y ";
   }
   public String getState() {
      return getDLcorner()+", "+ getURcorner();
   }
   public boolean contains (double x, double y){
   	return false;
   }
   public void dragDelta(double dx, double dy) {
   	dlCorner = new Vector2D(dlCorner.getX() + dx,dlCorner.getY() + dy);
   	urCorner = new Vector2D(urCorner.getX() + dx,urCorner.getY() + dy);
   }
   public void setSelected() {
   		view.isSelected();
   }
   public void setReleased(){
   		view.isReleased();
   }
   public void dragbDelta(double dx, double dy){
      return;
   }
}
