package net.hypernite.robocats.opmode.drive.util;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Motor {
    private final DcMotor MOTOR;// DcMotor It will interact With
    private Gear GEAR;// Current motor gear

    /**
     * Creates a new Motor Instance
     */
    public Motor(DcMotor motor) {
        this.MOTOR = motor;
        this.GEAR = Gear.MED;
    }

    /**
     * Sets the power of the current motor
     */
    public void setPower(double speed) {
        this.MOTOR.setPower(speed * this.GEAR.speed());
    }

    /**
     * Returns current gear
     */
    public double gear() {
        return this.GEAR.speed();
    }

    /**
     * Shifts the gear down
     */
    public void shift_down() {
        if(this.GEAR.equals(Gear.FAST)){
            this.GEAR = Gear.MED;
        } else if(this.GEAR.equals(Gear.MED)){
            this.GEAR = Gear.SLOW;
        }
    }

    /**
     * Shifts the gear up
     */
    public void shift_up() {
        if(this.GEAR.equals(Gear.SLOW)){
            this.GEAR = Gear.MED;
        } else if(this.GEAR.equals(Gear.MED)){
            this.GEAR = Gear.FAST;
        }
    }
}