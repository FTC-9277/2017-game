package org.firstinspires.ftc.teamcode.Supers.Commands;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Utils;
import org.firstinspires.ftc.teamcode.Supers.Subsystems.DriveSubsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class DriveCommand extends Command {
    Controller dController;
    DriveSubsystem drive;
    ExplosiveTele opmode;
    boolean balancingMode = false, slowToggle = false, isSlow = false;
    double speedModifier;

    public DriveCommand(ExplosiveTele opmode, DriveSubsystem drive){
        super(opmode, drive);
        this.drive = drive;
        this.opmode = opmode;
        this.dController = opmode.dController;
    }

    @Override
    public void init() {}

    @Override
    public void start() {
        drive.enablePID(true,0.0, 0,0); //stationary: p = 0.01, i = 0.00004
        drive.setPIDTolerance(2);
    }

    @Override
    public void loop() {
        if(dController.a()) drive.setPIDTarget(0);
        else if(dController.b()) drive.setPIDTarget(90);
        else if(dController.y()) drive.setPIDTarget(180);
        else if(dController.x()) drive.setPIDTarget(-90);

        if(dController.leftBumper()) balancingMode = true;
        else if(dController.rightBumper()) balancingMode = false;



        if(dController.rx() > 0.3){
            drive.setStrafeHeight(0.05); //-0.05
            //TelemetryLog.d("Robot", "Strafe Height: 0.1");
        } else if(dController.rightTrigger() > 0.3){
            drive.setStrafeHeight(0.045); //-0.04
            //TelemetryLog.d("Robot", "Strafe Height: 0.11");
        } else if(dController.leftTrigger() > 0.3){
            drive.setStrafeHeight(0.145); //0.05
            //TelemetryLog.d("Robot", "Strafe Height: 0.2");
        } else{
            if(!balancingMode) drive.setStrafeHeight(0.055); //-0.03
            else drive.setStrafeHeight(0.085); //0
            //TelemetryLog.d("Robot", "Strafe Height: 0.12/0.13");
        }

        if(dController.leftStickPressed()){
            slowToggle = true;
        } else{
            if(slowToggle){
                slowToggle = false;
                isSlow = !isSlow;
            }
        }

        if(isSlow) speedModifier = 0.5;
        else speedModifier = 1;

        drive.strafeArcadeDrive(Utils.getSquaredOutput(dController.lx()) * speedModifier, Utils.getSquaredOutput(dController.ly()) * speedModifier,Utils.getSquaredOutput(dController.rx()) * speedModifier);

        /*if(dController.dpadDown()){
            drive.setStrafeHeight(0.1);
        } else if(dController.dpadRight()){
            drive.setStrafeHeight(0.11);
        } else if(dController.dpadUp()){
            drive.setStrafeHeight(0.12);
        }*/
    }

    @Override
    public void stop() {

    }
}
