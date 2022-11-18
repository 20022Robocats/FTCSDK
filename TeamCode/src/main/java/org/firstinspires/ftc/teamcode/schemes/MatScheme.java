package org.firstinspires.ftc.teamcode.schemes;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.interfaces.IScheme;
import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;
import org.firstinspires.ftc.teamcode.MainClass;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Locale;

public class MatScheme extends IScheme {
    public Telemetry telemetry = MainClass.TELEMETRY;
    public ElapsedTime runtime = new ElapsedTime();
    public Gamepad g1;
    public Gamepad g2;

    private final Drive DRIVE;
    private final Claw CLAW;


    public MatScheme(Drive drive, Claw claw){
        super(drive,claw);
        Gamepad[] gamepads = IScheme.init();
        this.g1 = gamepads[0];
        this.g2 = gamepads[1];
        this.DRIVE = drive;
        this.CLAW = claw;
    }

    @Override
    public void control() {
        drive(DRIVE,g1);
        claw(CLAW,g2);
        telemetry();
    }

    private void drive(Drive DRIVE, Gamepad gamepad) {
        DRIVE.FR = gamepad.left_stick_y;
        DRIVE.FL = gamepad.left_stick_y;
        DRIVE.BR = gamepad.left_stick_y ;
        DRIVE.BL = gamepad.left_stick_y;
        if(gamepad.left_trigger!=0){
            DRIVE.shift("down");
        } else if(gamepad.right_trigger!=0){
            DRIVE.shift("up");
        }
        if( Math.abs(gamepad.left_stick_x) > Math.abs(gamepad.left_stick_y) ){
            DRIVE.FR = gamepad.left_stick_x;
            DRIVE.FL = -gamepad.left_stick_x;
            DRIVE.BR = -gamepad.left_stick_x;
            DRIVE.BL = gamepad.left_stick_x;
        }
        if( gamepad.right_stick_x != 0 ){
            DRIVE.FR = gamepad.right_stick_x;
            DRIVE.FL = -gamepad.right_stick_x;
            DRIVE.BR = gamepad.right_stick_x;
            DRIVE.BL = -gamepad.right_stick_x;
        }
        DRIVE.update();
    }

    private void claw(Claw CLAW, Gamepad gamepad) {
        if(CLAW.ACTIVE){
            if(Math.abs(gamepad.right_stick_y)>0.4) {
                CLAW.ONE = gamepad.right_stick_y;
            } else {
                CLAW.ONE = 0;
            }
            if(gamepad.left_trigger!=0){
                CLAW.s0.setPosition(0.35);
                CLAW.s1.setPosition(0.47);
            } else if(gamepad.right_trigger!=0) {
                CLAW.s0.setPosition(0.55);
                CLAW.s1.setPosition(0.25);
            }
            CLAW.update();
        }
    }

    private void telemetry() {
        String status_data = String.format(Locale.ENGLISH,
            "   > Time: %.0f",
            runtime.time()
        );
        telemetry.addLine("Status:");
        telemetry.addLine(status_data);
        telemetry.addLine("Drive:");
        telemetry.addLine(DRIVE.telemetry());
        telemetry.addLine("Claw:");
        telemetry.addLine(CLAW.telemetry());
        telemetry.update();
    }
}
