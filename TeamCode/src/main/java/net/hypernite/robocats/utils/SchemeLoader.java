package net.hypernite.robocats.utils;

import org.firstinspires.ftc.teamcode.schemes.ExampleScheme;
import org.firstinspires.ftc.teamcode.opmode.drive.Drive;
import org.firstinspires.ftc.teamcode.opmode.arm.Claw;
import org.firstinspires.ftc.teamcode.schemes.*;

@SuppressWarnings("all")
public class SchemeLoader<S extends IScheme> {
    public Drive DRIVE;
    public Claw CLAW;

    public SchemeLoader(Drive drive, Claw claw) {
        this.DRIVE = drive;
        this.CLAW = claw;
    }

    public S loadScheme(String name) {
        for(Scheme<S> Scheme : this.SCHEMES) {
            if(Scheme.name.equals(name)){
                return Scheme.SCHEME;
            }
        }
        return (S) new ExampleScheme();
    }

    private final Scheme[] SCHEMES = {
        new Scheme(
            "Mat",
            new MatScheme()
        )
    };

    public static class Scheme<S extends IScheme> {
        public final String name;
        public final S SCHEME;
        public Scheme(String name, S SCHEME) {
            this.SCHEME = SCHEME;
            this.name = name;
        }
    }
}
