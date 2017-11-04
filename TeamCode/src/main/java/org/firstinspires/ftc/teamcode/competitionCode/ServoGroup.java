package org.firstinspires.ftc.teamcode.competitionCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 11/4/2017.
 */

public class ServoGroup {
    Servo[] servos;
    Log servoLog;

    public ServoGroup(OpMode opmode, Servo... servos) {
        this.servos = servos;
        servoLog = new Log(opmode);
    }

    public void setDirection(Servo.Direction... directions) {
        if (directions.length == servos.length) {
            int curr = 0;
            for (Servo current : servos) {
                current.setDirection(directions[curr]);
                curr++;
            }
        } else {
            servoLog.add("Set Reversed has incorrect # of args");
        }
    }

    public void set(double pow) {
        for (Servo servo : servos) {
            servo.setPosition(pow);
        }
    }
}
