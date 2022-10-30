package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.util.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Claw {
    public Motor motor0 = null;
    public Motor motor1 = null;
    public Motor motor2 = null;
    public Motor motor3 = null;
    public double ONE = 0;
    public double TWO = 0;
    public double THREE = 0;
    public double FOUR = 0;
    public Claw(DcMotor _1, DcMotor _2, DcMotor _3, DcMotor _4) {
        _1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor0 = new Motor(_1);
        motor1 = new Motor(_2);
        motor2 = new Motor(_3);
        motor3 = new Motor(_4);
    }


    public void run() {
        motor0.setPower(ONE);
        motor1.setPower(TWO);
        motor2.setPower(THREE);
        motor3.setPower(FOUR);
    }
}
