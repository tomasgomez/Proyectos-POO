#include "CVector.h"
#include <math.h>

CVector CVector::operator+(const CVector &v) const {
   return CVector(x+v.x, y+v.y);
}

CVector CVector::operator+=(const CVector &v) {
   x+=v.x;
   y+=v.y;
   return *this;
}

CVector CVector::operator-(const CVector &v) const {
   return CVector(x-v.x, y-v.y);
}

CVector CVector::operator*(double scalar) const {
   return CVector(scalar*x, scalar*y);
}

CVector CVector::operator/(double scalar) const {
   return CVector(x/scalar, y/scalar);
}

double CVector::operator%(const CVector &v) const {
   return x*v.x + y*v.y;
}

CVector CVector::getProjectionOn(const CVector &v) const {
   double dotProduct= x*v.x + y*v.y;
   return (dotProduct/v.moduleSquared())*v;
}

double CVector::module() const {
   return sqrt(x*x + y*y);
}

double CVector::moduleSquared() const {
   return x*x + y*y;
}

CVector CVector::unitary() const {
   return (*this)/module();
}

double CVector::dot(const CVector &v) const {
   return (x*v.x + x*v.y);
}

CVector operator*(double scalar, const CVector &v){  // 3*v
   return CVector(scalar*v.x, scalar*v.y);
}

ostream & operator << (ostream &os, const CVector &v){
   os << "(" << v.x << "," << v.y << ")";
   return os;
}

