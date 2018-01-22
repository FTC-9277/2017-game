package org.firstinspires.ftc.teamcode.JeffreyPracticeCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics9277 on 1/18/2018.
 */

public class PracticeCode1 extends LinearOpMode {

    DcMotor  bLeft, bRight, left, right;
    double clicks;


    @Override
    public void runOpMode() throws InterruptedException {
        bLeft = hardwareMap.get(DcMotor.class,"bLeft");
        bRight = hardwareMap.get(DcMotor.class,"bRight");
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class,"right");


    }
}
