package org.firstinspires.ftc.teamcode.States.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.States.Framework.HazMatAutonomous;
import org.firstinspires.ftc.teamcode.States.HazmatRobot;

/**
 * Created by robotics9277 on 1/22/2018.
 */

@Autonomous(name = "Run Intake")
public class RunIntake extends HazMatAutonomous {
    HazmatRobot robot;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void body() throws InterruptedException {
        while(!isStopRequested()){
            robot.intake.intakeBottom(0.5);
            robot.intake.intakeTop(0.5);
        }
    }

    @Override
    public void exit() throws InterruptedException {

    }
}
