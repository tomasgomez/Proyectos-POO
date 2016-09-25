import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class PhysicsLab {
   public static void main(String[] args) {
      PhysicsLab_GUI lab_gui = new PhysicsLab_GUI();
      lab_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      lab_gui.setVisible(true);
   }
}

class PhysicsLab_GUI extends JFrame {
   public PhysicsLab_GUI() {
      setTitle("My Small and Nice Physics Laboratory");
      setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT+50);  // height+50 to account for menu height
      MyWorld world = new MyWorld();
      add(world.getView());
      createConfiguration(world);
      world.start();
   }
   private void createConfiguration(MyWorld world) {  // here, what we configure in PhysicsLab.java 
      double mass = 1.0;      // [kg]                 // in 1st. assignment.
      Vector2D position = new Vector2D(1.4,1.6);  // [m] 
      Vector2D speed = new Vector2D(0.5,0.5);     // [m/s]
      double radio = 0.15;     // [m]
      Ball b = new Ball(mass, radio, position, speed);
      world.addElement(b);      
      Receptacle c = new Receptacle(new Vector2D(0.0,1.5), new Vector2D(4.0, 4.1));
      world.addElement(c);
      Receptacle c_2 = new Receptacle(new Vector2D(2.0,2.0), new Vector2D(3.0, 3.0));
      world.addElement(c_2);
   }
}