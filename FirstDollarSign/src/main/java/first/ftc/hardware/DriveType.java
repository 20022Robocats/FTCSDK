package first.ftc.hardware;

import com.qualcomm.robotcore.hardware.*;

public class DriveType {
    private final HardwareMap HARDWAREMAP;
    public DriveType(HardwareMap hardwareMap){
        this.HARDWAREMAP = hardwareMap;
    }
    public HardwareMap getHardwareMap() {
        return this.HARDWAREMAP;
    }

    public static abstract class MECANUM<Cfg extends MECANUM.IMecanumConfig> extends DriveType {
        public final GearedMotor FR;
        public final GearedMotor FL;
        public final GearedMotor BR;
        public final GearedMotor BL;
        public MECANUM(
            HardwareMap hardwareMap, Cfg config, String frontRightMotorId,
            String frontLeftMotorId, String backRightMotorId, String backLeftMotorId
        ) {
            super(hardwareMap);
            DcMotor fr = this.getHardwareMap().get(DcMotor.class, frontRightMotorId);
            DcMotor fl = this.getHardwareMap().get(DcMotor.class, frontLeftMotorId);
            DcMotor br = this.getHardwareMap().get(DcMotor.class, backRightMotorId);
            DcMotor bl = this.getHardwareMap().get(DcMotor.class, backLeftMotorId);
            this.FR = new GearedMotor(fr, config.getFR());
            this.FL = new GearedMotor(fl, config.getFL());
            this.BR = new GearedMotor(br, config.getBR());
            this.BL = new GearedMotor(bl, config.getBL());
        }

        public void setPower(double speed) {
            this.FR.setPower(speed);
            this.FL.setPower(speed);
            this.BR.setPower(speed);
            this.BL.setPower(speed);
        }

        public void shift_down() {
            this.FR.shift_down();
            this.FL.shift_down();
            this.BR.shift_down();
            this.BL.shift_down();
        }

        public void shift_up() {
            this.FR.shift_up();
            this.FL.shift_up();
            this.BR.shift_up();
            this.BL.shift_up();
        }

        public static abstract class IMecanumConfig extends IMotorConfig {
            public abstract double getFR();
            public abstract double getFL();
            public abstract double getBR();
            public abstract double getBL();
        }
    }
}
