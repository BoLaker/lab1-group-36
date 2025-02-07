import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    private Car Volvo240;
    private Scania Scania;
    private Car Saab95;

    @BeforeEach
    void setUp() {
        Volvo240 = new Volvo240();
        Volvo240.setSpeed(1);// Startspeed for movement tests
        Saab95 = new Saab95();
        Saab95.setSpeed(1);// Startspeed for movement tests
        Scania = new Scania();
        Scania.setSpeed(0);// Startspeed for movement tests
    }

    @Test
    void testStartPosition() {
        assertEquals(0, Volvo240.getX());
        assertEquals(0, Volvo240.getY());
        assertEquals("North", Volvo240.getDirection());
        assertEquals(0, Saab95.getX());
        assertEquals(0, Saab95.getY());
        assertEquals("North", Saab95.getDirection());
        assertEquals(0, Scania.getX());
        assertEquals(0, Scania.getY());
        assertEquals("North", Scania.getDirection());
    }

    @Test
    void testMoveNorth() {
        Volvo240.move();
        assertEquals(0, Volvo240.getX()); // X should remain the same
        assertEquals(1, Volvo240.getY()); // Y should increase by speed
    }

    @Test
    void testMoveEastAfterTurn() {
        Volvo240.turnRight(); // Turn to East
        Volvo240.move();
        assertEquals(1, Volvo240.getX()); // X should increase by speed
        assertEquals(0, Volvo240.getY()); // Y should remain the same
    }

    @Test
    void testMoveWestAfterTurn() {
        Volvo240.turnLeft(); // Turn to West
        Volvo240.move();
        assertEquals(-1, Volvo240.getX()); // X should decrease by speed
        assertEquals(0, Volvo240.getY()); // Y should remain the same
    }

    @Test
    void testMoveSouthAfterTurn() {
        Volvo240.turnRight(); // East
        Volvo240.turnRight(); // South
        Volvo240.move();
        assertEquals(0, Volvo240.getX());
        assertEquals(-1, Volvo240.getY()); // Y should decrease by speed
    }

    @Test
    void testFullRotation() {
        Volvo240.turnRight(); // East
        Volvo240.turnRight(); // South
        Volvo240.turnRight(); // West
        Volvo240.turnRight(); // North (back to start)
        assertEquals("North", Volvo240.getDirection());
    }

    @Test
    void testNoMovementWhenSpeedIsZero() {
        Volvo240.setSpeed(0);
        Volvo240.move();
        assertEquals(0, Volvo240.getX());
        assertEquals(0, Volvo240.getY());
    }

    @Test
    void testGasIncreasesSpeed(){
        Volvo240.gas(1);
        assertEquals(2, Volvo240.getCurrentSpeed());
    }

    @Test
    void testBrakeDecreasesSpeed(){
        Volvo240.brake(1);
        assertEquals(0, Volvo240.getCurrentSpeed());
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
        assertEquals(100, Volvo240.getCurrentSpeed()); // Should not exceed enginePower
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
        Scania.raiseTrailer(30);
        assertEquals(30, Scania.getTrailerTilt());
    }

    @Test
    void testRaisePlatformWhileMoving() {
        Scania.gas(1); // Start moving
        Scania.raiseTrailer(20);
        assertEquals(0, Scania.getTrailerTilt()); // Should not change
    }
}



