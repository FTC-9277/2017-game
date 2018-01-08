package org.firstinspires.ftc.teamcode.States.OpModes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.States.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.States.Framework.Controller;
import org.firstinspires.ftc.teamcode.States.Framework.HazMatTeleOp;
import org.firstinspires.ftc.teamcode.States.HazmatRobot;

/**
 * Created by robotics9277 on 11/18/2017.
 */
@TeleOp(name = "Drive Test", group = "Experimental")
public class DriveTele extends HazMatTeleOp {
    HazmatRobot robot;
    DriveCommand drive;

    ModernRoboticsI2cRangeSensor range;
    @Override
    public void initHardware() {
        robot = new HazmatRobot(this);
        drive = new DriveCommand(this,robot.drive);

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.3);

        drive.enable();
    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        telemetry.addData("Range", range.getDistance(DistanceUnit.CM));
    }

    @Override
    public void exit() {

    }
}
