package net.hypernite.robocats.utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Gamepad;

import net.hypernite.robocats.opmode.drive.Drive;
import net.hypernite.robocats.opmode.arm.Claw;
import net.hypernite.robocats.Loader;



public abstract class IScheme {
    protected final Telemetry TELEMETRY = Loader.TELEMETRY;
    protected final Drive DRIVE = SchemeLoader.DRIVE;
    protected final Claw CLAW = SchemeLoader.CLAW;
    protected final Gamepad G1 = Loader.G1;
    protected final Gamepad G2 = Loader.G2;

    /**
     * Something you have to implement yourself, called every tick for control
     */
    public abstract void control();
}
