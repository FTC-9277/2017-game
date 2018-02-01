package org.firstinspires.ftc.teamcode.States.Commands;

import org.firstinspires.ftc.teamcode.States.Framework.Command;
import org.firstinspires.ftc.teamcode.States.Framework.Controller;
import org.firstinspires.ftc.teamcode.States.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.States.Framework.Log;
import org.firstinspires.ftc.teamcode.States.Framework.Utils;
import org.firstinspires.ftc.teamcode.States.Subsystems.DriveSubsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class DriveCommand extends Command {
    Controller dController;
    DriveSubsystem drive;
    HazMatTeleOp opmode;
    boolean balancingMode = false, slowToggle = false, isSlow = false;
    double speedModifier;

    public DriveCommand(HazMatTeleOp opmode, DriveSubsystem drive){
        super(opmode, drive);
        this.drive = drive;
        this.opmode = opmode;
        this.dController = opmode.dController;
    }

    @Override
    public void init() {}

    @Override
    public void start() {
        drive.enablePID(true,0.014, 0.357);
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



        if(dController.rightTrigger() > 0.3){
            drive.setStrafeHeight(0.05); //-0.05
            //Log.d("Robot", "Strafe Height: 0.1");
        } else if(dController.rx() > 0.3){
            drive.setStrafeHeight(0.05); //-0.04
            //Log.d("Robot", "Strafe Height: 0.11");
        } else if(dController.leftTrigger() > 0.3){
            drive.setStrafeHeight(0.145); //0.05
            //Log.d("Robot", "Strafe Height: 0.2");
        } else{
            if(!balancingMode) drive.setStrafeHeight(0.065); //-0.03
            else drive.setStrafeHeight(0.095); //0
            //Log.d("Robot", "Strafe Height: 0.12/0.13");
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
