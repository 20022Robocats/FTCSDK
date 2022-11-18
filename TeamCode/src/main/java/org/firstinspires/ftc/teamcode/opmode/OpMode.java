package org.firstinspires.ftc.teamcode.opmode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;

public class OpMode {
    public HardwareMap hardwareMap;// Hardware Map
    public Telemetry telemetry;// telemetry
    public Drive DRIVE;// Drive
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
