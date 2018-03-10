package org.firstinspires.ftc.teamcode.Supers.Commands;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.Subsystems.RelicSubsystem;

/**
 * Created by Varun on 3/8/2018.
 */

public class RelicCommand extends Command {
    Controller mController;
    RelicSubsystem relic;
    ExplosiveTele opmode;
    boolean aToggle = false, clawOpen = false;

    public RelicCommand(ExplosiveTele opmode, RelicSubsystem relic){
        super(opmode, relic);

        this.opmode = opmode;
        this.relic = relic;
        this.mController = opmode.mController;
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if(mController.leftBumper()) relic.extend(-0.5);
        else if(mController.rightBumper()) relic.extend(0.5);
        else relic.extend(0);

        if(mController.a()){
            if(!aToggle){
                aToggle = true;
                clawOpen = !clawOpen;
            }
        } else{
            aToggle = false;
        }

        relic.openClaw(clawOpen);
        //opmode.telemetry.addData("Claw", clawOpen);

        if(mController.dpadUp()) relic.armDown(false);
        else if(mController.dpadDown()) relic.armDown(true);
    }

    @Override
    public void stop() {

    }
}
