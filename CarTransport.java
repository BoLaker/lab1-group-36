import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck{
    private boolean rampDown;
    private final int maxCars = 6;
    public Stack<Car> loadedCars;

    CarTransport(){
        super(2, 350, Color.BLACK, "BilTransport");
        this.rampDown = false;
        this.loadedCars = new Stack<>();
    }

    public void lowerRamp(){
        if (getCurrentSpeed() == 0){
            rampDown = true;
        }
    }

    public  void raiseRamp(){
        rampDown = false;
    }
    public boolean getRampStatus(){
        return rampDown;
    }

    public void loadCar(Car car){ // inte kunna lasta cartransport
        if (rampDown && getCurrentSpeed() == 0 && loadedCars.size() < maxCars && !(car instanceof CarTransport)){
            loadedCars.push(car);
            car.setPosition(getX(), getY());
        }
        else{
            throw new IllegalArgumentException("Cant load cartransport to cartransport");
        }

    }

    public Car unloadCar(){
        if (rampDown && getCurrentSpeed() == 0 && !loadedCars.isEmpty()){
            Car car = loadedCars.pop();
            car.setPosition(getX() + 1, getY());
            return car;
        }
        throw new IllegalArgumentException("Cant unload Car");
    }
    public Stack<Car> getCars(){
        return loadedCars;
    }
    @Override
    public void move() {
        super.move(); // Flytta lastbilen först
         // Endast om det finns lastade bilar
            for (Car car : loadedCars) {
                car.setPosition(getX(), getY());
            }
        
    }
    
}
