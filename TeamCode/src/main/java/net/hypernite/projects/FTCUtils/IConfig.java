package net.hypernite.projects.FTCUtils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import net.hypernite.projects.FTCUtils.core.*;

@SuppressWarnings("all")
public abstract class IConfig<
    D extends DriveType,
    A extends ArmType,
    R extends IRobot<D,A>
> {
    public abstract <
        DCfg extends IMotorConfig,
        ACfg extends IMotorConfig
    > R getRobot(HardwareMap hardwareMap, DCfg drivecfg, ACfg armcfg );
    public abstract <M extends IMotorConfig> M getMotorConfig();
    public abstract <A extends IMotorConfig> A getArmConfig();
    @Override
    public String toString() {
        Class<R> obj = (Class<R>) new Object();
        return "Config:{Robot: " + obj.toString() + "}";
    }
}
