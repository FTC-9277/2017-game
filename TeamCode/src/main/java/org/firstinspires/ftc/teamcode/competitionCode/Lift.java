package org.firstinspires.ftc.teamcode.competitionCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by robotics9277 on 11/4/2017.
 */

public class Lift {
    Log liftLog;
    MotorGroup lift;
    ServoGroup intake;
    boolean intakeEnabled = false;

    public Lift(OpMode opMode, MotorGroup lift){
        this.lift = lift;
        liftLog = new Log(opMode);

        lift.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }

    public Lift(OpMode opMode, MotorGroup lift, ServoGroup intake){
        this.lift = lift;
        this.intake = intake;
        liftLog = new Log(opMode);
        intakeEnabled = true;

        lift.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }

    public void set(double power){
        lift.set(power);
    }

    public void setIntake(double power){
        intake.set(power);
    }
}
