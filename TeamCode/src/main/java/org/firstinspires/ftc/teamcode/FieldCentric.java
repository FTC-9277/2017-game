package org.firstinspires.ftc.teamcode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robotics9277 on 10/4/2017.
 */
@TeleOp(name = "Field Centric")
public class FieldCentric extends OpMode {
    private final int NAVX_DIM_I2C_PORT = 1;
    private AHRS navx;
    private boolean navxInitialized, servosInitialized;

    DcMotor fLeft, fRight, bLeft, bRight, strafe;
    Servo i1,i2;
    double x, y, z, angle, mSet,sSet, expAngle, error, errorScalar;

    int ticks = 0;

    @Override
    public void init() {
        errorScalar = 0.02;

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

        strafe.setDirection(DcMotorSimple.Direction.REVERSE);
        fRight.setDirection(DcMotorSimple.Direction.REVERSE);
        bRight.setDirection(DcMotorSimple.Direction.REVERSE);

        try {
            i1 = hardwareMap.get(Servo.class, "intake1");
            i2 = hardwareMap.get(Servo.class, "intake2");

            i1.setDirection(Servo.Direction.REVERSE);
            i2.setDirection(Servo.Direction.FORWARD);
            servosInitialized = true;
        } catch(Exception e){
            telemetry.addData("Servos", "Not Found");
            servosInitialized = false;
        }

    }

    @Override
    public void loop() {
        y = Math.abs(gamepad1.left_stick_y) > 0.1 ? gamepad1.left_stick_y : 0;
        x = Math.abs(gamepad1.left_stick_x) > 0.1 ? gamepad1.left_stick_x : 0;
        z = 0;//Math.abs(gamepad1.right_stick_x) > 0.3 ? gamepad1.right_stick_x : 0;

        if(navxInitialized){
            telemetry.addData("Yaw", navx.getYaw());
            telemetry.addData("Roll", navx.getRoll());
            telemetry.addData("Pitch", navx.getPitch());

            telemetry.addData("Ticks", ticks);
            ticks++;


            angle = Math.toRadians(navx.getYaw());
            mSet = (y * Math.cos(angle)) - (x * Math.sin(angle))/1.5;
            sSet = (y * Math.sin(angle)) + (x * Math.cos(angle));
            telemetry.addData("trig test", Math.cos(180));

            if(Math.abs(gamepad1.right_stick_x) > 0.3 && Math.abs(gamepad1.right_stick_y) > 0.3)  expAngle = Math.atan2(gamepad1.right_stick_x, gamepad1.right_stick_y);

            error = expAngle - navx.getYaw();

            telemetry.addData("Expected angle", expAngle);
            telemetry.addData("Error", error);

            telemetry.update();
        }

        setCapped(mSet - (error * errorScalar), mSet + (error * errorScalar), sSet);

        if(servosInitialized){
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

        if(gamepad1.a){
            navx.zeroYaw();
        }
    }

    public void set(double leftPow, double rightPow, double strafePow){
        fLeft.setPower(leftPow);
        bLeft.setPower(leftPow);
        fRight.setPower(rightPow);
        bRight.setPower(rightPow);
        strafe.setPower(strafePow);
    }

    private void setCapped(double leftPower, double rightPower, double strafePower) {
        double max = maxDouble(leftPower, rightPower, strafePower);

        if (max < 1) {
            set(leftPower, rightPower, strafePower);
            return;
    }

    set(leftPower / max, rightPower / max, strafePower / max);
}

    public static double maxDouble(double... nums) {
        double currMax = Math.abs(nums[0]);

        for (double i : nums) {
            currMax = Math.abs(i) > currMax ? Math.abs(i) : currMax;
        }

        return currMax;
    }

}
