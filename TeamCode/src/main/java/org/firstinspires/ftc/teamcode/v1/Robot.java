package org.firstinspires.ftc.teamcode.v1;

import com.qualcomm.robotcore.hardware.*;

public class Robot {
    public final DcMotor FR;
    public final DcMotor FL;
    public final DcMotor BR;
    public final DcMotor BL;
    public final DcMotor arm;
    public final Servo s2;// Left Servo
    public final Servo s3;// Right Servo
    public Robot(HardwareMap hardwareMap) {
        FR = hardwareMap.get(DcMotor.class, "motor0");
        FL = hardwareMap.get(DcMotor.class, "motor1");
        BR = hardwareMap.get(DcMotor.class, "motor2");
        BL = hardwareMap.get(DcMotor.class, "motor3");
        arm = hardwareMap.get(DcMotor.class, "arm0");
        s2 = hardwareMap.get(Servo.class, "servo2");
        s3 = hardwareMap.get(Servo.class, "servo3");
    }

    public void setPower(double speed) {
        this.FR.setPower(speed);
        this.FL.setPower(speed);
        this.BR.setPower(speed);
        this.BL.setPower(speed);
    }

    public int getYDefault() {
        return 0;
    }
}
