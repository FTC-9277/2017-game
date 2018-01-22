package org.firstinspires.ftc.teamcode.States.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.States.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.States.Framework.Controller;
import org.firstinspires.ftc.teamcode.States.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.States.HazmatRobot;

/**
 * Created by robotics9277 on 1/6/2018.
 */
@TeleOp(name="Intake Test")
public class IntakeTest extends HazMatTeleOp {
    HazmatRobot robot;
    IntakeCommand intake;

    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
        intake = new IntakeCommand(this, robot.intake);
    }

    @Override
    public void initAction() {
        mController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.3);
        mController.setTriggerDeadzone(0.1);

        intake.enable();
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
