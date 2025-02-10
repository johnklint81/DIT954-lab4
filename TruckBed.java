public class TruckBed {
  private double angle;

  public TruckBed() {
    // The bed is initially raised, so the angle is 0.
    this.angle = 0;
  }

  public void raise(double amount) {
    setAngle(angle - amount);
  }

  public void lower(double amount) {
    setAngle(angle + amount);
  }

  public double getAngle() {
    return angle;
  }
  
  private void setAngle(double angle) {
    this.angle = Math.max(Math.min(angle, 70), 0);
  }
}
