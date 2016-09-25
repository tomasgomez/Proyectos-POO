
#define NUM_WALLS 4
#include "Container.h"
#include <math.h>
#include <cmath>  
int Container::id=0;

   Container::Container():PhysicsElement(id++), a(0,0), b(0,0){   // nobody can create a block without state
   }
   
   Container::Container(CVector aPoint, CVector bPoint): 
         PhysicsElement(id++){
         CVector cPoint(aPoint.getX(), bPoint.getY());
         CVector dPoint(bPoint.getX(), aPoint.getY());
         a = aPoint;
         b = bPoint;
   }
   
   const PhysicsElement * Container::collideWith(const Ball * pb)const{
   	double diffR = pb->getPosition().getX()- a.getX();
      double diffL = pb->getPosition().getX()- b.getX();
      double diffU = pb->getPosition().getY()- a.getY();
      double diffD = pb->getPosition().getY()- b.getY();     
      bool nearWallH= (pb->getPosition().getX() >= (b.getX()-pb->getRadius()))&&(pb->getPosition().getX() <= (a.getX()+pb->getRadius()));
      bool nearWallV= (pb->getPosition().getY() >= (b.getY()-pb->getRadius()))&&(pb->getPosition().getY() <= (a.getY()+pb->getRadius()));
      bool WallTouch[NUM_WALLS] = {(abs(diffR)<=pb->getRadius())&&nearWallV,(abs(diffL)<=pb->getRadius())&&nearWallV,(abs(diffU)<=pb->getRadius())&&nearWallH,(abs(diffD)<=pb->getRadius())&&nearWallH};
      bool areTouching = WallTouch[0]||WallTouch[1]||WallTouch[2]||WallTouch[3];
      if(!areTouching) return NULL;
      int i=0;
      while(i<=3){
        if(WallTouch[i]==true) break;
        i++;
      }
      switch(i){
        case 0:{
			if ((diffR*pb->getSpeed().getX()) < 0) {
            return this;
			}
          else return NULL;
          }
        case 1:{
          if((diffL*pb->getSpeed().getX())<0) {
           return this;
		  }
          else return NULL;
          }
        case 2:{        
          if((diffU*pb->getSpeed().getY())<0) {
           return this;
		  }
          else return NULL;
          }
        case 3:{        
          if((diffD*pb->getSpeed().getY())<0) {
           return this;
		  }
          else return NULL;
          }
        default: return NULL;
      }
    }   
   
   
    string Container::getDescription()const{
      return "Container_" + std::to_string(getId())+":x,y";
   }

    string Container::getState()const{
      return std::to_string(a.getX())+","+std::to_string(b.getY()); 
   }
   
   void Container::computeNextState(double delta_t, MyWorld * world) {
   
   }
   
   void Container::updateState(){
   }
