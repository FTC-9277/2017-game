package org.firstinspires.ftc.teamcode.Supers.OpModes.Auto;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by Varun on 3/1/2018.
 */

@Autonomous(name = "Red Multi Glyph")
public class RedMultiGlyph extends ExplosiveAuto {
    HazmatRobot robot;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {
        robot.drive.setStrafeHeight(0.05 + 0.07);
    }

    @Override
    public void body() throws InterruptedException {
        robot.right.reset();
        //robot.left.reset();
        while(robot.right.getPosition() < 500 || robot.left.getPosition() < 1000){
            robot.drive.strafeArcadeDrive(0, -0.3, 0.18);
            //Log.d("Robot", "Right: " + robot.right.getPosition());
            //Log.d("Robot", "Left: " + robot.left.getPosition());
        }

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
        long current = System.currentTimeMillis();

        robot.drive.setStrafeHeight(-0.05 + 0.07);
        //Utils.sleep(500);

        frontDist = 0;
        while(frontDist > 10 || frontDist < 3){
            frontDist = robot.rfRange.getDistance(DistanceUnit.CM);
            robot.drive.strafeArcadeDrive(0.5,-0.4,0);
            robot.lt.setPower(0.5);
            robot.lb.setPower(0.5);
            robot.rt.setPower(0.5);
            robot.rb.setPower(0.5);
        }
        robot.drive.arcadeDrive(0,0);
        Log.d("Robot", "Front Distance: " + frontDist);
        time = System.currentTimeMillis();

        Log.d("Robot", "Strafe Wheel Reset");
        robot.drive.setPIDTarget(0);
        robot.drive.enablePID(true, 0.02, 0.00003, 0);
        robot.strafe.reset();

        current = System.currentTimeMillis();
        while(System.currentTimeMillis() - current < 1000){
            robot.drive.strafeArcadeDrive(0,0,0);
            //Log.d("Robot", "Timer: " + (System.currentTimeMillis() - time));
        }
        //robot.drive.enablePID(false);
        robot.drive.arcadeDrive(0,0);

        double sideDist = robot.lsRange.getDistance(DistanceUnit.CM);
        if(sideDist > 500) sideDist = 0;
        while(sideDist < 115 || sideDist > 125){
            robot.drive.strafeArcadeDrive(sideDist > 120 ? -0.3 : 0.3, 0, 0);
            sideDist = robot.lsRange.getDistance(DistanceUnit.CM);
            if(sideDist > 500) sideDist = 0;
        }
        robot.drive.strafeArcadeDrive(0,0,0);
        Log.d("Robot", "Side Distance: " + sideDist);

        robot.drive.retunePID(0,0,0);
        robot.right.reset();
        robot.left.reset();

        robot.drive.setStrafeHeight(0.05 + 0.07);

        current = System.currentTimeMillis();
        while(System.currentTimeMillis() - current < 1000){
            //robot.drive.strafeArcadeDrive(0,0,0);
            //Log.d("Robot", "Timer: " + (System.currentTimeMillis() - time));
        }

        Log.d("Robot", "Backing up");
        /*while(robot.left.getPosition() > -500 && robot.right.getPosition() > -500){
            robot.drive.strafeArcadeDrive(0,0.3,0);
            robot.fLeft.setPower(0.5);
            robot.bLeft.setPower(0.5);
            robot.fRight.setPower(0.5);
            robot.bRight.setPower(0.5);
            telemetry.addData("Left", robot.left.getPosition());
            telemetry.addData("Right", robot.right.getPosition());
        }*/
        while(robot.right.getPosition() > -500 || robot.left.getPosition() > -500){
            robot.drive.strafeArcadeDrive(0, 0.3, 0);
            //Log.d("Robot", "Right: " + robot.right.getPosition());
            //Log.d("Robot", "Left: " + robot.left.getPosition());
        }
        robot.drive.strafeArcadeDrive(0.0,0,0);

        robot.drive.setPIDTarget(180);
        robot.drive.retunePID(0.02, 0.00004, 0);

        current = System.currentTimeMillis();
        while(System.currentTimeMillis() - current < 1000){
            robot.drive.strafeArcadeDrive(0,0,0);
            //Log.d("Robot", "Timer: " + (System.currentTimeMillis() - time));
        }
        robot.drive.resetPID();
        robot.drive.setPIDTarget(180);
        robot.drive.retunePID(0.01, 0.00002, 0);
        current = System.currentTimeMillis();
        while(System.currentTimeMillis() - current < 2000){
            robot.drive.strafeArcadeDrive(0,0,0);
            //Log.d("Robot", "Timer: " + (System.currentTimeMillis() - time));
        }

        robot.drive.retunePID(0, 0, 0);
        robot.drive.strafeArcadeDrive(0,0,0);

        frontDist = robot.lfRange.getDistance(DistanceUnit.CM);
        while(frontDist > 40 || frontDist < 10){
            robot.drive.strafeArcadeDrive(0, -0.3, 0);
            frontDist = robot.lfRange.getDistance(DistanceUnit.CM);
            if(frontDist > 500) frontDist = 0;
        }
        robot.drive.strafeArcadeDrive(0,0,0);
    }

    @Override
    public void exit() throws InterruptedException {

    }
}
