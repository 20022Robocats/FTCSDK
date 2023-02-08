package org.firstinspires.ftc.teamcode.v1;

import com.qualcomm.robotcore.hardware.Gamepad;

@SuppressWarnings("all")
public class GamePadControl {
    public static String controlDrive(Robot.Drive drive, Gamepad gamepad1) {
        drive.FR.setPower(0);
        drive.FL.setPower(0);
        drive.BR.setPower(0);
        drive.BL.setPower(0);
        if(gamepad1.left_trigger != 0) {
            drive.FR.shift_down();
            drive.FL.shift_down();
            drive.BR.shift_down();
            drive.BL.shift_down();
        } else if(gamepad1.right_trigger != 0) {
            drive.FR.shift_up();
            drive.FL.shift_up();
            drive.BR.shift_up();
            drive.BL.shift_up();
        }
        if(Math.abs(gamepad1.left_stick_y) > 0.1) {
            drive.FR.setPower(gamepad1.left_stick_y);
            drive.FL.setPower(gamepad1.left_stick_y);
            drive.BR.setPower(gamepad1.left_stick_y);
            drive.BL.setPower(gamepad1.left_stick_y);
        }
        if(Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
            drive.FR.setPower(gamepad1.left_stick_x);
            drive.FL.setPower(-gamepad1.left_stick_x);
            drive.BR.setPower(-gamepad1.left_stick_x);
            drive.BL.setPower(gamepad1.left_stick_x);
        }
        if(gamepad1.right_stick_x != 0) {
            drive.FR.setPower(gamepad1.right_stick_x);
            drive.FL.setPower(-gamepad1.right_stick_x);
            drive.BR.setPower(gamepad1.right_stick_x);
            drive.BL.setPower(-gamepad1.right_stick_x);
        }

        return String.format(
            " > FR: %.0f\n > FL: %.0f\n > BR: %.0f\n > BL: %.0f",
            drive.FR.speed(),
            drive.FL.speed(),
            drive.BR.speed(),
            drive.BL.speed()
        );
    }
    public static String controlArm(Robot.Arm arm, Gamepad gamepad1) {
        //TODO@AtomicGamer9523
        return "";
    }
}
