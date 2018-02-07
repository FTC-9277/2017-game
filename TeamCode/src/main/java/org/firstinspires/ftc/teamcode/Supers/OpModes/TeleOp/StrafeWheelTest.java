package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.HazmatRobot;

/**
 * Created by robotics9277 on 12/15/2017.
 */
@TeleOp(name = "Strafe Wheel Test")
public class StrafeWheelTest extends ExplosiveTele {
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
                rHeight += 0.001;
            }
        } else{
            xToggle = false;
        }

        if(dController.y()){
            if(!yToggle){
                yToggle = true;
                rHeight -= 0.001;
            }
        } else{
            yToggle = false;
        }
        telemetry.addData("Height", rHeight);
        /*robot.rs.setPosition(0.5 - rHeight);
        robot.ls.setPosition(0.5 + lHeight);*/

        robot.drive.setStrafeHeight(rHeight);

        robot.strafe.set(dController.lx());
    }

    @Override
    public void exit() {

    }
}
