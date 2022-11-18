package org.firstinspires.ftc.teamcode;

public class Common {
    public static final String OpModeName = "PowerPlay 2022-2023";// Competition Season Name
    private static final String[] TEAM = {
        "Mr. Sweeten: Coach + Builder",
        "Mat: The World of Matrix is an illusion that only exists in the endless dreams of a doomed humanity",
        "Diego: Let's do this"
    };// Everyone from the Team

    public static final class MotorPin {
        public static String FR_NAME = "motor0";
        public static String FL_NAME = "motor1";
        public static String BR_NAME = "motor2";
        public static String BL_NAME = "motor3";

        public static String R_SERVO = "servo1";
        public static String L_SERVO = "servo0";

        public static String ARM_NAME = "arm0";

        // Default Rotation
        public static double FR = -1;
        public static double FL = -1;
        public static double BR = 1;
        public static double BL = -1;
        public static double ARM = -1;
    }

    public static String getTeam() {
        StringBuilder result = new StringBuilder();
        for( String user : Common.TEAM ) {
            result.append(" - ").append(user).append("\n");
        }
        return result.toString();
    }
}
