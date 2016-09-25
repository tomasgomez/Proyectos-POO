import java.util.*;

public class Ball extends PhysicsElement  {
   private static int id = 0;  						// Ball identification number
   private final double mass;
   private final double radius;
   private double gravity;							// Designed to work in y-axis, pointing down, positive number.
   private double frictionConstant;					// Respond to the expression: F=-b*v, b the friction constant
   private Vector2D pos_t;     						// current position at time t
   private Vector2D pos_tPlusDelta;  				// next position at t + delta
   private Vector2D speed_t;   						// speed at time t
   private Vector2D speed_tPlusDelta;   			// speed at t + delta
   private Vector2D accel_t;						// aceleration at time t
   private Vector2D accel_tPlusDelta;				// aceleration t + delta
   private ArrayList<Integer> joinedSprings;  		// array with Springs id numbers
   
   private Ball(){   // nobody can create a ball without state
     super(id++);
     mass = 0;
     radius = 0;
   }
   
   public Ball(double mass, double r, Vector2D position , Vector2D speed, MyWorld world){ 
      super(id++);
      this.mass = mass;
      radius = r;
      gravity = 0.0;
      frictionConstant = 0.0;
      pos_t = position;
      speed_t = speed;
      accel_t = new Vector2D(0,0);
      accel_tPlusDelta = new Vector2D(0,gravity*-1);
      joinedSprings = new ArrayList<Integer>();
   }

   public double getMass() {
      return mass;
   }

   public double getRadius() {
      return radius;
   }

   public Vector2D getPosition() {
      return pos_t;
   }

   public Vector2D getSpeed() {
      return speed_t;
   }

   public void setSpring(int springId){					// asignación del numero id de un spring unido a la bola
   	  joinedSprings.add(springId);
   }

   public void setGravity(double g){					// se asigna valor de la aceleración de gravedad. 
   	  gravity=Math.abs(g);
   }

   public void setFriction(double f){
   	  frictionConstant=Math.abs(f);									// se asigna valor de constante de friccion viscoso.
   }

   public boolean collide(Ball b){											//deteccion de colisión con otra bola
   	  Vector2D diffPosition = pos_t.minus(b.getPosition());
   	  boolean areTouching = (diffPosition.size())<(b.getRadius()+radius);	//se comprueba si las bolas se tocan comparando la distancias de sus centros y la suma de sus radios
   	  if(!areTouching) return false;
   	  Vector2D diffSpeed = speed_t.minus(b.getSpeed());				
   	  boolean areApproaching=diffPosition.dot(diffSpeed)<0;					//se comprueba si las bolas se estan acercando
   	  return areApproaching;
   }
   
   public void computeNextState(double delta_t, MyWorld w) {
     Vector2D speed=speed_t;
     PhysicsElement pe;
     accel_tPlusDelta=new Vector2D(0,gravity*-1).plus(speed.times(-1*frictionConstant/mass));						// se calcula aceleración producida por roce viscoso y gravedad.
     if(joinedSprings.size()>0){																					// si hay resortes unidos, se calcula la aceleración debida a ellos
     	 
	     for(PhysicsElement e : w.getElements()){																	// se recorre lista de elemento en el simulador para encontrar a el/los resortes unidos a la bola, cuya id se encuetra en el arreglo joined springs
	     	for(int s : joinedSprings){
	     		if(s==e.getId()){																					// si hay coincidencias de id, se comprueba que sea un resorte
	     			if (e instanceof Spring){
		     			Spring spring = (Spring) e;
		     			accel_tPlusDelta=accel_tPlusDelta.plus(spring.getForce(this.getId()).times(1/mass)); 		// se calcula la aceleración total, sumando cada aceleracion producida por resortes, cuya expresión responde a fuerza resorte/masa bola
	     			}
	     		}
	     	}
	     }
	     
     }

     if ((pe=w.getCollidingElement(this))!= null){ /* elastic collision */
        if (pe instanceof Ball) {
           Ball b =(Ball) pe;
           Vector2D diffPosition = pos_t.minus(b.getPosition());
           Vector2D diffSpeed =speed.minus(b.getSpeed());
           speed=speed.minus(diffPosition.times((2*((b.getMass())/(mass+b.getMass())))*(diffPosition.dot(diffSpeed)/(diffPosition.size()*diffPosition.size())))); // la velocidad resultante de un choque de bolas esta dada por esta expresión
        } 
        else{
           Container c = (Container) pe;						//se calculan a continuación las distancias de la coordenada x a las murallas derecha e izquierda y de la coordenada y de la bola a las murallas superior e inferior
           double diffR = pos_t.getX()-c.WallR();
           double diffL = pos_t.getX()-c.WallL();
           double diffU = pos_t.getY()-c.WallU();
           double diffD = pos_t.getY()-c.WallD();      
           boolean [] WallTouch = {(Math.abs(diffR)<=radius),(Math.abs(diffL)<=radius),(Math.abs(diffU)<=radius),(Math.abs(diffD)<=radius)};    // se crea vector booleano, donde cada valor representa si la bola esta tocando alguna muralla, formulas en documento RigidBodiesCollitions
           boolean [] Aproaching = {(diffR*speed.getX())<0,(diffL*speed.getX())<0,(diffU*speed.getY())<0,(diffD*speed.getY())<0};        // se crea vector para verificar si se acerca la bola a alguna muralla
           ENDCOLLITION:
           for(int i=0;i<=3;i++){
            if(WallTouch[i]&&Aproaching[i]){                                          // si se toca alguna muralla y se acerca la bola, si es a una muralla horizontal, se invierte la velocidad en el eje x, si es a una vertical, la velocidad del eje y
              switch(i){
                case 0: case 1: speed=new Vector2D(speed.getX()*-1,speed.getY());break ENDCOLLITION;
                case 2: case 3: speed=new Vector2D(speed.getX(),speed.getY()*-1);break ENDCOLLITION;
                default: speed=speed;
              }
            } 
           }
        }
      }
     
     
	 speed_tPlusDelta=speed.plus(accel_tPlusDelta.times(3.0).minus(accel_t).times(delta_t/2));                                     //tanto la velocidad como la posicion se actualizan en base a la aceleración de los resortes ( si es que hay) y la velocidad posterior a choques, bajo el algoritmo planteado al final del documento MassSimulationModel
	 pos_tPlusDelta=pos_t.plus(speed.times(delta_t)).plus(accel_tPlusDelta.times(4.0).minus(accel_t).times((delta_t*delta_t)/6));
   }

   public void updateState(){
   	 accel_t=accel_tPlusDelta;
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
   }

   public String getDescription() {
      return "Ball_" + getId()+":x\tBall_" + getId()+":y";
   }

   public String getState() {
      return pos_t.getX()+"\t"+pos_t.getY();
   }
}
