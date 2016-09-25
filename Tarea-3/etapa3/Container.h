#ifndef CONTAINER_H
#define CONTAINER_H
#include <string>
#include "Ball.h"
#include "CVector.h"
#include "PhysicsElement.h"
#include "Wall.h"
#define NUM_WALLS 4

class Container:public PhysicsElement {
private: 
   static int id;
   //Wall  walls[NUM_WALLS]; // other options are possible 
   CVector a;
   CVector b;
   Container();   // nobody can create an istance without state
public:
   Container(CVector aPoint, CVector bPoint);
   const CVector getA() const{
      return a; 
   } 
   const CVector getB() const{
      return b; 
   }
   const double getTouch() const;
   const PhysicsElement * collideWith(const Ball * b)const;
   string getDescription()const;
   string getState()const;
   void computeNextState(double delta_t, MyWorld * world);
   void updateState();
};
#endif