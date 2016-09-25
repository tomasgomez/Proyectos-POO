import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.awt.geom.Point2D;


public class MouseListener extends MouseAdapter {
   private MyWorld world;
   private PhysicsElement selectedElement;
   private Point2D.Double pos;
   public MouseListener (MyWorld w){
      world = w;
      pos = new Point2D.Double(0,0);
      
   } 
 public void mousePressed(MouseEvent e) {
   	if (!world.isRunning()){				// Para los tres metodos se agrega la condicion de que el mundo debe estar parado.
      MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),pos); // convert pixel to MyWorld coordenates
      selectedElement = world.findElement(pos.getX(), pos.getY());
	      if (selectedElement != null){
	         selectedElement.setSelected();
	         world.getView().repaint();
	      }
	  }
   }
  public void mouseDragged(MouseEvent e) {
   	if (!world.isRunning()){
      if (selectedElement == null) return;
      Point2D.Double p = new Point2D.Double(0,0);
      MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);
      if (selectedElement instanceof Spring){
         Spring spring = (Spring) selectedElement;
         if(Math.abs(spring.getaBallPosition().getX() - pos.getX()) < 0.05 && Math.abs(spring.getaBallPosition().getY() - pos.getY()) < 0.05)
            selectedElement.dragDelta(p.getX()-pos.getX(), p.getY()-pos.getY());
         else selectedElement.dragbDelta(p.getX()-pos.getX(), p.getY()-pos.getY());
         }
      else selectedElement.dragDelta(p.getX()-pos.getX(), p.getY()-pos.getY());
      pos = p;
      world.getView().repaint();
   		}
   	}

   public void mouseReleased(MouseEvent e) {
   	if (!world.isRunning()){
      if (selectedElement != null) {
      	 if (selectedElement instanceof Spring){	// si el elemento seleccionado es un Spring entonces se busca si hay una bola en donde se esta situando 
      	 	Spring spring = (Spring) selectedElement; // el mouse, en caso de que sea una bola, se hace la union de la bola con el elastico.
      	 	PhysicsElement ballToAttach;
      	 	MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),pos);
      	 	ballToAttach = world.findElement(pos.getX(), pos.getY());
      	 	if (ballToAttach != null && ballToAttach instanceof Ball){
                Ball ball =(Ball) ballToAttach;
                if(spring.contains(ball.getPosition().getX(),ball.getPosition().getY())==true){
                  if(spring.isaBallTouching()==true){
                       spring.unAttachaBall();                                             
      	 		        spring.attachBall(ball);
                       }
                  if(spring.isbBallTouching()==true){                                            
      	 		        spring.attachBall(ball);
                       } 
      	 	   }
             }
      	 }
         selectedElement.setReleased();
         world.getView().repaint();
      }
      return;
   		}
   	}
}