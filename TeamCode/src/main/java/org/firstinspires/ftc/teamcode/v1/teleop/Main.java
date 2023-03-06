package org.firstinspires.ftc.teamcode.v1.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.v1.Robot;

@TeleOp(
    name = "V1 - TeleOp"
)
public class Main extends LinearOpMode {
    private GamePadControl gamePadControl;

    @Override
    public void runOpMode() {
        waitForStart();
        Robot robot = new Robot(hardwareMap);
        int yDefault = robot.getYDefault();
        gamePadControl = new GamePadControl(
            robot,
            yDefault
        );

        while(opModeIsActive()){
            control();
        }
    }

    private void control() {
        gamePadControl.drive(gamepad1);
        gamePadControl.arm(gamepad2);
    }
}
