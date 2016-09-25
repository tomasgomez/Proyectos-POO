import java.awt.*;
import java.awt.geom.*;
import java.io.*;

/**
* @author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
*Funcion que permite pintar a la bola.
*/

public class BallView implements Serializable {
   private Ellipse2D.Double shape = null;
   private Ball b;
   private Color color;

/**
* Constructores para Ball
*@param BallView Constructor , setea los atributos en los valores de bola. 
*/  
   public BallView (Ball ball){
      b=ball;
      Vector2D c=b.getPosition();
      double radius = b.getRadius();
      shape = new Ellipse2D.Double(c.getX()-radius, c.getY()-radius, 2*radius, 2*radius);
      color = Color.GREEN;
   }
/**
*@param draw Metodo para pintar la bola.
*/
   public void draw (Graphics2D g){
      Vector2D c=b.getPosition();
      double radius = b.getRadius();   
      shape.setFrame(c.getX()-radius, c.getY()-radius, 2*radius, 2*radius);
      g.setColor(color);
      g.fill(shape);
   }
/**
*@param isSelected Metodo que pinta la bola de color Rojo cuando la bola esta seleccionada.
*/
   public void isSelected(){
   	color = Color.RED;
   }
/**
*@param isReleased Metodo que pinta la bola de color verde cuando la bola no esta seleccionada.
*/
   public void isReleased(){
   	color = Color.GREEN;
   }
}