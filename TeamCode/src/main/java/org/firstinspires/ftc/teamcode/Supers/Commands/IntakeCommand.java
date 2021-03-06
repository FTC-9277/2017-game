package org.firstinspires.ftc.teamcode.Supers.Commands;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.Subsystems.IntakeSubsystem;

/**
 * Created by robotics9277 on 12/9/2017.
 */

public class IntakeCommand extends Command {
    Controller mController;
    IntakeSubsystem intake;
    ExplosiveTele opmode;
    public boolean beltToggle, useBelt;

    public IntakeCommand(ExplosiveTele opmode, IntakeSubsystem intake){
        super(opmode,intake);

        this.opmode = opmode;
        this.intake = intake;
        this.mController = opmode.mController;
        this.beltToggle = false;
        this.useBelt = false;
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        intake.intakeTop(mController.ly()/2);
        intake.intakeBottom(mController.ry()/2);
    }

    @Override
    public void stop() {

    }
}
