package lukas;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;


@TeleOp(name = "chasisMovement03 (Blocks to Java)")
public class chasisMovement03 extends LinearOpMode {

    private DcMotor arm0;
    private DcMotor motor0;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private Servo servo2;
    private Servo servo3;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double speed;
        int presetConstant;

        arm0 = hardwareMap.get(DcMotor.class, "arm0");
        motor0 = hardwareMap.get(DcMotor.class, "motor0");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");

        // Put initialization blocks here.
        motor0.setDirection(DcMotorSimple.Direction.REVERSE);
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        arm0.setDirection(DcMotorSimple.Direction.FORWARD);
        motor3.setDirection(DcMotorSimple.Direction.FORWARD);
        motor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        speed = 0.5;
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                // movement
                if (gamepad1.left_trigger > 0.5) {
                    speed = 0.3;
                } else if (gamepad1.right_trigger > 0.5) {
                    speed = 0.55;
                } else {
                    speed = 0.55;
                }
                if (gamepad1.dpad_right) {
                    motor0.setPower(-speed);
                    motor1.setPower(speed);
                    motor2.setPower(speed);
                    motor3.setPower(-speed);
                } else if (gamepad1.dpad_left) {
                    motor0.setPower(speed);
                    motor1.setPower(-speed);
                    motor2.setPower(-speed);
                    motor3.setPower(speed);
                } else if (gamepad1.dpad_up) {
                    motor0.setPower(speed);
                    motor1.setPower(speed);
                    motor2.setPower(speed);
                    motor3.setPower(speed);
                } else if (gamepad1.dpad_down) {
                    motor0.setPower(-speed);
                    motor1.setPower(-speed);
                    motor2.setPower(-speed);
                    motor3.setPower(-speed);
                } else if (gamepad1.right_bumper) {
                    motor0.setPower(-speed);
                    motor1.setPower(speed);
                    motor2.setPower(-speed);
                    motor3.setPower(speed);
                } else if (gamepad1.left_bumper) {
                    motor0.setPower(speed);
                    motor1.setPower(-speed);
                    motor2.setPower(speed);
                    motor3.setPower(-speed);
                } else {
                    motor0.setPower(0);
                    motor1.setPower(0);
                    motor2.setPower(0);
                    motor3.setPower(0);
                }
                presetConstant = 350;
                // Preset Heights
                if (gamepad2.left_trigger > 0.5) {
                    do_something2(arm0.getCurrentPosition() - 100);
                } else if (gamepad2.right_trigger > 0.5) {
                    do_something2(arm0.getCurrentPosition() + 100);
                } else if (gamepad2.a) {
                    do_something2(0);
                } else if (gamepad2.left_stick_button) {
                    do_something2(0);
                } else if (gamepad2.b) {
                    do_something2(1700);
                } else if (gamepad2.x) {
                    do_something2(2800);
                } else if (gamepad2.y) {
                    do_something2(3650);
                } else if (gamepad2.dpad_down) {
                    do_something2(330);
                } else if (gamepad2.dpad_right) {
                    do_something2(475);
                } else if (gamepad2.dpad_left) {
                    do_something2(600);
                } else if (gamepad2.dpad_up) {
                    do_something2(725);
                }
                telemetry.addData("armTest", arm0.getCurrentPosition());
                telemetry.addData("motor0", motor0.getCurrentPosition());
                telemetry.addData("motor1", motor1.getCurrentPosition());
                telemetry.addData("motor2", motor2.getCurrentPosition());
                telemetry.addData("motor3", motor3.getCurrentPosition());
                telemetry.addData("saturation", 123);
                telemetry.update();
                if (gamepad2.left_bumper) {
                    servo2.setPosition(0.35);
                    servo3.setPosition(0.45);
                } else if (gamepad2.right_bumper) {
                    servo2.setPosition(0.4);
                    servo3.setPosition(0.35);
                }
                telemetry.update();
            }
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
}