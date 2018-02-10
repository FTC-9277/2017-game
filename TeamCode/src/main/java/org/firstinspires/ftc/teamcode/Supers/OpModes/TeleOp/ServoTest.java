package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by robotics9277 on 12/15/2017.
 */
@TeleOp(name = "Servo Test")
public class ServoTest extends ExplosiveTele {
    Servo claw, arm;
    double clawPos, armPos;
    boolean aToggle = false, bToggle = false, xToggle = false, yToggle = false;

    @Override
    public void initHardware() {
        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(Servo.class, "arm");
    }

    @Override
    public void initAction() {

    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        if(dController.a()){
            if(!aToggle){
                aToggle = true;
                armPos += 0.1;
            }
        } else{
            aToggle = false;
        }

        if(dController.b()){
            if(!bToggle){
                bToggle = true;
                armPos -= 0.1;
            }
        } else{
            bToggle = false;
        }

        if(dController.x()){
            if(!xToggle){
                xToggle = true;
                armPos = 0.05;
            }
        } else{
            xToggle = false;
        }

        if(dController.y()){
            if(!yToggle){
                yToggle = true;
                armPos = 0.85;
            }
        } else{
            yToggle = false;
        }

        if(dController.leftTrigger() > 0.1){
            clawPos = 0.56; //0.65
        } else if(dController.rightTrigger() > 0.1){
            clawPos = 0.04; //0.13
        }

        telemetry.addData("Arm Position", armPos);
        /*robot.rs.setPosition(0.5 - rHeight);
        robot.ls.setPosition(0.5 + lHeight);*/

        claw.setPosition(clawPos);
        arm.setPosition(armPos);
    }

    @Override
    public void exit() {

    }
}
