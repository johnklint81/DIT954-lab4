package app;

import models.entities.EntityFactory;
import app.interfaces.*;

import java.util.ArrayList;

public class ModelFacade {
    ArrayList<TickObserver> tickObservers = new ArrayList<>();
    ArrayList<EngineObserver> engineObservers = new ArrayList<>();
    ArrayList<TurboObserver> turboObservers = new ArrayList<>();
    ArrayList<BedObserver> bedObservers = new ArrayList<>();
    Vec2 worldSize;
    EntityFactory factory;
    EntityRepository repository;

    public ModelFacade(EntityRepository repository, Vec2 worldSize) {
        this.repository = repository;
        this.worldSize = worldSize;
        this.factory = new EntityFactory(this);
    }

    public EntityRepository getRepository() {
        return repository;
    }

    public EntityFactory getFactory() {
        return factory;
    }

    public Vec2 getWorldSize() {return worldSize;}

    public void listenTick(TickObserver observer) {
        tickObservers.add(observer);
    }

    public void listenEngine(EngineObserver observer) {
        engineObservers.add(observer);
    }

    public void listenTurbo(TurboObserver observer) {
        turboObservers.add(observer);
    }

    public void listenBed(BedObserver observer) {
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
}
