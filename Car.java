import java.awt.*;

public abstract class Car extends Vehicle{
    public Car(int Doors, double power, Color paint, String model) {
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
}
