package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.schemes.MatScheme;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;

public class SchemeLoader<S extends IScheme> {
    private final Drive DRIVE;
    private final Claw CLAW;

    public SchemeLoader(Drive drive, Claw claw) {
        this.DRIVE = drive;
        this.CLAW = claw;
    }

    @SuppressWarnings("unchecked")
    public S load(String name) {
        if(name.equals("Mat")) {
            return (S) new MatScheme(this.DRIVE,this.CLAW);
        }
        return null;
    }
}
