package org.firstinspires.ftc.teamcode.miscOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 12/6/2017.
 */
@TeleOp (name = "ServoTest2")
public class ServoTest2 extends OpMode {
    Servo ll, rl;

    CRServo rt, lt, rb, lb, il;

    @Override
    public void init() {
        rt = hardwareMap.get(CRServo.class, "rt");
        lt = hardwareMap.get(CRServo.class, "lt");
        rb = hardwareMap.get(CRServo.class, "rb");
        lb = hardwareMap.get(CRServo.class, "lb");
        ll = hardwareMap.get(Servo.class, "ll");
        rl = hardwareMap.get(Servo.class, "rl");
        il = hardwareMap.get(CRServo.class, "il");
    }

    @Override
    public void loop() {

        if(gamepad1.x) {
            rt.setPower(-0.5);
            lt.setPower(0.5);
            rb.setPower(-0.5);
            lb.setPower(0.5);
            il.setPower(0.5);

            ll.setPosition(0.5);
            rl.setPosition(0.5);
        } else if(gamepad1.y){
            rt.setPower(0.5);
            lt.setPower(-0.5);
            rb.setPower(0.5);
            lb.setPower(-0.5);
        } else {
            rt.setPower(0);
            lt.setPower(0);
            rb.setPower(0);
            lb.setPower(0);
            il.setPower(0);

            ll.setPosition(0);
            rl.setPosition(0);
        }

        /*telemetry.addData("rt", rt.getPosition());
        telemetry.addData("lb", lb.getPosition());
        telemetry.addData("rb", rb.getPosition());
        telemetry.addData("lt", lt.getPosition());*/

    }
}