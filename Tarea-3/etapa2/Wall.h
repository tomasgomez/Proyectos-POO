#ifndef WALL_H
#define WALL_H
#include "Ball.h"
#include "CVector.h"
#include "PhysicsElement.h"

class Wall: public PhysicsElement {
private: 
   static int id;
   CVector a, b;  // extreme points of the wall. This allows 
                  // containers of any shape although it is not
                  // required in this assignment 

public:
   Wall(); 
   Wall(CVector aPoint, CVector bPoint);
   const PhysicsElement * collideWith(const Ball * pb)const;
   CVector getAend() const {
      return a;
   }
   CVector getBend() const {
      return b;
   }  
   void set(CVector a, CVector b) { // to set a new position
       this->a=a;
  	    this->b=b;
   }
   virtual string getDescription()const;
   virtual string getState()const;
   void computeNextState(double delta_t, MyWorld * world);
   void updateState();
};
#endif