#ifndef BALL_H
#define BALL_H

#include <string>
#include <vector>
#include "CVector.h"
#include "PhysicsElement.h"
#include "MyWorld.h"

using namespace std;

class Ball: public PhysicsElement {
private:
   static int id;  // Ball identification number
   const double mass;
   const double radius;
   CVector pos_t;     // current position at time t
   CVector pos_tPlusDelta;  // next position in delta time in future
   CVector speed_t;   // speed at time t
   CVector speed_tPlusDelta;   // speed in delta time in future
   
   Ball();
public:
   Ball(double mass, double radius, CVector position, CVector speed);
   double getRadius() const;
   CVector getSpeed() const;
   CVector getPosition() const;
   double getMass() const;
   void computeNextState(double delta_t, MyWorld * world);
   const PhysicsElement * collideWith(const Ball *b) const;  
   void updateState();
   friend ostream & operator<< (ostream &, const Ball &);
   virtual string getDescription() const;
   virtual string getState() const;
};
#endif