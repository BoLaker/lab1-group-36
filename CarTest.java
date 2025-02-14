import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

public class CarTest {
    private Car Volvo240;
    private Scania Scania;
    private Car Saab95;
    private CarTransport CarTransport;
    private Volvo240 v1;
    private Volvo240 v2;
    private Volvo240 v3;
    private CarWorkshop<Volvo240> volvoWorkshop;
    private CarTransport transport;


    
    @BeforeEach
    void setUp() {
    Volvo240 = new Volvo240();
    Saab95 = new Saab95();
    Scania = new Scania();
    v1 = new Volvo240();
    v2 = new Volvo240();
    v3 = new Volvo240();

    CarTransport = new CarTransport();
    transport = new CarTransport();
    volvoWorkshop = new CarWorkshop<>(1);

   
    Volvo240.startEngine(); 
    Volvo240.gas(1); 
    Saab95.startEngine();
    Saab95.gas(1);
    Scania.startEngine();
    
}


    @Test
    void testStartPosition() {
        assertEquals(0, Volvo240.getX());
        assertEquals(0, Volvo240.getY());
        assertEquals(Car.Direction.North, Volvo240.getDirection());
        assertEquals(0, Saab95.getX());
        assertEquals(0, Saab95.getY());
        assertEquals(Car.Direction.North, Saab95.getDirection());
        assertEquals(0, Scania.getX());
        assertEquals(0, Scania.getY());
        assertEquals(Car.Direction.North, Scania.getDirection());
    }

    @Test
    void testMoveNorth() {
        Volvo240.startEngine();
        Volvo240.gas(1);
        Volvo240.move();
        assertEquals(0, Volvo240.getX()); // X should remain the same
        assertTrue( Volvo240.getY() > 0); // Y should increase by speed
    }

    @Test
    void testMoveEastAfterTurn() {
        Volvo240.startEngine();
        Volvo240.gas(1);
        Volvo240.turnRight(); // Turn to East
        Volvo240.move();
        assertTrue( Volvo240.getX()>0); // X should increase by speed
        assertEquals(0, Volvo240.getY()); // Y should remain the same
    }

    @Test
    void testMoveWestAfterTurn() {
        Volvo240.startEngine();
        Volvo240.gas(1);
        Volvo240.turnLeft(); // Turn to West
        Volvo240.move();
        assertTrue( Volvo240.getX()<0); // X should decrease by speed
        assertEquals(0, Volvo240.getY()); // Y should remain the same
    }

    @Test
    void testMoveSouthAfterTurn() {
        Volvo240.startEngine();
        Volvo240.gas(1);
        Volvo240.turnRight(); // East
        Volvo240.turnRight(); // South
        Volvo240.move();
        assertEquals(0, Volvo240.getX());
        assertTrue(Volvo240.getY()<0); // Y should decrease by speed
    }

    @Test
    void testFullRotation() {
        Volvo240.turnRight(); // East
        Volvo240.turnRight(); // South
        Volvo240.turnRight(); // West
        Volvo240.turnRight(); // North (back to start)
        assertEquals(Car.Direction.North, Volvo240.getDirection());
    }

    @Test
    void testNoMovementWhenSpeedIsZero() {
        Volvo240.startEngine();
        Volvo240.brake(1);
        Volvo240.move();
        assertEquals(0, Volvo240.getX());
        assertEquals(0, Volvo240.getY());
    }
    

    @Test
    void testGasIncreasesSpeed(){
        double initialSpeed = Volvo240.getCurrentSpeed();
        Volvo240.gas(1);
        assertTrue(initialSpeed < Volvo240.getCurrentSpeed());
    }

    @Test
    void testBrakeDecreasesSpeed(){
        double initialSpeed = Volvo240.getCurrentSpeed();
        Volvo240.brake(1);
        assertTrue(initialSpeed > Volvo240.getCurrentSpeed());
    }

    @Test
    void testBrakeNotUnderZero(){
        Volvo240.brake(1);
        Volvo240.brake(1);
        assertEquals(0, Volvo240.getCurrentSpeed());
    }

    @Test
    void testSpeedNotExceedEnginePower(){
        for (int i = 0; i < 100; i++) { // Gasses more than enginepower
            Volvo240.gas(1);
    }
        assertEquals(Volvo240.getEnginePower(), Volvo240.getCurrentSpeed()); // Should not exceed enginePower
    }

    @Test
    void testGasAndBrakeBounds() {
        assertThrows(IllegalArgumentException.class, () -> Volvo240.gas(-0.5));
        assertThrows(IllegalArgumentException.class, () -> Volvo240.gas(1.5));
        assertThrows(IllegalArgumentException.class, () -> Volvo240.brake(-0.5));
        assertThrows(IllegalArgumentException.class, () -> Volvo240.brake(1.5));
    }

    // Scania Test

    @Test
    void testRaisePlatform() {
        Scania.stopEngine();
        Scania.raiseTrailer(30);
        assertEquals(30, Scania.getTrailerTilt());
    }

    @Test
    void testRaisePlatformWhileMoving() { 
        
        Scania.gas(1); // Start moving
        Scania.raiseTrailer(20);
        assertEquals(0, Scania.getTrailerTilt()); // Should not change
    }

    //CartWorkshop test

    @Test
    void testLoadCarsWorkshop(){
        volvoWorkshop.loadCar(v1);
        assertEquals(1, volvoWorkshop.getcars().size());
        assertThrows(IllegalArgumentException.class, () -> volvoWorkshop.loadCar(v2));
        volvoWorkshop.unloadCar(v1);
        assertThrows(IllegalArgumentException.class, () -> volvoWorkshop.unloadCar(v2));
    }

    //CarTransport tester 
    @Test
        void testCarTransportRampCannotLowerWhileMoving() {
            CarTransport.gas(1);
            CarTransport.lowerRamp();
            assertEquals(false, CarTransport.getRampStatus());
        }
    
        @Test
        void testLoadCar() {
            transport.lowerRamp(); 
            transport.loadCar(Saab95);
            
            assertThrows(IllegalArgumentException.class, () -> transport.loadCar(CarTransport));
            assertEquals(1, transport.getCars().size(), "Car should be loaded onto CarTransport");
        }
    
        @Test
        void testCarTransportUnloadCar() {
            CarTransport.lowerRamp();
            CarTransport.loadCar(Saab95);
            Car unloadedCar = CarTransport.unloadCar();
            assertEquals(Saab95, unloadedCar);
        }

}



