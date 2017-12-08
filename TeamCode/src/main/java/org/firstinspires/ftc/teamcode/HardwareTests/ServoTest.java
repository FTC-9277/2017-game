package org.firstinspires.ftc.teamcode.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Experimental.Robot;

/**
 * Created by robotics9277 on 12/7/2017.
 */
@TeleOp(name = "Servo Hardware Test")
public class ServoTest extends OpMode {
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(this);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            robot.horizontal.setPosition(1);
        } else{
            robot.horizontal.setPosition(0.5);
        }

        if(gamepad1.b){
            robot.vertical.setPosition(1);
        } else{
            robot.vertical.setPosition(0.5);
        }
    }
}
