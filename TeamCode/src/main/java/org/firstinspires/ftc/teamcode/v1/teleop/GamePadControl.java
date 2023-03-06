package org.firstinspires.ftc.teamcode.v1.teleop;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.v1.Robot;

@SuppressWarnings("all")
public class GamePadControl {
    private final int ydefault;
    private final Robot robot;
    public GamePadControl(
        Robot robot,
        int ydefault
    ) {
        this.ydefault = ydefault;
        this.robot = robot;
    }
    public void drive(Gamepad gamepad1) {
        robot.setPower(0);
        if(Math.abs(gamepad1.left_stick_y) > 0.1) {
            robot.setPower(gamepad1.left_stick_y);
        }
        if(Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
            robot.FR.setPower(gamepad1.left_stick_x);
            robot.FL.setPower(-gamepad1.left_stick_x);
            robot.BR.setPower(-gamepad1.left_stick_x);
            robot.BL.setPower(gamepad1.left_stick_x);
        }
        if(gamepad1.right_stick_x != 0) {
            robot.FR.setPower(gamepad1.right_stick_x);
            robot.FL.setPower(-gamepad1.right_stick_x);
            robot.BR.setPower(gamepad1.right_stick_x);
            robot.BL.setPower(-gamepad1.right_stick_x);
        }
    }
    public void arm(Gamepad gamepad2) {
        double moveDelta = Math.floor(gamepad2.right_stick_y * 100);
        if(Math.abs(moveDelta) > 10) {
            manipulateClaw(robot.arm.getCurrentPosition() + (int) moveDelta);
        } else if (gamepad2.a) {
            manipulateClaw(0);
        } else if (gamepad2.b) {
            manipulateClaw(1700);
        } else if (gamepad2.x) {
            manipulateClaw(2800);
        } else if (gamepad2.y) {
            manipulateClaw(3650);
        } else if (gamepad2.dpad_down) {
            manipulateClaw(330);
        } else if (gamepad2.dpad_right) {
            manipulateClaw(475);
        } else if (gamepad2.dpad_left) {
            manipulateClaw(600);
        } else if (gamepad2.dpad_up) {
            manipulateClaw(725);
        }
        if(gamepad2.left_trigger != 0) {
            robot.s2.setPosition(0.35);
            robot.s3.setPosition(0.45);
        } else if(gamepad2.right_trigger != 0) {
            robot.s2.setPosition(0.4);
            robot.s3.setPosition(0.35);
        }
    }

    private synchronized void manipulateClaw(int arm0target) {
        int armtarget = arm0target + ydefault;
        robot.arm.setTargetPosition(armtarget);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (robot.arm.getCurrentPosition() < armtarget) {
            robot.arm.setPower(1);
        } else if (robot.arm.getCurrentPosition() > armtarget) {
            robot.arm.setPower(-1);
        } else {
            ((DcMotorEx) robot.arm).setVelocity(0);
        }
    }
}
