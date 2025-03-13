import java.util.ArrayList;

public class trailer implements trailer_interface{
    private double TrailerTilt;
    private final double MaxTilt = 70;
    private final double MinTilt = 0;
    trailer(){
        this.TrailerTilt = 0;
    }

    public double getTrailerTilt(){
        return TrailerTilt;
    }

    @Override
    public void raiseTrailer(double amount){
        TrailerTilt = Math.min(TrailerTilt + amount, MaxTilt);
    }

    @Override
    public void lowerTrailer(double amount){
        TrailerTilt = Math.max(TrailerTilt - amount, MinTilt);
    }
}
