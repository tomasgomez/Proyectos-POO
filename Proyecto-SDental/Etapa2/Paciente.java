import java.awt.*;
import java.util.*;

/**
*	Clase Paciente que representa al paciente y sus atributos
*	@author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
*	@version: 09/06/2016
*
*/
public class Paciente {
	private final String name;
	private String rut;
	private String phone;
	// private Date born;
	private String born;
	private String direction;
	private ArrayList<Treatment> treatment;

	public Paciente(String n , String r, String p, String b,  String dir){ 	//	int year, int month, int day ,
		name = n;
		rut = r;
		phone = p;
		born = b;
		//GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		//born = calendar.getTime();
		direction = dir;
		treatment = new ArrayList<Treatment>();
	}
        
        public String getName()
        {  
        	return name;
        }
        
        public String getRut()
        {
            return rut;
        }
        
        public String getBorn()
        {
            return born;
        }
        
        public String getPhone()
        {
            return phone;
        }
        
        public String getDir()
        {
            return direction;
        }

        public void attachTreatment(Treatment t)
        {
        	treatment.add(t);
        }
}