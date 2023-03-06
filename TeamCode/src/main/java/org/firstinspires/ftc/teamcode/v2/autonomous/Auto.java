package org.firstinspires.ftc.teamcode.v2.autonomous;

import org.firstinspires.ftc.teamcode.v2.*;

@SuppressWarnings("all")
public class Auto {
    public static void run(Main main, int ydefault) {
//        Robot robot = main.robot;
//        Telemetry telemetry = main.robot;
//        int speed;
//        int currentColor;
//        int zone;
//        ColorSensor color = main.hardwareMap.get(ColorSensor.class, "color");
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        // Lift off
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        robot.getArm().closeClaw();
//        armthing(1500);
//        main.sleep(1000);
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        // Forward movement 1
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        drivething(1300, 1300, 1300, 1300, 700);
//        main.sleep(2000);
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        // Read color
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        currentColor = Color.rgb(color.red(), color.green(), color.blue());
//        if (JavaUtil.colorToHue(currentColor) > 0 && JavaUtil.colorToHue(currentColor) < 60 && JavaUtil.colorToSaturation(currentColor) > 0) {
//            zone = 3;
//        } else if (JavaUtil.colorToHue(currentColor) > 100 && JavaUtil.colorToHue(currentColor) < 160 && JavaUtil.colorToSaturation(currentColor) > 0) {
//            zone = 1;
//        } else {
//            zone = 2;
//        }
//        telemetry.addData("hue", JavaUtil.colorToHue(currentColor));
//        telemetry.addData("saturation", JavaUtil.colorToSaturation(currentColor));
//        telemetry.addData("value", JavaUtil.colorToValue(currentColor));
//        telemetry.addData("zone", zone);
//        main.sleep(1000);
//        telemetry.update();
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        // Forward movement 2
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        drivething(1550, 1550, 1550, 1550, 700);
//        main.sleep(4000);
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        // Move back to clear signal cone
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        drivething(-200, -200, -200, -200, 700);
//        main.sleep(1000);
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        // Move right 3
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        armthing(3700);
//        drivething(-650, 650, 650, -650, 700);
//        main.sleep(4000);
//        motor0.setPower(0);
//        motor1.setPower(0);
//        motor2.setPower(0);
//        motor3.setPower(0);
//        main.sleep(1000);
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        armthing(3400);
//        main.sleep(500);
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        // release and move back
//        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        robot.getArm().openClaw();
//        main.sleep(3000);
//        armthing(3700);
//        main.sleep(1000);
//        drivething(-300, -300, -300, -300, 700);
//        main.sleep(3000);
//        armthing(725);
//        if (zone == 3) {
//            drivething(-575, 575, 575, -575, 1000);
//        } else if (zone == 2) {
//            drivething(700, -700, -700, 700, 1000);
//        } else if (zone == 1) {
//            drivething(1700, -1700, -1700, 1700, 1000);
//        }
//        main.sleep(3000);
//        telemetry.addData("zone", zone);
//        telemetry.update();
    }


//    private static void armthing(int arm0target) {
//        arm0.setTargetPosition(arm0target);
//        arm0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (arm0.getCurrentPosition() < arm0target) {
//            arm0.setPower(1);
//        } else if (arm0.getCurrentPosition() > arm0target) {
//            arm0.setPower(-1);
//        } else {
//            ((DcMotorEx) arm0).setVelocity(0);
//        }
//    }

//    private static void drivething(int motor0target, int motor1target, int motor2target, int motor3target, int speed) {
//        int motor0pos=0;
//        int motor1pos=0;
//        int motor2pos=0;
//        int motor3pos=0;
//
//        motor0pos += motor0target;
//        motor0.setTargetPosition(motor0pos);
//        motor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (motor0.getCurrentPosition() < motor0pos) {
//            ((DcMotorEx) motor0).setVelocity(speed);
//        } else if (motor0.getCurrentPosition() > motor0pos) {
//            ((DcMotorEx) motor0).setVelocity(-speed);
//        } else {
//            ((DcMotorEx) motor0).setVelocity(0);
//        }
//        motor1pos += motor1target;
//        motor1.setTargetPosition(motor1pos);
//        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (motor1.getCurrentPosition() < motor1pos) {
//            ((DcMotorEx) motor1).setVelocity(speed);
//        } else if (motor1.getCurrentPosition() > motor1pos) {
//            ((DcMotorEx) motor1).setVelocity(-speed);
//        } else {
//            ((DcMotorEx) motor1).setVelocity(0);
//        }
//        motor2pos += motor2target;
//        motor2.setTargetPosition(motor2pos);
//        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (motor2.getCurrentPosition() < motor2pos) {
//            ((DcMotorEx) motor2).setVelocity(speed);
//        } else if (motor2.getCurrentPosition() > motor2pos) {
//            ((DcMotorEx) motor2).setVelocity(-speed);
//        } else {
//            ((DcMotorEx) motor2).setVelocity(0);
//        }
//        motor3pos += motor3target;
//        motor3.setTargetPosition(motor3pos);
//        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (motor3.getCurrentPosition() < motor2pos) {
//            ((DcMotorEx) motor3).setVelocity(speed);
//        } else if (motor3.getCurrentPosition() > motor2pos) {
//            ((DcMotorEx) motor3).setVelocity(-speed);
//        } else {
//            ((DcMotorEx) motor3).setVelocity(0);
//        }
//    }
}
