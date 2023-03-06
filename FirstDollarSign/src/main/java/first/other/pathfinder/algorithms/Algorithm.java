package first.other.pathfinder.algorithms;

import first.other.pathfinder.interfaces.*;
import first.other.pathfinder.*;

import java.util.function.Function;

public class Algorithm
    implements IGetter<Function<Task<?>, Algorithm.RunResult>>
{
    public static final RunResult RUN_OK = RunResult.OK;
    private final Function<Task<?>, RunResult> func;
    public final String name;
    public Algorithm(String name, Function<Task<?>, RunResult> func) {
        this.func = func;
        this.name = name;
    }

    @SuppressWarnings("all")
    public void run(Task<?> task) {
        this.func.apply(task);
    }

    @Override
    public Function<Task<?>, RunResult> get() { return this.func; }

    @SuppressWarnings("all")
    public static enum RunResult {
        OK(0),
        FAILED(1),
        GOAL_OUT_OF_BOUNDS(2),
        INTERRUPTED(3)
        ;
        private final int exit;
        RunResult(int exit) {
            this.exit = exit;
        }
        public boolean isOk() {
            return this.exit == 0;
        }
    }
}
