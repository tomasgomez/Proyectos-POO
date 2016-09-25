import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.event.*; 
import java.util.Arrays;

/**
*	Clase grafica de la clase ListaPacientes
*	@author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
*	@version: 09/06/2016
*
*/
public class ListaPacientes_GUI extends JPanel { 
	/**
	*	String que define el nombre del boton para ingresar un paciente
	*/
    private static String OK = "Entrar";

    /**
	*	Instancia del listener que informara los eventos ocurridos
	*/
    private ListaPacientesListener listener = new ListaPacientesListener(this);

    /**
	*	Instancia de ListaPacientes que sera el frame donde estara situada la instancia grafica
	*/
    private ListaPacientes frame;


    /**
	*	Campo para el nombre
	*/
    private JTextField name = new JTextField(25);

    /**
	*	Campo para el rut
	*/
    private JTextField rut = new JTextField(10);

    /**
	*	Campo para el telefono
	*/
    private JTextField phone = new JTextField(10);

    /**
	*	Campo para el nacimiento
	*/
    private JTextField born = new JTextField(2);

    /**
	*	Campo para el direccion
	*/
    private JTextField direction = new JTextField(20);

    public ListaPacientes_GUI(ListaPacientes p){
    	super(new GridLayout(8,1));
    	frame = p;
        name.setActionCommand(OK);
        name.addActionListener(listener);
        rut.setActionCommand(OK);
        rut.addActionListener(listener);
        phone.setActionCommand(OK);
        phone.addActionListener(listener);
        born.setActionCommand(OK);
        born.addActionListener(listener);
        direction.setActionCommand(OK);
        direction.addActionListener(listener);
        JLabel  label1 = new JLabel("Nombre: ");
        label1.setLabelFor(name);
        JLabel  label2 = new JLabel("Rut (Sin puntos ni guión): ");
        label2.setLabelFor(rut);
        JLabel  label3 = new JLabel("Telefono: ");
        label3.setLabelFor(phone);
        JLabel  label4 = new JLabel("Nacimiento (dia/mes/año): ");
        label4.setLabelFor(born);
        JLabel  label5 = new JLabel("Dirección: ");
        label5.setLabelFor(direction);

        JComponent buttonPane = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label1);
        textPane.add(name);
        textPane.add(label2);
        textPane.add(rut);
        textPane.add(label3);
        textPane.add(phone);
        textPane.add(label4);
        textPane.add(born);
        textPane.add(label5);
        textPane.add(direction);

        add(textPane);
        add(buttonPane);

    }
    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(1,1));
        JButton enterButton = new JButton("Ingresar Paciente");

        enterButton.setActionCommand(OK);
        enterButton.addActionListener(listener);

        p.add(enterButton);

        return p;
    }

    public void update_gui (String ctrl_evento){
    	if (OK.equals(ctrl_evento)) {
    		String name_input = name.getText();
			String rut_input = rut.getText();
    		String phone_input = phone.getText();
    		String born_input = born.getText();
    		String direction_input = direction.getText();

    		if (!name_input.equals("") && !rut_input.equals("") && !phone_input.equals("") && !born_input.equals("") && !direction_input.equals(""))
    		{
		    		Paciente paciente = new Paciente(name_input , rut_input , phone_input , born_input , direction_input);
		    		frame.software.getPacientes().add(paciente);
		    		JOptionPane.showMessageDialog(frame,
                    		"Paciente agregado con éxito.");
    		}

    		else 
    		{
                JOptionPane.showMessageDialog(frame,
                    "Algún dato no fue agregado correctamente, por favor intente denuevo.",
                    "Mensaje de error!",
                    JOptionPane.ERROR_MESSAGE);
            }
    		//if( isCorrectInput( name_input , rut_input , phone_input , born_input , direction_input ) )

    	}
    }	
}