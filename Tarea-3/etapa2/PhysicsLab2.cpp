#include <iostream>
#include <cstdlib>  // c++ exit
#include "MyWorld.h"
#include "Ball.h"
#include "CVector.h"
#include "PhysicsElement.h"
#include "Container.h"
#include "Wall.h"

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
      CVector speed(1.2,1.5);     //  [m/s]
      Ball b0(mass, radius, position, speed);
      position.set(10,10);
      speed.set(-1.3,-0.9);
      Ball b1(mass, radius, position, speed);
      position.set(10.0,10.0);
      CVector position1(0.0,0.0);
      Container c0(position,position1);
      world.addElement(&b0);
      world.addElement(&b1);
      world.addElement(&c0);
      world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s].
}
