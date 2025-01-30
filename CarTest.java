import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    private Car Volvo240;

    @BeforeEach
    void setUp() {
        Volvo240 = new Volvo240();
        Volvo240.setSpeed(1); // Set start speed for movement tests
    }

    @Test
    void testStartPosition() {
        assertEquals(0, Volvo240.getX());
        assertEquals(0, Volvo240.getY());
        assertEquals("North", Volvo240.getDirection());
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
    void testMoveWestAfterMultipleTurns() {
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
}

