package org.firstinspires.ftc.teamcode.v1;

import com.qualcomm.robotcore.hardware.HardwareMap;
import net.hypernite.projects.FTCUtils.*;

@SuppressWarnings("all")
public class Robot extends IRobot<Robot.Drive,Robot.Arm> {
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
        private final double liftSpeed;
        public Arm(HardwareMap hardwareMap, Config.Arm cfg) {
            super(hardwareMap);
            this.liftSpeed = cfg.getLIFT();
        }

        @Override
        public void openClaw() {
            //TODO@AtomicGamer9523
        }

        @Override
        public void closeClaw() {
            //TODO@AtomicGamer9523
        }

        @Override
        public void setLiftingPower(int number) {
            //TODO@AtomicGamer9523
        }
    }
}
