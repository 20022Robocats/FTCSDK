package org.firstinspires.ftc.teamcode.interfaces;

import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;
import org.firstinspires.ftc.teamcode.MainClass;

import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class IScheme {
    /**
     * Returns loaded gamepads
     */
    public static Gamepad[] init() {
        return new Gamepad[]{MainClass.G1, MainClass.G2};
    }

    public IScheme(Drive drive, Claw claw) {}

    /**
     * Something you have to implement yourself, called every tick for control
     */
    public abstract void control();
}
