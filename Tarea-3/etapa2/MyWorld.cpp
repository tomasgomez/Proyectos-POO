#include <iostream>
#include "MyWorld.h"
#include "Ball.h"
#include "PhysicsElement.h"

using namespace std;

MyWorld::MyWorld(ostream & output):out(output){
}

void MyWorld::addElement(PhysicsElement * e) {
      elements.push_back(e);
}

void MyWorld::printStateDescription(){
     out <<"Time\t";
     for (PhysicsElement *e:elements)
       out << e->getDescription() + "\t";
     out << endl;
}

void MyWorld::printState(double t){
   out << t;
   for (PhysicsElement *e: elements)  
     out << "\t" << e->getState();  // print each element state
   out << endl;
}

void MyWorld::simulate (double delta_t, double endTime, double samplingTime) {  // simulate passing time
      double t=0;
      printStateDescription();
      printState(t);
      while (t<endTime) {
         for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
           for (PhysicsElement * e: elements)   // compute each element next state based on current global state  
                e->computeNextState(delta_t,this); // compute each element next state based on current global state
           for (PhysicsElement * e: elements)  // for each element update its state. 
                e->updateState();     // update its state
         }
         printState(t);
      }
}   

const PhysicsElement* MyWorld::findCollidingBall(Ball* me) {
     for (PhysicsElement * pe: elements)  
        if (pe!=me) {
          const PhysicsElement* collisionElement = pe->collideWith(me);
          if (collisionElement != NULL) 
              return collisionElement;
        }
     return NULL;
}
