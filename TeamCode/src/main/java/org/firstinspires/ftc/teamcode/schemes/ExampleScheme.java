package org.firstinspires.ftc.teamcode.schemes;

import net.hypernite.robocats.utils.IScheme;

public class ExampleScheme extends IScheme {
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
    public ExampleScheme(){ super(); }

    /**
     * Needed to be overwritten for the robot to work.
     * This function bellow is called every millisecond.
     */
    @Override
    public void control() {
        TELEMETRY.addLine("Drive:");
        TELEMETRY.addLine(
            DRIVE.telemetry()
        );
        TELEMETRY.addLine("Claw:");
        TELEMETRY.addLine(
            CLAW.telemetry()
        );
        TELEMETRY.update();
    }
}
