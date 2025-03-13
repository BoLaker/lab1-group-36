import java.awt.*;
import java.util.ArrayList;
public abstract class Truck extends Car implements trailer_interface{
    public Truck(int nrDoors, double enginePower, Color color, String modelName){
        super(nrDoors, enginePower, color, modelName);
    }
    
    @Override
    public double speedFactor(){
        return getEnginePower()*0.01;
    }
}
