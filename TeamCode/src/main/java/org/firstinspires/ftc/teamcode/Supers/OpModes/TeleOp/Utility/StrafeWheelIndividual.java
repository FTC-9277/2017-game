package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp.Utility;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by robotics9277 on 12/15/2017.
 */
@TeleOp(name = "Strafe Height Individual")
public class StrafeWheelIndividual extends ExplosiveTele {
    HazmatRobot robot;

    double lHeight = 0, rHeight = 0;
    boolean aToggle = false, bToggle = false, xToggle = false, yToggle = false;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
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
                rHeight += 0.01;
            }
        } else{
            aToggle = false;
        }

        if(dController.b()){
            if(!bToggle){
                bToggle = true;
                rHeight -= 0.01;
            }
        } else{
            bToggle = false;
        }

        if(dController.x()){
            if(!xToggle){
                xToggle = true;
                lHeight += 0.01;
            }
        } else{
            xToggle = false;
        }

        if(dController.y()){
            if(!yToggle){
                yToggle = true;
                lHeight -= 0.01;
            }
        } else{
            yToggle = false;
        }
        telemetry.addData("Right Height", rHeight);
        telemetry.addData("Left Height", lHeight);
        robot.rs.setPosition(0.5 - rHeight);
        robot.ls.setPosition(0.5 + lHeight);

        //robot.drive.setStrafeHeight(rHeight);

        robot.strafe.set(dController.lx());
    }

    @Override
    public void exit() {

    }
}
