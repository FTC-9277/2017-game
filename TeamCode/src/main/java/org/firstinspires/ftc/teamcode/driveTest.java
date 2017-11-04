package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.competitionCode.HDrive;
import org.firstinspires.ftc.teamcode.competitionCode.MotorGroup;

/**
 * Created by robotics9277 on 11/4/2017.
 */
@TeleOp(name = "Drive Test")
public class driveTest extends OpMode {
    HDrive hDrive;
    MotorGroup left, right, strafe;
    DcMotor bLeft, bRight, fLeft, fRight, strafeMotor;

    @Override
    public void init() {
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        strafeMotor = hardwareMap.get(DcMotor.class, "strafe");

        left = new MotorGroup(this, bLeft, fLeft);
        right = new MotorGroup(this, bRight, fRight);
        strafe = new MotorGroup(this, strafeMotor);

        left.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
        strafe.setDirection(DcMotorSimple.Direction.REVERSE);

        hDrive = new HDrive(this,left,right,strafe);
    }

    @Override
    public void loop() {
        hDrive.strafeArcadeDrive(gamepad1.left_stick_x,gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
