package org.firstinspires.ftc.teamcode.v2.teleop;

import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.v2.*;

import first.ftc.$;

@TeleOp(
    name = Config.OpModeName + " - TeleOp"
)
public class Main extends $.Op {
    public void run() {
        Robot robot = $.load(this, Config.class);
        $.run(u->{
            u.waitForStart();

            int yDefault = robot.getArm().motor0.getPos();

            GamePadControl control = new GamePadControl(
                telemetry,
                robot,
                yDefault
            );

            while(u.opModeIsActive()){
                control.drive(gamepad1);
                control.arm(gamepad2, this.getRuntime());
                control.telemetry();
            }
        });
    }
}
