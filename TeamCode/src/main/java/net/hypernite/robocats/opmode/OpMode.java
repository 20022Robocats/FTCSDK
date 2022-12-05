package net.hypernite.robocats.opmode;

import net.hypernite.robocats.opmode.drive.Drive;
import net.hypernite.robocats.opmode.arm.Claw;

public class OpMode {
    public final Drive DRIVE;// Drive
    public Claw CLAW;// Claw & Arm

    /**
     * Crates a new OpMode
     */
    public OpMode() {
        DRIVE = new Drive();
        try {
            CLAW = new Claw();
        } catch (IllegalArgumentException e) {
            CLAW = new Claw(false);
        }
    }
}
