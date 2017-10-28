package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics9277 on 10/26/2017.
 */

public class PositionPIDTest extends OpMode{
    DcMotor fLeft, fRight, bLeft, bRight, strafe;

    @Override
    public void init() {
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        strafe = hardwareMap.get(DcMotor.class, "strafe");
    }

    @Override
    public void loop() {

    }

    public void setZero(DcMotor motor) {

    }
}
