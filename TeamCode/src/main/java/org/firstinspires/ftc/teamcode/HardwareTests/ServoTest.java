package org.firstinspires.ftc.teamcode.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Experimental.HazmatRobot;

/**
 * Created by robotics9277 on 12/7/2017.
 */
@TeleOp(name = "Servo Hardware Test")
public class ServoTest extends OpMode {
    HazmatRobot robot;

    @Override
    public void init() {
        robot = new HazmatRobot(this);
    }

    public void start(){
        robot.ls.setPosition(0.5);
        robot.rs.setPosition(0.5);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            robot.ls.setPosition(0.6);
            robot.rs.setPosition(0.4);
        } else if(gamepad1.b){
            robot.ls.setPosition(0.4);
            robot.rs.setPosition(0.6);
        } else{
            robot.ls.setPosition(0.6);
            robot.rs.setPosition(0.4);
        }

        //to move up, increase left, decrease right
    }
}
