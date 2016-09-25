import java.awt.*;
import java.util.*;

/**
*	Clase Paciente que representa al paciente y sus atributos
*	@author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
*	@version: 09/06/2016
*
*/
public class Treatment
{
	private final String title;
	private final Paciente paciente;
	private int cost;
	private String description;

	public Treatment(String t, Paciente p, int c, String d)
	{
		title = t;
		paciente = p;
		cost = c;
		description = d;
	}

	public String getTitle()
        {  
        	return title;
        }

    public Paciente getPaciente()
        {  
        	return paciente;
        }

     public int getCost()
    	{
    		return cost;
    	}
}