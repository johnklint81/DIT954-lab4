public abstract class CollisionChecker {

    public static boolean collides(Entity entity1, Entity entity2) {
        var entity1X = entity1.getPos().x();
        var entity1Y = entity1.getPos().y();
        var entity1Width = entity1.getSize().x();
        var entity1Height = entity1.getSize().y();

        var entity2X = entity2.getPos().x();
        var entity2Y = entity2.getPos().y();
        var entity2Width = entity2.getSize().x();
        var entity2Height = entity2.getSize().y();

        // Check for collision using Axis-Aligned Bounding Box (AABB)
        boolean xOverlap = entity1X < entity2X + entity2Width && entity1X + entity1Width > entity2X;
        boolean yOverlap = entity1Y < entity2Y + entity2Height && entity1Y + entity1Height > entity2Y;

        return xOverlap && yOverlap;
    }

    public static boolean outside(Entity entity, Vec2 panelSize) {
        double x = entity.getPos().x();
        double y = entity.getPos().x();
        double entitySize = entity.getSize().x();

        return x > (panelSize.x() - entitySize) || x < 0;
    }
}
