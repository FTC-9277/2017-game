package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp;

import android.hardware.camera2.CameraDevice;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
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
    VuforiaLocalizer vuforia;

    long current, last = 0;
    double lCurrent, rCurrent, lPrev = 0, rPrev = 0;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);

        drive = new DriveCommand(this,robot.drive);
        lift = new LiftCommand(this, robot.lift);
        intake = new IntakeCommand(this, robot.intake);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "Ae1zrnj/////AAAAGav1yQVpZUSKniMroT7r6iABdn4S2dvC3kr8kHPXmGVTUv+TPviY2luSPOvIb3766OLU7oSCOdj3mv92vurqm8ijBYuzI0lnp51aYSsHH3y4GBTw77Kpvav2XNkHJ1TtcUtou0aZH2hw4N5RrYKXXf+ahVWuXvUMJqU3ccVWCMxNA76qQfRTexbnryWrFftMXgM5+1QTR6srigPms0lW86MFJJ9AzwdB2WVbZe6PoEeiEgoOjd1/AAbTCMML2O7vRaM8eXCL1NS8SDZ3a2bJ6jopy/ChNkjMuQboWGn2A29XDcANIM+y28S+o0jfCWg7eMlai5HFdU0IZnPJ/efMbLsnddFyuGQNzihNS2ocx2mZ";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
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
