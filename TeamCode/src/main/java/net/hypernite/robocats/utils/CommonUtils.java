package net.hypernite.robocats.utils;

import org.firstinspires.ftc.teamcode.Common;

public class CommonUtils {
    public static String getTeam() {
        StringBuilder result = new StringBuilder();
        for( String user : Common.TEAM ) {
            result.append(" - ").append(user).append("\n");
        }
        return result.toString();
    }
}
