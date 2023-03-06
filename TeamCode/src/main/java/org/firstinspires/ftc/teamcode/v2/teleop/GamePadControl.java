package org.firstinspires.ftc.teamcode.v2.teleop;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.v2.*;

import com.qualcomm.robotcore.hardware.*;

@SuppressWarnings("all")
public class GamePadControl {
    private final Telemetry telemetry;
    private final int ydefault;
    private final Robot robot;
    public GamePadControl(
        Telemetry telemetry,
        Robot robot,
        int ydefault
    ) {
        this.telemetry = telemetry;
        this.ydefault = ydefault;
        this.robot = robot;
    }
    public void drive(Gamepad gamepad1) {
        robot.getDrive().setPower(0);
        if(gamepad1.left_trigger != 0) {
            robot.getDrive().shift_down();
        } else if(gamepad1.right_trigger != 0) {
            robot.getDrive().shift_up();
        }
        if(Math.abs(gamepad1.left_stick_y) > 0.1) {
            robot.getDrive().setPower(gamepad1.left_stick_y);
        }
        if(Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
            robot.getDrive().FR.setPower(gamepad1.left_stick_x);
            robot.getDrive().FL.setPower(-gamepad1.left_stick_x);
            robot.getDrive().BR.setPower(-gamepad1.left_stick_x);
            robot.getDrive().BL.setPower(gamepad1.left_stick_x);
        }
        if(gamepad1.right_stick_x != 0) {
            robot.getDrive().FR.setPower(gamepad1.right_stick_x);
            robot.getDrive().FL.setPower(-gamepad1.right_stick_x);
            robot.getDrive().BR.setPower(gamepad1.right_stick_x);
            robot.getDrive().BL.setPower(-gamepad1.right_stick_x);
        }
    }
    public void arm(Gamepad gamepad2, double runtime) {
        double moveDelta = Math.floor(gamepad2.right_stick_y * 100);
        telemetry.addData("moveDelta", moveDelta);
        if(Math.abs(moveDelta) > 10) {
            manipulateClaw(robot.getArm().motor0.getPos() + (int) moveDelta);
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
            robot.getArm().closeClaw();
        } else if(gamepad2.right_trigger != 0) {
            robot.getArm().openClaw();
        }
    }

    public void telemetry() {
        telemetry.addData("FR", robot.getDrive().FR.speed());
        telemetry.addData("FL", robot.getDrive().FL.speed());
        telemetry.addData("BR", robot.getDrive().BR.speed());
        telemetry.addData("BL", robot.getDrive().BL.speed());
        telemetry.addData("ARM", robot.getArm().motor0.speed());
        telemetry.update();
    }

    private synchronized void manipulateClaw(int arm0target) {
        int armtarget = arm0target + ydefault;
        robot.getArm().motor0.moveTo(armtarget);
        robot.getArm().motor0.MOTOR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (robot.getArm().motor0.getPos() < armtarget) {
            robot.getArm().motor0.setPower(1);
        } else if (robot.getArm().motor0.getPos() > armtarget) {
            robot.getArm().motor0.setPower(-1);
        } else {
            ((DcMotorEx) robot.getArm().motor0.MOTOR).setVelocity(0);
        }
    }
}
