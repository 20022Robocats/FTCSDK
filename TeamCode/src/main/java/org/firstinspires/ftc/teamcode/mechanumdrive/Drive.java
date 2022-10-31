package org.firstinspires.ftc.teamcode.mechanumdrive;

import org.firstinspires.ftc.teamcode.mechanumdrive.util.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Drive {
    public Motor motor0 = null;
    public Motor motor1 = null;
    public Motor motor2 = null;
    public Motor motor3 = null;
    public double FR = 0;
    public double FL = 0;
    public double BR = 0;
    public double BL = 0;
    public Drive(DcMotor fr, DcMotor fl, DcMotor br, DcMotor bl) {
        motor0 = new Motor(fr);
        motor1 = new Motor(fl);
        motor2 = new Motor(br);
        motor3 = new Motor(bl);
    }

    public double gear() {
        return motor0.gear();
    }

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

    public void run() {
        motor0.setPower(FR);
        motor1.setPower(FL);
        motor2.setPower(BR);
        motor3.setPower(BL);
    }
}