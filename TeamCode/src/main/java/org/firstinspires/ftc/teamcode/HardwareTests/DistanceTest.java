package org.firstinspires.ftc.teamcode.HardwareTests;

import android.util.Log;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceImpl;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by robotics9277 on 1/3/2018.
 */
@TeleOp(name = "Distance Test")
public class DistanceTest extends OpMode {
    DistanceSensor dist, color;
    LynxI2cColorRangeSensor distance;

    ModernRoboticsI2cRangeSensor range;

    I2cDeviceSynch light;

    @Override
    public void init() {
        color = hardwareMap.get(DistanceSensor.class, "color");

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
    }

    @Override
    public void loop() {
        telemetry.addData("Range", range.getDistance(DistanceUnit.CM));

        telemetry.addData("Color Distance", color.getDistance(DistanceUnit.CM));
        telemetry.addData("Color Inches", color.getDistance(DistanceUnit.INCH));
        telemetry.addData("Color mm", color.getDistance(DistanceUnit.MM));
        //Log.d("Robot", "Distance: " + distance.getDistance(DistanceUnit.CM));
    }
}
