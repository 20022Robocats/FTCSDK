package org.firstinspires.ftc.teamcode.v1;

import com.qualcomm.robotcore.eventloop.opmode.*;
import net.hypernite.projects.FTCUtils.*;

@SuppressWarnings("all")
@TeleOp(name = Config.OpModeName + " - TeleOp")
public class DriverControlled extends DriverOp<Robot.Drive, Robot.Arm, Robot, Config> {
    @Override public void runOpMode() { this.run(this); }
    public DriverControlled() { super(Config.class); }

    private Robot robot;

    @Override
    public void onInit() {
        robot = loadRobot();
    }

    @Override
    public void onRun() {
        telemetry.addLine("Drive:");
        telemetry.addLine(
            GamePadControl.controlDrive(robot.getDrive(), gamepad1)
        );
        telemetry.addLine("Claw:");
        telemetry.addLine(
            GamePadControl.controlArm(robot.getArm(), gamepad2)
        );
        telemetry.update();
    }

    @Override
    public void onCleanup() { robot.cleanup(); }
}
