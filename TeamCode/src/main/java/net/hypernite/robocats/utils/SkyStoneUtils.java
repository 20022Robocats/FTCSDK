package net.hypernite.robocats.utils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.ClassFactory;

import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import net.hypernite.robocats.VuforiaKey;

public class SkyStoneUtils {
    public static boolean ACTIVE = true;
    public static class SkystoneDeterminationPipeline extends OpenCvPipeline {
        /*
         * An enum to define the skystone position
         */
        public enum SkystonePosition {
            LEFT,
            CENTER,
            RIGHT
        }

        /*
         * Some color constants
         */
        static final Scalar BLACK = new Scalar(0, 0, 0);
        static final Scalar WHITE = new Scalar(255, 255, 255);

        /*
         * The core values which define the location and size of the sample regions
         */
        static final Point REGION1_TOP_LEFT_ANCHOR_POINT = new Point(109,98);
        static final Point REGION2_TOP_LEFT_ANCHOR_POINT = new Point(181,98);
        static final Point REGION3_TOP_LEFT_ANCHOR_POINT = new Point(253,98);
        static final int REGION_WIDTH = 20;
        static final int REGION_HEIGHT = 20;

        /*
         * Points which actually define the sample region rectangles, derived from above values
         *
         * Example of how points A and B work to define a rectangle
         *
         *   ------------------------------------
         *   | (0,0) Point A                    |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                  Point B (70,50) |
         *   ------------------------------------
         *
         */
        final Point region1_pointA = new Point(
                REGION1_TOP_LEFT_ANCHOR_POINT.x,
                REGION1_TOP_LEFT_ANCHOR_POINT.y
        );
        final Point region1_pointB = new Point(
                REGION1_TOP_LEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOP_LEFT_ANCHOR_POINT.y + REGION_HEIGHT
        );
        final Point region2_pointA = new Point(
                REGION2_TOP_LEFT_ANCHOR_POINT.x,
                REGION2_TOP_LEFT_ANCHOR_POINT.y
        );
        final Point region2_pointB = new Point(
                REGION2_TOP_LEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION2_TOP_LEFT_ANCHOR_POINT.y + REGION_HEIGHT
        );
        final Point region3_pointA = new Point(
                REGION3_TOP_LEFT_ANCHOR_POINT.x,
                REGION3_TOP_LEFT_ANCHOR_POINT.y
        );
        final Point region3_pointB = new Point(
                REGION3_TOP_LEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION3_TOP_LEFT_ANCHOR_POINT.y + REGION_HEIGHT
        );

        /*
         * Working variables
         */
        Mat region1_Cb, region2_Cb, region3_Cb;
        final Mat YCrCb = new Mat();
        final Mat Cb = new Mat();
        int avg1, avg2, avg3;

