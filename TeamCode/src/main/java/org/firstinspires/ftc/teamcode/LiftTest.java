package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics9277 on 11/2/2017.
 */
@TeleOp(name = "Lift Test")
@Disabled
public class LiftTest extends OpMode{
    DcMotor lift1, lift2;

    @Override
    public void init() {
        lift1 = hardwareMap.get(DcMotor.class, "lift1");
        lift2 = hardwareMap.get(DcMotor.class, "lift2");
        lift2.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0.1) {
            lift1.setPower(1);
            lift2.setPower(1);
        } else if (gamepad1.left_trigger > 0.1) {
            lift1.setPower(-1);
            lift2.setPower(-1);
        } else {
            lift1.setPower(0);
            lift2.setPower(0);
        }
    }
}
