import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;

public class WorldControlMenuListener implements ActionListener {
   private MyWorld world;
   public WorldControlMenuListener (MyWorld w){
      world = w;
   }
   public void actionPerformed(ActionEvent e) {
      JMenuItem menuItem = (JMenuItem)(e.getSource());
      String text = menuItem.getText();
      if (text.equals("Start"))   world.start();
      if (text.equals("Stop"))    world.stop();
      if (text.equals("Delta time")) {
         String data = JOptionPane.showInputDialog("Enter new delta t [s]");
         world.setDelta_t(Double.parseDouble(data));
      }
      if (text.equals("Graphics Refresh time")) {
         String data = JOptionPane.showInputDialog("Enter new Refresh time [s]");
         world.setRefreshPeriod(Double.parseDouble(data));
      }
   }
}