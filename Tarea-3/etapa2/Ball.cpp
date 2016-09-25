#include "Ball.h"
#include "PhysicsElement.h"
#include "CVector.h"
#include "Container.h"
#include "Wall.h"
#include <math.h>
#include <cmath>  
#define UP  3
#define DOWN  2
#define LEFT  2
#define RIGHT  4

   int Ball::id=0;

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
        if(pe->getDescription()[0]=='B'){
         const Ball* pb= dynamic_cast<const Ball*> (pe);
         if (pb != NULL) {
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
        else if(const Container* container = dynamic_cast<const Container*> (pe)){
          if(container != NULL){
            CVector start  = container->getA();
            CVector finish = container->getB();
            double diffR,diffL,diffU,diffD, aproachingR,aproachingL,aproachingU,aproachingD;
            
            diffR = pos_t.getX()-start.getX();
            aproachingR = diffR*speed.getX();

				diffL = pos_t.getX() - finish.getX();
				aproachingL = diffL*speed.getX();

				diffU = pos_t.getX() - start.getY();
				aproachingU = diffU*speed.getY();

			   diffD = pos_t.getX() - finish.getY();
			   aproachingD = diffD*speed.getY();

            if(((abs(diffR)<=radius)||(abs(diffL)<=radius))&&(((aproachingR)<0)||((aproachingL)<0))){
                    CVector new_speed(speed.getX()*-1,speed.getY());
                    speed = new_speed ;
                 }
            if(((abs(diffU)<=radius)||(abs(diffD)<=radius))&&(((aproachingU)<0)||((aproachingD)<0))){
                    CVector new_speed(speed.getX(),speed.getY()*-1);
                    speed = new_speed ;                
                    }
            else speed=speed;
              }
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