package org.firstinspires.ftc.teamcode.multipartclaw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MainClass;
import org.firstinspires.ftc.teamcode.util.IClaw;

public class MultiPartClaw extends IClaw {
    public HardwareMap hardwareMap = MainClass.HMAP;
    public Gamepad gamepad = MainClass.G2;
    public Claw CLAW;
    public Servo s1;

    public MultiPartClaw(){
        super();
    }

    @Override
    public void init() {
        CLAW = new Claw(
            hardwareMap.get(DcMotor.class, "arm0"),
            hardwareMap.get(DcMotor.class, "arm1"),
            hardwareMap.get(DcMotor.class, "arm2"),
            hardwareMap.get(DcMotor.class, "arm3")
        );
//        CAM = hardwareMap.get(WebcamName.class, "cam1");
        s1 = hardwareMap.get(Servo .class, "s1");
    }

    @Override
    public void run() {
        if(gamepad.dpad_down) {
            CLAW.ONE = 0.75;
            CLAW.TWO = 0.75;
        } else if(gamepad.dpad_up){
            CLAW.ONE = -0.75;
            CLAW.TWO = -0.75;
        } else {
            if(gamepad.left_stick_y == 0){
                // CLAW.ONE = -0.27;
                // CLAW.TWO = -0.27;
            } else {
                CLAW.ONE = (gamepad.left_stick_y * 0.75);
                CLAW.TWO = (gamepad.left_stick_y * 0.75);
            }
            CLAW.THREE= -gamepad.right_stick_y * 0.75;
            if(gamepad.left_trigger!=0){
                s1.setPosition(0);
            } else if(gamepad.right_trigger!=0){
                s1.setPosition(1);
            }
        }
        CLAW.run();
    }

    @Override
    public String telemetry() {
        return String.format(
                "   > Bottom1: %.1f\n   > Bottom2: %.1f\n   > Middle: %.1f\n   > UTIL.CLAW.MOTOR.FOUR: %.1f",
                CLAW.ONE,
                CLAW.TWO,
                CLAW.THREE,
                CLAW.FOUR
        );
    }
}
