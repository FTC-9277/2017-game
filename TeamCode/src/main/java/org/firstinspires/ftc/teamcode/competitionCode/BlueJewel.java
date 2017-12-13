package org.firstinspires.ftc.teamcode.competitionCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 11/4/2017.
 */
@Autonomous(name = "Blue Jewel")
@Disabled
public class BlueJewel extends LinearOpMode {
    Servo horiz, vert, rt,lt;
    ColorSensor color;

    @Override
    public void runOpMode() throws InterruptedException {
        horiz = hardwareMap.get(Servo.class, "horizontal");
        vert = hardwareMap.get(Servo.class, "vertical");
        rt = hardwareMap.get(Servo.class, "rt");
        lt = hardwareMap.get(Servo.class, "lt");
        color = hardwareMap.get(ColorSensor.class, "color");

        rt.setPosition(0.5);
        lt.setPosition(0.5);

        horiz.setPosition(0.6);
        vert.setPosition(0.06);

        waitForStart();

        sleep(100);

        telemetry.addData("Stage", 1);
        telemetry.update();

        vert.setPosition(0.5);

        sleep(1000);

        vert.setPosition(0.75);

        sleep(3000);

        telemetry.addData("Stage", 2);
        telemetry.update();

        if(color.red() > color.blue()){
            telemetry.addData("Red", color.red());
            telemetry.update();
            horiz.setPosition(0.5);
        } else if(color.blue() > color.red()){
            telemetry.addData("Blue", color.red());
            telemetry.update();
            horiz.setPosition(0.8);
        } else {
            telemetry.addData("Color not found", "RIP");
            telemetry.update();
        }

        sleep(5000);

        telemetry.addData("Stage", 3);
        telemetry.update();

        sleep(2000);
    }
}
