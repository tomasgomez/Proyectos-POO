import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.event.*;

/**
*	Clase principal donde se maneja el programa completo
*	@author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
*	@version: 09/06/2016
*
*/

public class SoftwareDental extends JFrame{
		/**
		*	Instancia de la clase listapacientes
		*/
		public ListaPacientes listapacientes;
		
		/**
		*	Metodo que abre la pantalla de ingreso para el usuario
		*/
    	public SoftwareDental (){
        	IngresoUsuario ingreso = new IngresoUsuario(this);
        	ingreso.setVisible(true);
    	}

    	/**
		*	Metodo que inicia la sesion del usuario luego de que el haya ingresado correctamente usuario y contraseña
		*/
    	public void iniciarSesion(){
        	listapacientes = new ListaPacientes();
    	}

  		public static void main( String[] args) { 
    		SoftwareDental software = new SoftwareDental();
    	}
}

		
		


/**
* Clase 
*/
class IngresoUsuario extends JFrame {
    	private IngresoUsuario_GUI ingreso_gui = new IngresoUsuario_GUI(this);
    	public SoftwareDental software;
      
    	public IngresoUsuario (SoftwareDental gui_aux) {
      	software = gui_aux;
      	setTitle("Software Dental"); 
      	setSize( 500, 300);
      	setLocationRelativeTo(null);
      	getContentPane().add(ingreso_gui);  
      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}

}

class ListaPacientes extends JFrame{
      public ListaPacientes (){
        setTitle("Software Dental");
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      }
}


	