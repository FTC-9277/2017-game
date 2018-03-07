package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Supers.Misc.PIDDashboard;

/**
 * Created by Varun on 2/28/2018.
 */
@TeleOp(name = "Ratchet Test")
public class RatchetTest extends ExplosiveTele {
    Servo rl;
    PIDDashboard dash;

    @Override
    public void initHardware() {
        rl = hardwareMap.get(Servo.class, "rl");
        dash = new PIDDashboard(this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        if(dash.hasNew()){
            rl.setPosition(dash.get().get(0));
        }
    }

    @Override
    public void exit() {
        dash.close();
    }
}
