import java.awt.*;
import java.awt.geom.*;

public class BallView {
   private Ellipse2D.Double shape = null;
   private Ball b;
   private Color color;
   
   public BallView (Ball ball){
      b=ball;
      Vector2D c=b.getPosition();
      double radius = b.getRadius();
      shape = new Ellipse2D.Double(c.getX()-radius, c.getY()-radius, 2*radius, 2*radius);
      color = Color.GREEN;
   }
   public void draw (Graphics2D g){
      Vector2D c=b.getPosition();
      double radius = b.getRadius();   
      shape.setFrame(c.getX()-radius, c.getY()-radius, 2*radius, 2*radius);
      g.setColor(color);
      g.fill(shape);
   }
}