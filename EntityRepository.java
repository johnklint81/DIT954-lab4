import java.util.Iterator;
import java.util.stream.Stream;

public interface EntityRepository extends Iterable<Entity> {
    void add(Entity entity);
    Entity pop();
}
