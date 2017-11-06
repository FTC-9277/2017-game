package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 11/4/2017.
 */
@Autonomous(name = "Jewel Auto")
public class jewelAuto extends LinearOpMode {
    Servo horiz, vert;
    ColorSensor color;
    int redThreshold = 0, blueThreshold = 0; //TODO: Set

    @Override
    public void runOpMode() throws InterruptedException {
        horiz = hardwareMap.get(Servo.class, "horizontal");
        vert = hardwareMap.get(Servo.class, "vertical");
        color = hardwareMap.get(ColorSensor.class, "color");

        waitForStart();

        wait(100);

        vert.setPosition(0); //TODO: Set

        wait(500);

        if(color.red() > redThreshold){
            telemetry.addData("Red", color.red());
            telemetry.update();
            horiz.setPosition(0); //TODO: Set
        } else if(color.blue() > blueThreshold){
            telemetry.addData("Blue", color.red());
            telemetry.update();
            horiz.setPosition(0); //TODO: Set
        }

        wait(2000);
    }
}
