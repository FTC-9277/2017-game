package org.firstinspires.ftc.teamcode.competitionCode.Experimental.Commands;

import org.firstinspires.ftc.teamcode.competitionCode.Experimental.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.competitionCode.Experimental.Framework.Command;
import org.firstinspires.ftc.teamcode.competitionCode.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.competitionCode.Experimental.Framework.HazMatTeleOp;

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

    }

    @Override
    public void loop() {
        if(dController.leftTrigger() > 0.1 || dController.rightTrigger() > 0.1){
            drive.fieldCentricTurnDrive(dController.lx(), dController.ly(), dController.rx(), dController.ry(), dController.leftTrigger(), dController.rightTrigger());
            turnToggle = true;
        } else{
            if(turnToggle){
                drive.strafeArcadeDrive(0,0,0);
                drive.resetPID();
                turnToggle = false;
            } else{
                drive.fieldCentricDrive(dController.lx(), dController.lx(), dController.rx(), dController.rx());
            }
        }
    }

    @Override
    public void stop() {

    }
}
