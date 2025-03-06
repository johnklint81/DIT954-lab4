package app;

import models.Entity;
import app.interfaces.EntityRepository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class InMemoryEntityRepository implements EntityRepository {
    ArrayList<Entity> entities = new ArrayList<>();
    int maxSize;

    public InMemoryEntityRepository(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void add(Entity entity) {
        if (entities.size() >= maxSize) {
            throw new IllegalStateException("entities.Entity repository is already full");
        }
        if (entities.contains(entity)) {
            throw new IllegalArgumentException("entities.Entity already in repository");
        }
        entities.add(entity);
    }

    @Override
    public <T extends Entity> Entity pop(Class<T> type) {

        var entity = entities.reversed().stream().filter(c -> type.isAssignableFrom(c.getClass())).findFirst();
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("Entity repository has no entities of type left");
        }
        entities.remove(entity.get());
        return entity.get();
    }

    @Override
    public @NotNull Iterator<Entity> iterator() {
        return entities.iterator();
    }
}
