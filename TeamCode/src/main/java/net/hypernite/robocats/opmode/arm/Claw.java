package net.hypernite.robocats.opmode.arm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import net.hypernite.robocats.opmode.arm.util.Motor;
import org.firstinspires.ftc.teamcode.Common;
import net.hypernite.robocats.Loader;

import java.util.Locale;

public class Claw {
    public final HardwareMap hardwareMap = Loader.HMAP;// Hardware Map
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
        DcMotor dcMotor0 = hardwareMap.get(DcMotor.class, "arm0");
        dcMotor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor0 = new Motor(dcMotor0);
        s0 = hardwareMap.get(Servo.class, "servo0");
        s1 = hardwareMap.get(Servo.class, "servo1");
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
        motor0.setPower(ONE * Common.Motor.ARM);
    }

    /**
     * Returns String to be printed to telemetry
     */
    public String telemetry() {
        if(ACTIVE) {
            return String.format(Locale.ENGLISH,
                "   > Lift: %.1f",
                ONE * Common.Motor.ARM
            );
        } else {
            return "   > CLAW DISABLED";
        }
    }
}
