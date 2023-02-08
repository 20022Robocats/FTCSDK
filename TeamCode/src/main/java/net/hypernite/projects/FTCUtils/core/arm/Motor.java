package net.hypernite.projects.FTCUtils.core.arm;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Motor {
    private final DcMotor MOTOR;// DcMotor It will interact With

    /**
     * Creates a new Motor Instance
     */
    public Motor(DcMotor motor) {
        this.MOTOR = motor;
    }

    /**
     * Sets the power of the current motor
     */
    public void setPower(double speed) {
        this.MOTOR.setPower(speed);
    }

    /**
     * moves the motor by the offset, in regard to last position
     * @param offset offset
     */
    public void move(int offset) {
        this.moveTo(this.getPos() + offset);
    }

    /**
     * Moves the motor to a constant position, in regard to position 0
     * @param number number to move by
     */
    public void moveTo(int number) {
        this.MOTOR.setTargetPosition(number);
    }

    /**
     * Gets the current motor position, where 0 is the origin
     * @return current Motor Position
     */
    public int getPos() {
        return this.MOTOR.getCurrentPosition();
    }
}