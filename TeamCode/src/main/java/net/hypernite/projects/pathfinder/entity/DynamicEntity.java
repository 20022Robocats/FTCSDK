package net.hypernite.projects.pathfinder.entity;

import net.hypernite.projects.pathfinder.coordinates.*;
import net.hypernite.projects.pathfinder.interfaces.*;

public abstract class DynamicEntity
    extends Entity<DynamicCoordinate, HitBox<DynamicCoordinate>>
    implements IMovable<DynamicCoordinate>
{
    private DynamicCoordinate cords;
    private final DynamicHitBox hitBox;
    public DynamicEntity(DynamicCoordinate dynamicCoordinate, DynamicHitBox hitBox) {
        super(dynamicCoordinate, hitBox);
        this.cords = dynamicCoordinate;
        this.hitBox = hitBox;
    }
    @Override
    public DynamicCoordinate get() { return this.cords; }
    @Override
    public void moveX(int amount) {
        this.onMoveX(amount);
        this.hitBox.moveX(amount);
        this.cords.moveX(amount);
    }
    @Override
    public void moveZ(int amount) {
        this.onMoveZ(amount);
        this.hitBox.moveZ(amount);
        this.cords.moveZ(amount);
    }
    @Override
    public int x() { return this.cords.getX(); }
    @Override
    public int z() { return this.cords.getZ(); }
    @Override
    public DynamicCoordinate update(DynamicCoordinate newValue) {
        DynamicCoordinate old = this.get();
        this.set(newValue);
        return old;
    }
    @Override
    public void set(DynamicCoordinate value) { this.cords = value; }
    public abstract void onMoveX(int amount);
    public abstract void onMoveZ(int amount);
}
