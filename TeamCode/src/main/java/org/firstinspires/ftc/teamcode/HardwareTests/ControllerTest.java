package org.firstinspires.ftc.teamcode.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework.HazMatTeleOp;

/**
 * Created by robotics9277 on 12/9/2017.
 */
@TeleOp(name = "Controller Test")
public class ControllerTest extends HazMatTeleOp {
    @Override
    public void initHardware() {

    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.1);
    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        telemetry.addData("lx", dController.lx());
        telemetry.addData("ly", dController.ly());
        telemetry.addData("mag", Math.sqrt(Math.pow(dController.lx(),2) + Math.pow(dController.ly(),2)));
        telemetry.addData("Deadzone Mag", dController.joystickDeadzoneMagnitude);
    }

    @Override
    public void exit() {

    }
}
