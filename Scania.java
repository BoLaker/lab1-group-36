import java.awt.*;

public class Scania extends Car{

    private double TrailerTilt;
    private final double MaxTilt = 70;
    private final double MinTilt = 0;

    Scania(){
        super(2,150,Color.WHITE,"Scania");
        this.TrailerTilt = 0;
    }

    public void raiseTrailer(double amount){
        if(getCurrentSpeed() == 0){
            TrailerTilt = Math.min(TrailerTilt + amount, MaxTilt);
        }
        else{
            System.out.println("Can't raise trailer while moving");
        }
    }

    public void lowerTrailer(double amount){
        if(getCurrentSpeed() == 0){
            TrailerTilt = Math.max(TrailerTilt - amount, MinTilt);
        }
    }

    public double getTrailerTilt(){
        return TrailerTilt;
    }

    public void move() {
        if (TrailerTilt > 0) {
            System.out.println("Can't move with raised trailer");
        }
        else {
            super.move();
        }
    }

    @Override
    public double speedFactor(){
        return super.speedFactor();
    }

   

}
