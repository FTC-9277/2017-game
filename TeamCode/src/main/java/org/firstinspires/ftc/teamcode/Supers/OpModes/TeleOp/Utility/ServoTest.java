package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp.Utility;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;
import org.firstinspires.ftc.teamcode.Supers.Misc.PIDDashboard;

/**
 * Created by robotics9277 on 12/15/2017.
 */
@TeleOp(name = "Servo Test ")
public class ServoTest extends ExplosiveTele {
    CRServo slide;
    Servo claw, arm;
    double clawPos, armPos;
    boolean aToggle = false, bToggle = false, xToggle = false, yToggle = false;
    PIDDashboard dash;

    @Override
    public void initHardware() {
        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(Servo.class, "arm");
        slide = hardwareMap.get(CRServo.class, "ll");
        dash = new PIDDashboard(this);
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.1);
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
                armPos = 0.225;
            }
        } else{
            xToggle = false;
        }

        if(dController.y()){
            if(!yToggle){
                yToggle = true;
                armPos = 0.6;
            }
        } else{
            yToggle = false;
        }

        if(dController.leftTrigger() > 0.1){
            clawPos = 0.56; //0.65
        } else if(dController.rightTrigger() > 0.1){
            clawPos = 0.04; //0.13
        }

        slide.setPower(dController.ly() / 2);

        telemetry.addData("Arm Position", armPos);
        /*robot.rs.setPosition(0.5 - rHeight);
        robot.ls.setPosition(0.5 + lHeight);*/

        if(dash.hasNew()){
            clawPos = dash.get().get(0);
            armPos = dash.get().get(1);
        }

        claw.setPosition(clawPos);
        arm.setPosition(armPos);
    }

    @Override
    public void exit() {
        dash.close();
    }
}
