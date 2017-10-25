package org.firstinspires.ftc.teamcode;

import android.drm.DrmStore;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by robotics9277 on 10/4/2017.
 */
@TeleOp(name = "Main")
public class MainTele extends OpMode {
    private final int NAVX_DIM_I2C_PORT = 0;
    private AHRS navx;
    private boolean navxInitialized, servosInitialized;

    DcMotor fLeft, fRight, bLeft, bRight, strafe;
    Servo i1, i2;
    double x, y, z, yaw, leftSet, rightSet, error, errorScaler, angle;

    @Override
    public void init() {
        /*try{
            navx = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"), NAVX_DIM_I2C_PORT, AHRS.DeviceDataType.kProcessedData);
            navxInitialized = true;
        } catch (Exception e){
            telemetry.addData("navX initialization", "failed");
            navxInitialized = false;
        }*/
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        strafe = hardwareMap.get(DcMotor.class, "strafe");
        yaw = navx.getYaw();
        angle = Math.atan2(gamepad1.right_stick_x, gamepad1. right_stick_y);
        errorScaler = 0.01;
        error = angle - yaw;


        try {
            i1 = hardwareMap.get(Servo.class, "intake1");
            i2 = hardwareMap.get(Servo.class, "intake2");

            i1.setDirection(Servo.Direction.REVERSE);
            i2.setDirection(Servo.Direction.FORWARD);
            servosInitialized = true;
        } catch (Exception e) {
            telemetry.addData("Servos", "Not Found");
            servosInitialized = false;
        }


    }

    @Override
    public void loop() {
        if (navxInitialized) {
            telemetry.addData("Yaw", navx.getYaw());
            telemetry.addData("Roll", navx.getRoll());
            telemetry.addData("Pitch", navx.getPitch());
        }


        y = Math.abs(gamepad1.left_stick_y) > 0.1 ? gamepad1.left_stick_y / 1.5 : 0;
        x = Math.abs(gamepad1.left_stick_x) > 0.1 ? gamepad1.left_stick_x : 0;
        z = Math.abs(gamepad1.right_stick_x) > 0.3 ? gamepad1.right_stick_x : 0;
        rightSet = Math.abs(x - y) > 1 ? 1 * ((x - y) / Math.abs(x - y)) : x - y;
        leftSet = Math.abs(y + x) > 1 ? 1 * ((y + x) / Math.abs(y + x)) : y + x;

        fRight.setPower(-y - z);
        bRight.setPower(-y - z);
        fLeft.setPower(y - z);
        bLeft.setPower(y - z);
        strafe.setPower(-x);

        if (servosInitialized) {
            if (gamepad1.right_trigger > 0.1) {
                i1.setPosition(0);
                i2.setPosition(0);
            } else if (gamepad1.left_trigger > 0.1) {
                i1.setPosition(1);
                i2.setPosition(1);
            } else {
                i1.setPosition(0.5);
                i2.setPosition(0.5);

            }
            if (yaw < 0) {
                fRight.setPower(rightSet - error);
                fLeft.setPower(leftSet + error);
                bRight.setPower(rightSet - error);
                bLeft.setPower(leftSet + error);
            } else {
                fRight.setPower(rightSet + error);
                fLeft.setPower(leftSet - error);
                bRight.setPower(rightSet + error);
                bLeft.setPower(leftSet - error);
            }

        /*if(gamepad1.a){
            fRight.setPower(0.5);
            bRight.setPower(0.5);
            fLeft.setPower(-0.5);
            bLeft.setPower(-0.5);
            strafe.setPower(0.75);
        } else{
            fRight.setPower(0);
            bRight.setPower(0);
            fLeft.setPower(0);
            bLeft.setPower(0);
            strafe.setPower(0);
        }*/

            telemetry.addData("fRight", fRight.getCurrentPosition());
            telemetry.addData("bRight", bRight.getCurrentPosition());
            telemetry.addData("fLeft", fLeft.getCurrentPosition());
            telemetry.addData("bLeft", bLeft.getCurrentPosition());
            telemetry.addData("Strafe", strafe.getCurrentPosition());
        }
    }
}