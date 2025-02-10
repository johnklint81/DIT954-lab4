import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ScaniaTest {

  private Scania scania;

  @BeforeEach
  void setUp() {
    scania = new Scania();
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
}
