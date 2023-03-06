package lukas;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

//@SuppressWarnings("all")
@Autonomous(name = "compAutoLeft01 (Lukas)")
@Disabled
public class compAutoLeft01 extends LinearOpMode {

    private DcMotor arm0;
    private DcMotor motor0;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private Servo servo2;
    private Servo servo3;
    private ColorSensor color;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        int speed;
        int currentColor;
        int zone;

        arm0 = hardwareMap.get(DcMotor.class, "arm0");
        motor0 = hardwareMap.get(DcMotor.class, "motor0");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        color = hardwareMap.get(ColorSensor.class, "color");

        // Put initialization blocks here.
        arm0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor0.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        speed = 1000;
        waitForStart();
        if (opModeIsActive()) {
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Lift off
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            servo2.setPosition(0.35);
            servo3.setPosition(0.45);
            sleep(500);
            do_something2(1500);
            sleep(1000);
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Forward movement 1
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            do_something(1300, 1300, 1300, 1300, 700);
            sleep(2000);
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Read color
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            currentColor = Color.rgb(color.red(), color.green(), color.blue());
            if (JavaUtil.colorToHue(currentColor) > 0 && JavaUtil.colorToHue(currentColor) < 60 && JavaUtil.colorToSaturation(currentColor) > 0) {
                zone = 3;
            } else if (JavaUtil.colorToHue(currentColor) > 100 && JavaUtil.colorToHue(currentColor) < 160 && JavaUtil.colorToSaturation(currentColor) > 0) {
                zone = 1;
            } else {
                zone = 2;
            }
            telemetry.addData("hue", JavaUtil.colorToHue(currentColor));
            telemetry.addData("saturation", JavaUtil.colorToSaturation(currentColor));
            telemetry.addData("value", JavaUtil.colorToValue(currentColor));
            telemetry.addData("zone", zone);
            sleep(1000);
            telemetry.update();
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Forward movement 2
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            do_something(1550, 1550, 1550, 1550, 700);
            sleep(4000);
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Move back to clear signal cone
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            do_something(-200, -200, -200, -200, 700);
            sleep(1000);
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Move right 3
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            do_something2(3700);
            do_something(-650, 650, 650, -650, 700);
            sleep(4000);
            motor0.setPower(0);
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            sleep(1000);
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            do_something2(3400);
            sleep(500);
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // release and move back
            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            servo2.setPosition(0.45);
            servo3.setPosition(0.35);
            sleep(3000);
            do_something2(3700);
            sleep(1000);
            do_something(-300, -300, -300, -300, 700);
            sleep(3000);
            do_something2(725);
            if (zone == 3) {
                do_something(-575, 575, 575, -575, 1000);
            } else if (zone == 2) {
                do_something(700, -700, -700, 700, 1000);
            } else if (zone == 1) {
                do_something(1700, -1700, -1700, 1700, 1000);
            }
            sleep(3000);
            telemetry.addData("zone", zone);
            telemetry.update();
        }
    }

    /**
     * Describe this function...
     */
    private void do_something2(int arm0target) {
        arm0.setTargetPosition(arm0target);
        arm0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (arm0.getCurrentPosition() < arm0target) {
            arm0.setPower(1);
        } else if (arm0.getCurrentPosition() > arm0target) {
            arm0.setPower(-1);
        } else {
            ((DcMotorEx) arm0).setVelocity(0);
        }
    }

    /**
     * Describe this function...
     */
    private void do_something(int motor0target, int motor1target, int motor2target, int motor3target, int speed) {
        int motor0pos=0;
        int motor1pos=0;
        int motor2pos=0;
        int motor3pos=0;

        motor0pos += motor0target;
        motor0.setTargetPosition(motor0pos);
        motor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (motor0.getCurrentPosition() < motor0pos) {
            ((DcMotorEx) motor0).setVelocity(speed);
        } else if (motor0.getCurrentPosition() > motor0pos) {
            ((DcMotorEx) motor0).setVelocity(-speed);
        } else {
            ((DcMotorEx) motor0).setVelocity(0);
        }
        motor1pos += motor1target;
        motor1.setTargetPosition(motor1pos);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (motor1.getCurrentPosition() < motor1pos) {
            ((DcMotorEx) motor1).setVelocity(speed);
        } else if (motor1.getCurrentPosition() > motor1pos) {
            ((DcMotorEx) motor1).setVelocity(-speed);
        } else {
            ((DcMotorEx) motor1).setVelocity(0);
        }
        motor2pos += motor2target;
        motor2.setTargetPosition(motor2pos);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (motor2.getCurrentPosition() < motor2pos) {
            ((DcMotorEx) motor2).setVelocity(speed);
        } else if (motor2.getCurrentPosition() > motor2pos) {
            ((DcMotorEx) motor2).setVelocity(-speed);
        } else {
            ((DcMotorEx) motor2).setVelocity(0);
        }
        motor3pos += motor3target;
        motor3.setTargetPosition(motor3pos);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (motor3.getCurrentPosition() < motor2pos) {
            ((DcMotorEx) motor3).setVelocity(speed);
        } else if (motor3.getCurrentPosition() > motor2pos) {
            ((DcMotorEx) motor3).setVelocity(-speed);
        } else {
            ((DcMotorEx) motor3).setVelocity(0);
        }
    }
}