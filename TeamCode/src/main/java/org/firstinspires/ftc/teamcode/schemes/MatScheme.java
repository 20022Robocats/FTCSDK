package org.firstinspires.ftc.teamcode.schemes;

import com.qualcomm.robotcore.util.ElapsedTime;
import net.hypernite.robocats.utils.IScheme;

public class MatScheme extends IScheme {
    public final ElapsedTime runtime = new ElapsedTime();

    public MatScheme(){
        super();
    }

    @Override
    public void control() {
        drive();
        claw();
        telemetry();
    }

    private void drive() {
        DRIVE.FR = 0;
        DRIVE.FL = 0;
        DRIVE.BR = 0;
        DRIVE.BL = 0;
        if(G1.left_trigger!=0){
            DRIVE.shift("down");
        } else if(G1.right_trigger!=0){
            DRIVE.shift("up");
        }
        if(Math.abs(G1.left_stick_y) > 0.1) {
            DRIVE.FR = G1.left_stick_y;
            DRIVE.FL = G1.left_stick_y;
            DRIVE.BR = G1.left_stick_y;
            DRIVE.BL = G1.left_stick_y;
        }
        if( Math.abs(G1.left_stick_x) > Math.abs(G1.left_stick_y) ){
            DRIVE.FR = G1.left_stick_x;
            DRIVE.FL = -G1.left_stick_x;
            DRIVE.BR = -G1.left_stick_x;
            DRIVE.BL = G1.left_stick_x;
        }
        if( G1.right_stick_x != 0 ){
            DRIVE.FR = G1.right_stick_x;
            DRIVE.FL = -G1.right_stick_x;
            DRIVE.BL = -G1.right_stick_x;
            DRIVE.BR = G1.right_stick_x;
        }
        DRIVE.update();
    }
    private void claw() {
        if(CLAW.ACTIVE){
            if(Math.abs(G2.right_stick_y)>0.4) {
                CLAW.ONE = G2.right_stick_y;
            } else {
                CLAW.ONE = 0;
            }
            if(G2.left_trigger!=0){
                CLAW.s0.setPosition(0.35);
                CLAW.s1.setPosition(0.47);
            } else if(G2.right_trigger!=0) {
                CLAW.s0.setPosition(0.55);
                CLAW.s1.setPosition(0.25);
            }
            CLAW.update();
        }
    }
    private void telemetry() {
        String status_data = String.format(java.util.Locale.ENGLISH,
            "   > Time: %.0f",
            runtime.time()
        );
        TELEMETRY.addLine("Status:");
        TELEMETRY.addLine(status_data);
        TELEMETRY.addLine("Drive:");
        TELEMETRY.addLine(DRIVE.telemetry());
        TELEMETRY.addLine("Claw:");
        TELEMETRY.addLine(CLAW.telemetry());
        TELEMETRY.update();
    }
}
