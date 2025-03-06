import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {
  ModelFacade model = new ModelFacade(new InMemoryEntityRepository(5), Vec2.ZERO);
  private Scania scania;

  @BeforeEach
  void setUp() {
    scania = new Scania(model);
  }

  @Test
  void testRaiseBed() {
    scania.raiseBed(10);
    assertEquals(10, scania.getTruckBedAngle());
  }

  @Test
  void testLowerBed() {
    scania.raiseBed(10);
    scania.lowerBed(5);
    assertEquals(5, scania.getTruckBedAngle());
  }

  @Test
  void testRaiseBedWhileMoving() {
    scania.startEngine();
    assertThrows(IllegalStateException.class, () -> scania.raiseBed(10));
  }

  @Test
  void testLowerBedWhileMoving() {
    scania.startEngine();
    assertThrows(IllegalStateException.class, () -> scania.lowerBed(10));
  }

  @Test
  void testOverMoveBed() {
    scania.raiseBed(1000);
    assertEquals(70, scania.getTruckBedAngle());
  }

  @Test
  void testUnderMoveBed() {
    scania.lowerBed(1000);
    assertEquals(0, scania.getTruckBedAngle());
  }
  @Test
  void canMove() {
    scania.raiseBed(10);
    assertThrows(IllegalArgumentException.class, () -> scania.gas(10));
    scania.lowerBed(10);
    assertTrue(scania.canMove());
  }

  @Test
  void speedFactor() {
    assertEquals(100 * 0.01, scania.speedFactor());
  }
}
