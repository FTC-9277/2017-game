package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics9277 on 10/26/2017.
 */

public class PositionPIDTest extends LinearOpMode{

    DcMotor fLeft, bLeft, fRight, bRight;

    @Override
    public void runOpMode() throws InterruptedException {
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bRight = hardwareMap.get(DcMotor.class, "bRight");

        bRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();


    }

    public void reset() {

    }

    public void forward(double pow) {
        while(){

        }
    }
}
