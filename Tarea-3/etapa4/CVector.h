#ifndef CVECTOR_H
#define CVECTOR_H
#include <string>
#include <ostream>
using namespace std;

class CVector {
   private:
     double x, y;  // we will use cartesian coordinates
   public:
     CVector () { x = y = 0;}
     CVector (double x, double y) {
       this->x=x;
  	    this->y=y;
     }
     double getX() const { return x; }
     double getY() const { return y; }
     void set(double x, double y) { // to set a new position
       this->x=x;
  	    this->y=y;
     }
     CVector operator+(const CVector &v) const;
     CVector operator+=(const CVector &v);
     CVector operator-(const CVector &v) const;   //
     CVector operator*(double scalar) const;   //  (*this)*3
     double operator%(const CVector &v) const; // dot product, or internal product
     CVector operator/(double scalar) const;   //  (*this)/3
     CVector getProjectionOn(const CVector &v) const;
     double module() const;
     double moduleSquared() const;
     CVector unitary() const;
     double dot(const CVector &v) const;
     static string getDescription() {return "(x,y)";}
     string getState() const {
        return to_string(x)+","+to_string(y); 
     }
     friend CVector operator*(double scalar, const CVector &v);  // 3*v
     friend ostream & operator << (ostream &os, const CVector &v);
};
#endif