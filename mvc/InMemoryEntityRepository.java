package mvc;

import models.Entity;
import mvc.interfaces.EntityRepository;
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
    public Entity pop() {
        if (entities.isEmpty()) {
            throw new IllegalStateException("entities.Entity repository is empty");
        }
        return entities.removeLast();
    }

    @Override
    public @NotNull Iterator<Entity> iterator() {
        return entities.iterator();
    }
}
