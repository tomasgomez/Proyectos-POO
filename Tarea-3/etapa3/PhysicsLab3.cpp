#include <iostream>
#include <cstdlib>  // c++ exit
#include "MyWorld.h"
#include "Ball.h"
#include "CVector.h"
#include "PhysicsElement.h"
#include "Container.h"
#include "Wall.h"
#include "Spring.h"


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
      double stiffness1 = 1;
      double restLength1 = 1;
      CVector position(1,1);  // [m] 
      CVector speed(0.0,0.0);     //  [m/s]
      Ball b0(mass, radius, position, speed);
      position.set(4,3);
      speed.set(-0.0,-0.0);
      Ball b1(mass, radius, position, speed);
      
      position.set(2.3,2.3);
      CVector position1(0,0);
      Container c0(position,position1);
      Spring s1(1,1);
      s1.attach(&b0);
      s1.attach(&b1);
      
      
      
      world.addElement(&b0);
      world.addElement(&b1);
      world.addElement(&s1);
      //world.addElement(&c0);
      world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s].
}
