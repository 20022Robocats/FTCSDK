package first.ftc.algorithms;

import first.other.pathfinder.algorithms.*;
import first.other.pathfinder.*;

public class Algorithm1
    extends Algorithm
{
    private static RunResult taskHandler(Task<?> task) {
        int increaseX = task.goalCoordinates.getX() - task.entity.x();
        int increaseZ = task.goalCoordinates.getZ() - task.entity.z();

        task.entity.moveX(increaseX);
        task.entity.moveZ(increaseZ);

        return Algorithm.RUN_OK;
    }

    public Algorithm1() {
        super("Algol1",Algorithm1::taskHandler);
    }
}
