#ifndef MY_WORD_H
#define MY_WORD_H

class PhysicsElement;// incomplete class declaration to deal with circular class definition
                     // also known as Forward declaration
class Ball;

#include <vector>
#include <ostream>
#include <iostream>  // needed by cout
using namespace std;

class MyWorld {
private:
   ostream & out;
   vector<PhysicsElement*> elements;  // array to hold everything in my world.
public:
   MyWorld(ostream & output=cout);
   void addElement(PhysicsElement *e);
   void printStateDescription();
   void printState(double t);
   void simulate (double delta_t, double endTime, double samplingTime);
   const PhysicsElement * findCollidingBall(Ball * me);
};
#endif