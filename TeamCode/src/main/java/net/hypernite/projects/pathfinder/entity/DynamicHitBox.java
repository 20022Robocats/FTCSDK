package net.hypernite.projects.pathfinder.entity;

import net.hypernite.projects.pathfinder.coordinates.*;
import net.hypernite.projects.pathfinder.interfaces.*;

public class DynamicHitBox
    extends HitBox<DynamicCoordinate>
    implements IProperty<DynamicCoordinate>, IMovable<DynamicCoordinate>
{
    private final DynamicCoordinate center;
    public <C extends Coordinate> DynamicHitBox(int length, int width, C center) {
        super(length, width, center);
        this.center = new DynamicCoordinate(center);
    }

    @Override
    public DynamicCoordinate get() { return this.center; }
    @Override
    public DynamicCoordinate update(DynamicCoordinate newValue) {
        return (DynamicCoordinate) this.center.update(newValue);
    }
    @Override
    public void set(DynamicCoordinate value) { this.center.set(value); }
    @Override
    public void moveX(int amount) { this.center.moveX(amount); }
    @Override
    public void moveZ(int amount) { this.center.moveZ(amount); }
    @Override
    public int x() { return this.center.getX(); }
    @Override
    public int z() { return this.center.getZ(); }
}
