package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp.Utility;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;
import org.firstinspires.ftc.teamcode.Supers.Misc.PIDDashboard;
import org.firstinspires.ftc.teamcode.Supers.Misc.PIDTunable;

/**
 * Created by Varun on 2/26/2018.
 */

@TeleOp(name = "Remote PID Tuner ")
public class RemotePIDTuner extends ExplosiveTele {
    HazmatRobot robot;
    DriveCommand drive;
    PIDDashboard dash;
    PIDTunable tunable;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
        drive = new DriveCommand(this,robot.drive);
        dash = new PIDDashboard(this);
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.3);
        dController.setTriggerDeadzone(0.1);

        drive.enable();
    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        if(dash.hasNew()){
            tunable = dash.get();
            robot.drive.retunePID(tunable.get(0), tunable.get(1), tunable.get(2)); //current stationary: p: 0.02 i: 0.00005 (might be due to low battery)
            telemetry.addData("kP", tunable.get(0));
            telemetry.addData("kI", tunable.get(1));
            telemetry.addData("kD", tunable.get(2));
        }
    }

    @Override
    public void exit() {
        dash.close();
    }
}
