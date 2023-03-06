package first.ftc;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import androidx.annotation.NonNull;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.Optional;

import first.ftc.hardware.*;

/**
 * <h1><b style="color:orange"><code>$</code></b>, A highly useful utility class</h1>
 * <h3>Example:</h3><pre>{@code
 * @TeleOp(name="Hello $ World!")
 * public class Main extends $.Op {
 *     public void run() {
 *         Robot robot = $.load(this, Config.class);
 *         $.run(u -> {
 *             telemetry.addLine("Hello $ World!");
 *             while(u.opModeIsActive()) {
 *                 telemetry.update();
 *             }
 *         });
 *     }
 * }
 * }</pre>
 * @see #load
 * @see #run
 * @see Op
 * @see IRobot
 * @see IConfig
 * @see #onPanic
 */
@SuppressWarnings("NewApi")
public class $ <
    O extends $.Op,
    D extends DriveType,
    A extends ArmType,
    R extends $.IRobot<D, A>,
    C extends $.IConfig<D, A, R>
> {
    //////////////////////////////////////////////////
    ////               Constructors               ////
    //////////////////////////////////////////////////
    /**
     * used by static methods to check if the core is loaded
     */
    private static boolean isSelfLoaded = false;
    /**
     * Instance to talk to, created when you call {@link #load}
     */
    @NonNull
    @SuppressWarnings("all")
    private static Optional<$> INSTANCE = Optional.empty();
    /**
     * gets the current instance <b>CAN BE NULL</b>
     * @return current {@link $} instance
     */
    @NonNull
    @SuppressWarnings("rawtypes")
    private static Optional<$> getInstance() {
        if(!isSelfLoaded) return Optional.empty();
        return INSTANCE;
    }
    /**
     * gets the current instance of {@link $}, <b>IT IS NOT NULL</b>.<br>
     * It does use an <span style="color:rgb(207, 54, 207)">assert</span> to verify
     * @return a non-null instance of the current instance
     */
    @NonNull
    @SuppressWarnings("rawtypes")
    private static $ getInstanceStrict() {
        Optional<$> inst = getInstance();
        assert inst.isPresent();
        return inst.get();
    }
    /**
     * Exception handler for any issue that occurs
     */
    @NonNull
    @SuppressWarnings("rawtypes")
    private static Internals.ExceptionHandler thisExceptionHandler = new Internals.ExceptionHandler(
        e -> {
            if(!isSelfLoaded) return false;
            if(!$.getInstance().isPresent()) return false;
            $ currentInstance = $.getInstance().get();
            currentInstance.linearOpMode.telemetry.clearAll();
            currentInstance.linearOpMode.telemetry.addLine(e.toString());
            while(
                !currentInstance.linearOpMode.isStopRequested()
            ) {
                currentInstance.linearOpMode.telemetry.update();
            }
            return false;
        }
    );
    /**
     * internal {@link LinearOpMode}
     */
    @NonNull
    private final O linearOpMode;
    /**
     * internal instance of {@link R}obot
     */
    @NonNull
    @SuppressWarnings("SpellCheckingInspection")
    private final R robot;
    /**
     * Creates a new internal instance of {@link $}
     * @param opMode a non-null {@link LinearOpMode}
     * @param configClass a non-null {@link Class} of {@link C}
     */
    private $(
        @NonNull O opMode,
        @NonNull Class<C> configClass
    ){
        if(PKGINF.CONFIG.USE_WEB_DASHBOARD) {
            Internals.runner(()->{
                com.acmerobotics.dashboard.FtcDashboard dashboard =
                    com.acmerobotics.dashboard.FtcDashboard.getInstance();
                opMode.telemetry = new com.acmerobotics.dashboard.telemetry.MultipleTelemetry(
                    opMode.telemetry,
                    dashboard.getTelemetry()
                );
                return null;
            });
        }
        this.linearOpMode = opMode;
        Optional<R> robot = Internals.runner(()->{
            Optional<C> configOp = Internals.ConfigLoader.load(configClass);
            assert configOp.isPresent();
            C config = configOp.get();
            isSelfLoaded = true;
            return config.getRobot(
                this.linearOpMode.hardwareMap,
                config.getDriveConfig(),
                config.getArmConfig()
            );
        });
        assert robot.isPresent();
        this.robot = robot.get();
    }
    //////////////////////////////////////////////////
    ////                Public API                ////
    //////////////////////////////////////////////////
    /**
     * <h2>Loads the robot and the configuration for it</h2>
     * Additionally loads {@link $}.<br>
     * Example:<pre>{@code 
     * @TeleOp(
     *     name = Config.OpModeName + " - TeleOp"
     * )
     * public class Main extends LinearOpMode {
     *     @Override public void runOpMode() {
     *         Robot robot = $.load(this, Config.class);
     *         $.run(u -> {
     *             u.waitForStart();
     *             while(u.opModeIsActive()){
     *                 telemetry.addLine("Hello World!");
     *                 telemetry.update();
     *             }
     *         });
     *     }
     * }
     * }</pre>
     * @param opMode an OpMode that extends {@link LinearOpMode}
     * @param configClass a java class that extends {@link IConfig}
     * @param <O> linear OpMode
     * @param <D> drivetrain type
     * @param <A> arm and claw type
     * @param <R> robot
     * @param <C> config for the robot
     * @return a new robot instance
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public static <
        O extends Op,
        D extends DriveType,
        A extends ArmType,
        R extends IRobot<D, A>,
        C extends IConfig<D, A, R>
    > R load(
        @NonNull O opMode,
        @NonNull Class<C> configClass
    ) {
        {
            $<O,D,A,R,C> self = new $<>(opMode, configClass);
            $.INSTANCE = Optional.of(self);
        }
        $<O,D,A,R,C> self = $.getInstanceStrict();
        return self.robot;
    }
    /**
     * A basic Handler of what to do when something goes wrong.<br>
     * Example:<pre>{@code 
     * $.onPanic(e->{
     *      telemetry.clearAll();
     *      telemetry.addLine(e.toString());
     *      while(!isStopRequested()){
     *          telemetry.update();
     *      }
     *      return false;// <- means it wasn't a successful recovery
     * });
     * }</pre>
     * @param func a function that takes an {@link Exception} and returns a {@link Boolean}
     *             if the recovery was successful.
     */
    public static void onPanic(
        @NonNull Function<Exception,Boolean> func
    ) {
        thisExceptionHandler = new Internals.ExceptionHandler(func);
    }
    /**
     * Evaluate code safely<br>
     * Example:<pre>{@code
     * $.run(u->{
     *     u.waitForStart();
     *     telemetry.addLine("Hello $ World!");
     *     while(u.opModeIsActive()){
     *         telemetry.update();
     *     }
     * });
     * }</pre>
     * @param func a lambda that takes a {@link RunUtil} and should return nothing
     */
    public static void run(
        @NonNull Consumer<RunUtil> func
    ) {
        if(!isSelfLoaded) return;
        RunUtil util = new Internals.RunUtilImpl();
        func.accept(util);
    }
    /**
     * A Utility Interface when running something
     */
    public interface RunUtil {
        /**
         * Pauses the Linear Op Mode until start has been pressed or until the current thread
         * is interrupted.
         */
        void waitForStart();
        /**
         * Answer as to whether this opMode is active and the robot should continue onwards. If the
         * opMode is not active, the OpMode should terminate at its earliest convenience.
         * @return whether the OpMode is currently active.
         */
        boolean opModeIsActive();
        /**
         * If the current OpMode is the following one, it will stop it.
         * Otherwise it will do nothing
         */
        void stop();
    }
    //////////////////////////////////////////////////
    ////              Public Classes              ////
    //////////////////////////////////////////////////
    /**
     * A Simple Wrapper around {@link LinearOpMode}
     */
    public static abstract class Op extends LinearOpMode {
        @Override
        public void runOpMode() throws InterruptedException {
            this.run();
        }
        public abstract void run() throws InterruptedException;
    }
    /**
     * A Rich Wrapper for creating a robot object<br>
     * Example:<pre>{@code
     * public class Robot extends IRobot<Robot.Drive,Robot.Arm> {
     *     public Robot(HardwareMap hardwareMap, Config.Motor dCfg, Config.Arm aCfg) {
     *         super(
     *             new Drive(hardwareMap, dCfg),
     *             new Arm(hardwareMap, aCfg)
     *         );
     *     }
     *
     *     public static class Drive extends DriveType.MECANUM<Config.Motor> {
     *         public Drive(HardwareMap hardwareMap, Config.Motor cfg) {
     *             super(hardwareMap, cfg,
     *                 "motor0",
     *                 "motor1",
     *                 "motor2",
     *                 "motor3"
     *             );
     *         }
     *     }
     *
     *     public static class Arm extends ArmType.SingleMotorArm {
     *         public Arm(HardwareMap hardwareMap, Config.Arm cfg) {
     *             super(hardwareMap, cfg);
     *         }
     *     }
     * }
     * }</pre>
     * @param <D> a type of a drive train
     * @param <A> a type of a arm and claw design
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static abstract class IRobot<
        //////////////////////////////////////////////////
        ////               Constructors               ////
        //////////////////////////////////////////////////
        D extends DriveType,
        A extends ArmType
    > {
        /**
         * Internal Drive train<br>
         * Type: {@link D}
         */
        @NonNull
        private final D DRIVE;
        /**
         * Internal Arm and Claw<br>
         * Type: {@link A}
         */
        @NonNull
        private final A ARM;

        /**
         * Needed to construct a new instance of the robot<br>
         * Takes two parameters; the drive train, and the claw<br>
         * Example: <pre>{@code
         *
         * }</pre>
         * @param drive Drive train
         * @param arm Arm and Claw
         */
        public IRobot(
            @NonNull D drive,
            @NonNull A arm
        ) {
            this.DRIVE = drive;
            this.ARM = arm;
        }
        //////////////////////////////////////////////////
        ////               Public Methods             ////
        //////////////////////////////////////////////////
        /**
         * Unloads robot and cleans up the memory
         */
        public void cleanup() {

            //TODO: spawn thread to clean up and post results
        }
        /**
         * Used for interacting with the drive motors.
         * @return current drive
         */
        @NonNull
        public D getDrive() {
            return this.DRIVE;
        }
        /**
         * Used for interacting with the arm & claw motors
         * @return current drive
         */
        @NonNull
        public A getArm() {
            return this.ARM;
        }
        ///////////////////////////////////////////////////
        ////                 Internals                 ////
        ///////////////////////////////////////////////////
        /**
         * Formats to a clean String
         * @return robot data
         */
        @NonNull
        @Override
        public String toString() {
            String driveString = getDrive().toString();
            String armString = getArm().toString();
            return "Robot{Drive: " + driveString + ", Arm: " + armString +"}";
        }
    }
    /**
     * A utility class for creating the configuration for the robot.<br>
     * Includes configuration for the drive train, the arm and the claw.<br>
     * Used internally for configuring which way the motors turn, and at what speed.<br>
     * Can also be called externally if customization is needed!<br>
     * Example: <pre>{@code
     * public final class Config extends $.IConfig<Robot.Drive, Robot.Arm, Robot> {
     *     public static final String OpModeName = "V2";
     *
     *     public Arm getArmConfig() { return new Arm(); }
     *
     *     public Motor getDriveConfig() { return new Motor(); }
     *
     *     public <D extends IMotorConfig, A extends IMotorConfig> Robot getRobot(
     *         HardwareMap hardwareMap, D driveCfg, A armCfg
     *     ) {
     *         return new Robot(hardwareMap, (Motor) driveCfg, (Arm) armCfg );
     *     }
     *
     *     public static class Motor extends DriveType.MECANUM.IMecanumConfig {
     *         public double getFR() { return 2; } // Front Right (motor0)
     *         public double getFL() { return -2; }// Front Left  (motor1)
     *         public double getBR() { return 2; } // Back Right  (motor2)
     *         public double getBL() { return -2; }// Back Left   (motor3)
     *     }
     *
     *     public static class Arm extends ArmType.SingleMotorArm.ISingleMotorArmConfig {
     *         public double getLIFT() { return -1; }// Arm Motor (arm0)
     *     }
     * }
     * }</pre>
     * @param <D> type of a drive train
     * @param <A> type of an arm and claw setup
     * @param <R> an instance of an {@link IRobot}
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static abstract class IConfig<
        //////////////////////////////////////////////////
        ////               Constructors               ////
        //////////////////////////////////////////////////
        D extends DriveType,
        A extends ArmType,
        R extends IRobot<D,A>
    > {
        //////////////////////////////////////////////////
        ////               Public Methods             ////
        //////////////////////////////////////////////////
        /**
         * Returns a new instance of the robot with loaded configuration for it <br>
         * Example: <pre>{@code
         * public final class Config extends IConfig<Robot.Drive,Robot.Arm,Robot> {
         *     @Override public Arm getArmConfig() { return new Arm(); }
         *     @Override public Motor getMotorConfig() { return new Motor(); }
         *     @Override
         *     public <D extends IMotorConfig, A extends IMotorConfig> Robot getRobot(
         *         HardwareMap hardwareMap, D drivecfg, A armcfg
         *     ) {
         *         return new Robot(hardwareMap, (Motor) drivecfg, (Arm) armcfg );
         *     }
         *
         *     public static final class Motor extends DriveType.MECANUM.IMecanumConfig {
         *         @Override public double getFR() { return 2; } // Front Right (motor0)
         *         @Override public double getFL() { return -2; }// Front Left  (motor1)
         *         @Override public double getBR() { return 2; } // Back Right  (motor2)
         *         @Override public double getBL() { return -2; }// Back Left   (motor3)
         *     }
         *
         *     public static final class Arm extends ArmType.SingleMotorArm.ISingleMotorArmConfig {
         *         @Override public double getLIFT() { return -1; }// Arm Motor (arm0)
         *     }
         * }
         * }</pre>
         * @param hardwareMap hardware map of the robot, usually retrievable from the OpMode
         * @param driveCfg Configuration for the Drive Train; type: {@link DCfg}
         * @param armCfg Configuration for the Arm and Claw; type: {@link ACfg}
         * @param <DCfg> {@link Class} that extends {@link IMotorConfig}
         * @param <ACfg> {@link Class} that extends {@link IMotorConfig}
         * @return an instance of {@link IRobot} with the configuration already loaded
         */
        public abstract <
            DCfg extends IMotorConfig,
            ACfg extends IMotorConfig
        > R getRobot(
            HardwareMap hardwareMap,
            DCfg driveCfg,
            ACfg armCfg
        );
        /**
         * Retrieves the current configuration for the drive train
         * @param <DC> Class that extends drive train configuration
         * @return {@link DC} the current config for the drive train
         */
        public abstract <DC extends IMotorConfig> DC getDriveConfig();
        /**
         * Retrieves the current configuration for the arm
         * @param <AC> Class that extends the arm configuration
         * @return the current config for the arm
         */
        public abstract <AC extends IMotorConfig> AC getArmConfig();
        ///////////////////////////////////////////////////
        ////                 Internals                 ////
        ///////////////////////////////////////////////////
        /**
         * Formats the Config to a nice {@link String}
         * @return formatted {@link String}
         */
        @NonNull
        @Override
        @SuppressWarnings("all")
        public String toString() {
            try {
                Class<R> obj = (Class<R>) new Object();
                return "Config:{Robot: " + obj.toString() + "}";
            } catch(Exception e) {
                return "Config";
            }
        }
    }
    ///////////////////////////////////////////////////
    ////                 Internals                 ////
    ///////////////////////////////////////////////////
    /**
     * Internals of all utilities
     */
    @SuppressWarnings("all")
    private static final class Internals {
        public static class ExceptionHandler {
            private final Function<Exception,Boolean> handler;
            public ExceptionHandler(Function<Exception,Boolean> handler) {
                this.handler = handler;
            }
            public void handle(Exception e) {
                isSelfLoaded = this.handler.apply(e);
            }
        }
        public static class RunUtilImpl
            implements RunUtil
        {
            @Override
            public void stop() {
                $ inst = $.getInstanceStrict();
                inst.linearOpMode.requestOpModeStop();
            }
            @Override
            public boolean opModeIsActive() {
                $ inst = $.getInstanceStrict();
                return inst.linearOpMode.opModeIsActive();
            }

            @Override
            public void waitForStart() {
                $ inst = $.getInstanceStrict();
                inst.linearOpMode.waitForStart();
            }
        }
        @NonNull
        public static <T> Optional<T> runner(Supplier<T> func) {
            try {
                return Optional.ofNullable(func.get());
            } catch(Exception e){
                thisExceptionHandler.handle(e);
                return Optional.empty();
            }
        }
        public static class ConfigLoader {
            //////////////////////////////////////////////////
            ////               Public Methods             ////
            //////////////////////////////////////////////////
            @NonNull
            public static <
                D extends DriveType,
                A extends ArmType,
                R extends IRobot<D,A>,
                C extends IConfig<D,A,R>
            > Optional<C> load(
                Class<C> configClass
            ) {
                try {
                    return Optional.ofNullable(
                        configClass.newInstance()
                    );
                } catch(Exception e) {
                    // Unreachable (usually)
                    return Optional.empty();
                }
            }
        }
    }
}
