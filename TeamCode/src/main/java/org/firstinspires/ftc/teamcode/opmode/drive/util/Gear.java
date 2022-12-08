package org.firstinspires.ftc.teamcode.opmode.drive.util;

public enum Gear {
    FAST(1.0),// Fastest Gear
    MED(0.75),// Medium Gear
    SLOW(0.5);// Slowest Gear

    private final double __speed;// saves gear speed multiplier

    /**
     * Creates a new gear instance
     */
    Gear(double speed){
        this.__speed = speed;
    }

    /**
     * Returns current gear
     */
    public double speed(){
        return this.__speed;
    }
}
