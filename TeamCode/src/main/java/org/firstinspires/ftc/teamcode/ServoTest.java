package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 11/6/2017.
 */
@TeleOp(name = "Servo Test")
@Disabled
public class ServoTest extends OpMode {
    Servo rt,lt,rb,lb,ll,rl,horizontal,vertical;

    @Override
    public void init() {
        rt = hardwareMap.get(Servo.class, "rt");
        lt = hardwareMap.get(Servo.class, "lt");
        rb = hardwareMap.get(Servo.class, "rb");
        lb = hardwareMap.get(Servo.class, "lb");
        ll = hardwareMap.get(Servo.class, "ll");
        rl = hardwareMap.get(Servo.class, "rl");
        horizontal = hardwareMap.get(Servo.class, "horizontal");
        vertical = hardwareMap.get(Servo.class,"vertical");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            ll.setPosition(0.55);
        } else{
            ll.setPosition(0);
        }

        if(gamepad1.b){
            rl.setPosition(0.15);
        } else{
            rl.setPosition(1);
        }

        telemetry.addData("Left", ll.getPosition());
        telemetry.addData("Right", rl.getPosition());

        rt.setPosition(0.5);
        lt.setPosition(0.5);
        rb.setPosition(0.5);
        lb.setPosition(0.5);
    }
}
