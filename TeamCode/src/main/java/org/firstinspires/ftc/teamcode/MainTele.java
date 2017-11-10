package org.firstinspires.ftc.teamcode;

import android.drm.DrmStore;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    DcMotor fLeft, fRight, bLeft, bRight, strafe, lLift, rLift;
    Servo rt,lt,rb,lb,ll,rl,horizontal,vertical;
    AnalogInput pl,pr;
    ColorSensor color;

    double x, y, z, yaw, leftSet, rightSet, error, errorScaler, angle;

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
        lLift = hardwareMap.get(DcMotor.class, "lLift");
        rLift = hardwareMap.get(DcMotor.class, "rLift");

        lLift.setDirection(DcMotorSimple.Direction.REVERSE);

        rt = hardwareMap.get(Servo.class, "rt");
        lt = hardwareMap.get(Servo.class, "lt");
        rb = hardwareMap.get(Servo.class, "rb");
        lb = hardwareMap.get(Servo.class, "lb");
        ll = hardwareMap.get(Servo.class, "ll");
        rl = hardwareMap.get(Servo.class, "rl");
        horizontal = hardwareMap.get(Servo.class, "horizontal");
        vertical = hardwareMap.get(Servo.class,"vertical");

        lb.setDirection(Servo.Direction.REVERSE);
        lt.setDirection(Servo.Direction.REVERSE);

        pl = hardwareMap.get(AnalogInput.class, "pl");
        pr = hardwareMap.get(AnalogInput.class, "pr");
        color = hardwareMap.get(ColorSensor.class, "color");
    }

    @Override
    public void loop() {
        if (navxInitialized) {
            telemetry.addData("Yaw", navx.getYaw());
            telemetry.addData("Roll", navx.getRoll());
            telemetry.addData("Pitch", navx.getPitch());
            /*telemetry.addData("raw x", navx.getRawGyroX());
            telemetry.addData("raw y", navx.getRawGyroY());
            telemetry.addData("raw z", navx.getRawGyroZ());*/
        }
        telemetry.update();

        /*setServo(gamepad1.a, rt);
        setServo(gamepad1.b,lt);
        setServo(gamepad1.x,rb);
        setServo(gamepad1.y,lb);
        setServo(gamepad1.dpad_down,ll);
        setServo(gamepad1.dpad_left, rl);
        setServo(gamepad1.dpad_right, horizontal);
        setServo(gamepad1.dpad_up, vertical);

        lLift.setPower(gamepad1.right_stick_y);
        rLift.setPower(gamepad1.right_stick_y);

        telemetry.addData("fRight", fRight.getCurrentPosition());
        telemetry.addData("bRight", bRight.getCurrentPosition());
        telemetry.addData("fLeft", fLeft.getCurrentPosition());
        telemetry.addData("bLeft", bLeft.getCurrentPosition());
        telemetry.addData("Strafe", strafe.getCurrentPosition());

        telemetry.addData("LP", pl.getVoltage());
        telemetry.addData("RP", pr.getVoltage());
        telemetry.addData("Red", color.red());
        telemetry.addData("Blue", color.blue());*/
    }

    public void stop(){
        navx.close();
    }

    public void setServo(boolean input, Servo s){
        if(input){
            s.setPosition(1);
        } else{
            s.setPosition(0.5);
        }
    }
}