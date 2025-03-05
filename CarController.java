import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    // The frame that represents this instance View of the MVC pattern
    public CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Volvo240 volvo240 = new Volvo240();
        volvo240.turnRight();
        volvo240.setPosition(0, 300);

        Saab95 saab95 = new Saab95();
        saab95.turnRight();
        saab95.setPosition(0, 100);

        Scania scania = new Scania();
        scania.turnRight();
        scania.setPosition(0, 200);

        cc.cars.add(volvo240);
        cc.cars.add(saab95);
        cc.cars.add(scania);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
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
                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                if (x >= 699 || x <= -50 || y >= 499 || y <= 0) {
                    car.turnLeft();
                    car.turnLeft();
                    car.setPosition(Math.max(0, Math.min(699, car.getX())), Math.max(0, Math.min(699, car.getY())));

                    frame.drawPanel.moveit(car ,x, y);
                }
            }
            for (Car car : cars) {
                if (car instanceof Volvo240 volvo240) {
                    double x = volvo240.getX();
                    double y = volvo240.getY();
                    if (x >= 250 && x <= 350 && y >= 250 && y <= 350){
                        volvo240.setPosition(300, volvo240.getY());
                    }
                }
            }
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

