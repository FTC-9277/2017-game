package org.firstinspires.ftc.teamcode.Meet3.Experimental.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.HazMatAutonomous;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Utils;

/**
 * Created by robotics9277 on 12/10/2017.
 */
@Autonomous(name = "Red Jewel Auto")
public class RedJewelAuto extends HazMatAutonomous {
    Servo horizontal, vertical;
    ColorSensor color;
    DistanceSensor distance;

    @Override
    public void initHardware() {
        horizontal = hardwareMap.get(Servo.class, "horizontal");
        vertical = hardwareMap.get(Servo.class, "vertical");
        color = hardwareMap.get(ColorSensor.class, "color");
        distance = hardwareMap.get(DistanceSensor.class, "color");
    }

    @Override
    public void initAction() {

    }

    @Override
    public void body() {
        horizontal.setPosition(0.625);

        sleep(1000);

        vertical.setPosition(0.76);

        sleep(1000);

        if(distance.getDistance(DistanceUnit.CM) < 10){
            if(color.red() > color.blue()){
                horizontal.setPosition(horizontal.getPosition() - 0.3);
            } else if(color.blue() > color.red()){
                horizontal.setPosition(horizontal.getPosition() + 0.3);
            } else{
                telemetry.addData("Jewel Color"," Not Identified");
            }
        } else{
            telemetry.addData("Jewels", "Not Found");
        }

        sleep(2000);

        //horizontal.setPosition(0.625);

        sleep(500);

        vertical.setPosition(0.1);

        Utils.sleep(2000);
    }

    @Override
    public void exit() {

    }
}
