import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;


public class EditMenuListener implements ActionListener {
   private MyWorld  world;
     
        
   public EditMenuListener (MyWorld  w){
      world = w;
   }
   public void actionPerformed(ActionEvent e) {
      JMenuItem menuItem = (JMenuItem)(e.getSource());
      String text = menuItem.getText();
      if (text.equals("Ball")) 
         world.addElement(new Ball(1.0, 0.1, new Vector2D(1.5,2.75), new Vector2D(0.5, 0.25)));
      if (text.equals("Receptacle"))
         world.addElement(new Receptacle(new Vector2D(0.0,1.5),new Vector2D(3,4.0)));
      if (text.equals("Spring")){
         Ball ball = new Ball(1.0, 0.1, new Vector2D(1.5,2.75), new Vector2D(0.5, 0.25));	 //se definen bolas para que se pueda ver el elastico.
         Ball ball2 = new Ball(1.0, 0.1, new Vector2D(2.0,2.75), new Vector2D(0.5, 0.25));
         Spring spring = new Spring(1,1);
         spring.attachBall(ball);
         spring.attachBall(ball2);
         world.addElement(spring);
         }
   }
}