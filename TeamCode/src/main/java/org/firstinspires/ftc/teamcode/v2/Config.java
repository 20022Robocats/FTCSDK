package org.firstinspires.ftc.teamcode.v2;

import com.qualcomm.robotcore.hardware.*;

import first.ftc.hardware.*;
import first.ftc.$;

@SuppressWarnings("all")
public final class Config extends $.IConfig<Robot.Drive, Robot.Arm, Robot> {
    public static final String OpModeName = "V2";

    public Arm getArmConfig() { return new Arm(); }

    public Motor getDriveConfig() { return new Motor(); }

    public <D extends IMotorConfig, A extends IMotorConfig> Robot getRobot(
        HardwareMap hardwareMap, D driveCfg, A armCfg
    ) {
        return new Robot(hardwareMap, (Motor) driveCfg, (Arm) armCfg );
    }

    public static class Motor extends DriveType.MECANUM.IMecanumConfig {
        public double getFR() { return 2; } // Front Right (motor0)
        public double getFL() { return -2; }// Front Left  (motor1)
        public double getBR() { return 2; } // Back Right  (motor2)
        public double getBL() { return -2; }// Back Left   (motor3)
    }

    public static class Arm extends ArmType.SingleMotorArm.ISingleMotorArmConfig {
        public double getLIFT() { return -1; }// Arm Motor (arm0)
    }
}
