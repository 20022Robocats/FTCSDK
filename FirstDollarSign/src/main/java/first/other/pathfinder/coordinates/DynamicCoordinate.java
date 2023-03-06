package first.other.pathfinder.coordinates;

import first.other.pathfinder.interfaces.*;

public class DynamicCoordinate
    extends Coordinate
    implements IProperty<Coordinate>, IMorphable<DynamicCoordinate,Coordinate>, IMovable<Coordinate>
{
    private int x;
    private int z;
    public DynamicCoordinate(int x, int z) { super(x, z);this.x = x;this.z = z; }
    public <T extends Coordinate> DynamicCoordinate(T cords) { super(cords.getX(), cords.getZ()); }
    @Override
    public int getX() { return this.x; }
    @Override
    public int getZ() { return this.z; }
    public void setX(int x) { this.x = x; }
    public void setZ(int z) { this.z = z; }
    @Override
    public Coordinate get() { return new Coordinate(this.getX(), this.getZ()); }
    @Override
    public Coordinate update(Coordinate newValue) {
        Coordinate old = this.get();
        this.set(newValue);
        return old;
    }
    @Override
    public void set(Coordinate value) { this.setX(value.getX()); this.setZ(value.getZ()); }
    @Override
    public DynamicCoordinate morph(Coordinate coordinate) {
        return new DynamicCoordinate(coordinate);
    }
    @Override
    public Coordinate morph() { return new Coordinate(this.getX(), this.getZ()); }
    @Override
    public String toString() { return "DynamicCoordinate ("+this.getX()+", "+this.getZ()+")"; }
    @Override
    public void moveX(int amount) { this.setX(this.x() + amount); }
    @Override
    public void moveZ(int amount) { this.setZ(this.z() + amount); }
    @Override
    public int x() { return this.getX(); }
    @Override
    public int z() { return this.getZ(); }
}
