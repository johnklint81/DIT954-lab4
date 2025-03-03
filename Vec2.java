public final class Vec2 {
    private double x;
    private double y;

    static Vec2 ZERO = new Vec2(0,0);

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPos(Vec2 pos) {
        x = pos.x;
        y = pos.y;
    }

    public double distanceTo(Vec2 other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }


    public void add(Vec2 other) {
        add(other.x, other.y);
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
