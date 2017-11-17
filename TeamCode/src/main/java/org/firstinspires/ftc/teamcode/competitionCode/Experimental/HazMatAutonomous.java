package org.firstinspires.ftc.teamcode.competitionCode.Experimental;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Varun on 11/16/2017.
 */

public abstract class HazMatAutonomous extends LinearOpMode {

    public abstract void initHardware();

    public abstract void initAction();

    public abstract void body();

    public abstract void exit();

    @Override
    public void runOpMode(){
        telemetry.addData("Initializing", "Started");
        telemetry.update();

        initHardware();
        initAction();

        telemetry.addData("Initializing", "Finished");
        telemetry.update();

        waitForStart();

        telemetry.addData("Body", "Started");
        telemetry.update();

        body();

        telemetry.addData("Body", "Finished");
        telemetry.update();

        exit();
    }
}
