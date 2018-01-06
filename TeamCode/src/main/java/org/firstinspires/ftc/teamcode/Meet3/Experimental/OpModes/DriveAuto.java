package org.firstinspires.ftc.teamcode.Meet3.Experimental.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics9277 on 1/5/2018.
 */
@Autonomous(name = "Drive Auto")
public class DriveAuto extends LinearOpMode {
    DcMotor fLeft,bLeft;
    @Override
    public void runOpMode() throws InterruptedException {
        fLeft = hardwareMap.get(DcMotor.class,"fLeft");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");

        waitForStart();

        long time = System.currentTimeMillis();

        while(System.currentTimeMillis() - time < 3000){
            fLeft.setPower(1);
            bLeft.setPower(1);
        }
    }
}
