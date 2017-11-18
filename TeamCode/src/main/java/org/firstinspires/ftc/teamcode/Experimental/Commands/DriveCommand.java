package org.firstinspires.ftc.teamcode.Experimental.Commands;

import org.firstinspires.ftc.teamcode.Experimental.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Experimental.Framework.Command;
import org.firstinspires.ftc.teamcode.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Experimental.Framework.HazMatTeleOp;

/**
 * Created by Varun on 11/17/2017.
 */

public class DriveCommand extends Command {
    Controller dController;
    DriveSubsystem drive;
    HazMatTeleOp opmode;
    boolean turnToggle = false;

    public DriveCommand(HazMatTeleOp opmode, DriveSubsystem drive){
        super(opmode, drive);
        this.drive = drive;
        this.opmode = opmode;
        this.dController = opmode.dController;
    }

    @Override
    public void init() {
        drive.enablePID(true);
    }

    @Override
    public void start() {
        drive.setPID(0.015,0,0);
    }

    @Override
    public void loop() {
        if(dController.a()) drive.setPIDTarget(180);
        else if(dController.b()) drive.setPIDTarget(90);
        else if(dController.y()) drive.setPIDTarget(0);
        else if(dController.x()) drive.setPIDTarget(-90);

        if(dController.rx() != 0){
            drive.fieldCentricDrive(dController.lx(), dController.ly(), dController.rx());
            turnToggle = true;
        } else{
            if(turnToggle){
                drive.strafeArcadeDrive(0,0,0);
                drive.resetPID();
                turnToggle = false;
                drive.enablePID(true);
            } else{
                drive.fieldCentricDrive(dController.lx(), dController.lx(), dController.rx());
            }
        }
    }

    @Override
    public void stop() {

    }
}
