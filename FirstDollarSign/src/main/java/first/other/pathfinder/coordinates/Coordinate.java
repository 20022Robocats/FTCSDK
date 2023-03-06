package first.other.pathfinder.coordinates;

public class Coordinate {
    private final int __x__;
    private final int __z__;
    public Coordinate(int x, int z) {
        this.__x__ = x;
        this.__z__ = z;
    }

    protected int getOriginalX() { return this.__x__; }
    protected int getOriginalZ() { return this.__z__; }

    public int getX() { return this.__x__; }
    public int getZ() { return this.__z__; }

    @Override
    public String toString() {
        return "Coordinate (" + this.getX() + ", " + this.getZ() + ")";
    }
    @Override
    @SuppressWarnings("all")
    public boolean equals(Object o) {
        if(o==this)return true;
        if(!(o instanceof Coordinate))return false;
        Coordinate c = (Coordinate) o;
        return
            Integer.compare(
                this.getX(),
                c.getX()
            ) == 0
            &&
            Integer.compare(
                this.getZ(),
                c.getZ()
            ) == 0
        ;
    }
    @SuppressWarnings("unchecked")
    public <T extends Coordinate> T squash() { return (T) new Coordinate(this.getX(), this.getZ()); }
    public <T extends Coordinate> boolean eq(T o) {
        return this.equals(o);
    }
    public <T extends Coordinate> boolean neq(T o) {
        return !this.eq(o);
    }
}
