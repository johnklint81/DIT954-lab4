import java.util.Iterator;
import java.util.stream.Stream;

public interface EntityRepository extends Iterable<Entity> {
    void add(Entity entity);
    void remove(Entity entity);
    Entity pop();
}
