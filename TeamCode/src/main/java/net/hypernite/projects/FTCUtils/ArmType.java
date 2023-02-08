package net.hypernite.projects.FTCUtils;

import com.qualcomm.robotcore.hardware.HardwareMap;

import net.hypernite.projects.FTCUtils.core.*;

public class ArmType {
    private final HardwareMap HARDWAREMAP;
    public ArmType(HardwareMap hardwareMap){
        this.HARDWAREMAP = hardwareMap;
    }
    public HardwareMap getHardwareMap() {
        return this.HARDWAREMAP;
    }
    public static abstract class CustomArm extends ArmType {
        public CustomArm(HardwareMap hardwareMap) {
            super(hardwareMap);
        }
        public abstract void openClaw();
        public abstract void closeClaw();
        public abstract void setLiftingPower(int number);
    }

    public static abstract class ISingleMotorArmConfig extends IMotorConfig {
        public abstract double getLIFT();
    }
}