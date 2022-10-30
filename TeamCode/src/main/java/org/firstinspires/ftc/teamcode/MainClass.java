package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Mechanum Drive (USETHIS)")
public class MainClass extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private WebcamName CAM = null;
    private Drive DRIVE = null;
    private Claw CLAW = null;
    private Servo s1;



    @Override
    public void runOpMode(){
        waitForStart();
        try {
            this.__init();
            s1 = hardwareMap.get(Servo.class, "s1");
        } catch(Error e) {
            System.exit(2);
        }
        if(opModeIsActive()){
            while(opModeIsActive()) {
                try {
                    this.drivecontroll();
                    this.clawcontroll();
                    this.telemetry();
                    DRIVE.run();
                    CLAW.run();
                } catch(Error e) {
                    System.exit(1);
                }
            }
        }
    }


    private void drivecontroll() {
        DRIVE.FR = gamepad1.left_stick_y;
        DRIVE.FL = gamepad1.left_stick_y;
        DRIVE.BR = -gamepad1.left_stick_y;
        DRIVE.BL = -gamepad1.left_stick_y;

        if(gamepad1.left_trigger!=0){
            DRIVE.shift("down");
        } else if(gamepad1.right_trigger!=0){
            DRIVE.shift("up");
        };

        if(
                gamepad1.left_stick_x <-0.25
                        ||
                        gamepad1.left_stick_x > 0.25
        ){
            DRIVE.FR = gamepad1.left_stick_x;
            DRIVE.FL = -gamepad1.left_stick_x;
            DRIVE.BR = gamepad1.left_stick_x;
            DRIVE.BL = -gamepad1.left_stick_x;
        }

        if(
                gamepad1.right_stick_x != 0
        ){
            DRIVE.FR = gamepad1.right_stick_x;
            DRIVE.FL = -gamepad1.right_stick_x;
            DRIVE.BR = -gamepad1.right_stick_x;
            DRIVE.BL = gamepad1.right_stick_x;
        }
    }

    private void clawcontroll() {
        if(gamepad2.dpad_down) {
            CLAW.ONE = 0.75;
            CLAW.TWO = 0.75;
        } else if(gamepad2.dpad_up){
            CLAW.ONE = -0.75;
            CLAW.TWO = -0.75;
        } else {
            if(gamepad2.left_stick_y == 0){
                // CLAW.ONE = -0.27;
                // CLAW.TWO = -0.27;
            } else {
                CLAW.ONE = (gamepad2.left_stick_y * 0.75);
                CLAW.TWO = (gamepad2.left_stick_y * 0.75);
            }
            CLAW.THREE= -gamepad2.right_stick_y * 0.75;
            if(gamepad2.left_trigger!=0){
                s1.setPosition(0);
            } else if(gamepad2.right_trigger!=0){
                s1.setPosition(1);
            }
        }
    }




















    private void __init() {
        CAM = hardwareMap.get(WebcamName.class, "cam1");
        DRIVE = new Drive(
                hardwareMap.get(DcMotor.class, "motor0"),
                hardwareMap.get(DcMotor.class, "motor1"),
                hardwareMap.get(DcMotor.class, "motor2"),
                hardwareMap.get(DcMotor.class, "motor3")
        );
        CLAW = new Claw(
                hardwareMap.get(DcMotor.class, "arm0"),
                hardwareMap.get(DcMotor.class, "arm1"),
                hardwareMap.get(DcMotor.class, "arm2"),
                hardwareMap.get(DcMotor.class, "arm3")
        );
    }
    private void telemetry() {
        String motor_data = String.format(
                "   > gear: %.2f\n   > fr: %.2f\n   > fl: %.2f\n   > br: %.2f\n   > bl: %.2f",
                DRIVE.gear(),
                DRIVE.FR,
                DRIVE.FL,
                DRIVE.BR,
                DRIVE.BL
        );
        String claw_data = String.format(
                "   > 1: %.2f\n   > 2: %.2f\n   > 3: %.2f\n   > 4: %.2f",
                CLAW.ONE,
                CLAW.TWO,
                CLAW.THREE,
                CLAW.FOUR
        );
        String status_data = String.format(
                "   > Time: %.1f",
                runtime.time()
        );
        telemetry.addLine("Status:");
        telemetry.addLine(status_data);
        telemetry.addLine("Drive:");
        telemetry.addLine(motor_data);
        telemetry.addLine("Claw:");
        telemetry.addLine(claw_data);
        telemetry.update();
    }
}