package org.firstinspires.ftc.teamcode.multipartclaw.util;

public enum Gear {
    FAST(1),
    MED(0.75),
    SLOW(0.5);
    private double __speed;
    Gear(double speed){
        this.__speed = speed;
    }
    public void setSpeed(double speed){
        this.__speed = speed;
    }
    public double speed(){
        return this.__speed;
    }
}
