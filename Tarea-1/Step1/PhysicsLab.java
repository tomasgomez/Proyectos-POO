public class PhysicsLab {
   public static void main(String[] args) {
      if (args.length != 3)  {
         System.out.println("usage: java PhysicsLab <delta_time[s]> <end_time[s]> <sampling_time[s]>");
         System.exit(-1);
      }
      double deltaTime = Double.parseDouble(args[0]);    // [s]
      double endTime = Double.parseDouble(args[1]);      // [s]
      double samplingTime = Double.parseDouble(args[2]); // [s]
      MyWorld world = new MyWorld(System.out);
      
      double mass1 = 1.0;      // [kg] 
      double mass2 = 1.0;      // [kg] 
      Vector2D position1 = new Vector2D(0.0,0.0);  // [m] 
      Vector2D position2 = new Vector2D(2.0,2.0);  // [m]
      Vector2D speed1 = new Vector2D(1.0,1.0);     // [m/s]
      Vector2D speed2 = new Vector2D(-1.0,-1.0);     // [m/s]
      double radio1= 0.1;     // [m]
      double radio2= 0.1;     // [m]
      Ball b1 = new Ball(mass1, radio1, position1, speed1, world);
      Ball b2 = new Ball(mass2, radio2, position2, speed2, world);

      world.addElement(b1);
      world.addElement(b2);
      world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s].
   }
}
