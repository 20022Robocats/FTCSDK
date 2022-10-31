package org.firstinspires.ftc.teamcode.mechanumdrive;

import org.firstinspires.ftc.teamcode.MainClass;
import org.firstinspires.ftc.teamcode.util.IDrive;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class MechanumDrive extends IDrive {
    public static HardwareMap hardwareMap = MainClass.HMAP;
    public static Gamepad gamepad = MainClass.G1;
    private static Drive DRIVE;

    public MechanumDrive(){
        super();
    }


    @Override
    public void init() {
        MechanumDrive.DRIVE = new Drive(
            hardwareMap.get(DcMotor.class, "motor0"),
            hardwareMap.get(DcMotor.class, "motor1"),
            hardwareMap.get(DcMotor.class, "motor2"),
            hardwareMap.get(DcMotor.class, "motor3")
        );
    }

    @Override
    public void run() {
        DRIVE.FR = gamepad.left_stick_y;
        DRIVE.FL = gamepad.left_stick_y;
        DRIVE.BR = -gamepad.left_stick_y;
        DRIVE.BL = -gamepad.left_stick_y;

        if(gamepad.left_trigger!=0){
            DRIVE.shift("down");
        } else if(gamepad.right_trigger!=0){
            DRIVE.shift("up");
        }

        if(
            gamepad.left_stick_x <-0.25
            ||
            gamepad.left_stick_x > 0.25
        ){
            DRIVE.FR = gamepad.left_stick_x;
            DRIVE.FL = -gamepad.left_stick_x;
            DRIVE.BR = gamepad.left_stick_x;
            DRIVE.BL = -gamepad.left_stick_x;
        }

        if(
            gamepad.right_stick_x != 0
        ){
            DRIVE.FR = gamepad.right_stick_x;
            DRIVE.FL = -gamepad.right_stick_x;
            DRIVE.BR = -gamepad.right_stick_x;
            DRIVE.BL = gamepad.right_stick_x;
        }
        DRIVE.run();
    }

    @Override
    public String telemetry() {
        return String.format(
                "   > Gear: %.1f\n   > FR: %.1f\n   > FL: %.1f\n   > BR: %.1f\n   > BL: %.1f",
                DRIVE.gear(),
                DRIVE.FR,
                DRIVE.FL,
                DRIVE.BR,
                DRIVE.BL
        );
    }
}
