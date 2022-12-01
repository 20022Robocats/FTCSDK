package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utils.SkyStoneUtils.CameraUtils;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvCamera;

@TeleOp
public class FieldNav extends LinearOpMode {
    CameraUtils cam = null;

    @Override
    public void runOpMode() {
        cam = new CameraUtils(this.hardwareMap);

        cam.vuforiaPassthroughCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                cam.vuforiaPassthroughCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
            }
        });


        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("Realtime analysis", cam.snapshot());
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }



        int snapshotAnalysis = cam.snapshot();
        telemetry.addData("Snapshot post-START analysis", snapshotAnalysis);
        telemetry.update();
        switch(snapshotAnalysis) {
            case 0: left();
            case 2: right();
            default: center();
        }
    }

    @SuppressWarnings("EmptyMethod")
    private void left() {

    }

    @SuppressWarnings("EmptyMethod")
    private void right() {

    }

    @SuppressWarnings("EmptyMethod")
    private void center() {

    }
}