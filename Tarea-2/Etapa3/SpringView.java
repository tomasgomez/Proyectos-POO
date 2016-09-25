import java.awt.geom.*;
import java.awt.*;
class SpringView {
   private static final double xPoints[]={0,0.10, 0.125, 0.175, 0.225, 0.275, 0.325, 
                                          0.375, 0.425, 0.475, 0.525,0.575,0.625,
                                          0.675,0.725,0.775,0.825,0.875, 0.90,1.0};
   private static final double yPoints[]={0,0,-0.05,0.05,-0.05,0.05,-0.05,0.05,-0.05,0.05,
                                          -0.05,0.05,-0.05,0.05,-0.05,0.05,-0.05,0.05,0,0};
   private static final Path2D.Double polyline = 
                      new Path2D.Double(Path2D.WIND_EVEN_ODD,xPoints.length);
   private Spring s;
   private Color color;
   
   static {  // static initialization block. It creates a spring of length = 1.
      polyline.moveTo (xPoints[0], yPoints[0]);
      for (int index = 1; index < xPoints.length;index++)
         polyline.lineTo(xPoints[index], yPoints[index]);
   }
   public SpringView(Spring _s) {
      s =_s;
   }
   public void draw(Graphics2D g){
      Vector2D a = s.getaBallPosition();
      Vector2D v = s.getVector();
      AffineTransform at = AffineTransform.getTranslateInstance(a.getX(), a.getY());
      at.rotate(v.getX(), v.getY());
      at.scale(v.module(),  s.getRestLength());
      Path2D.Double shape = (Path2D.Double) at.createTransformedShape(polyline);
      if ((v.module() < s.getRestLength())&&(color == Color.ORANGE))
         g.setColor(Color.BLACK);
      else
         g.setColor(color);
      g.draw(shape);
   }
   public void isSelected(){
   	color = Color.RED;
   }
   public void isReleased(){
   	color = Color.BLACK;
   }
}