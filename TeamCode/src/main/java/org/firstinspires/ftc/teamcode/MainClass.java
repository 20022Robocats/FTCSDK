package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.interfaces.SchemeLoader;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.interfaces.IScheme;
import org.firstinspires.ftc.teamcode.opmode.OpMode;

@TeleOp(name = Common.OpModeName + " - TeleOp")
public class MainClass<S extends IScheme> extends LinearOpMode {
    public static Telemetry TELEMETRY;// Telemetry, needed for screen control
    public static HardwareMap HMAP;// Hardware Map, used for interacting with Motors
    public static Gamepad G1;// Gamepad for Driving
    public static Gamepad G2;// Gamepad for controlling Arm and Claw
    private S Scheme;// Controller Scheme



    /**
     * Entry point of the program,
     * No Modification Intended
     */
    @Override
    public void runOpMode() {
        preInit();
        waitForStart();
        postInit();
        if(opModeIsActive()){
            while(opModeIsActive()) {
                Scheme.control();
            }
        }
    }


    /**
     * Prints watermark to telemetry
     */
    private void preInit() {
        // Prints watermark
        this.telemetry.addLine(Common.getTeam());
        this.telemetry.update();
    }


    /**
     * Does a few things:
     * - Loads Properties
     * - Sets up OpMode
     * - Loads OpMode Scheme
     */
    private void postInit() {
        // Loads Properties
        MainClass.TELEMETRY = this.telemetry;
        MainClass.HMAP = this.hardwareMap;
        MainClass.G1 = this.gamepad1;
        MainClass.G2 = this.gamepad2;

        // Sets up OpMode
        OpMode OPMODE = new OpMode();
        OPMODE.hardwareMap = this.hardwareMap;
        OPMODE.telemetry = this.telemetry;

        // Loads OpMode Scheme
        SchemeLoader<S> loader = new SchemeLoader<S>(OPMODE.DRIVE, OPMODE.CLAW);
        Scheme = loader.load("Mat");
    }
}