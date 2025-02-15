/*Eftersom klasserna Saaab95 och Volvo240 delar många egenskaper och metoder är implementationsarv att föredra för att minska dupplicering av kod. */

import java.awt.*;
public abstract class Car implements Movable {
    
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double x, y; // Bilens position
    public enum Direction{
        North,South,West,East
    }
    private Direction direction;

    Car(int Doors, double power, Color paint, String model) {
        nrDoors = Doors;
        enginePower = power;
        color = paint;
        modelName = model;
        stopEngine();

        this.x = 0;
        this.y = 0;
        this.currentSpeed = 0;
        this.direction = Direction.North; // StartDirection
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

    public abstract double speedFactor();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void gas(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException("Can only increase gas by 1");
        }
       incrementSpeed(amount);
    }

    public void brake(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException("Can only decrease brake by 1");
        }
        decrementSpeed(amount);
    }
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    @Override
    public void move() {
        switch (direction) {
            case Direction.North:
                y += currentSpeed;
                break;
            case Direction.South:
                y -= currentSpeed;
                break;
            case Direction.East:
                x += currentSpeed;
                break;
            case Direction.West:
                x -= currentSpeed;
                break;
        }
    }

    @Override
    public void turnLeft() {
        switch (direction) {
            case Direction.North:
                direction = Direction.West;
                break;
            case Direction.West:
                direction = Direction.South;
                break;
            case Direction.South:
                direction = Direction.East;
                break;
            case Direction.East:
                direction = Direction.North;
                break;


        }
    }
    @Override
    public void turnRight() {
        switch (direction) {
            case Direction.North:
                direction = Direction.East;
                break;
            case Direction.East:
                direction = Direction.South;
                break;
            case Direction.South:
                direction = Direction.West;
                break;
            case Direction.West:
                direction = Direction.North;
                break;
        }
    }
    public static void main(String[] args){
     
}
}
