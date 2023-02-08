package net.hypernite.projects.FTCUtils.core;

import com.qualcomm.robotcore.hardware.HardwareMap;
import net.hypernite.projects.FTCUtils.*;

@SuppressWarnings("all")
public class ConfigLoader {
    public static
    <
        D extends DriveType,
        A extends ArmType,
        R extends IRobot<D,A>,
        C extends IConfig<D,A,R>
    >
    C load(
        Class<C> configClass
    ) {
        try {
            return configClass.newInstance();
        } catch(Exception e) {
            return (C) new DummyConfig<D,A,R>();
        }
    }
    private static final class DummyConfig
        <
            D extends DriveType,
            A extends ArmType,
            R extends IRobot<D,A>
        >
        extends IConfig<D,A,R>
    {

        @Override
        public <
            DCfg extends IMotorConfig,
            ACfg extends IMotorConfig
        > R getRobot(HardwareMap hardwareMap, DCfg drivecfg, ACfg armcfg ){
            return null;
        }

        @Override public <M extends IMotorConfig> M getMotorConfig() { return null; }
        @Override public <A1 extends IMotorConfig> A1 getArmConfig() { return null; }
    }
}
