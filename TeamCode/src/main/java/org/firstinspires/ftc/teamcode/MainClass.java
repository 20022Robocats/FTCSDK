package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import net.hypernite.robocats.Loader;

@SuppressWarnings("all")
@TeleOp(name = Common.OpModeName + " - TeleOp")
public class MainClass extends LinearOpMode {
    public Loader Scheme;// Controller Scheme

    /**
     * Entry point of the program,
     * No Modification Intended
     */
    @Override
    public void runOpMode() {
        initialize();
        while(opModeIsActive()) {
            Scheme.control();
        }
    }

    /**
     * Does a few things:
     * - Loads Properties
     * - Sets up OpMode
     * - Loads OpMode Scheme
     * - Print Scheme to telemetry
     * - Waits for start to continue execution
     */
    private void initialize() {
        Scheme = Loader.load(telemetry, hardwareMap, gamepad1, gamepad2);
        waitForStart();
    }
}