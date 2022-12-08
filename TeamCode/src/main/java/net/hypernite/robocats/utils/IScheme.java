package net.hypernite.robocats.utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;

public abstract class IScheme {
    protected final Telemetry TELEMETRY;
    protected final Drive DRIVE;
    protected final Gamepad G1;
    protected final Gamepad G2;
    protected final Claw CLAW;

    public IScheme(){
        this.TELEMETRY = Loader.TELEMETRY;
        this.DRIVE = Loader.DRIVE;
        this.CLAW = Loader.CLAW;
        this.G1 = Loader.G1;
        this.G2 = Loader.G2;
    }

    /**
     * Something you have to implement yourself, called every tick for control
     */
    public abstract void control();
}
