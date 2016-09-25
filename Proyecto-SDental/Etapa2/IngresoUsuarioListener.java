import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.event.*; 


/**
*	Clase listener de la clase IngresoUsuario
*	@author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
*	@version: 09/06/2016
*
*/
public class IngresoUsuarioListener implements ActionListener {
	/**
	*	Instancia grafica del listener
	*/ 
    private IngresoUsuario_GUI gui;

    /**
    *	Constructor para el listener
    *	@param gui_aux Instancia de la clase grafica de IngresoUsuario, se utiliza para actualizar los campos graficos
	*/
    public IngresoUsuarioListener (IngresoUsuario_GUI gui_aux){
      gui = gui_aux;
    }

    /**
    *	Metodo listener que actualiza los campos graficos
	*/
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        gui.update_gui(cmd);
        
    }
} 