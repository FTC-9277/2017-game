package org.firstinspires.ftc.teamcode.Supers.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.CRServoGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class IntakeSubsystem extends Subsystem {
    private CRServoGroup ti, bi;

    public IntakeSubsystem(OpMode opmode, CRServoGroup ti, CRServoGroup bi){
        super(opmode);

        this.ti = ti;
        this.bi = bi;
    }

    @Override
    public void enable() {
        ti.set(0);
        bi.set(0);
    }

    @Override
    public void disable() {
        ti.set(0);
        bi.set(0);
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
}
