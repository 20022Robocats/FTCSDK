package net.hypernite.projects.pathfinder.coordinates;

import net.hypernite.projects.pathfinder.interfaces.*;

public class StaticCoordinate
    extends Coordinate
    implements IGetter<Coordinate>, IMorphable<StaticCoordinate, Coordinate>
{
    public StaticCoordinate(int x, int z) {
        super(x, z);
    }
    public <T extends Coordinate> StaticCoordinate(T cords) { super(cords.getX(), cords.getZ()); }
    @Override
    public Coordinate get() {
        return new Coordinate(this.getX(), this.getZ());
    }
    @Override
    public StaticCoordinate morph(Coordinate coordinate) {
        return new StaticCoordinate(coordinate);
    }
    @Override
    public Coordinate morph() {
        return new Coordinate(this.getX(), this.getZ());
    }
    @Override
    public String toString() {
        return "StaticCoordinate (" + this.getX() + ", " + this.getZ() + ")";
    }
}
