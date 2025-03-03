import java.awt.*;

public class Scania extends Truck implements BedObserver {

  private final TruckBed truckBed;

  protected Scania(ModelFacade model)  {
    super(model, 2, 100, Color.WHITE, "Scania");
    truckBed = new TruckBed();
    model.listenBed(this);
  }

  @Override
  public boolean canMove() {
    return truckBed.getAngle() == 0;
  }

  @Override
  protected double speedFactor() {
    return getEnginePower() * 0.01;
  }

  public void raiseBed(double amount) {
    if (getCurrentSpeed() > 0) {
      throw new IllegalStateException("Cannot raise bed while moving");
    }

    truckBed.raise(amount);
  }

  public void lowerBed(double amount) {
    if (getCurrentSpeed() > 0) {
      throw new IllegalStateException("Cannot lower bed while moving");
    }

    truckBed.lower(amount);
  }

  public double getTruckBedAngle() {
    return truckBed.getAngle();
  }
}
