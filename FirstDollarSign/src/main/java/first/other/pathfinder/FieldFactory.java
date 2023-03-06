package first.other.pathfinder;

import first.other.pathfinder.coordinates.*;
import first.other.pathfinder.entity.*;

public class FieldFactory {
    private static final int[] OBSTACLE_CORDS = {4,8,12,16,20};
    public static Plane2D newField() {
        Plane2D plane = new Plane2D(24, 24);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                plane.addObstacle(
                    new StaticEntity(
                        new StaticCoordinate(OBSTACLE_CORDS[i], OBSTACLE_CORDS[j])
                    )
                );
            }
        }

        return plane;
    }
}
