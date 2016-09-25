import java.util.*;

public class Spring extends PhysicsElement{
    private static int id=0;
	private double stiffness, restLength;
	Vector2D length10, length10_PlusDelta, force10, force10_PlusDelta;
	Ball ball0, ball1;

	private Spring(){
		super(id++);
		stiffness=0.0;
		restLength=0.0;
	}

	public Spring(double length, double stiff){
		super(id++);
		stiffness=stiff;
		restLength=length;
	}

	public void setBalls(Ball b0, Ball b1){
		ball0=b0;
		ball1=b1;
		length10=ball1.getPosition().minus(ball0.getPosition());
		force10=length10.times(stiffness*(1-(restLength/(length10.size()))));
		ball0.setSpring(this.getId());
		ball1.setSpring(this.getId());
	}

	public String getDescription(){
		return "Spring_"+getId()+":restLength\tSpring_"+getId()+":actualLength";
    }

    public String getState(){
    	return restLength+"\t"+length10.size();

    }

    public boolean collide(Ball b){
    	return false;
    }

    public Vector2D getForce(int ballId){
    	if(ball0.getId()==ballId) return force10;
    	else return force10.times(-1);
    }

    public void computeNextState(double delta_t, MyWorld world){
       	length10_PlusDelta=ball1.getPosition().minus(ball0.getPosition());
    	force10_PlusDelta=length10.times(stiffness*(1-(restLength/(length10.size()))));	
    }

    public void updateState(){
    	length10=length10_PlusDelta;
    	force10=force10_PlusDelta;
    }
}
