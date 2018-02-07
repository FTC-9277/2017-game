package org.firstinspires.ftc.teamcode.Supers.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Utils;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by robotics9277 on 12/10/2017.
 */
@Autonomous(name = "Red Jewel Auto")
public class RedJewelAuto extends ExplosiveAuto {
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
