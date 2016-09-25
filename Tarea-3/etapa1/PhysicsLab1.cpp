#include <iostream>
#include <cstdlib>  // c++ exit
#include "MyWorld.h"
#include "Ball.h"
#include "CVector.h"
#include "PhysicsElement.h"

using namespace std;

int main(int argvc, char * argv[]) {
      if (argvc != 4)  {
        cout << "usage: "<< argv[0] << " <delta_time[s]> <end_time[s]> <sampling_time[s]>" << endl;
        exit(-1);
      }
      double deltaTime = atof(argv[1]);    // [s]
      double endTime = atof(argv[2]);      // [s]
      double samplingTime = atof(argv[3]); // [s]
      MyWorld world(cout);
      
      double mass = 1.0;      // 1 [kg] 
      double radius = 0.1;    // 10 [cm] 
      CVector position(0,0);  // [m] 
      CVector speed(1,1);     //  [m/s]
      Ball b0(mass, radius, position, speed);
      position.set(2,2);
      speed.set(-1,-1);
      Ball b1(mass, radius, position, speed);
      world.addElement(&b0);
      world.addElement(&b1);
      world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s].
}
