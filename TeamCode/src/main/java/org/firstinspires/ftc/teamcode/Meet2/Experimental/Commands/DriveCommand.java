package org.firstinspires.ftc.teamcode.Meet2.Experimental.Commands;

import org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework.Command;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Subsystems.DriveSubsystem;

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
    public void init() {}

    @Override
    public void start() {
        drive.enablePID(true,0.014, 0.357);
    }

    @Override
    public void loop() {
        if(dController.a()) drive.setPIDTarget(180);
        else if(dController.b()) drive.setPIDTarget(-90);
        else if(dController.y()) drive.setPIDTarget(0);
        else if(dController.x()) drive.setPIDTarget(90);

        drive.strafeArcadeDrive(dController.lx(), dController.ly(),dController.rx());
    }

    @Override
    public void stop() {

    }
}
