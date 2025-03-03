public abstract class Entity implements TickObserver {
    private Vec2 pos, size;
    Entity(ModelFacade model, Vec2 pos, Vec2 size) {
        this.pos = pos;
        this.size = size;

        model.listenTick(this);
    }

    public abstract void tick();

    public Vec2 getPos() {
        return pos;
    }

    public Vec2 getSize() {
        return size;
    }

    public void setPos(Vec2 pos) {
        this.pos = pos;
    }
}
