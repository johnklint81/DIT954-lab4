package mvc.interfaces;

import models.Entity;

public interface EntityRepository extends Iterable<Entity> {
    void add(Entity entity);
    Entity pop();
}
