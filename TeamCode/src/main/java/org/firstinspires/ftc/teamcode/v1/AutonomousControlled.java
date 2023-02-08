package org.firstinspires.ftc.teamcode.v1;

import com.qualcomm.robotcore.eventloop.opmode.*;
import net.hypernite.projects.FTCUtils.*;

@SuppressWarnings("all")
@Autonomous(name = Config.OpModeName + " - Autonomous")
public class AutonomousControlled extends AutoOp<Robot.Drive, Robot.Arm, Robot, Config> {
    @Override public void runOpMode() { this.run(this); }
    public AutonomousControlled() { super(Config.class); }

    private Robot robot;

    @Override
    public void onInit() { robot = loadRobot(); }

    @Override
    public void onRun() {
        telemetry.addLine("Hello World");
        telemetry.update();
    }

    @Override
    public void onCleanup() { robot.cleanup(); }
}
