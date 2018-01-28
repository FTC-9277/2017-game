package org.firstinspires.ftc.teamcode.States.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.States.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.States.HazmatRobot;

/**
 * Created by Robotics9277 on 1/28/2018.
 */

//@TeleOp(name = "jewel test")
public class jewelTest extends OpMode {
   Servo horizontal, vertical;
   double horizontalServoPosition, verticalServoPosition;



    @Override
    public void init() {
        horizontalServoPosition = 1;
        verticalServoPosition = 1;
        horizontal = hardwareMap.get(Servo.class, "horizontal");
        vertical = hardwareMap.get(Servo.class, "vertical");

    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            horizontal.setPosition(1);
        } else {
            horizontal.setPosition(0);
        }

        if(gamepad1.y){
            vertical.setPosition(1);
        } else {
            vertical.setPosition(0);
        }

        telemetry.addData("horizontal servo", horizontal.getPosition());
        telemetry.addData("vertical servo", vertical.getPosition());
    }
    }
