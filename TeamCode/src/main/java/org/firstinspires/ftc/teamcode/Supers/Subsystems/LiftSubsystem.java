package org.firstinspires.ftc.teamcode.Supers.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.TelemetryLog;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class LiftSubsystem extends Subsystem {
    TelemetryLog LiftLog;
    MotorGroup liftMotors;
    Servo ratchet;

    public LiftSubsystem(OpMode opmode, MotorGroup liftMotors, Servo rl){
        super(opmode);

        this.liftMotors = liftMotors;
        this.ratchet = rl;
        LiftLog = new TelemetryLog(opmode);
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
            ratchet.setPosition(0.6); //0.6
        } else{
            ratchet.setPosition(0);
        }
    }

    public void setLift(double pow){
        liftMotors.set(pow);
    }
}
