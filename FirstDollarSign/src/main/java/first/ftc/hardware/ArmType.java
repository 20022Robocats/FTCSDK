package first.ftc.hardware;

import com.qualcomm.robotcore.hardware.*;

public class ArmType {
    //////////////////////////////////////////////////
    ////               Constructors               ////
    //////////////////////////////////////////////////
    private final HardwareMap HARDWAREMAP;
    public ArmType(HardwareMap hardwareMap){
        this.HARDWAREMAP = hardwareMap;
    }
    public HardwareMap getHardwareMap() {
        return this.HARDWAREMAP;
    }
    //////////////////////////////////////////////////
    ////               Public Methods             ////
    //////////////////////////////////////////////////
    /**
     * A type of an Arm that is fully customizable
     *
     */
    public static abstract class CustomArm
            //////////////////////////////////////////////////
            ////               Constructors               ////
            //////////////////////////////////////////////////
            extends ArmType
    {
        public CustomArm(HardwareMap hardwareMap) { super(hardwareMap); }
        //////////////////////////////////////////////////
        ////               Public Methods             ////
        //////////////////////////////////////////////////
        public abstract void openClaw();
        public abstract void closeClaw();
        public abstract void setLiftingPower(double number);
        public abstract void moveToHeight(PresetHeights height);
    }

    /**
     * A type of arm that uses 1 motor to lift that claw up and down
     */
    public static abstract class SingleMotorArm <
        Cfg extends SingleMotorArm.ISingleMotorArmConfig
    >
        extends ArmType
    {
        public SingleMotorArm(HardwareMap hardwareMap, Cfg config) {
            super(hardwareMap);
        }

        public static abstract class ISingleMotorArmConfig extends IMotorConfig {
            public abstract double getLIFT();
        }
    }
}
