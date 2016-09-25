import java.util.*;
import java.awt.*;
/**
 * @author Johannes Rothkegel Sielfeld, Tomas Gomez Molina
 * @param Ball() Clase Bola, es un elemento fisico que .
 * @return 
 *
 */
public class Ball extends PhysicsElement implements Simulateable  {
/**
 * Atributos de Ball.
 * @param id numero de identificacion de la bola.
 * @param mass valor del peso de la bola.
 * @param radius valor del radio de la bola.
 * @param pos_t valor actual de la posicion de la bola.
 * @param pos_tPlusDelta valor de la posicion pasado un delta_t.
 * @param speed_t velocidad en el instante actual.
 * @param speed_tPlusDelta valor de la velocidad despues de un tiempo delta_t
 * @param a_t  aceleracion en el instante actual.
 * @param a_tMinusDelta valor de la aceleracion despues de un tiempo delta_t
 * @param springs arreglo de springs a los que esta ligada la bola.
 */
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private Vector2D pos_t;     // current position at time t
   private Vector2D pos_tPlusDelta;  // next position at t + delta
   private Vector2D speed_t;   // speed at time t
   private Vector2D speed_tPlusDelta;   // speed at t + delta
   private Vector2D a_t;    // acceleration at time t
   private Vector2D a_tMinusDelta; 
   private ArrayList<Spring> springs;
   private BallView view;
   
   
 /**
 * Constructores para Ball
 *@param Ball Constructor por defecto. 
 */
   private Ball(){   // nobody can create a ball without state
     super(id++);
     mass=0;
     radius=0;
   }
   public Ball(double mass, double r, Vector2D position, Vector2D speed){
      super(id++);
      this.mass = mass;
      radius = r;
      pos_t = position;
      speed_t = speed;
      a_t=a_tMinusDelta= new Vector2D();
      springs = new ArrayList<Spring>();
      view = new BallView(this);
   }
   
/**
* Metodos para Ball
*@param attach Agrega un spring al arreglo spring. Este resorte esta pegado a la bola. 
*/
   public void attach(Spring s){
     springs.add(s);
   }
/**
*@param unAttach Quita un spring al arreglo spring.Este resorte se despegado a la bola. 
*/
   public void unAttach(Spring s){ // Metodo para remover un elastico de la bola.
      springs.remove(s);
   }
/**
*@param isAttached Metodo para saber si la bola tiene algun elastico unido a ella.
*@return boolean Recibe true si tiene y false si no tiene.
*/
   public boolean isAttached(){	
   	 return !springs.isEmpty();
   }
/**
*@param getFirstSpring Metodo para obtener el primer resorte agregado a la bola.
*@return Spring Devuelve el primer spring pegado a la bola.
*/
   public Spring getFirstSpring(){
   	 return springs.get(0);
   }
/**
*@param getMass Obtiene la masa de la bola.
*@return double Valor de la masa de la bola.
*/
   public double getMass() {
      return mass;
   }
/**
*@param getRadius Obtiene el valor del radio.
*@return double Valor del radio.
*/
   public double getRadius() {
      return radius;
   }
/**
*@param getPosition Obtiene posicion del centro de la bola.
*@return Vector2d Vector con coordenadas del centro de la bola.
*/
   public Vector2D getPosition() {
      return pos_t;
   }
/**
*@param getSpeed Obtiene velocidad de la bola.
*@return Vector2D Vector con componentes de velocidad de la bola.
*/
   public Vector2D getSpeed() {
      return speed_t;
   }
/**
*@param getNetForce Obtiene fuerza neta de la bola.
*@return Vector2D Vector con componentes de fuerza de la bola.
*/
   private Vector2D getNetForce(){
     Vector2D force = new Vector2D();
     for (Spring s:springs)
       force=force.plus(s.getForce(this));
     return force;
   }
/**
*@param collide Colision de dos bolas.
*@return PhysicsElement 
*/
   public PhysicsElement collide(Ball b){
      if (this == b) return null;
      Vector2D vectorBetweenCenters=getPosition().minus(b.getPosition());
      boolean areTouching = vectorBetweenCenters.module() < radius+b.getRadius();
      Vector2D diffSpeed = getSpeed().minus(b.getSpeed());
      boolean areApproaching = diffSpeed.dot(vectorBetweenCenters) < 0;
      return (areTouching && areApproaching?this: null);
   }
/**
*@param computeNextState   Siguiente estado de la bola.
*/
   
   public void computeNextState(double delta_t, MyWorld w) {
     Vector2D speed=speed_t;
     PhysicsElement pe;
     if ((pe=w.getCollidingElement(this))!= null){ /* elastic collision */
        if (pe instanceof Ball) {
           Ball b =(Ball) pe;
         //speed=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());  // one dimession
           Vector2D dpos = getPosition().minus(b.getPosition()).unitary();
           Vector2D dv = getSpeed().minus(b.getSpeed());
           speed=speed_t.minus( dpos.times( dv.dot(dpos)*2*b.getMass()/(mass+b.getMass()) ) );
        } else { 
            if( pe instanceof Wall)
              if (((Wall)pe).getOrientation()==Wall.SIDEWAYS)
                 speed = new Vector2D(speed_t.getX(),-speed_t.getY());
              else
                 speed = new Vector2D(-speed_t.getX(),speed_t.getY());
        }
     } 
     
     a_t = getNetForce().times(1/mass);
     speed_tPlusDelta = speed.plus(a_t.times(3).minus(a_tMinusDelta).times(delta_t/2));
     pos_tPlusDelta = pos_t.plus(speed.times(delta_t)).plus(a_t.times(4).minus(a_tMinusDelta).times(delta_t*delta_t/6));
    
  //   speed_tPlusDelta = speed;
  //   pos_tPlusDelta = pos_t.plus(speed.times(delta_t));
   }
/**
*@param updateState Actualiza estado de bola.
*/
   public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
     a_tMinusDelta = a_t;
   }
/**
*@param paintView Dibuja la bola en pantalla.
*/
   public void paintView(Graphics2D g){
      view.draw(g);
   }
   public String getDescription() {
      return "Ball_" + getId()+Vector2D.getDescription();
   }
/**
*@param getState  Obtiene el estado de la bola.
*@return String   retorna string con estado de bola.
*/
   public String getState() {
      return getPosition()+" ";
   }

   public boolean contains (double x, double y){
   	return false;
   }
/**
*@param dragDelta Mueve en un delta a la bola.
*/   
   public void dragDelta(double dx, double dy) {	//mueve las posiciones x, y con los delta dx y dy.
   		pos_t = new Vector2D(pos_t.getX()+dx,pos_t.getY()+dy);
   }
/**
*@param setSelected Si esta seleccionada la bola, cambia su color.
*/   
   public void setSelected() {
   		view.isSelected();
   }
/**
*@param setReleased Si no esta seleccionada la bola, deja la bola de un color.
*/   
   public void setReleased(){
   		view.isReleased();
   } 
   
   public void dragbDelta(double dx, double dy){
      return;
   }
}

