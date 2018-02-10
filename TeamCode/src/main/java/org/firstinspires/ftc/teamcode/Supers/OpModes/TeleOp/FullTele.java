package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Supers.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Supers.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Supers.Commands.LiftCommand;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by robotics9277 on 12/10/2017.
 */
@TeleOp(name = "Full Tele")
public class FullTele extends ExplosiveTele {

    HazmatRobot robot;
    DriveCommand drive;
    LiftCommand lift;
    IntakeCommand intake;

    long current, last = 0;
    double lCurrent, rCurrent, lPrev = 0, rPrev = 0;

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
            robot.lb.setPower(0.5);
            robot.rb.setPower(-0.5);
        }

        //telemetry.addData("Latency",robot.imu.getLatency());

        current = System.currentTimeMillis();
        lCurrent = robot.left.getPosition();
        rCurrent = robot.right.getPosition();

        telemetry.addData("Left Clicks", lCurrent);
        telemetry.addData("Right Clicks", rCurrent);
        telemetry.addData("Left Speed", ((lCurrent-lPrev)/(current-last)));
        telemetry.addData("Right Speed", ((rCurrent-rPrev)/(current-last)));

        last = current;
        lPrev = lCurrent;
        rPrev = rCurrent;
    }

    @Override
    public void exit() {

    }
}
