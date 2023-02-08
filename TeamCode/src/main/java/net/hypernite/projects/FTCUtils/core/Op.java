package net.hypernite.projects.FTCUtils.core;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import net.hypernite.projects.FTCUtils.*;


public abstract class Op<
    D extends DriveType,
    A extends ArmType,
    R extends IRobot<D, A>,
    C extends IConfig<D, A, R>
> extends LinearOpMode {
    //////////////////////////////////////////////////
    ////               Constructors               ////
    //////////////////////////////////////////////////
    private final C CONFIG;
    public Op(Class<C> configClass) {
        this.CONFIG = ConfigLoader.load(configClass);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        this.telemetry = new MultipleTelemetry(
            this.telemetry,
            dashboard.getTelemetry()
        );
    }

    //////////////////////////////////////////////////
    ////               Public Methods             ////
    //////////////////////////////////////////////////

    /**
     * <h1><b>DO NOT USE</b></h1>
     * If you want to load the robot, use {@link #loadRobot()}
     * @return Config
     */
    public C getConfig() { return this.CONFIG; }

    /**
     * Loads and returns the loaded robot
     * @return Loaded Robot
     */
    public R loadRobot() {
        return this.getConfig().getRobot(
            this.hardwareMap,
            this.getConfig().getMotorConfig(),
            this.getConfig().getArmConfig()
        );
    }

    ///////////////////////////////////////////////////
    ////                 Internals                 ////
    ///////////////////////////////////////////////////

}
