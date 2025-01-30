/*Eftersom klasserna Saaab95 och Volvo240 delar många egenskaper och metoder är implementationsarv att föredra för att minska dupplicering av kod. */

import java.awt.*;
public class Car implements Movable {
    public static Double x;
    public static Double y; //bil position
    public String direction; // riktning, North, South, East, West

    public int nrDoors; // Number of doors on the car
    public double enginePower; // Engine power of the car
    public double currentSpeed; // The current speed of the car
    public Color color; // Color of the car
    public String modelName; // The car model name

    
    public Car(int Doors,double power, Color paint, String model){
        nrDoors=Doors;
        enginePower=power;
        color = paint;
        modelName = model;
        stopEngine();

        this.x = (double) 0.0;
        this.y = (double) 0.0;
        this.currentSpeed = 0;
        this.direction = "North"; //basriktning
    }


    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public static double getY(){
        return y;
    }
    public static double getX(){
        return x;
    }

    public String getDirection(){
        return direction;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }

    public void stopEngine(){
	    currentSpeed = 0;
    }
    public double speedFactor(){
        return enginePower*0.01;
    }
    public void setSpeed(double speed){
        this.currentSpeed = speed;
    }



    @Override
    public void move(){
        switch (direction){
            case "North":
                y += currentSpeed;
                break;
            case "East":
                x += currentSpeed;
                break;
            case "South":
                y -= currentSpeed;
                break;
            case "West":
                x -= currentSpeed;
                break;
        }
    }

    @Override
    public void turnLeft() {
        switch (direction){
            case "North":
                direction = "West";
                break;
            case "East":
                direction = "North";
                break;
            case "South":
                direction = "East";
                break;
            case "West":
                direction = "South";
                break;
        }
    }

    @Override
    public void turnRight() {
        switch (direction){
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


