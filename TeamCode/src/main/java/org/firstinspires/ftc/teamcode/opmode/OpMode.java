package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;

public class OpMode {
    public final Drive DRIVE;// Drive
    public Claw CLAW;// Claw & Arm

    /**
     * Crates a new OpMode
     */
    public OpMode(HardwareMap hardwareMap) {
        DRIVE = new Drive(hardwareMap);
        try {
            CLAW = new Claw(hardwareMap);
        } catch (IllegalArgumentException e) {
            CLAW = new Claw(false);
        }
    }
}
