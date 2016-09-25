import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
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
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;


@SuppressWarnings("unchecked")	//omitir mensajes de unchecked.


public class FileMenuListener extends JPanel implements ActionListener {
   private MyWorld  world;
   JFileChooser fc;
     
        
   public FileMenuListener (MyWorld  w){
      world = w;
      fc = new JFileChooser();

   }

   public void actionPerformed(ActionEvent e) {
      if (!world.isRunning()){										// El mundo debe estar detenido para realizar alguna accion de guardado o cargado.
         JMenuItem menuItem = (JMenuItem)(e.getSource());
         String text = menuItem.getText();
         try{
               if (text.equals("Save")) {
                  int returnVal = fc.showSaveDialog(FileMenuListener.this);
                  if (returnVal == JFileChooser.APPROVE_OPTION){
                     File file = fc.getSelectedFile(); 												//utilizando JFileChooser el usuario define un archivo
                     ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));	//a crear, luego ese archivo se retiene en file
                     out.writeObject(world.getPhysicsElements());									//y luego se escribe con todos los objetos serializados.
                     out.close();
                  }
               }

                 
               if (text.equals("Load")) {
                  int returnVal = fc.showOpenDialog(FileMenuListener.this);							//idem que anterior, pero el usuario solo elige el archivo
                  if (returnVal == JFileChooser.APPROVE_OPTION){									//que quiere cargar.
                     File file = fc.getSelectedFile();
                     ObjectInputStream in =  new ObjectInputStream(new FileInputStream(file));
                     ArrayList<PhysicsElement> physicElements = (ArrayList<PhysicsElement>)in.readObject();
                     world.loadWorld(physicElements);
                     world.getView().repaint();
                     in.close();
                  }
               }
            }
         catch (Exception k)  {  
            k.printStackTrace(); 
         }
      }
   }
}