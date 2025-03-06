public record Vec2(double x, double y) {
    static Vec2 ZERO = new Vec2(0, 0);

    public double distanceTo(Vec2 other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public Vec2 add(double x, double y) {
        return new Vec2(this.x + x, this.y + y);
    }

    public Vec2 add(Vec2 other) {
        return add(other.x, other.y);
    }

    public Vec2 copy() {
        return new Vec2(x, y);
    }
}
