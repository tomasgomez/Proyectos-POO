import java.util.*;
import java.io.*;

public class MyWorld {
   private PrintStream out;  
   private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.

   public MyWorld(){
      this(System.out);  
   }

   public MyWorld(PrintStream output){
      out = output;
      elements = new ArrayList<PhysicsElement>();     
   }

   public void addElement(PhysicsElement e) {
      elements.add(e);
   }

   public ArrayList<PhysicsElement> getElements(){
   	  return elements;
   }
   
   public PhysicsElement getCollidingElement(Ball b) {
      for (PhysicsElement e: elements) 
         if (e.collide(b) && (b!=e))
            return e;
      return null;      
   }
   
   private void printStateDescription(){
     String s ="Time\t";
     for (PhysicsElement e:elements)
       s+=e.getDescription() + "\t";
     out.println(s);
   }

   private void printState(double t){
     String s = " "+t;
     for (PhysicsElement e:elements)
       s+="\t "+ e.getState();
     out.println(s);    
   }

   public void simulate (double delta_t, double endTime, double samplingTime) {  // simulate time passing
      double t=0;
      printStateDescription();
      printState(t);
      while (t<endTime) {
         for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
           for (PhysicsElement e: elements)   
              e.computeNextState(delta_t, this); // compute each element next state based on current global state
           for (PhysicsElement e: elements)  // for each element update its state. 
              e.updateState();     // update its state
         }
         printState(t);
      }
   }   
} 