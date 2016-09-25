#ifndef SPRING_H
#define SPRING_H
#include "Ball.h"
#include "CVector.h"
#include "PhysicsElement.h"

class Spring: public PhysicsElement {
private: 
   static int id;
   double restLength; 
   double stiffness;
   Ball *aBall = NULL;
   Ball *bBall = NULL;

   Spring();    // nobody can create a Wall without state
public:
   Spring(double restLength_, double stiffness_);
   const PhysicsElement * collideWith(const Ball * pb)const;
   void attach (Ball *b);
   CVector getVector() const;
   CVector getForce(Ball *b);
   virtual string getDescription()const;
   virtual string getState()const;
   void computeNextState(double delta_t, MyWorld * world);
   void updateState();
};
#endif