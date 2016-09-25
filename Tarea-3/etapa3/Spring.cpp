#include <stdlib.h>
#include <iostream>
#include "Spring.h"


using namespace std;

int Spring::id=0;

Spring::Spring():PhysicsElement(id++){   // nobody can create a block without state
}

Spring::Spring(double restLength_, double stiffness_): 
      PhysicsElement(id++){
      restLength = restLength_;
      stiffness = stiffness_;
}
void Spring::attach(Ball *b){
  if (aBall == NULL)
    aBall = b;
  else
    bBall = b;
  b -> setSpring(this);
}
CVector Spring::getVector() const{
  CVector vb = bBall -> getPosition();
  CVector va = aBall -> getPosition();
  return vb - va;
}
CVector Spring::getForce(Ball *b){
  CVector force;
  if((aBall == NULL) || (bBall == NULL))
    return force;
  if ((b != aBall) && (b != bBall))
    return force;
  CVector springVector = this -> getVector();
  double stretch = springVector.module() - restLength;
  force = springVector.unitary()*(stretch * stiffness);
  if (b == aBall) return force;
  return force * (-1.0);
}
const PhysicsElement* Spring::collideWith(const Ball* pb) const { 
  return NULL;
}

string Spring::getDescription() const {
   return "";
}

string Spring::getState() const{
   return ""; 
}
void Spring::computeNextState(double delta_t, MyWorld * world){

}
   
void Spring::updateState(){

}