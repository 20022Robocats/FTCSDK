package org.firstinspires.ftc.teamcode.mechanumdrive.util;

import com.qualcomm.robotcore.hardware.DcMotor;


public class Motor {
    private DcMotor MOTOR;
    private Gear GEAR;

    public Motor(DcMotor motor) {
        this.MOTOR = motor;
        this.GEAR = Gear.MED;
    }

    public void setTargetPosition(int num){
        this.MOTOR.setTargetPosition(num);
    }

    public void setPower(double speed) {
        this.MOTOR.setPower(speed * this.GEAR.speed());
    }

    public double gear() {
        return this.GEAR.speed();
    }

    public void shift_down() {
        if(this.GEAR.equals(Gear.FAST)){
            this.GEAR = Gear.MED;
        } else if(this.GEAR.equals(Gear.MED)){
            this.GEAR = Gear.SLOW;
        }
    }

    public void shift_up() {
        if(this.GEAR.equals(Gear.SLOW)){
            this.GEAR = Gear.MED;
        } else if(this.GEAR.equals(Gear.MED)){
            this.GEAR = Gear.FAST;
        }
    }
}