import java.util.*;

public class Vector2D {
	private double x, y;  // we will use cartesian coordinates
	public Vector2D () {
		x = y = 0;
	}
	public Vector2D (double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public Vector2D plus(Vector2D v) {                  //le suma al vector que se le aplica la operacion el vector que pasa por argumento
     	if (v == null) return new Vector2D(x,y);
     	return new Vector2D (x+v.getX(), y+v.getY());
	}
	public Vector2D times(double scalar) {              // se multiplica el valor que pasa por argumento a cada componente
	 	return new Vector2D (x*scalar, y*scalar);
	}
	public Vector2D minus(Vector2D v) {                 // le resta al vector que se le aplica la operacion el vector que pasa por argumento
	 	if (v == null) return new Vector2D(x,y);
	 	return new Vector2D(x-v.getX(), y-v.getY());
	}
	public String toString() {							//muestra coordenadas como un string
		return x+";"+y;
	}
	public double size(){								//magnitud del vector
		return Math.sqrt((x*x)+(y*y));	
	}
	public double dot(Vector2D v){						//producto punto
		return x*v.getX() + y*v.getY();
	}
}