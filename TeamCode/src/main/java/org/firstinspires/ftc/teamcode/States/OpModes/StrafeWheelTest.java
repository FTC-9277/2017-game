package org.firstinspires.ftc.teamcode.States.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.States.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.States.HazmatRobot;

/**
 * Created by robotics9277 on 12/15/2017.
 */
@TeleOp(name = "Strafe Wheel Test")
public class StrafeWheelTest extends HazMatTeleOp {
    HazmatRobot robot;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        if(dController.a()){
            robot.drive.setStrafeHeight(0.1);
        } else if(dController.b()){
            robot.drive.setStrafeHeight(0.11);
        } else if(dController.x()){
            robot.drive.setStrafeHeight(0.12);
        } else if(dController.y()){
            robot.drive.setStrafeHeight(0.105);
        }
    }

    @Override
    public void exit() {

    }
}
