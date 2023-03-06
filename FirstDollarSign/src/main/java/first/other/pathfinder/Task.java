package first.other.pathfinder;

import first.other.pathfinder.coordinates.*;
import first.other.pathfinder.entity.*;

public class Task
    <E extends DynamicEntity>
{
    public final StaticCoordinate goalCoordinates;
    public final Plane2D plane;
    public E entity;
    public Task(E entity, StaticCoordinate goalCoordinates, Plane2D plane) {
        this.goalCoordinates = goalCoordinates;
        this.entity = entity;
        this.plane = plane;
    }
}
