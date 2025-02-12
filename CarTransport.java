import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck{
    private boolean rampDown;
    private final int maxCars = 6;
    private Stack<Car> loadedCars;

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

    public void loadCar(Car car){
        if (rampDown && getCurrentSpeed() == 0 && loadedCars.size() < maxCars){
            loadedCars.push(car);
            car.setPosition(getX(), getY());
        }
    }

    public Car unloadCar(){
        if (rampDown && getCurrentSpeed() == 0 && !loadedCars.isEmpty()){
            Car car = loadedCars.pop();
            car.setPosition(getX() + 1, getY());
            return car;
        }
        return null;
    }
    public Stack<Car> getCars(){
        return loadedCars;
    }
    @Override
    public void move(){
        if (loadedCars.isEmpty()){
            super.move();
            for (Car car : loadedCars){
                car.setPosition(getX(), getY());
            }
        }
    }
}
