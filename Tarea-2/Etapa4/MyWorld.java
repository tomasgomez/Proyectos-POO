import java.util.*;
import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;

public class MyWorld implements ActionListener {
   private PrintStream out;  
   private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
   private Timer passingTime;   // NEW
   private double t;        // simulation time
   private double delta_t;        // in seconds
   private double refreshPeriod;  // in seconds
   private MyWorldView view;

   public MyWorld(){
      this(System.out);  
   }
   public MyWorld(PrintStream output){
      out = output;
      t = 0;
      refreshPeriod = 0.05;      // [s]
      delta_t = 0.005;          //  [s]
      elements = new ArrayList<PhysicsElement>();
      view = new MyWorldView(this);
      passingTime = new Timer((int)(refreshPeriod*1000), this); 
   }

   public void addElement(PhysicsElement e) {
      elements.add(e);
      view.repaint();
   }
   public PhysicsElement getCollidingElement(Ball b) {
      PhysicsElement pe;
      for (PhysicsElement e: elements){
         pe = e.collide(b);
         if (pe!=null)
            return pe;
      }
      return null;      
   }
   public MyWorldView getView() {
      return view;
   }
   public ArrayList<PhysicsElement> getPhysicsElements(){
      return elements;
   }
   public void start() {
      if(passingTime.isRunning()) return;
      passingTime.start();      
   }
   public void stop() {
      passingTime.stop();
   }
   public void actionPerformed (ActionEvent event) {  // like simulate method of 1st. Assignment, 
      double nextStop=t+refreshPeriod;                // the arguments are attributes here.
      for (; t<nextStop; t+=delta_t){
         for (PhysicsElement e: elements)
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.computeNextState(delta_t, this); // compute each element next state based on current global state
            }
         for (PhysicsElement e: elements)  // for each element update its state. 
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.updateState();            // update its state
            }
      }
      view.repaint();
   }
   private void printStateDescription(){
     String s ="Time,\t";
     for (PhysicsElement e:elements)
       s+=e.getDescription() + "\t";
     out.println(s);
   }
   private void printState(double t){
     String s = t + " ";
     for (PhysicsElement e:elements)
       s+=",\t "+ e.getState();
     out.println(s);    
   } 
   public void setDelta_t(double delta) {
     delta_t = delta;
   }  
   public void setRefreshPeriod(double refresh) {
     refreshPeriod = refresh;
     passingTime.setDelay((int)(refreshPeriod*1000)); // convert from [s] to [ms]
   } 
   public PhysicsElement findElement(double x, double y) { // metodo para encontrar un elemento en cierta posicion del espacio de trabajo.
   	 for (PhysicsElement e: elements){						// en este caso se encuentran elemento dependiendo del orden en que haya sido agregado.
   	 	if (e instanceof Ball){
   	 		Ball b = (Ball) e;
   	 		if (Math.abs(b.getPosition().getX()-x) < b.getRadius() && Math.abs(b.getPosition().getY()- y) < b.getRadius()) return e ; //el Mouse debe estar
   	 		else continue;																											//presionando dentro del
   	 																																//radio de la bola.
   	 	}
   	 	if (e instanceof Receptacle){
   	 		Receptacle r = (Receptacle) e;
   	 		if (Math.abs(r.getDLcorner().getX() - x) < 0.05 && Math.abs(r.getDLcorner().getY() - y) < 0.05 || 
   	 			Math.abs(r.getURcorner().getX() - x) < 0.05 && Math.abs(r.getURcorner().getY() - y) < 0.05) //Se define un "circulo" de radio 0.05 en los 
   	 			return e ;																					//extremos superior derecho e inferior izquierdo
   	 			else continue;																				//donde el contenedor sera manipulado.
   	 	}
         if (e instanceof Spring){
   	 		Spring r = (Spring) e;
   	 		if (Math.abs(r.getaBallPosition().getX() - x) < 0.05 && Math.abs(r.getaBallPosition().getY() - y) < 0.05 ){
               r.setaContains(true);
               return e;
               }
            else if (Math.abs(r.getbBallPosition().getX() - x) < 0.05 && Math.abs(r.getbBallPosition().getY() - y) < 0.05 ){ //Se define un "circulo" de radio 0.05 en los 
   	 			r.setbContains(true);
               return e ;	
               }																				//extremos derecho y izquierdo
   	 		else continue;																				//del resorte.
   	 	}
   	 }
   	 return null;
   }

   public boolean isRunning() {
   	return passingTime.isRunning(); //Metodo para preguntar si esta corriendo el tiempo o no.
   }

   public void loadWorld(ArrayList<PhysicsElement> oldWorld){
   		elements = oldWorld;	//Metodo para cargar objetos que fueron guardados previamente.
   }
}