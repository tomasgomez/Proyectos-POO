import java.util.*;

public class Container extends PhysicsElement{
	private static int id=0;
	private Vector2D uLP;
	private Vector2D dRP;

	private Container(){
		super(id++);
		uLP= new Vector2D(0.0,0.0);
		dRP= new Vector2D(0.0,0.0);
	}

	public Container(Vector2D upLeftPosition, Vector2D downRightPosition, MyWorld world){
		super(id++);
		uLP = upLeftPosition;
		dRP = downRightPosition;
	}

	public String getDescription(){
		return "Container_" + getId()+":upLeft x\tContainer_" + getId()+":upLeft y\tContainer_" + getId()+":downRight x\tContainer_" + getId()+":downRight y";
	}

	public double WallR(){
		return dRP.getX();
	}

	public double WallL(){
		return uLP.getX();
	}

	public double WallU(){
		return uLP.getY();
	}

	public double WallD(){
		return dRP.getY();
	}

	public String getState(){
	    return uLP.getX()+"\t"+uLP.getY()+'\t'+dRP.getX()+'\t'+dRP.getY();
	}

	public boolean collide(Ball b){
	  double diffR = b.getPosition().getX()-dRP.getX();
      double diffL = b.getPosition().getX()-uLP.getX();
      double diffU = b.getPosition().getY()-uLP.getY();
      double diffD = b.getPosition().getY()-dRP.getY();     
      boolean nearWallH= (b.getPosition().getX() >= (uLP.getX()-b.getRadius()))&&(b.getPosition().getX() <= (dRP.getX()+b.getRadius()));
      boolean nearWallV= (b.getPosition().getY() >= (dRP.getY()-b.getRadius()))&&(b.getPosition().getY() <= (uLP.getY()+b.getRadius()));
      boolean [] WallTouch = {(Math.abs(diffR)<=b.getRadius())&&nearWallV,(Math.abs(diffL)<=b.getRadius())&&nearWallV,(Math.abs(diffU)<=b.getRadius())&&nearWallH,(Math.abs(diffD)<=b.getRadius())&&nearWallH};
      boolean areTouching = WallTouch[0]||WallTouch[1]||WallTouch[2]||WallTouch[3];
      if(!areTouching) return false;
      int i=0;
      while(i<=3){
        if(WallTouch[i]==true) break;
        i++;
      }
      switch(i){
        case 0: return (diffR*b.getSpeed().getX())<0;
        case 1: return (diffL*b.getSpeed().getX())<0;
        case 2: return (diffU*b.getSpeed().getY())<0;
        case 3: return (diffD*b.getSpeed().getY())<0;
        default: return false;
      }
    }

	public void computeNextState(double delta_t, MyWorld world){}
	public void updateState(){}

}
