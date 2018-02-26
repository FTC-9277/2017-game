package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp.Utility;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by robotics9277 on 11/18/2017.
 */
@TeleOp(name = "Range Test", group = "Experimental")
public class RangeTest extends ExplosiveTele {
    HazmatRobot robot;

    long start, end, lsTime, rsTime, lfTime, rfTime;
    double ls,rs,lf,rf;

    boolean a = false, b = false, x = false, y = false;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.3);
    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        /*start = System.currentTimeMillis();
        ls = robot.lsRange.getDistance(DistanceUnit.CM);
        end = System.currentTimeMillis();
        lsTime = end-start;

        start = System.currentTimeMillis();
        rs = robot.rsRange.getDistance(DistanceUnit.CM);
        end = System.currentTimeMillis();
        rsTime = end-start;

        start = System.currentTimeMillis();
        lf = robot.lfRange.getDistance(DistanceUnit.CM);
        end = System.currentTimeMillis();
        lfTime = end-start;

        start = System.currentTimeMillis();
        rf = robot.rfRange.getDistance(DistanceUnit.CM);
        end = System.currentTimeMillis();
        rfTime = end-start;

        telemetry.addData("lsRange", ls);
        telemetry.addData("lfRange", lf);
        telemetry.addData("rsRange", rs);
        telemetry.addData("rfRange", rf);

        telemetry.addData("lsTime", lsTime);
        telemetry.addData("lfTime", lfTime);
        telemetry.addData("rsTime", rsTime);
        telemetry.addData("rfTime", rfTime);*/

        telemetry.addData("lsRange", robot.lsRange.getDistance(DistanceUnit.CM));
        telemetry.addData("lfRange", robot.lfRange.getDistance(DistanceUnit.CM));
        telemetry.addData("rsRange", robot.rsRange.getDistance(DistanceUnit.CM));
        telemetry.addData("rfRange", robot.rfRange.getDistance(DistanceUnit.CM));
    }

    @Override
    public void exit() {

    }
}
