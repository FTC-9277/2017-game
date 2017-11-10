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
    Servo horiz, vert, rt,lt;
    ColorSensor color;
    int redThreshold = 0, blueThreshold = 0; //TODO: Set

    @Override
    public void runOpMode() throws InterruptedException {
        horiz = hardwareMap.get(Servo.class, "horizontal");
        vert = hardwareMap.get(Servo.class, "vertical");
        rt = hardwareMap.get(Servo.class, "rt");
        lt = hardwareMap.get(Servo.class, "lt");
        color = hardwareMap.get(ColorSensor.class, "color");

        rt.setPosition(0.5);
        lt.setPosition(0.5);

        horiz.setPosition(0.7);

        waitForStart();

        Thread.sleep(100);

        telemetry.addData("Stage", 1);
        telemetry.update();

        vert.setPosition(0.7);

        Thread.sleep(100);

        vert.setPosition(1); //TODO: Set

        Thread.sleep(500);

        telemetry.addData("Stage", 2);
        telemetry.update();

        if(color.red() > color.blue()){
            telemetry.addData("Red", color.red());
            telemetry.update();
            horiz.setPosition(0.4); //TODO: Set
        } else if(color.blue() > color.red()){
            telemetry.addData("Blue", color.red());
            telemetry.update();
            horiz.setPosition(1); //TODO: Set
        }

        telemetry.addData("Stage", 3);
        telemetry.update();

        Thread.sleep(2000);
    }
}
