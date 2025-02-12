import java.awt.*;
import java.util.Stack;


public class CarTransport extends Truck{
    public boolean rampDown;
    private final int maxCars = 6;
    private Stack<Car> loadedCars;


    public Stack<Car> getLoadedCars(){
        return loadedCars;
    }

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

    public void loadCar(Car car) {
        System.out.println("Attempting to load car: " + car);
        System.out.println("Ramp down: " + rampDown + ", Speed: " + getCurrentSpeed() + ", Cars loaded: " + loadedCars.size());

        if (car != null && rampDown && getCurrentSpeed() == 0 && loadedCars.size() < maxCars) {
            loadedCars.push(car);
            car.setPosition(getX(), getY());
            System.out.println("Car loaded successfully! New count: " + loadedCars.size());
        } else {
            System.out.println("Failed to load car.");
        }
    }



    public Car unloadCar() {
        if (!rampDown || getCurrentSpeed() > 0 || loadedCars.isEmpty()) {
            return null; // Return null safely instead of causing an exception
        }

        Car car = loadedCars.pop();
        if (car != null) {
            car.setPosition(getX() + 1, getY()); // Place the car next to the truck
        }
        return car;
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
