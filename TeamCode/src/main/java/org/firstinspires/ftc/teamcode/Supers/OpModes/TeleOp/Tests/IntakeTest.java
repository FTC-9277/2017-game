package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Supers.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by robotics9277 on 1/6/2018.
 */
@TeleOp(name="Intake Test ")
public class IntakeTest extends ExplosiveTele {
    HazmatRobot robot;
    IntakeCommand intake;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
        intake = new IntakeCommand(this, robot.intake);
    }

    @Override
    public void initAction() {
        mController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.3);
        mController.setTriggerDeadzone(0.1);

        intake.enable();
    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {

    }

    @Override
    public void exit() {

    }
}
