package net.hypernite.projects.FTCUtils;

import net.hypernite.projects.FTCUtils.core.*;

public abstract class IRobot<D extends DriveType, A extends ArmType> {
    //////////////////////////////////////////////////
    ////               Constructors               ////
    //////////////////////////////////////////////////
    private final D DRIVE;
    private final A ARM;
    public IRobot(D drive, A arm) {
        this.DRIVE = drive;
        this.ARM = arm;
    }

    //////////////////////////////////////////////////
    ////               Public Methods             ////
    //////////////////////////////////////////////////

    /**
     * Unloads robot and cleans up the memory
     */
    public void cleanup() {

    }

    /**
     * Used for interacting with the drive motors.
     * @return current drive
     */
    public D getDrive() { return this.DRIVE; }

    /**
     * Used for interacting with the arm & claw motors
     * @return current drive
     */
    public A getArm() { return this.ARM; }

    ///////////////////////////////////////////////////
    ////                 Internals                 ////
    ///////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Robot{Drive: " + getDrive().toString() + ", Arm: " + getArm().toString() +"}";
    }
}
