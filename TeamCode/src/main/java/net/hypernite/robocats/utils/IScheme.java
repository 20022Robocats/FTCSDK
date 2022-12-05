package net.hypernite.robocats.utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import net.hypernite.robocats.opmode.drive.Drive;
import net.hypernite.robocats.opmode.arm.Claw;
import net.hypernite.robocats.Loader;

import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class IScheme {
    protected final Telemetry TELEMETRY = Loader.TELEMETRY;
    protected final Gamepad G1 = Loader.G1;
    protected final Gamepad G2 = Loader.G2;
    protected Drive DRIVE;
    protected Claw CLAW;

    public IScheme() {
        this.DRIVE = SchemeLoader.DRIVE;
        this.CLAW = SchemeLoader.CLAW;
    }

    /**
     * Something you have to implement yourself, called every tick for control
     */
    public abstract void control();
}
