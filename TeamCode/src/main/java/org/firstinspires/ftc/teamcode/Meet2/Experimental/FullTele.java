package org.firstinspires.ftc.teamcode.Meet2.Experimental;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Meet2.Experimental.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Commands.LiftCommand;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework.HazMatTeleOp;

/**
 * Created by robotics9277 on 12/10/2017.
 */
@TeleOp(name = "Full Tele")
public class FullTele extends HazMatTeleOp {
    HazmatRobot robot;
    DriveCommand drive;
    LiftCommand lift;
    IntakeCommand intake;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);

        drive = new DriveCommand(this,robot.drive);
        lift = new LiftCommand(this, robot.lift);
        intake = new IntakeCommand(this, robot.intake);
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.3);
        dController.setTriggerDeadzone(0.1);

        mController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.3);
        mController.setTriggerDeadzone(0.1);

        drive.enable();
        lift.enable();
        intake.enable();
    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        robot.vertical.setPosition(0.1);
    }

    @Override
    public void exit() {

    }
}