        // Volatile since accessed by OpMode thread w/o synchronization
        private volatile SkystonePosition position = SkystonePosition.LEFT;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 2);
        }

        @Override
        public void init(Mat firstFrame) {
            inputToCb(firstFrame);
            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
            region2_Cb = Cb.submat(new Rect(region2_pointA, region2_pointB));
            region3_Cb = Cb.submat(new Rect(region3_pointA, region3_pointB));
        }

        @Override
        public Mat processFrame(Mat input) {
            /*
             * Overview of what we're doing:
             *
             * We first convert to YCrCb color space, from RGB color space.
             * Why do we do this? Well, in the RGB color space, chroma and
             * luma are intertwined. In YCrCb, chroma and luma are separated.
             * YCrCb is a 3-channel color space, just like RGB. YCrCb 's 3 channels
             * are Y, the luma channel (which essentially just a B&W image), the
             * Cr channel, which records the difference from red, and the Cb channel,
             * which records the difference from blue. Because chroma and luma are
             * not related in YCrCb, vision code written to look for certain values
             * in the Cr/Cb channels will not be severely affected by differing
             * light intensity, since that difference would most likely just be
             * reflected in the Y channel.
             *
             * After we've converted to YCrCb, we extract just the 2nd channel, the
             * Cb channel. We do this because stones are bright yellow and contrast
             * STRONGLY on the Cb channel against everything else, including SkyStones
             * (because SkyStones have a black label).
             *
             * We then take the average pixel value of 3 different regions on that Cb
             * channel, one positioned over each stone. The brightest of the 3 regions
             * is where we assume the SkyStone to be, since the normal stones show up
             * extremely darkly.
             *
             * We also draw rectangles on the screen showing where the sample regions
             * are, as well as drawing a solid rectangle over top the sample region
             * we believe is on top of the SkyStone.
             *
             * In order for this whole process to work correctly, each sample region
             * should be positioned in the center of each of the first 3 stones, and
             * be small enough such that only the stone is sampled, and not any of the
             * surroundings.
             */

            /*
             * Get the Cb channel of the input frame after conversion to YCrCb
             */
            inputToCb(input);
            avg1 = (int) Core.mean(region1_Cb).val[0];
            avg2 = (int) Core.mean(region2_Cb).val[0];
            avg3 = (int) Core.mean(region3_Cb).val[0];
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLACK, // The color the rectangle is drawn in
                    2
            ); // Thickness of the rectangle lines

            /*
             * Draw a rectangle showing sample region 2 on the screen.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region2_pointA, // First point which defines the rectangle
                    region2_pointB, // Second point which defines the rectangle
                    BLACK, // The color the rectangle is drawn in
                    2
            ); // Thickness of the rectangle lines

            /*
             * Draw a rectangle showing sample region 3 on the screen.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region3_pointA, // First point which defines the rectangle
                    region3_pointB, // Second point which defines the rectangle
                    BLACK, // The color the rectangle is drawn in
                    2
            ); // Thickness of the rectangle lines


            /*
             * Find the max of the 3 averages
             */
            int maxOneTwo = Math.max(avg1, avg2);
            int max = Math.max(maxOneTwo, avg3);

            /*
             * Now that we found the max, we actually need to go and
             * figure out which sample region that value was from
             */
            if(max == avg1) {
                position = SkystonePosition.LEFT; // Record our analysis

                /*
                 * Draw a solid rectangle on top of the chosen region.
                 * Simply a visual aid. Serves no functional purpose.
                 */
                Imgproc.rectangle(
                        input, // Buffer to draw on
                        region1_pointA, // First point which defines the rectangle
                        region1_pointB, // Second point which defines the rectangle
                        WHITE, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            } else if(max == avg2) {
                position = SkystonePosition.CENTER; // Record our analysis

                /*
                 * Draw a solid rectangle on top of the chosen region.
                 * Simply a visual aid. Serves no functional purpose.
                 */
                Imgproc.rectangle(
                        input, // Buffer to draw on
                        region2_pointA, // First point which defines the rectangle
                        region2_pointB, // Second point which defines the rectangle
                        WHITE, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            } else {
                position = SkystonePosition.RIGHT; // Record our analysis

                /*
                 * Draw a solid rectangle on top of the chosen region.
                 * Simply a visual aid. Serves no functional purpose.
                 */
                Imgproc.rectangle(
                        input, // Buffer to draw on
                        region3_pointA, // First point which defines the rectangle
                        region3_pointB, // Second point which defines the rectangle
                        WHITE, // The color the rectangle is drawn in
                        -1
                ); // Negative thickness means solid fill
            }

            /*
             * Render the 'input' buffer to the viewport. But note this is not
             * simply rendering the raw camera feed, because we called functions
             * to add some annotations to this buffer earlier up.
             */
            return input;
        }

        /*
         * Call this from the OpMode thread to obtain the latest analysis
         */
        public SkystonePosition getAnalysis() {
            return position;
        }

        public int analyze() {
            if(SkyStoneUtils.ACTIVE) {
                switch(this.getAnalysis()){
                    case LEFT: {
                        return 0;
                    }
                    case RIGHT: {
                        return 2;
                    }
                    default: {
                        return 1;
                    }
                }
            }
            return 3;
        }
    }

    public static class CameraUtils {
        public SkystoneDeterminationPipeline pipeline;
        public OpenCvCamera vuforiaPassthroughCam;
        public VuforiaLocalizer vuforia;


        public CameraUtils(HardwareMap hardwareMap){
            try{
                SkyStoneUtils.ACTIVE = true;
                int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
                int[] viewportContainerIds = OpenCvCameraFactory.getInstance().splitLayoutForMultipleViewports(cameraMonitorViewId, 2, OpenCvCameraFactory.ViewportSplitMethod.VERTICALLY);
                VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(viewportContainerIds[0]);
                parameters.vuforiaLicenseKey = VuforiaKey.KEY;
                parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
                parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
                vuforia = ClassFactory.getInstance().createVuforia(parameters);
                vuforiaPassthroughCam = OpenCvCameraFactory.getInstance().createVuforiaPassthrough(vuforia, parameters, viewportContainerIds[1]);
                pipeline = new SkystoneDeterminationPipeline();
                vuforiaPassthroughCam.setPipeline(pipeline);
                FtcDashboard.getInstance().startCameraStream(vuforia,60);
            }catch(Exception e) {
                SkyStoneUtils.ACTIVE = false;
            }
        }

        public int snapshot() {
            if(SkyStoneUtils.ACTIVE) return this.pipeline.analyze();
            return 3;
        }
    }
}
