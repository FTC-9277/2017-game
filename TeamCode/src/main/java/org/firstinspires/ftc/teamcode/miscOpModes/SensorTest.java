package org.firstinspires.ftc.teamcode.miscOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;

/**
 * Created by robotics9277 on 11/18/2017.
 */
@TeleOp(name = "Sensor Test")
@Disabled
public class SensorTest extends OpMode {
    //SensorREVColorDistance color;
    ColorSensor color;
    DistanceSensor distance;

    @Override
    public void init() {
       color = hardwareMap.get(ColorSensor.class, "color");
    }

    @Override
    public void loop() {
       telemetry.addData("Color", color.blue());
    }
}
