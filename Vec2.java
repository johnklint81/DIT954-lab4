public final class Vec2 {
    private final double x;
    private final double y;

    static Vec2 ZERO = new Vec2(0,0);

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 setPos(double x, double y) {
        return new Vec2(x, y);
    }

    public Vec2 setPos(Vec2 pos) {
        return new Vec2(pos.x, pos.y);
    }

    public double distanceTo(Vec2 other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public Vec2 add(double x, double y) {
        return new Vec2(this.x + x, this.y +y);
    }

    public Vec2 add(Vec2 other) {
        return add(other.x, other.y);
    }

    public Vec2 copy() {
        return new Vec2(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
