package first.ftc.hardware;

import com.qualcomm.robotcore.hardware.*;

public class SimpleMotor {
    //////////////////////////////////////////////////
    ////               Constructors               ////
    //////////////////////////////////////////////////
    /**
     * Internal {@link DcMotor} used for interaction
     */
    public final DcMotor MOTOR;
    /**
     * Creates a new Motor Instance
     */
    public SimpleMotor(DcMotor motor) {
        this.MOTOR = motor;
    }
    //////////////////////////////////////////////////
    ////               Public Methods             ////
    //////////////////////////////////////////////////
    /**
     * Sets the power of the current motor
     */
    public void setPower(double speed) {
        this.MOTOR.setPower(speed);
    }
    /**
     * Returns current speed
     */
    public double speed() {
        return this.MOTOR.getPower();
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