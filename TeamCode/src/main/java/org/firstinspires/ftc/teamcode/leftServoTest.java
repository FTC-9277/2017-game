package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 11/6/2017.
 */
@TeleOp(name = "Left Servos")
public class leftServoTest extends OpMode {
    Servo l1,l2,l3,l4;

    @Override
    public void init() {
        l1 = hardwareMap.get(Servo.class, "l1");
        l2 = hardwareMap.get(Servo.class, "l2");
        l3 = hardwareMap.get(Servo.class, "l3");
        l4 = hardwareMap.get(Servo.class, "l4");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            l1.setPosition(1);
            telemetry.addData("a", gamepad1.a);
        }
        else l1.setPosition(0.5);

        if(gamepad1.b) {
            l2.setPosition(1);
            telemetry.addData("b", gamepad1.b);

        }
        else l2.setPosition(0.5);

        if(gamepad1.x) {
            l3.setPosition(1);
            telemetry.addData("x", gamepad1.x);
        }
        else l3.setPosition(0.5);

        if(gamepad1.y) {
            l4.setPosition(1);
            telemetry.addData("y", gamepad1.y);
        }
        else l4.setPosition(0.5);

        telemetry.addData("l3 pos", l3.getPosition());
        telemetry.addData("l3 controller", l3.getController());
        telemetry.addData("l3 port", l3.getPortNumber());
        telemetry.update();

    }
}
