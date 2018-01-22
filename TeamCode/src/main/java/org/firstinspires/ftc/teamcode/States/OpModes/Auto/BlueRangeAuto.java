package org.firstinspires.ftc.teamcode.States.OpModes.Auto;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.States.Framework.HazMatAutonomous;
import org.firstinspires.ftc.teamcode.States.Framework.Utils;
import org.firstinspires.ftc.teamcode.States.HazmatRobot;

/**
 * Created by robotics9277 on 12/15/2017.
 */
@Autonomous(name = "Blue Range Auto")
public class BlueRangeAuto extends HazMatAutonomous {
    HazmatRobot robot;

    double target;
    long current;

    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    final int lTarget = 0, cTarget = 0, rTarget = 0;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {

        robot.drive.setStrafeHeight(0.11);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "Ae1zrnj/////AAAAGav1yQVpZUSKniMroT7r6iABdn4S2dvC3kr8kHPXmGVTUv+TPviY2luSPOvIb3766OLU7oSCOdj3mv92vurqm8ijBYuzI0lnp51aYSsHH3y4GBTw77Kpvav2XNkHJ1TtcUtou0aZH2hw4N5RrYKXXf+ahVWuXvUMJqU3ccVWCMxNA76qQfRTexbnryWrFftMXgM5+1QTR6srigPms0lW86MFJJ9AzwdB2WVbZe6PoEeiEgoOjd1/AAbTCMML2O7vRaM8eXCL1NS8SDZ3a2bJ6jopy/ChNkjMuQboWGn2A29XDcANIM+y28S+o0jfCWg7eMlai5HFdU0IZnPJ/efMbLsnddFyuGQNzihNS2ocx2mZ";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
    }

    @Override
    public void body() throws InterruptedException {
        relicTrackables.activate();

        robot.horizontal.setPosition(0.625);

        Utils.sleep(200);

        robot.vertical.setPosition(0.7);

        Utils.sleep(1000);

        if(robot.distance.getDistance(DistanceUnit.CM) < 20){
            if(robot.color.red() > robot.color.blue()){
                robot.horizontal.setPosition(robot.horizontal.getPosition() + 0.3);
                Utils.sleep(500);
                robot.horizontal.setPosition(robot.horizontal.getPosition() - 0.2);
            } else if(robot.color.blue() > robot.color.red()){
                robot.horizontal.setPosition(robot.horizontal.getPosition() - 0.3);
                Utils.sleep(500);
                robot.horizontal.setPosition(robot.horizontal.getPosition() + 0.2);
            } else{
                telemetry.addData("Jewel Color"," Not Identified");
            }
        } else{
            telemetry.addData("Jewels", "Not Found");
        }

        Utils.sleep(500);

        robot.vertical.setPosition(0.1);

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

        telemetry.addData("Vumark", vuMark);
        Log.d("Robot", "Vumark: " + vuMark);

        Utils.sleep(500);

        relicTrackables.deactivate();

        while(robot.imu.getYaw() < 88 && opModeIsActive() && !isStopRequested()){
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

        while(robot.left.getPosition() < 1300 && robot.right.getPosition() < 1300 && opModeIsActive() && !isStopRequested()){
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

        while(System.currentTimeMillis() - current < 4000 && opModeIsActive() && !isStopRequested()){
            robot.drive.strafeArcadeDrive(0,0,0);
        }
        robot.drive.arcadeDrive(0,0);

        robot.drive.closePID();

        robot.drive.arcadeDrive(0,0);

        Utils.sleep(100);

        robot.strafe.reset();

        if(vuMark == RelicRecoveryVuMark.LEFT){
            robot.drive.setStrafeHeight(0.1);
            Utils.sleep(1000);

            robot.strafe.reset();

            while(robot.strafe.getPosition() < 40  && opModeIsActive() && !isStopRequested()){
                robot.drive.strafeArcadeDrive(-0.1,0,0);
                Log.d("Robot", "Strafe: "+ + robot.strafe.getPosition());
            }
            robot.drive.strafeArcadeDrive(0,0,0);

            robot.drive.setStrafeHeight(0.13);

            Utils.sleep(250);

            alignForward();
            Utils.sleep(500);
            score();
        } else if(vuMark == RelicRecoveryVuMark.CENTER || vuMark == RelicRecoveryVuMark.UNKNOWN){
            robot.drive.setStrafeHeight(0.1);
            Utils.sleep(1000);

            robot.strafe.reset();

            while(robot.strafe.getPosition() > -200 && opModeIsActive() && !isStopRequested()){
                robot.drive.strafeArcadeDrive(0.1,0,0);
                Log.d("Robot", "Strafe: "+ + robot.strafe.getPosition());
            }
            robot.drive.strafeArcadeDrive(0,0,0);

            robot.drive.setStrafeHeight(0.13);

            Utils.sleep(250);

            alignForward();
            Utils.sleep(250);
            score();
        }
        else if(vuMark == RelicRecoveryVuMark.RIGHT){
            robot.drive.setStrafeHeight(0.1);
            Utils.sleep(1000);

            robot.strafe.reset();

            while(robot.strafe.getPosition() > -450 && opModeIsActive() && !isStopRequested()){
                robot.drive.strafeArcadeDrive(0.1,0,0);
                Log.d("Robot", "Strafe: "+ + robot.strafe.getPosition());
            }
            robot.drive.strafeArcadeDrive(0,0,0);

            robot.drive.setStrafeHeight(0.13);

            Utils.sleep(250);

            alignForward();
            Utils.sleep(250);
            score();
        }
    }

    @Override
    public void exit() throws InterruptedException {
        robot.drive.arcadeDrive(0,0);
        Utils.sleep(1000);
    }

    public void alignForward(){
        Log.d("Robot", "Range 1: " + robot.lfRange.getDistance(DistanceUnit.CM));

        if(robot.lfRange.getDistance(DistanceUnit.CM) > 25){
            target = 18;
        } else{
            target = 14;
        }

        Log.d("Robot", "Target: " + target);

        while(robot.lfRange.getDistance(DistanceUnit.CM) > target && opModeIsActive() && !isStopRequested()){
            robot.drive.strafeArcadeDrive(0,-0.05,0);
        }

        robot.drive.arcadeDrive(0,0);

        Log.d("Robot", "Range 2: " + robot.lfRange.getDistance(DistanceUnit.CM));

    }

    public void score() throws InterruptedException {
        Utils.sleep(250);

        current = System.currentTimeMillis();

        while(System.currentTimeMillis() - current < 3000 && opModeIsActive() && !isStopRequested()){
            robot.lt.setPower(-0.5);
            robot.lb.setPower(-0.5);
            robot.rt.setPower(-0.5);
            robot.rb.setPower(-0.5);
        }

        robot.left.reset();
        robot.right.reset();

        while(robot.left.getPosition() > -200 && robot.right.getPosition() > -200 && opModeIsActive() && !isStopRequested()){
            robot.drive.strafeArcadeDrive(0,0.1,0);
            //robot.fLeft.setPower(0.5);
            //robot.bLeft.setPower(0.5);
            //robot.fRight.setPower(0.5);
            //robot.bRight.setPower(0.5);
            telemetry.addData("Left", robot.left.getPosition());
            telemetry.addData("Right", robot.right.getPosition());
        }
        robot.drive.strafeArcadeDrive(0,0,0);

        robot.lt.setPower(0);
        robot.lb.setPower(0);
        robot.rt.setPower(0);
        robot.rb.setPower(0);

        Utils.sleep(500);
    }
}
