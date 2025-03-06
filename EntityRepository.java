public interface EntityRepository {
    public void add(Entity entity);
    public void remove(Entity entity);
    public Entity pop();
    public Entity[] list();
}
