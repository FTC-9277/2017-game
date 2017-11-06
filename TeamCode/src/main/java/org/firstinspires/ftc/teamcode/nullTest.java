package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 11/4/2017.
 */
@TeleOp(name = "Null Test")
public class nullTest extends OpMode{
    Servo horiz, vert;
    ColorSensor color;

    @Override
    public void init() {
        horiz = hardwareMap.servo.get("horizontal");
        //vert = hardwareMap.get(Servo.class, "vertical");
        color = hardwareMap.get(ColorSensor.class, "color");
    }

    @Override
    public void loop() {
        telemetry.addData("Horizontal", horiz.getPosition());
        horiz.setPosition(0.5);
        telemetry.addData("Port Num",horiz.getPortNumber());
        //vert.setPosition(0.5);
        //telemetry.addData("Vertical", vert.getPosition());
        telemetry.addData("Red", color.red());
        telemetry.addData("Blue", color.blue());
        telemetry.update();
    }
}
