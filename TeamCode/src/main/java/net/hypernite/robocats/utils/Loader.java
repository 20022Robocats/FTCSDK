package net.hypernite.robocats.utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;
import org.firstinspires.ftc.teamcode.opmode.OpMode;

public class Loader<S extends IScheme> {
    public static Telemetry TELEMETRY;
    public static HardwareMap HMAP;
    public static Drive DRIVE;
    public static Gamepad G1;
    public static Gamepad G2;
    public static Claw CLAW;

    public Loader(Telemetry telemetry, HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2) {
        Loader.TELEMETRY = telemetry;
        Loader.HMAP = hardwareMap;
        Loader.G1 = gamepad1;
        Loader.G2 = gamepad2;
        OpMode opMode = new OpMode(Loader.HMAP);
        Loader.DRIVE = opMode.DRIVE;
        Loader.CLAW = opMode.CLAW;
    }

    public S load(String name) {
        SchemeLoader<S> loader = new SchemeLoader<>(Loader.DRIVE, Loader.CLAW);
        S Scheme = loader.loadScheme(name);
        Loader.TELEMETRY.addLine("Scheme Loaded: " + name);
        Loader.TELEMETRY.addLine(CommonUtils.getTeam());
        Loader.TELEMETRY.update();
        return Scheme;
    }
}
