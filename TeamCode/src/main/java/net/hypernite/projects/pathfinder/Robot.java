package net.hypernite.projects.pathfinder;

import net.hypernite.projects.pathfinder.coordinates.*;
import net.hypernite.projects.pathfinder.algorithms.*;
import net.hypernite.projects.pathfinder.entity.*;

@SuppressWarnings("SpellCheckingInspection")
public abstract class Robot
    extends DynamicEntity
{
    public final Algorithm algorithm;
    public final Plane2D plane;

    public Robot(DynamicCoordinate dynamicCoordinate, DynamicHitBox hitBox, Algorithm algorithm) {
        super(dynamicCoordinate, hitBox);
        this.algorithm = algorithm;
        this.plane = FieldFactory.newField();
    }
    @Override
    public abstract void onMoveX(int amount);
    @Override
    public abstract void onMoveZ(int amount);
    public <C extends Coordinate> void moveto(C cords) {
        this.moveto(new StaticCoordinate(cords));
    }
    private void moveto(StaticCoordinate cords) {
        Task<?> task = new Task<>(
            this,
            cords,
            this.plane
        );
        this.algorithm.run(task);
    }
    public void moveto(int x, int z) { this.moveto(new Coordinate(x,z)); }
}
