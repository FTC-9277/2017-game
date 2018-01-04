package org.firstinspires.ftc.teamcode.HardwareTests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by robotics9277 on 1/3/2018.
 */
@TeleOp(name = "Distance Test")
public class DistanceTest extends OpMode {
    DistanceSensor distance, color;

    @Override
    public void init() {
        distance = hardwareMap.get(DistanceSensor.class, "frontDist");
        color = hardwareMap.get(DistanceSensor.class, "color");
    }

    @Override
    public void loop() {
        telemetry.addData("Distance", distance.getDistance(DistanceUnit.CM));
        telemetry.addData("Inches", distance.getDistance(DistanceUnit.INCH));
        telemetry.addData("mm", distance.getDistance(DistanceUnit.MM));
        telemetry.addData("Color Distance", color.getDistance(DistanceUnit.CM));
        telemetry.addData("Color Inches", color.getDistance(DistanceUnit.INCH));
        telemetry.addData("Color mm", color.getDistance(DistanceUnit.MM));
        Log.d("Robot", "Distance: " + distance.getDistance(DistanceUnit.CM));
    }
}
