package net.hypernite.projects.pathfinder.entity;

import net.hypernite.projects.pathfinder.coordinates.*;
import net.hypernite.projects.pathfinder.interfaces.*;

public class HitBox
    <CoordinateType extends Coordinate>
    implements IGetter<CoordinateType>
{
    private final CoordinateType center;
    private final int length;
    private final int width;
    public <C extends Coordinate> HitBox(int length, int width, C center) {
        this.center = center.squash();
        this.length = length;
        this.width = width;
    }

    @Override
    public CoordinateType get() { return this.center; }
    public int getLength(){ return this.length; }
    public int getWidth(){ return this.width; }
    public Coordinate getTopRight() {
        return new StaticCoordinate(
            this.center.getX() + (length / 2),
            this.center.getZ() + (width / 2)
        );
    }
    public Coordinate getBottomLeft() {
        return new StaticCoordinate(
            this.center.getX() - (length / 2),
            this.center.getZ() - (width / 2)
        );
    }

    public static <C1 extends Coordinate, C2 extends Coordinate> boolean detectCollision(
        int length, int width, C1 coordinates, C2 cords
    ) {
        StaticCoordinate topRight = new StaticCoordinate(
            coordinates.getX() + (length / 2),
            coordinates.getZ() + (width / 2)
        );
        StaticCoordinate bottomLeft = new StaticCoordinate(
            coordinates.getX() - (length / 2),
            coordinates.getZ() - (width / 2)
        );
        int topX = topRight.getX();
        int topZ = topRight.getZ();
        int botX = bottomLeft.getX();
        int botZ = bottomLeft.getZ();
        return
            (topX >= cords.getX() & botX >= cords.getX()) ||
            (topX <= cords.getX() & botX <= cords.getX()) ||
            (topZ >= cords.getZ() & botZ >= cords.getZ()) ||
            (topZ <= cords.getZ() & botZ <= cords.getZ())
        ;
    }
    public <C extends Coordinate, C2 extends Coordinate> boolean willCollide(
        C coordinates, C2 cords
    ) {
        return HitBox.detectCollision(this.length, this.width, coordinates, cords);
    }
}
