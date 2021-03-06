package org.firstinspires.ftc.teamcode.Supers.OpModes.Auto;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Utils;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by Varun on 2/10/2018.
 */
@Autonomous(name = "Intake Glyph Auto")
public class IntakeGlyphAuto extends ExplosiveAuto {
    HazmatRobot robot;
    long time;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {
        time = System.currentTimeMillis();
        robot.drive.setStrafeHeight(0.05 + 0.07);
    }

    @Override
    public void body() throws InterruptedException {
        double frontDist = 0;
        while(frontDist > 14 || frontDist < 3){
            frontDist = robot.rfRange.getDistance(DistanceUnit.CM);
            robot.drive.strafeArcadeDrive(0,-0.5,0);
            robot.lt.setPower(0.5);
            robot.lb.setPower(0.5);
            robot.rt.setPower(0.5);
            robot.rb.setPower(0.5);
        }
        Log.d("Robot", "Front Distance: " + frontDist);
        time = System.currentTimeMillis();

        robot.drive.setStrafeHeight(-0.05 + 0.07);
        //Utils.sleep(500);

        frontDist = 0;
        while(frontDist > 10 || frontDist < 3){
            frontDist = robot.rfRange.getDistance(DistanceUnit.CM);
            robot.drive.strafeArcadeDrive(0.3,-0.4,0);
            robot.lt.setPower(0.5);
            robot.lb.setPower(0.5);
            robot.rt.setPower(0.5);
            robot.rb.setPower(0.5);
        }
        time = System.currentTimeMillis();

        /*while(System.currentTimeMillis() - time < 4000){
            robot.drive.strafeArcadeDrive(-0.3,-0.3,0);
            robot.lt.setPower(0.5);
            robot.lb.setPower(0.5);
            robot.rt.setPower(0.5);
            robot.rb.setPower(0.5);
        }
        time = System.currentTimeMillis();

        while(System.currentTimeMillis() - time < 1000){
            robot.drive.strafeArcadeDrive(0,0,0);
            robot.lt.setPower(0.5);
            robot.lb.setPower(0.5);
            robot.rt.setPower(0.5);
            robot.rb.setPower(0.5);
        }*/
        time = System.currentTimeMillis();

        robot.drive.setStrafeHeight(0.05 + 0.07);
        Utils.sleep(500);

        while(System.currentTimeMillis() - time < 3000){
            robot.drive.strafeArcadeDrive(0,0.3,0);
            robot.lt.setPower(0.5);
            robot.lb.setPower(0.5);
            robot.rt.setPower(0.5);
            robot.rb.setPower(0.5);
        }
    }

    @Override
    public void exit() throws InterruptedException {

    }
}
