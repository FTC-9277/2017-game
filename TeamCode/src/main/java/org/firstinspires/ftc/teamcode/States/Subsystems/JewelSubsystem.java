package org.firstinspires.ftc.teamcode.States.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.States.Framework.Subsystem;

/**
 * Created by robotics9277 on 11/18/2017.
 */

public class JewelSubsystem extends Subsystem {
    Servo horizontal, vertical;
    ColorSensor color;
    DistanceSensor distance;

    public JewelSubsystem(OpMode opmode, Servo horizontal, Servo vertical, ColorSensor color, DistanceSensor distance){
        super(opmode);

        this.horizontal = horizontal;
        this.vertical = vertical;
        this.color = color;
        this.distance = distance;
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void stop() {

    }

    public int getRed(){
        return color.red();
    }

    public int getBlue(){
        return color.blue();
    }

    public double getDist(){
        return distance.getDistance(DistanceUnit.CM);
    }

    public void setHorizontal(double pos){
        horizontal.setPosition(pos);
    }

    public void setVertical(double pos){
        vertical.setPosition(pos);
    }
}
