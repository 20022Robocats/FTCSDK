package org.firstinspires.ftc.teamcode.v1;

import net.hypernite.projects.FTCUtils.core.*;
import net.hypernite.projects.FTCUtils.*;
import com.qualcomm.robotcore.hardware.*;

@SuppressWarnings("all")
public final class Config extends IConfig<Robot.Drive, Robot.Arm, Robot> {
    public static final String OpModeName = "HyperNite";

    @Override
    public Arm getArmConfig() { return new Arm(); }

    @Override
    public Motor getMotorConfig() { return new Motor(); }

    @Override
    public <D extends IMotorConfig, A extends IMotorConfig> Robot getRobot(
        HardwareMap hardwareMap, D drivecfg, A armcfg
    ) {
        return new Robot(hardwareMap, (Motor) drivecfg, (Arm) armcfg );
    }

    public static final class Motor extends DriveType.MECANUM.IMecanumConfig {
        @Override public double getFR() { return 2; } // Front Right (motor0)
        @Override public double getFL() { return -2; }// Front Left  (motor1)
        @Override public double getBR() { return 2; } // Back Right  (motor2)
        @Override public double getBL() { return -2; }// Back Left   (motor3)
    }

    public static final class Arm extends ArmType.ISingleMotorArmConfig {
        @Override public double getLIFT() { return -1; }// Arm Motor (arm0)
    }
}
