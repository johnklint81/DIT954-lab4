package app.interfaces;

import models.Entity;

public interface EntityRepository extends Iterable<Entity> {
    void add(Entity entity);
    <T extends Entity> Entity pop(Class<T> type);
}
