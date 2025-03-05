import java.util.ArrayList;

public class ModelFacade implements EntityRepository {
    ArrayList<Entity> entityRepository = new ArrayList<>();
    ArrayList<TickObserver> tickObservers = new ArrayList<>();
    ArrayList<EngineObserver> engineObservers = new ArrayList<>();
    ArrayList<TurboObserver> turboObservers = new ArrayList<>();
    ArrayList<BedObserver> bedObservers = new ArrayList<>();
    Vec2 worldSize;

    ModelFacade(Vec2 worldSize) {
        this.worldSize = worldSize;
    }

    Vec2 getWorldSize() {return worldSize;}

    void listenTick(TickObserver observer) {
        tickObservers.add(observer);
    }

    void listenEngine(EngineObserver observer) {
        engineObservers.add(observer);
    }

    void listenTurbo(TurboObserver observer) {
        turboObservers.add(observer);
    }

    void listenBed(BedObserver observer) {
        bedObservers.add(observer);
    }

    void tick() {
        tickObservers.forEach(TickObserver::tick);
    }

    void setEngines(boolean newState) {
        engineObservers.forEach(e -> e.setEngine(newState));
    }

    void gas(double amount) {
        engineObservers.forEach(e -> e.gas(amount));
    }

    void brake(double amount) {
        engineObservers.forEach(e -> e.brake(amount));
    }

    void lowerBeds(double amount) {
        bedObservers.forEach(e -> e.lowerBed(amount));
    }

    void raiseBeds(double amount) {
        bedObservers.forEach(e -> e.raiseBed(amount));
    }

    void setTurbos(boolean newState) {
        turboObservers.forEach(e -> e.setTurbo(newState));
    }

    public void add(Entity entity) {entityRepository.add(entity); }

    public void remove(Entity entity) {entityRepository.remove(entity); }

    public Entity[] list() {
        int size = this.entityRepository.size();
        Entity[] entityArray = new Entity[size];
        for (int i = 0; i < size; i++) {
            entityArray[i] = this.entityRepository.get(i);
        }
        return entityArray;
    }
}
