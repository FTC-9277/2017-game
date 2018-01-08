package org.firstinspires.ftc.teamcode.States.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.States.Framework.Log;
import org.firstinspires.ftc.teamcode.States.Framework.MotorGroup;
import org.firstinspires.ftc.teamcode.States.Framework.Subsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class LiftSubsystem extends Subsystem {
    Log LiftLog;
    MotorGroup liftMotors;
    Servo ll, rl;

    public LiftSubsystem(OpMode opmode, MotorGroup liftMotors, Servo ll, Servo rl){
        super(opmode);

        this.liftMotors = liftMotors;
        this.ll = ll;
        this.rl = rl;
        LiftLog = new Log(opmode);
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

    public void setLock(boolean locked){
        if(locked){
            ll.setPosition(0.45); //0.4
            rl.setPosition(0.35); //0.6
        } else{
            ll.setPosition(1);
            rl.setPosition(0);
        }
    }

    public void setLift(double pow){
        liftMotors.set(pow);
    }
}
