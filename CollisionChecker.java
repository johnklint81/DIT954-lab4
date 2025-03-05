public abstract class CollisionChecker {

    public static boolean collides(Entity entity1, Entity entity2) {
        var entity1X = entity1.getPos().getX();
        var entity1Y = entity1.getPos().getY();
        var entity1Width = entity1.getSize().getX();
        var entity1Height = entity1.getSize().getY();

        var entity2X = entity2.getPos().getX();
        var entity2Y = entity2.getPos().getY();
        var entity2Width = entity2.getSize().getX();
        var entity2Height = entity2.getSize().getY();

        // Check for collision using Axis-Aligned Bounding Box (AABB)
        boolean xOverlap = entity1X < entity2X + entity2Width && entity1X + entity1Width > entity2X;
        boolean yOverlap = entity1Y < entity2Y + entity2Height && entity1Y + entity1Height > entity2Y;

        return xOverlap && yOverlap;
    }

    public static boolean outside(Entity entity, Vec2 panelSize) {
        double x = entity.getPos().getX();
        double y = entity.getPos().getX();
        double entitySize = entity.getSize().getX();

        return x > (panelSize.getX() - entitySize) || x < 0;
    }
}
