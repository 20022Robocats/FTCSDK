package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.IMode;

@TeleOp(name = "PowerPlay 2022-2023")
public class MainClass<T extends IMode> extends LinearOpMode {
    public static Telemetry TELEMETRY;
    public static HardwareMap HMAP;
    public static Gamepad G1;
    public static Gamepad G2;

    @Override
    public void runOpMode(){
        waitForStart();
        MainClass.TELEMETRY = this.telemetry;
        MainClass.HMAP = this.hardwareMap;
        MainClass.G1 = this.gamepad1;
        MainClass.G2 = this.gamepad2;
        if(opModeIsActive()){

            OpModePOWERPLAY OPMODE = new OpModePOWERPLAY();

            while(opModeIsActive()) {
                OPMODE.run();
            }
        }
    }
}