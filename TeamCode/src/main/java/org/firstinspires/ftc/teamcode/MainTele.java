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
import org.firstinspires.ftc.teamcode.competitionCode.HDrive;
import org.firstinspires.ftc.teamcode.competitionCode.MotorGroup;

/**
 * Created by robotics9277 on 10/4/2017.
 */
@TeleOp(name = "Main")
public class MainTele extends OpMode {
    private final int NAVX_DIM_I2C_PORT = 0;
    private AHRS navx;
    private boolean navxInitialized, servosInitialized;

    private MotorGroup left, right, strafeMotors;
    private HDrive drive;

    //Merge Commit

    DcMotor fLeft, fRight, bLeft, bRight, strafe, lLift, rLift;
    Servo rt,lt,rb,lb,ll,rl,horizontal,vertical;
    AnalogInput pl,pr;
    ColorSensor color;

    boolean lockToggle = false, locked = false;

    double x, y, z, yaw, leftSet, rightSet, error, errorScaler, angle, turnToggle, horizCurrent, vertCurrent;

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
        rt.setDirection(Servo.Direction.REVERSE);

        pl = hardwareMap.get(AnalogInput.class, "pl");
        pr = hardwareMap.get(AnalogInput.class, "pr");
        color = hardwareMap.get(ColorSensor.class, "color");

        left = new MotorGroup(this, bLeft,fLeft);
        right = new MotorGroup(this, bRight,fRight);
        strafeMotors = new MotorGroup(this, strafe);

        left.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
        right.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);
        strafeMotors.setDirection(DcMotorSimple.Direction.FORWARD);

        lt.setPosition(0.5);
        rt.setPosition(0.5);

        drive = new HDrive(this, left, right, strafeMotors, navx);

        drive.enablePID(true,0.005);
    }

    @Override
    public void loop() {
        horizontal.setPosition(0.6);
        vertical.setPosition(0.06);

        /*if(gamepad2.x){
            if(!lockToggle){
                lockToggle = true;
                locked = !locked;
            }
        } else{
            lockToggle = false;
        }*/

        if(gamepad2.x){
            locked = true;
        } else if(gamepad2.y){
            locked = false;
        }

        rt.setPosition((gamepad2.left_stick_y/2) + 0.5);
        lt.setPosition((gamepad2.left_stick_y/2) + 0.5);
        rb.setPosition((gamepad2.right_stick_y/2) + 0.5);
        lb.setPosition((gamepad2.right_stick_y/2) + 0.5);

        if(gamepad1.a){
            rt.setPosition(1);
            rb.setPosition(1);
            lt.setPosition(0);
            lb.setPosition(0);
        } else if(gamepad1.b){
            rt.setPosition(0);
            rb.setPosition(0);
            lt.setPosition(1);
            lb.setPosition(1);
        }

        /*setServo(gamepad2.a, gamepad2.b, rt);
        setServo(gamepad2.a, gamepad2.b, lt);
        setServo(gamepad2.a, gamepad2.b, rb);
        setServo(gamepad2.a, gamepad2.b, lb);*/

        if(locked){
            ll.setPosition(0.55);
        } else{
            ll.setPosition(0);
        }

        if(locked){
            rl.setPosition(0.15);
        } else{
            rl.setPosition(1);
        }

        if(gamepad2.left_trigger > gamepad2.right_trigger){
            lLift.setPower(-gamepad2.left_trigger);
            rLift.setPower(-gamepad2.left_trigger);
        } else{
            lLift.setPower(gamepad2.right_trigger);
            rLift.setPower(gamepad2.right_trigger);
        }

        if(gamepad1.left_trigger > 0.1 || gamepad1.right_trigger > 0.1){
            drive.fieldCentricDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_trigger, gamepad1.right_trigger);
            turnToggle = 0;
        } else{
            if(turnToggle < 30){
                turnToggle++;
                drive.resetPID();
                drive.strafeArcadeDrive(0,0,0);
            } else{
                drive.fieldCentricDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_stick_y);
            }
        }
        //drive.strafeArcadeDrive(gamepad1.left_stick_x, gamepad1.left_stick_y,gamepad1.right_stick_x);

        telemetry.addData("Lift Locked", locked);

        if (navxInitialized) {
            telemetry.addData("Yaw", navx.getYaw());
            telemetry.addData("Roll", navx.getRoll());
            telemetry.addData("Pitch", navx.getPitch());
            /*telemetry.addData("raw x", navx.getRawGyroX());
            telemetry.addData("raw y", navx.getRawGyroY());
            telemetry.addData("raw z", navx.getRawGyroZ());*/
        }

        telemetry.addData("TAN", 180 - Math.toDegrees(Math.atan2(gamepad1.right_stick_x,gamepad1.right_stick_y)));

        telemetry.addData("fRight", fRight.getCurrentPosition());
        telemetry.addData("bRight", bRight.getCurrentPosition());
        telemetry.addData("fLeft", fLeft.getCurrentPosition());
        telemetry.addData("bLeft", bLeft.getCurrentPosition());
        telemetry.addData("Strafe", strafe.getCurrentPosition());

        telemetry.addData("LP", pl.getVoltage());
        telemetry.addData("RP", pr.getVoltage());
        telemetry.addData("Red", color.red());
        telemetry.addData("Blue", color.blue());

        telemetry.update();
    }

    public void stop(){
        navx.close();
    }

    public void setServo(boolean input1, boolean input2, Servo s){
        if(input1){
            s.setPosition(1);
        } else if(input2){
            s.setPosition(0);
        } else{
            s.setPosition(0.5);
        }
    }
}