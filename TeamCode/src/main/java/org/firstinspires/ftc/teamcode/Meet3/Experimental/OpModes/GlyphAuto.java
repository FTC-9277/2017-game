package org.firstinspires.ftc.teamcode.Meet3.Experimental.OpModes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.HazMatAutonomous;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Utils;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.HazmatRobot;

/**
 * Created by robotics9277 on 12/15/2017.
 */
@Autonomous(name = "Glyph Auto")
public class GlyphAuto extends HazMatAutonomous {
    HazmatRobot robot;

    double lPos, rPos;
    long current;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {
        robot.drive.setStrafeHeight(0.105);
    }

    @Override
    public void body() {
        while(robot.imu.getYaw() < 88){
            robot.drive.strafeArcadeDrive(0,0,-0.3);
            telemetry.addData("Yaw", robot.imu.getYaw());
            telemetry.addData("Left", robot.left.getPosition());
            telemetry.addData("Right", robot.right.getPosition());
        }

        robot.drive.strafeArcadeDrive(0,0,0);

        robot.drive.setStrafeHeight(0.2);

        Utils.sleep(500);

        robot.left.reset();
        robot.right.reset();

        Utils.sleep(100);

        while(robot.left.getPosition() < 1300 && robot.right.getPosition() < 1300){
            robot.drive.strafeArcadeDrive(0,-0.3,0);
            //robot.fLeft.setPower(0.5);
            //robot.bLeft.setPower(0.5);
            //robot.fRight.setPower(0.5);
            //robot.bRight.setPower(0.5);
            telemetry.addData("Left", robot.left.getPosition());
            telemetry.addData("Right", robot.right.getPosition());
        }
        robot.drive.strafeArcadeDrive(0,0,0);

        Utils.sleep(100);

        current = System.currentTimeMillis();

        robot.drive.setPIDTarget(180);
        robot.drive.enablePID(true,0.01,0.357); //0.014

        while(System.currentTimeMillis() - current < 4000){
            robot.drive.strafeArcadeDrive(0,0,0);
        }
        robot.drive.arcadeDrive(0,0);

        robot.drive.closePID();

        robot.drive.arcadeDrive(0,0);

        Log.d("Robot", "Range: " + robot.range.getDistance(DistanceUnit.CM));

        robot.drive.setStrafeHeight(0.1);

        Utils.sleep(1000);

        current = System.currentTimeMillis();

        /*while(System.currentTimeMillis() - current < 1000){
            robot.drive.strafeArcadeDrive(-0.3,0,0);
        }*/


        Utils.sleep(5000);
    }

    @Override
    public void exit() {
        robot.drive.arcadeDrive(0,0);
        Utils.sleep(5000);
    }
}
