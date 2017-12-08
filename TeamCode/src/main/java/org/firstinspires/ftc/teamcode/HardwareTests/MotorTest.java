package org.firstinspires.ftc.teamcode.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Experimental.HazmatRobot;

/**
 * Created by robotics9277 on 12/7/2017.
 */

@TeleOp(name = "Motor Test")
public class MotorTest extends OpMode{
    HazmatRobot robot;

    @Override
    public void init() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            robot.left.set(1);
        } else{
            robot.left.set(0);
        }

        if(gamepad1.b){
            robot.liftMotors.set(1);
        } else{
            robot.liftMotors.set(0);
        }

        if(gamepad1.x){
            robot.right.set(1);
        } else{
            robot.right.set(0);
        }

        if(gamepad1.y){
            robot.strafe.set(1);
        } else{
            robot.strafe.set(0);
        }
    }
}
