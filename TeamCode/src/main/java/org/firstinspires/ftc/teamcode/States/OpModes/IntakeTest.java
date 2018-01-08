package org.firstinspires.ftc.teamcode.States.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

/**
 * Created by robotics9277 on 1/6/2018.
 */
@TeleOp(name="Intake Test")
public class IntakeTest extends OpMode {
    CRServo rt,lt,rb,lb;

    @Override
    public void init() {
        rt = hardwareMap.get(CRServo.class, "rt");
        lt = hardwareMap.get(CRServo.class, "lt");
        rb = hardwareMap.get(CRServo.class, "rb");
        lb = hardwareMap.get(CRServo.class, "lb");
    }

    @Override
    public void loop() {
        rt.setPower(gamepad1.right_stick_y/2);
        rb.setPower(gamepad1.right_stick_y/2);
        lt.setPower(gamepad1.left_stick_y/2);
        lb.setPower(gamepad1.left_stick_y/2);
    }
}
