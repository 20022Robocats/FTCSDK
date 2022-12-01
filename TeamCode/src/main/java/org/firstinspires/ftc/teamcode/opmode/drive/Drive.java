package org.firstinspires.ftc.teamcode.opmode.drive;

import org.firstinspires.ftc.teamcode.opmode.drive.util.Motor;
import org.firstinspires.ftc.teamcode.MainClass;
import org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Locale;

public class Drive {
    public static final HardwareMap hardwareMap = MainClass.HMAP;
    public final Motor motor0;
    public final Motor motor1;
    public final Motor motor2;
    public final Motor motor3;
    public double FR = 0;// 1
    public double FL = 0;// 0
    public double BR = 0;// 2
    public double BL = 0;// 3

    /**
     * Crates a new Drive Instance
     */
    public Drive() {
        DcMotor fr = hardwareMap.get(DcMotor.class, Common.MotorPin.FR_NAME);
        DcMotor fl = hardwareMap.get(DcMotor.class, Common.MotorPin.FL_NAME);
        DcMotor br = hardwareMap.get(DcMotor.class, Common.MotorPin.BR_NAME);
        DcMotor bl = hardwareMap.get(DcMotor.class, Common.MotorPin.BL_NAME);
        motor0 = new Motor(fr);
        motor1 = new Motor(fl);
        motor2 = new Motor(br);
        motor3 = new Motor(bl);
    }

    /**
     * Updates the motors
     */
    public void update() {
        motor0.setPower(FR * Common.MotorPin.FR);
        motor1.setPower(FL * Common.MotorPin.FL);
        motor2.setPower(BR * Common.MotorPin.BR);
        motor3.setPower(BL * Common.MotorPin.BL);
    }

    /**
     * returns telemetry String
     */
    public String telemetry() {
        return String.format(Locale.ENGLISH,
            "   > Gear: %.1f\n   > FR: %.1f\n   > FL: %.1f\n   > BR: %.1f\n   > BL: %.1f",
            motor0.gear(),
            FR * Common.MotorPin.FR,
            FL * Common.MotorPin.FL,
            BR * Common.MotorPin.BR,
            BL * Common.MotorPin.BL
        );
    }

    /**
     * gear shifts up or down
     */
    public void shift(String way) {
        if(way.equals("up")) {
            motor0.shift_up();
            motor1.shift_up();
            motor2.shift_up();
            motor3.shift_up();
        } else if(way.equals("down")) {
            motor0.shift_down();
            motor1.shift_down();
            motor2.shift_down();
            motor3.shift_down();
        }
    }
}
