package mvc;

import entities.Entity;

public interface EntityRepository extends Iterable<Entity> {
    void add(Entity entity);
    Entity pop();
}
