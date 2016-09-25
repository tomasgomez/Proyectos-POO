import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.event.*; 
import java.util.Arrays;

/**
*	Clase grafica de la clase IngresoUsuario
*	@author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
*	@version: 09/06/2016
*
*/
public class IngresoUsuario_GUI extends JPanel { 

	/**
	*	String que define el nombre del boton para entrar
	*/
    private static String OK = "Entrar";

    /**
	*	String que define el nombre del boton para el usuario que olvido su contraseña
	*/
    private static String HELP = "Olvidé mi contraseña";

    /**
	*	Instancia del listener que informara los eventos ocurridos
	*/
    private IngresoUsuarioListener listener = new IngresoUsuarioListener(this);

    /**
	*	Instancia de IngresoUsuario que sera el frame donde estara situada la instancia grafica
	*/
    private IngresoUsuario controllingFrame;

    /**
	*	Campo para la contraseña
	*/
    private JPasswordField passwordField = new JPasswordField(10);

    /**
	*	Campo para el usuario
	*/
    private JTextField textfield = new JTextField(10);

    /**
	*	Metodo principal de la clase
	*	@param f es el Frame donde se va a correr el grafico , luego se le entregara a ControllingFrame 
	*/
    public IngresoUsuario_GUI(IngresoUsuario f) {

        controllingFrame = f;
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(listener);
        textfield.setActionCommand(OK);
        textfield.addActionListener(listener);
        JLabel  label1 = new JLabel("Usuario: ");
        label1.setLabelFor(textfield);
        JLabel  label2 = new JLabel("Contraseña: ");
        label2.setLabelFor(passwordField);

        JComponent buttonPane = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label1);
        textPane.add(textfield);
        textPane.add(label2);
        textPane.add(passwordField);

        add(textPane);
        add(buttonPane);
    
  } 

  	/**
  	*	Metodo que crea el panel de botones y asigna los listeners a cada boton
  	*	@return Devuelve el panel con los botones y listeners listos
  	*/
	protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton enterButton = new JButton("Entrar");
        JButton helpButton = new JButton("Olvidé mi contraseña");

        enterButton.setActionCommand(OK);
        helpButton.setActionCommand(HELP);
        enterButton.addActionListener(listener);
        helpButton.addActionListener(listener);

        p.add(enterButton);
        p.add(helpButton);

        return p;
    }

    /**
    *	Metodo que reacciona frente a algun evento avisado por el listener
    *	@param ctrl_evento String que tiene la informacion de cual boton fue presionado
    */
    public void update_gui (String ctrl_evento){
    if (OK.equals(ctrl_evento)) { //Procesa el usuario y la contraseña
            char[] pssw_input = passwordField.getPassword();
            char[] user_input = textfield.getText().toCharArray();

            if (isPasswordCorrect(pssw_input , user_input)) {
                JOptionPane.showMessageDialog(controllingFrame,
                    "Entró! Usuario y Contraseña Correcta.");
                    controllingFrame.software.iniciarSesion();
                    controllingFrame.setVisible(false);
                    controllingFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                    "Contraseña o Usuario inválido. Por favor intente denuevo.",
                    "Mensaje de error!",
                    JOptionPane.ERROR_MESSAGE);
            }

            //Se borra la posible contraseña por seguridad.
            Arrays.fill(pssw_input, '0');

            passwordField.selectAll();
            resetFocus();
        }
        else { //El usuario olvidó la contraseña.
            JOptionPane.showMessageDialog(controllingFrame,
                "Contáctese con el administrador\n"
              );
        }
    }

    /*
    *	Metodo que vuelve a posicionar al usuario en el campo de contraseña , que es mas probable que haya ocurrido algun error
    **/
    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    /**
     *	Metodo que confirma si la contraseña y el usuario son correctos o no
     *	@param pssw_input	Arreglo de caracteres que contiene la contraseña ingresada
     *	@param user_input	Arreglo de caracteres que contiene el usuario ingresado 
     *	@return <ul>
     *          <li>true: Coinciden el usuario y la contraseña</li>
     *          <li>false: No coinciden el usuario y la contraseña</li>
     *          </ul>
     */
    private static boolean isPasswordCorrect(char[] pssw_input , char[] user_input) {
        boolean isCorrect = true;
        char[] correctPassword = { 'e', 'l', 'o', '3', '2', '9'};
        char[] correctUser = { 'a', 'd', 'm', 'i', 'n'};

        if (pssw_input.length != correctPassword.length || user_input.length != correctUser.length ) {
            isCorrect = false;
        } else if (Arrays.equals (pssw_input, correctPassword) && Arrays.equals (user_input, correctUser)){
            isCorrect = true;
        }
        else {
            isCorrect = false;
        }

        //Se borra la contraseña correcta.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }
} 