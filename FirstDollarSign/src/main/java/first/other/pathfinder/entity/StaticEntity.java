package first.other.pathfinder.entity;

import first.other.pathfinder.coordinates.*;
import first.other.pathfinder.interfaces.*;

public class StaticEntity
    extends Entity<StaticCoordinate, StaticHitBox>
    implements IGetter<StaticCoordinate>
{
    public StaticEntity(StaticCoordinate cords) {
        super(cords, new StaticHitBox(1,1,cords));
    }
    public <C extends Coordinate> boolean willCollide(C cords) { return this.willCollide(cords); }
    @Override
    public StaticCoordinate get() { return this.getOriginalCoordinates(); }
}
