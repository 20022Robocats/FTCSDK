package org.firstinspires.ftc.teamcode;

import net.hypernite.robocats.utils.SchemeLoader.Scheme;
import org.firstinspires.ftc.teamcode.schemes.*;

@SuppressWarnings("all")
public final class Common {
    public static final String OpModeName = "PowerPlay 2022-2023";
    public static final String[] TEAM = {
        "Mr. Sweeten: Coach + Builder",
        "Mat: The Matrix is the world that has been pulled over your eyes to blind you from the truth.",
        "Diego: Let's do this"
    };

    // Default Motor Rotations
    public static final class Motor {
        public static final double FR = -1;// Front Right
        public static final double FL = 1;// Front Left
        public static final double BR = 1;// Back Right
        public static final double BL = -1;// Back Left
        public static final double ARM = -1;// Arm Motor
    }

    public static final String SchemeInUse = "Mat";

    public static final Scheme[] Schemes = {
        new Scheme(
            "Mat",
            new MatScheme()
        )
    };
}
