package org.firstinspires.ftc.teamcode.miscOpModes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by robotics9277 on 11/4/2017.
 */
@TeleOp(name = "Null test")
public class nullTest extends OpMode{
    @Override
    public void init() {

        Log.d("HazmatRobot: ", "Null Tele Initialized");
    }

    @Override
    public void loop() {
        telemetry.addData("Gamepad 1", gamepad1.a);
        if(gamepad1.a){
            Log.d("HazmatRobot: ", "Gamepad 1 'A' pressed");
        }
    }
}
