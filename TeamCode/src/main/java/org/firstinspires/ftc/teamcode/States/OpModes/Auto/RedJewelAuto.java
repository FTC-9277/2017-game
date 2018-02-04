package org.firstinspires.ftc.teamcode.States.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

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
 * Created by robotics9277 on 12/10/2017.
 */
@Autonomous(name = "Red Jewel Auto")
public class RedJewelAuto extends HazMatAutonomous {
    HazmatRobot robot;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void body() throws InterruptedException {
        robot.horizontal.setPosition(0.625);

        sleep(250);

        robot.vertical.setPosition(0.8);

        sleep(1000);

        if(robot.distance.getDistance(DistanceUnit.CM) < 20){
            if(robot.color.red() > robot.color.blue()){
                robot.horizontal.setPosition(robot.horizontal.getPosition() - 0.3);
                Utils.sleep(500);
                robot.horizontal.setPosition(robot.horizontal.getPosition() + 0.2);
            } else if(robot.color.blue() > robot.color.red()){
                robot.horizontal.setPosition(robot.horizontal.getPosition() + 0.3);
                Utils.sleep(500);
                robot.horizontal.setPosition(robot.horizontal.getPosition() - 0.2);
            } else{
                telemetry.addData("Jewel Color"," Not Identified");
            }
        } else{
            telemetry.addData("Jewels", "Not Found");
        }

        Utils.sleep(500);

        robot.vertical.setPosition(0.1);

        Utils.sleep(1000);
    }

    @Override
    public void exit() {

    }
}
