public interface EngineObserver {
    void setEngine(boolean newState);
    void gas(double amount);
    void brake(double amount);
}
