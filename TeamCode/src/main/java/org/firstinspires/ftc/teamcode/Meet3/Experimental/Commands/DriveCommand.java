package org.firstinspires.ftc.teamcode.Meet3.Experimental.Commands;

import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Command;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Subsystems.DriveSubsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class DriveCommand extends Command {
    Controller dController;
    DriveSubsystem drive;
    HazMatTeleOp opmode;

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
        if(dController.a()) drive.setPIDTarget(180);
        else if(dController.b()) drive.setPIDTarget(-90);
        else if(dController.y()) drive.setPIDTarget(0);
        else if(dController.x()) drive.setPIDTarget(90);

        if(dController.rightTrigger() > 0.3){
            drive.setStrafeHeight(0.1);
        } else if(dController.rx() > 0.3){
            drive.setStrafeHeight(0.11);
        } else if(dController.leftTrigger() > 0.3){
            drive.setStrafeHeight(0.2);
        } else{
            drive.setStrafeHeight(0.12);
        }

        drive.strafeArcadeDrive(dController.lx(), dController.ly(),dController.rx());

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
