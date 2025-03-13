import java.awt.*;

public class Scania extends Truck{
    public trailer trailr = new trailer();
    Scania(){
        super(2,150,Color.WHITE,"Scania");
    }

    @Override
    public void raiseTrailer(double amount){
        if(getCurrentSpeed() == 0){
            trailr.raiseTrailer(amount);
        }
        else{
            System.out.println("Can't raise trailer while moving");
        }
    }

    @Override
    public void lowerTrailer(double amount){
        if(getCurrentSpeed() == 0){
            trailr.lowerTrailer(amount);
        }
    }

    public double getTrailerTilt(){
        return trailr.getTrailerTilt();
    }

    public void move() {
        System.out.println(getCurrentSpeed());
        System.out.println(getTrailerTilt());
        if (trailr.getTrailerTilt() > 0) {
            System.out.println("Can't move with raised trailer");
        }
        else {
            super.move();
        }
    }

    @Override
    public double speedFactor(){
        return getEnginePower()*0.01;
    }

   

}
