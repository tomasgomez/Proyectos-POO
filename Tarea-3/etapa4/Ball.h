#ifndef BALL_H
#define BALL_H

class PhysicsElement; // incomplete class declaration to deal with circular class definition
class Spring;

#include <string>
#include <vector>
#include "CVector.h"
#include "PhysicsElement.h"
#include "MyWorld.h"
#include "Spring.h"
#include "Container.h"

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
   CVector accel_t;
   CVector accel_tPlusDelta;
   vector<Spring*>  joinedSprings; 
    
   Ball();
public:
   Ball(double mass, double radius, CVector position, CVector speed);
   double getRadius() const;
   CVector getSpeed() const;
   CVector getPosition() const;
   CVector getAceleration();
   double getMass() const;
   void computeNextState(double delta_t, MyWorld * world);
   const PhysicsElement * collideWith(const Ball *b) const;  
   void updateState();
   friend ostream & operator<< (ostream &, const Ball &);
   virtual string getDescription() const;
   virtual string getState() const;
   void setSpring(Spring *s);
};
#endif