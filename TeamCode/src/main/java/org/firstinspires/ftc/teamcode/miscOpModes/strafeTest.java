package org.firstinspires.ftc.teamcode.miscOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics9277 on 11/4/2017.
 */
@TeleOp(name = "Strafe test")
@Disabled
public class strafeTest extends OpMode {
    DcMotor strafe;
    @Override
    public void init() {
        strafe = hardwareMap.get(DcMotor.class, "strafe");
    }

    @Override
    public void loop() {
        strafe.setPower(gamepad1.right_stick_x);
    }
}
