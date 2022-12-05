package net.hypernite.robocats;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

import net.hypernite.robocats.utils.SchemeLoader;
import net.hypernite.robocats.utils.CommonUtils;
import net.hypernite.robocats.opmode.OpMode;
import net.hypernite.robocats.utils.IScheme;

public class Loader<S extends IScheme> {
    public static Telemetry TELEMETRY;
    public static HardwareMap HMAP;
    public static Gamepad G1;
    public static Gamepad G2;
    private final S Scheme;

    private Loader(S Scheme) { this.Scheme = Scheme; }

    public static <S extends IScheme> Loader<S> load(Telemetry telemetry, HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2) {
        Loader.TELEMETRY = telemetry;
        Loader.HMAP = hardwareMap;
        Loader.G1 = gamepad1;
        Loader.G2 = gamepad2;
        OpMode OpMode = new OpMode();
        S Scheme = new SchemeLoader<S>(OpMode.DRIVE, OpMode.CLAW).loadScheme(Common.SchemeInUse);
        Loader.TELEMETRY.addLine("Scheme Loaded: " + Common.SchemeInUse);
        Loader.TELEMETRY.addLine(CommonUtils.getTeam());
        Loader.TELEMETRY.update();
        return new Loader<>(Scheme);
    }

    public void control() {
        this.Scheme.control();
    }
}
