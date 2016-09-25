#include "Wall.h"

   int Wall::id=0;
   
   Wall::Wall():PhysicsElement(id++), a(0,0), b(0,0){   // nobody can create a block without state
   }
   
   Wall::Wall(CVector start, CVector finish): 
         PhysicsElement(id++){
         a = start;                // comienzo
         b = finish;               // final de la pared
   }
   
   const PhysicsElement* Wall::collideWith(const Ball * pb)const{ return NULL;}
   
   string Wall::getDescription()const{
      return "Wall_" + std::to_string(getId())+":x,y";

   }
   string Wall::getState()const{
      return std::to_string(a.getX())+","+std::to_string(b.getY()); 
   }

   void Wall::updateState(){
   }

   void Wall::computeNextState(double delta_t, MyWorld * world) {
   
   }