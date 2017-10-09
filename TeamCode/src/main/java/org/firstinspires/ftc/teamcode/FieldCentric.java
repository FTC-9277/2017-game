package org.firstinspires.ftc.teamcode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 10/4/2017.
 */
@TeleOp(name = "Field Centric")
public class FieldCentric extends OpMode {
    private final int NAVX_DIM_I2C_PORT = 0;
    private AHRS navx;
    private boolean navxInitialized;

    DcMotor fLeft, fRight, bLeft, bRight, strafe;
    Servo i1,i2;
    double x, y, z, angle, mSet,sSet;

    @Override
    public void init() {
        try{
            navx = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"), NAVX_DIM_I2C_PORT, AHRS.DeviceDataType.kProcessedData);
            navxInitialized = true;
        } catch (Exception e){
            telemetry.addData("navX initialization", "failed");
            navxInitialized = false;
        }
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        strafe = hardwareMap.get(DcMotor.class, "strafe");

        i1 = hardwareMap.get(Servo.class, "intake1");
        i2 = hardwareMap.get(Servo.class, "intake2");

        i1.setDirection(Servo.Direction.REVERSE);
        i2.setDirection(Servo.Direction.FORWARD);

    }

    @Override
    public void loop() {
        y = Math.abs(gamepad1.left_stick_y) > 0.1 ? gamepad1.left_stick_y : 0;
        x = Math.abs(gamepad1.left_stick_x) > 0.1 ? gamepad1.left_stick_x : 0;
        z = Math.abs(gamepad1.right_stick_x) > 0.3 ? gamepad1.right_stick_x : 0;

        if(navxInitialized){
            telemetry.addData("Yaw", navx.getYaw());
            telemetry.addData("Roll", navx.getRoll());
            telemetry.addData("Pitch", navx.getPitch());

            angle = navx.getYaw();

        }

        fRight.setPower(-y - z);
        bRight.setPower(-y - z);
        fLeft.setPower(y - z);
        bLeft.setPower(y - z);
        strafe.setPower(-x);

        if(gamepad1.right_trigger > 0.1){
            i1.setPosition(0);
            i2.setPosition(0);
        } else if(gamepad1.left_trigger > 0.1){
            i1.setPosition(1);
            i2.setPosition(1);
        }
        else{
            i1.setPosition(0.5);
            i2.setPosition(0.5);
        }
    }
}
