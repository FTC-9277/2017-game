package org.firstinspires.ftc.teamcode.Supers.Subsystems;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

/**
 * Created by robotics9277 on 11/18/2017.
 */

public class RelicSubsystem extends Subsystem {
    CRServo slide;
    Servo arm, claw;
    final double CLAW_OPEN = 0.56, CLAW_CLOSED = 0.04, ARM_DOWN = 0.172, ARM_UP = 0.9;


   public RelicSubsystem(OpMode opMode, CRServo slide, Servo arm, Servo claw){
       super(opMode);
       this.slide = slide;
       this.arm = arm;
       this.claw = claw;
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

    public void extend(double power){
       slide.setPower(power);
    }

    public void openClaw(boolean isOpen){
        claw.setPosition(isOpen ? CLAW_OPEN : CLAW_CLOSED);
    }

    public void armDown(boolean isDown){
        arm.setPosition(isDown ? ARM_DOWN : ARM_UP);
    }
}
