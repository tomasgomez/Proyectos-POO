#include "Ball.h"
#include "PhysicsElement.h"
#include "CVector.h"

   int Ball::id=0;
   static CVector pos_tPlusDelta;
   static CVector speed_tPlusDelta;



   Ball::Ball():PhysicsElement(id++),mass(1.0), radius(0.1),
             pos_t(0,0), speed_t(0,0){   // nobody can create a block without state
   }

   Ball::Ball(double mass, double radius, CVector position, CVector speed): 
         PhysicsElement(id++),mass(mass),radius(radius){
         pos_t = position;
         speed_t = speed;
   }
   double Ball::getRadius() const {
      return radius;
   }
   CVector Ball::getSpeed() const {
       return speed_t;
   }
   CVector Ball::getPosition() const {
       return pos_t;
   }
   double Ball::getMass() const {
       return mass;
   }
   void Ball::computeNextState(double delta_t, MyWorld * world) {
   // you need to review, complete and/or change this code
     CVector speed = speed_t;
     const PhysicsElement * pe;  // Assumption: on collision we only change speed.
     if ((pe=world->findCollidingBall(this))!= NULL){ /* elastic collision */ 
        const Ball* pb= dynamic_cast<const Ball*> (pe);
        if (pb !=NULL) {
          CVector diffPosition = pos_t - pb->getPosition();
          CVector diffSpeed = speed_t - pb->getSpeed();
          double numerator = diffPosition.dot(diffSpeed);
          double denominator = (diffPosition.module()*(diffPosition.module()));
          if(denominator == 0) denominator = 1;
          double mass_factor = (pb->getMass())/(mass + pb->getMass());
          double constant = 2*mass_factor*numerator/denominator;
          speed = speed_t - (diffPosition*constant);
        }     
     }
     speed_tPlusDelta = speed;
     pos_tPlusDelta = pos_t + speed*delta_t;
     
   }
   const PhysicsElement* Ball::collideWith(const Ball* pb) const { // it return itself 
                                                                   // or a physical element which is part of it 
                                                                   // you need to review and complete this code
      if (this == pb) return NULL;
      const Ball& b=*pb;
      CVector diffPosition = pos_t - b.getPosition();
      bool areTouching = (diffPosition.module())<(b.getRadius()+radius);
      if(!areTouching) return NULL;
      CVector diffSpeed = speed_t - b.getSpeed();
      bool areApproaching = diffPosition.dot(diffSpeed)<0;
      if(areApproaching==true) return this;
      else return NULL;
   }
   void Ball::updateState(){
        pos_t = pos_tPlusDelta;
        speed_t = speed_tPlusDelta;
   }
   string Ball::getDescription() const {
      return "Ball_" + std::to_string(getId())+":x,y";
   }
   
   string Ball::getState() const{
      return std::to_string(pos_t.getX())+","+std::to_string(pos_t.getY()); 
   }

   ostream & operator<< (ostream & os, const Ball & b) {
     os << b.pos_t;
     return os;
   }