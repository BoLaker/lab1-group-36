import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements CarButtonListener{
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    private Car generateRandomCar() {
        int randomNum = new Random().nextInt(3); // Assuming 3 types of cars
        switch (randomNum) {
            case 0: return new Volvo240();
            case 1: return new Saab95();
            case 2: return new Scania();
            default: return new Volvo240();
        }
    }

    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    private List<CarObserver> observers = new ArrayList<>();
    public CarController(ArrayList<Car> cars){
        this.cars = cars;
        this.timer.start();
    }
    //methods:
    public void addObserver(CarObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (CarObserver observer : observers) {
            observer.updateCars(cars);
        }
    }
    public static void main(String[] args) {
        // Instance of this class
        ArrayList<Car> cars = new ArrayList<>();

        Volvo240 volvo = new Volvo240();
        volvo.setPosition(0, 300);
        volvo.turnRight();

        Saab95 saab = new Saab95();
        saab.setPosition(0, 100);
        saab.turnRight();

        Scania scania = new Scania();
        scania.setPosition(0, 200);
        scania.turnRight();

        cars.add(volvo);
        cars.add(saab);
        cars.add(scania);

        // Start a new view and send a reference of self
        CarController controller = new CarController(cars);
        CarView view = new CarView("CarSim 1.0", controller);
        controller.addObserver(view); // Koppla View som observer
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                if (x >= 699 || x <= -50 || y >= 499 || y <= 0) {
                    car.turnLeft();
                    car.turnLeft();
                    car.setPosition(Math.max(0, Math.min(699, car.getX())), Math.max(0, Math.min(499, car.getY())));
                }
            }
            notifyObservers();
        }
    }

    public void addCar() {
        if (cars.size() < 10) { // Prevent more than 10 cars
            Car newCar = generateRandomCar();

            // Set random starting position (within the panel size)
            int randomX = new Random().nextInt(700); // Adjust to fit screen width
            int randomY = new Random().nextInt(500); // Adjust to fit screen height

            newCar.setPosition(randomX, randomY);

            cars.add(newCar);
        }
    }

    public void removeCar() {
        if (!cars.isEmpty()) { // Only remove if there's a car
            cars.remove(cars.size() - 1); // Remove the last added car
        }
    }

    // Calls the gas method for each car once
    @Override
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    @Override
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    @Override
    public void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    @Override
    public void stopAllCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    @Override
    public void raiseTrailer(int amount) {
        for (Car car : cars) {
            if (car instanceof Scania) {
                Scania scania = (Scania) car;
                scania.raiseTrailer(amount);
            }
        }
    }

    @Override
    public void lowerTrailer(int amount) {
        for (Car car : cars) {
            if (car instanceof Scania) {
                Scania scania = (Scania) car;
                scania.lowerTrailer(amount);
            }
        }
    }

    @Override
    public void setTurboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                Saab95 saab95 = (Saab95) car;
                saab95.setTurboOn();
            }
        }
    }

    @Override
    public void setTurboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                Saab95 saab95 = (Saab95) car;
                saab95.setTurboOff();
            }
        }
    }
}

