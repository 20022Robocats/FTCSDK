package net.hypernite.projects.FTCUtils;

import net.hypernite.projects.FTCUtils.core.*;


public abstract class DriverOp<
    D extends DriveType,
    A extends ArmType,
    R extends IRobot<D, A>,
    C extends IConfig<D, A, R>
    >
    extends Op<D, A, R, C>
{
    public DriverOp(Class<C> configClass) {
        super(configClass);
    }
    public <T extends DriverOp<D, A, R, C>> void run(T opMode) {
        try {
            this.onInit();
            this.waitForStart();
            while(this.opModeIsActive()){
                this.onRun();
            }
            this.onCleanup();
        } catch(Exception e) {
            this.telemetry.clearAll();
            this.telemetry.addLine(e.toString());
            this.telemetry.update();
        }
    }

    public abstract void onInit();
    public abstract void onRun();
    public abstract void onCleanup();
}
