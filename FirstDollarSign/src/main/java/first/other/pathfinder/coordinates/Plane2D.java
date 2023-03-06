package first.other.pathfinder.coordinates;

import first.other.pathfinder.entity.*;

import java.util.ArrayList;

public class Plane2D {
    private final int length;
    private final int width;
    private final ArrayList<StaticEntity> obstacles;
    public DynamicEntity robot;
    public Plane2D(int length, int width) {
        this.obstacles = new ArrayList<>(25);
        this.length = length;
        this.width = width;
    }
    public void addObstacle(StaticEntity obstacle) {
        if(!isInside(obstacle.get())) return;
        this.obstacles.add(obstacle);
    }
    public boolean willCollide(DynamicEntity entity) {
        ArrayList<StaticEntity> obstacles = getObstacles();
        for(StaticEntity obstacle : obstacles) {
            if(entity.willCollide(obstacle)) return true;
        }
        return false;
    }
    public ArrayList<StaticEntity> getObstacles() { return this.obstacles; }
    public <C extends Coordinate> boolean isInside(C cords) {
        StaticCoordinate selfCenter = new StaticCoordinate(this.length / 2, this.width / 2);
        return !HitBox.detectCollision(this.length, this.width, selfCenter, cords);
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("2DPlane: (");
        str.append(length);
        str.append(" by ");
        str.append(width);
        str.append("), obstacles: {");
        for(StaticEntity entity : this.obstacles) {
            str.append("(");
            str.append(entity.get().getX());
            str.append(",");
            str.append(entity.get().getZ());
            str.append(")");
        }
        str.append("}");
        return str.toString();
    }
}
