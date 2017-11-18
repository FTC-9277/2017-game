package org.firstinspires.ftc.teamcode.miscOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by robotics9277 on 10/26/2017.
 */

public class PositionPIDTest extends LinearOpMode{

    DcMotor fLeft, bLeft, fRight, bRight, strafe;
    double pow;

    @Override
    public void runOpMode() throws InterruptedException {
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        strafe = hardwareMap.get(DcMotor.class, "strafe");

        fRight.setDirection(DcMotorSimple.Direction.REVERSE);
        bRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();


    }

    public void reset() {
        fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void forward(double leftDist, double rightDist) {
        double leftPow, rightPow;
        leftPow = leftDist - .5*(bLeft.getCurrentPosition() + fLeft.getCurrentPosition());
        rightPow = rightDist - .5*(fRight.getCurrentPosition() + bRight.getCurrentPosition());

        fLeft.setPower(leftPow);
        bLeft.setPower(leftPow);
        bRight.setPower(rightPow);
        fRight.setPower(rightPow);

    }
}
