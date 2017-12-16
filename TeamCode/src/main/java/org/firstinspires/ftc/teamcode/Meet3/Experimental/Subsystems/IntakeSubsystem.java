package org.firstinspires.ftc.teamcode.Meet3.Experimental.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.CRServoGroup;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Subsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class IntakeSubsystem extends Subsystem {
    private CRServoGroup ti, bi;
    private CRServo ci;

    public IntakeSubsystem(OpMode opmode, CRServoGroup ti, CRServoGroup bi, CRServo ci){
        super(opmode);

        this.ti = ti;
        this.bi = bi;
        this.ci = ci;
    }

    @Override
    public void enable() {
        ti.set(0);
        bi.set(0);
        ci.setPower(0);
    }

    @Override
    public void disable() {
        ti.set(0);
        bi.set(0);
        ci.setPower(0);
    }

    @Override
    public void stop() {

    }

    public void intakeTop(double pow){
        ti.set(pow);
    }

    public void intakeBottom(double pow){
        bi.set(pow);
    }

    public void intakeLift(double pow){
        ci.setPower(pow);
    }
}
