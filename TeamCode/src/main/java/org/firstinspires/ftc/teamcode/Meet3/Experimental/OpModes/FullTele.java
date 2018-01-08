package org.firstinspires.ftc.teamcode.Meet3.Experimental.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Meet3.Experimental.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Commands.LiftCommand;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.HazmatRobot;

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

        if(mController.b()){
            robot.lt.setPower(0.5);
            robot.lb.setPower(0.5);
            robot.rb.setPower(-0.5);
            robot.rt.setPower(-0.5);
        }
    }

    @Override
    public void exit() {

    }
}
