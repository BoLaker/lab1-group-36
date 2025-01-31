import java.awt.*;

class Volvo240 extends Car{

    public final static double trimFactor = 1.25;

    public Volvo240(){
        super(4,100,Color.black,"Volvo240");
    }
    @Override
    public double speedFactor(){
        return super.speedFactor() * trimFactor;
    }

    public void incrementSpeed(double amount){
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount){
         currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    // TODO fix this method according to lab pm


    // TODO fix this method according to lab pm

}
