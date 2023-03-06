package first.other.pathfinder.entity;

import first.other.pathfinder.coordinates.*;
import first.other.pathfinder.interfaces.*;

@SuppressWarnings("all")
public class Entity
    <Cords extends Coordinate, HB extends HitBox<Cords>>
    implements IGetter<Cords>
{
    private final Cords coordinates;
    private final HB hitBox;
    public Entity(Cords cords, HB hitBox) {
        this.coordinates = cords;
        this.hitBox = hitBox;
    }
    public Cords getOriginalCoordinates() {
        return coordinates;
    }
    public <C extends Coordinate> boolean willCollide(C cords) {
        return this.hitBox.willCollide(this.coordinates, cords);
    }
    public HB getHitBox() { return this.hitBox; }
    @Override
    public Cords get() { return this.coordinates; }
    public <E extends Entity<?,?>> boolean willCollide(E entity) {
//        entity.getHitBox().
//        HitBox.detectCollision(this.hitBox.getLength(), this.hitBox.getWidth(), this.coordinates, entity.get());

        return true;
    }

}
