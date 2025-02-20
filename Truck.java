import java.awt.*;
public class Truck extends Car{
    public Truck(int nrDoors, double enginePower, Color color, String modelName){
        super(nrDoors, enginePower, color, modelName);
    }
    @Override
    public double speedFactor(){
        return getEnginePower()*0.01;
    }
}
