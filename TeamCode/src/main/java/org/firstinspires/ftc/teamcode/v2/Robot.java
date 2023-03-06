package org.firstinspires.ftc.teamcode.v2;

import com.qualcomm.robotcore.hardware.*;

import first.ftc.hardware.*;
import first.ftc.$;

public class Robot extends $.IRobot<Robot.Drive,Robot.Arm> {
    public Robot(HardwareMap hardwareMap, Config.Motor dCfg, Config.Arm aCfg) {
        super(
            new Drive(hardwareMap, dCfg),
            new Arm(hardwareMap, aCfg)
        );
    }

    public static class Drive extends DriveType.MECANUM<Config.Motor> {
        public Drive(HardwareMap hardwareMap, Config.Motor cfg) {
            super(hardwareMap, cfg,
                "motor0",
                "motor1",
                "motor2",
                "motor3"
            );
        }
    }

    public static class Arm extends ArmType.CustomArm {
        public SimpleMotor motor0;// Interaction Motor
        public Servo s2;// Left Servo
        public Servo s3;// Right Servo
        private final double liftSpeed;
        public Arm(HardwareMap hardwareMap, Config.Arm cfg) {
            super(hardwareMap);
            this.liftSpeed = cfg.getLIFT();
            DcMotor dcMotor0 = this.getHardwareMap().get(DcMotor.class, "arm0");
            dcMotor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor0 = new SimpleMotor(dcMotor0);
            s2 = this.getHardwareMap().get(Servo.class, "servo2");
            s3 = this.getHardwareMap().get(Servo.class, "servo3");
        }

        @Override
        public void openClaw() {
            s2.setPosition(0.4);
            s3.setPosition(0.35);
        }

        @Override
        public void closeClaw() {
            s2.setPosition(0.35);
            s3.setPosition(0.45);
        }

        @Override
        public void setLiftingPower(double number) {
            motor0.setPower(number * liftSpeed);
        }

        @Override
        public void moveToHeight(PresetHeights height) {
            //TODO: implement
        }
    }
}
