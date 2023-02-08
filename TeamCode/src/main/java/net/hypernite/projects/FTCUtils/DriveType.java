package net.hypernite.projects.FTCUtils;

import net.hypernite.projects.FTCUtils.core.drive.*;
import net.hypernite.projects.FTCUtils.core.*;
import com.qualcomm.robotcore.hardware.*;

public class DriveType {
    private final HardwareMap HARDWAREMAP;
    public DriveType(HardwareMap hardwareMap){
        this.HARDWAREMAP = hardwareMap;
    }
    public HardwareMap getHardwareMap() {
        return this.HARDWAREMAP;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static abstract class MECANUM<Cfg extends MECANUM.IMecanumConfig> extends DriveType {
        public final Motor FR;
        public final Motor FL;
        public final Motor BR;
        public final Motor BL;
        public MECANUM(
            HardwareMap hardwareMap, Cfg config, String frontRightMotorId,
            String frontLeftMotorId, String backRightMotorId, String backLeftMotorId
        ) {
            super(hardwareMap);
            DcMotor fr = this.getHardwareMap().get(DcMotor.class, frontRightMotorId);
            DcMotor fl = this.getHardwareMap().get(DcMotor.class, frontLeftMotorId);
            DcMotor br = this.getHardwareMap().get(DcMotor.class, backRightMotorId);
            DcMotor bl = this.getHardwareMap().get(DcMotor.class, backLeftMotorId);
            this.FR = new Motor(fr, config.getFR());
            this.FL = new Motor(fl, config.getFL());
            this.BR = new Motor(br, config.getBR());
            this.BL = new Motor(bl, config.getBL());
        }

        public static abstract class IMecanumConfig extends IMotorConfig {
            public abstract double getFR();
            public abstract double getFL();
            public abstract double getBR();
            public abstract double getBL();
        }
    }
}
