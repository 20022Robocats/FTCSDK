package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import net.hypernite.robocats.utils.IScheme;
import net.hypernite.robocats.utils.Loader;

@SuppressWarnings("all")
@TeleOp(name = Common.OpModeName + " - TeleOp")
public class MainClass<S extends IScheme> extends LinearOpMode {

    /**
     * Entry point of the program,
     * No Modification Intended
     */
    @Override
    public void runOpMode() {
        Loader loader = new Loader(telemetry, hardwareMap, gamepad1, gamepad2);
        waitForStart();
        S Scheme = (S) loader.load("Mat");
        while(opModeIsActive()) {
            Scheme.control();
        }
    }
}