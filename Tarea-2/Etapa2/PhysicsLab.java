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
      add(world.getView()); // it is equivalent to getContentPane().add(...);
      EditMenuListener eListener = new EditMenuListener(world);
      WorldControlMenuListener wcListener = new WorldControlMenuListener(world);
      setJMenuBar(createLabMenuBar(eListener, wcListener));
   }
   private JMenuBar createLabMenuBar(EditMenuListener el, WorldControlMenuListener wl) {
      JMenuBar mb = new JMenuBar();
      
      JMenu menu = new JMenu ("Edit");
      mb.add(menu);
      JMenu subMenu = new JMenu("Insert");  
      menu.add(subMenu);
      
      JMenuItem menuItem = new JMenuItem("Ball");
      menuItem.addActionListener(el);
      subMenu.add(menuItem);
      menuItem = new JMenuItem("Spring");
      menuItem.addActionListener(el);
      subMenu.add(menuItem);
      menuItem = new JMenuItem("Receptacle");
      menuItem.addActionListener(el);
      subMenu.add(menuItem);
      
      menu = new JMenu("MyWorld");
      mb.add(menu);               //
      menuItem = new JMenuItem("Start");
      menuItem.addActionListener(wl);
      menu.add(menuItem);        //
      menuItem = new JMenuItem("Stop");
      menuItem.addActionListener(wl);
      menu.add(menuItem);        //
      JMenu subMenu2 = new JMenu("Simulator");  
      menu.add(subMenu2);        //
      
      menuItem = new JMenuItem("Delta time");
      menuItem.addActionListener(wl);
      subMenu2.add(menuItem);    //
      menuItem = new JMenuItem("Graphics Refresh time");
      menuItem.addActionListener(wl);
      subMenu2.add(menuItem);    //
      
      return mb;          
   }   
}