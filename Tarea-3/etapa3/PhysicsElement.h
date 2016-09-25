#ifndef PHYSICS_ELEMENT_H
#define PHYSICS_ELEMENT_H

class MyWorld;  // incomplete class declaration to deal with circular class definition
                // also known as Forward declaration
class Ball;

#include <string>
using namespace std;

class PhysicsElement {
private:
   const int myId; /* to identify each element within its category */
protected:
   PhysicsElement(int id);
   int getId() const;
public:
   virtual string getDescription() const =0;
   virtual string getState() const =0;
   virtual const PhysicsElement * collideWith (const Ball * pb) const=0;  
      virtual void computeNextState(double delta_t, MyWorld * world)=0;
   virtual void updateState()=0; 
};
#endif