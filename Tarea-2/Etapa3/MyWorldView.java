import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*; 
import java.awt.*;
import java.util.*;

public class MyWorldView extends JPanel { 
   
   public MyWorldView(MyWorld w){
      world = w;
      mListener = new MouseListener(w);
      addMouseMotionListener(mListener);
      addMouseListener(mListener);      
   }

   public void paintComponent(Graphics g){
      Graphics2D g2 = (Graphics2D)g;
      super.paintComponent(g); // it paints the background
      g2.drawString("ELO329 1er.Sem. 2016,   1 [m] = "+AXES_SCALE+" [pixels]", WIDTH/4, 30);
      g2.transform(SPACE_TRANSFORM);
      g2.setStroke(new BasicStroke(0.02f));
      g2.draw(X_AXIS);
      g2.draw(Y_AXIS);
      ArrayList<PhysicsElement> elements = world.getPhysicsElements();
      for (PhysicsElement e:elements)
         e.paintView(g2);
   }
   public void removeMouseListener(){
      removeMouseMotionListener(mListener);
      removeMouseListener(mListener);            
   }
   public void addMouseListener(){
      addMouseMotionListener(mListener);
      addMouseListener(mListener);      
   }

   private MyWorld world;
   private MouseListener mListener;

// BEGIN declarations to use metric coordinate system (not pixels)
   public static int WIDTH = 900;  // in pixels
   public static int HEIGHT = 800; // in pixels
   public static int X_ORIGEN = (int)(WIDTH*0.05); // MyWorld space origen (x,y) will be on
   public static int Y_ORIGEN = (int)(HEIGHT*1.1); // (X_ORIGEN,Y_ORIGEN) of the panel space.
   public static AffineTransform SPACE_TRANSFORM;  // transforms (x,y) in (X,Y) of panel.
   public static AffineTransform SPACE_INVERSE_TRANSFORM; // transforms (X,Y) in (x,y) of my world.
   public static Line2D.Double X_AXIS;  // lines to draw my world axes (singular axis, plural axes).
   public static Line2D.Double Y_AXIS;
   private static double AXES_SCALE = 200.0;  // 1 [m] equals AXES_SCALE [pixels]
   
   static {
       SPACE_TRANSFORM = AffineTransform.getTranslateInstance(X_ORIGEN, Y_ORIGEN);
       SPACE_TRANSFORM.scale(AXES_SCALE,-AXES_SCALE);  // it also inverts direction of y-coordinate 
       try {
          SPACE_INVERSE_TRANSFORM = SPACE_TRANSFORM.createInverse();
       }catch (NoninvertibleTransformException e){ 
          System.exit(0);
       }
       X_AXIS = new Line2D.Double(-0.1, 1.5, 3.0, 1.5);  // each axis length is 3 [m] from origen.
       Y_AXIS = new Line2D.Double(0.0, 1.4, 0.0, 3.0);       
   }
// END declarations to use metric coordinate system (not pixels)
}