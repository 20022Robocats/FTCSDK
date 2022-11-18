package org.firstinspires.ftc.teamcode.opmode.arm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.opmode.arm.util.Motor;
import org.firstinspires.ftc.teamcode.MainClass;
import org.firstinspires.ftc.teamcode.Common;

import java.util.Locale;

public class Claw {
    public HardwareMap hardwareMap = MainClass.HMAP;// Hardware Map
    public boolean ACTIVE = true;// Useful when expansion hub not plugged in
    public double ONE = 0;// Speed
    private Motor motor0;// Interaction Motor
    public Servo s0;// Left Servo
    public Servo s1;// Right Servo

    /**
     * Creates a new Claw instance
     */
    public Claw(){
        super();
        DcMotor dcMotor0 = hardwareMap.get(DcMotor.class, Common.MotorPin.ARM_NAME);
        dcMotor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor0 = new Motor(dcMotor0);
        s0 = hardwareMap.get(Servo.class, Common.MotorPin.L_SERVO);
        s1 = hardwareMap.get(Servo.class, Common.MotorPin.R_SERVO);
    }

    /**
     * Used for disabling the claw, useful when the expansion hub is missing
     */
    public Claw(boolean enabled) {
        this.ACTIVE = enabled;
    }

    /**
     * Updates DcMotor speed
     */
    public void update() {
        motor0.setPower(ONE * Common.MotorPin.ARM);
    }

    /**
     * Returns String to be printed to telemetry
     */
    public String telemetry() {
        if(ACTIVE) {
            return String.format(Locale.ENGLISH,
                "   > Lift: %.1f",
                ONE * Common.MotorPin.ARM
            );
        } else {
            return "   > CLAW DISABLED";
        }
    }
}
