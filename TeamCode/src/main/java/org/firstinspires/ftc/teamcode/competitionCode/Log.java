package org.firstinspires.ftc.teamcode.competitionCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.command.dexer.Main;

import javax.lang.model.type.PrimitiveType;

/**
 * Created by robotics9277 on 11/4/2017.
 */

public class Log {
    Telemetry telemetry;

    public Log(OpMode opmode){
        this.telemetry = opmode.telemetry;
    }

    public void add(int msg){
        telemetry.addData("", msg);
    }

    public void add(double msg) {telemetry.addData("", msg);}

    public void add(String msg){
        telemetry.addData(msg, "");
    }

    public void add(String str, int i){
        telemetry.addData(str, i);
    }

    public void add(String str, double d){
        telemetry.addData(str, d);
    }

    public void add(String str, long l){
        telemetry.addData(str, l);
    }

    public void add(String str, boolean b) {telemetry.addData(str, b);}

    public void clear(){telemetry.clear();}

    public void update(){telemetry.update();}
}
