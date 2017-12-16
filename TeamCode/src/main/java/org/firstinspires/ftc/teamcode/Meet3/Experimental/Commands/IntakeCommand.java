package org.firstinspires.ftc.teamcode.Meet3.Experimental.Commands;

import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Command;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Subsystems.IntakeSubsystem;

/**
 * Created by robotics9277 on 12/9/2017.
 */

public class IntakeCommand extends Command {
    Controller mController;
    IntakeSubsystem intake;
    HazMatTeleOp opmode;

    public IntakeCommand(HazMatTeleOp opmode, IntakeSubsystem intake){
        super(opmode,intake);

        this.opmode = opmode;
        this.intake = intake;
        this.mController = opmode.mController;
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {        intake.intakeTop(mController.ly()/2);
        intake.intakeBottom(mController.ry()/2);

        if(mController.ly() > 0){intake.intakeLift(mController.ly()/2); }
        else if(mController.a()){
            intake.intakeLift(0.5);
        } else{
            intake.intakeLift(0);
        }
    }

    @Override
    public void stop() {

    }
}
