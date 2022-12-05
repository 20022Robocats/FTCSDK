package net.hypernite.robocats.utils;

import org.firstinspires.ftc.teamcode.schemes.ExampleScheme;
import net.hypernite.robocats.opmode.drive.Drive;
import net.hypernite.robocats.opmode.arm.Claw;
import org.firstinspires.ftc.teamcode.Common;

public class SchemeLoader<S extends IScheme> {
    public static Drive DRIVE;
    public static Claw CLAW;

    public SchemeLoader(Drive drive, Claw claw) {
        SchemeLoader.DRIVE = drive;
        SchemeLoader.CLAW = claw;
    }

    public S loadScheme(String name) {
        for(Scheme<S> Scheme : SchemeLoader.SCHEMES) {
            if(Scheme.name.equals(name)){
                return Scheme.SCHEME;
            }
        }
        return (S) new ExampleScheme();
    }

    private static final Scheme[] SCHEMES = Common.Schemes;

    public static class Scheme<S extends IScheme> {
        public String name;
        public S SCHEME;
        public Scheme(String name, S SCHEME) {
            this.SCHEME = SCHEME;
            this.name = name;
        }
    }
}
