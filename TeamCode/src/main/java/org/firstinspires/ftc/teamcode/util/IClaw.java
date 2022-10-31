package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class IClaw {
    public IClaw() {}
    public abstract void init();
    public abstract void run();
    public abstract String telemetry();
}
