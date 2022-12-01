package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.utils.SkyStoneUtils.CameraUtils;
import org.firstinspires.ftc.teamcode.opmode.drive.Drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@SuppressWarnings("unused")
@Autonomous(name = Common.OpModeName + " - Autonomous")
public class AutoClass extends LinearOpMode {
    public CameraUtils cam;
    private Drive DRIVE;

    private void preInit() {
        this.telemetry.addLine(Common.getTeam());
        cam = new CameraUtils(this.hardwareMap);
        this.telemetry.update();
    }

    private void postInit() {
        DRIVE = new Drive();
    }

    @Override
    public void runOpMode() {
        preInit();
        waitForStart();
        postInit();
        if(opModeIsActive()){

            DRIVE.FR = -0.5 * Common.MotorPin.FR;
            DRIVE.FL = -0.5 * Common.MotorPin.FL;
            DRIVE.BR = -0.5 * Common.MotorPin.BR;
            DRIVE.BL = -0.5 * Common.MotorPin.BL;

            DRIVE.update();

            sleep(2100);

            DRIVE.FR = 0;
            DRIVE.FL = 0;
            DRIVE.BR = 0;
            DRIVE.BL = 0;

            DRIVE.update();
        }
    }
}
