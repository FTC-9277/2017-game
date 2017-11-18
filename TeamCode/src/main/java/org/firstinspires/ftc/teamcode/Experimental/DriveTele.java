package org.firstinspires.ftc.teamcode.Experimental;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Experimental.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Experimental.Framework.Controller;
import org.firstinspires.ftc.teamcode.Experimental.Framework.HazMatTeleOp;

/**
 * Created by robotics9277 on 11/18/2017.
 */
@TeleOp(name = "Drive Test", group = "Experimental")
public class DriveTele extends HazMatTeleOp {
    Robot robot;
    DriveCommand drive;

    @Override
    public void initHardware() {
        robot = new Robot(this);
        drive = new DriveCommand(this,robot.drive);
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.1);

        drive.enable();
    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {

    }

    @Override
    public void exit() {

    }
}
