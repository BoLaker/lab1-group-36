/*Eftersom klasserna Saaab95 och Volvo240 delar många egenskaper och metoder är implementationsarv att föredra för att minska dupplicering av kod. */

import java.awt.*;
public class Car implements Movable {

    public int nrDoors; // Number of doors on the car
    public double enginePower; // Engine power of the car
    public double currentSpeed; // The current speed of the car
    public Color color; // Color of the car
    public String modelName; // The car model name
    private double x, y; // Bilens position
    private String direction; // Riktning: "North", "South", "East", "West"

    public Car(int Doors, double power, Color paint, String model) {
        nrDoors = Doors;
        enginePower = power;
        color = paint;
        modelName = model;
        stopEngine();

        this.x = 0;
        this.y = 0;
        this.currentSpeed = 0;
        this.direction = "North"; // StartDirection
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public double speedFactor() {
        return enginePower * 0.01;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public void setSpeed(double speed) {
        this.currentSpeed = speed;
    }

    public void gas(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException("Can only increase gas by 1");
        }
        currentSpeed = Math.min(currentSpeed + amount, enginePower);
    }

    public void brake(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException("Can only decrease brake by 1");
        }
        currentSpeed = Math.max(currentSpeed - amount, 0);
    }

    @Override
    public void move() {
        switch (direction) {
            case "North":
                y += currentSpeed;
                break;
            case "South":
                y -= currentSpeed;
                break;
            case "East":
                x += currentSpeed;
                break;
            case "West":
                x -= currentSpeed;
                break;
        }
    }

    @Override
    public void turnLeft() {
        switch (direction) {
            case "North":
                direction = "West";
                break;
            case "West":
                direction = "South";
                break;
            case "South":
                direction = "East";
                break;
            case "East":
                direction = "North";
                break;


        }
    }
    @Override
    public void turnRight() {
        switch (direction) {
            case "North":
                direction = "East";
                break;
            case "East":
                direction = "South";
                break;
            case "South":
                direction = "West";
                break;
            case "West":
                direction = "North";
                break;
        }
    }
}