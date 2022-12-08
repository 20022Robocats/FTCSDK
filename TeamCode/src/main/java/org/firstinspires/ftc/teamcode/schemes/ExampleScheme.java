package org.firstinspires.ftc.teamcode.schemes;

import net.hypernite.robocats.utils.IScheme;

/**
 * Whenever you include IScheme you automatically load a bunch of items.
 * That includes:
 *   - TELEMETRY: telemetry of the robot, used for displaying messages
 *   - G1: Gamepad #1
 *   - G2: Gamepad #2
 *   - DRIVE: controls motors related to driving
 *   - CLAW: controls motors related to claw mechanics
 * Additionally make sure you override the 'control' method, example under the constructor.
 */
public class ExampleScheme extends IScheme {

    /**
     * Constructor, needed for loading
     */
    public ExampleScheme(){ super(); }

    /**
     * Needed to be overwritten for the robot to work.
     * This function bellow is called every millisecond.
     */
    @Override
    public void control() {
        drive();
        telemetry();
    }


    private void drive() {
        if(G1.a){
            DRIVE.FR = 1;
        }
        if(G1.b){
            DRIVE.FL = 1;
        }
        if(G1.y){
            DRIVE.BR = 1;
        }
        if(G1.x){
            DRIVE.BL = 1;
        }
        DRIVE.update();
    }

    // Telemetry
    private void telemetry() {
        TELEMETRY.addLine("RUNNING EXAMPLE SCHEME");
        TELEMETRY.update();
    }
}
