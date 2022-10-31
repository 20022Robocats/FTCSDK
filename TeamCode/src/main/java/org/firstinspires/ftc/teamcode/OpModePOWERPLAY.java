package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanumdrive.MechanumDrive;
import org.firstinspires.ftc.teamcode.multipartclaw.MultiPartClaw;

public class OpModePOWERPLAY {
    public ElapsedTime runtime = new ElapsedTime();
    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public Gamepad gamepad1;
    public Gamepad gamepad2;

    public MechanumDrive MECH;
    public MultiPartClaw CLAW;

    public OpModePOWERPLAY() {
        this.telemetry = MainClass.TELEMETRY;
        this.hardwareMap = MainClass.HMAP;
        this.gamepad1 = MainClass.G1;
        this.gamepad2 = MainClass.G2;
        MECH = new MechanumDrive();
        CLAW = new MultiPartClaw();
        MECH.init();
        CLAW.init();
    }


    public void run() {
        MECH.run();
        CLAW.run();
        telemetry();
    }

    private void telemetry() {
        String status_data = String.format(
                "   > Time: %.1f",
                runtime.time()
        );
        telemetry.addLine("Status:");
        telemetry.addLine(status_data);
        telemetry.addLine("Drive:");
        telemetry.addLine(MECH.telemetry());
        telemetry.addLine("Claw:");
        telemetry.addLine(CLAW.telemetry());
        telemetry.update();
    }
}
