package org.firstinspires.ftc.teamcode.Meet3.Experimental.Commands;

import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Command;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.Meet3.Experimental.Subsystems.LiftSubsystem;

/**
 * Created by robotics9277 on 12/9/2017.
 */

public class LiftCommand extends Command {
    Controller mController;
    LiftSubsystem lift;
    HazMatTeleOp opmode;

    public LiftCommand(HazMatTeleOp opmode, LiftSubsystem lift){
        super(opmode,lift);

        this.opmode = opmode;
        this.lift = lift;
        this.mController = opmode.mController;
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {
        lift.setLock(false);
    }

    @Override
    public void loop() {
        if(mController.x()){
            lift.setLock(true);
        } else if(mController.y()){
            lift.setLock(false);
        }

        if(mController.leftTrigger() > 0.1){
            lift.setLift(mController.leftTrigger());
        } else if(mController.rightTrigger() > 0.1){
            lift.setLift(-mController.rightTrigger());
        } else{
            lift.setLift(0);
        }
    }

    @Override
    public void stop() {
        lift.setLift(0);
    }
}
