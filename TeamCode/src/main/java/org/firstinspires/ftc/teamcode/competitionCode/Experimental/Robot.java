package org.firstinspires.ftc.teamcode.competitionCode.Experimental;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.competitionCode.Experimental.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.competitionCode.Log;
import org.firstinspires.ftc.teamcode.competitionCode.MotorGroup;

/**
 * Created by Varun on 11/17/2017.
 */

public class Robot {
    private final int NAVX_DIM_I2C_PORT = 0;
    private AHRS ahrs;
    private boolean ahrsInitialized, motorsInitialized, servosInitialized, sensorsInitialized, robotInitialized = false;

    private MotorGroup left, right, strafeMotors;

    private DcMotor fLeft, fRight, bLeft, bRight, strafe, lLift, rLift;
    private Servo rt,lt,rb,lb,ll,rl,horizontal,vertical;
    private AnalogInput pl,pr;
    private ColorSensor color;

    Log RobotLog;

    DriveSubsystem drive;

    public Robot(OpMode opmode){
        RobotLog = new Log(opmode);

        getHardware(opmode);
        if(robotInitialized){
            drive = new DriveSubsystem(opmode,left,right,strafeMotors, ahrs);
        }
    }

    private void getHardware(OpMode opmode){
        try{
            ahrs = AHRS.getInstance(opmode.hardwareMap.deviceInterfaceModule.get("dim"), NAVX_DIM_I2C_PORT, AHRS.DeviceDataType.kProcessedData);
            ahrsInitialized = true;
        } catch (Exception e){
            RobotLog.add("AHRS initialization failed");
            ahrsInitialized = false;
        }

        try{
            fLeft = opmode.hardwareMap.get(DcMotor.class, "fLeft");
            bLeft = opmode.hardwareMap.get(DcMotor.class, "bLeft");
            fRight = opmode.hardwareMap.get(DcMotor.class, "fRight");
            bRight = opmode.hardwareMap.get(DcMotor.class, "bRight");
            strafe = opmode.hardwareMap.get(DcMotor.class, "strafe");
            lLift = opmode.hardwareMap.get(DcMotor.class, "lLift");
            rLift = opmode.hardwareMap.get(DcMotor.class, "rLift");

            lLift.setDirection(DcMotorSimple.Direction.REVERSE);

            left = new MotorGroup(opmode, bLeft,fLeft);
            right = new MotorGroup(opmode, bRight,fRight);
            strafeMotors = new MotorGroup(opmode, strafe);

            left.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
            right.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);
            strafeMotors.setDirection(DcMotorSimple.Direction.FORWARD);

            motorsInitialized = true;
        } catch (Exception e){
            RobotLog.add("Motor initialization failed");
            motorsInitialized = false;
        }

        try{
            rt = opmode.hardwareMap.get(Servo.class, "rt");
            lt = opmode.hardwareMap.get(Servo.class, "lt");
            rb = opmode.hardwareMap.get(Servo.class, "rb");
            lb = opmode.hardwareMap.get(Servo.class, "lb");
            ll = opmode.hardwareMap.get(Servo.class, "ll");
            rl = opmode.hardwareMap.get(Servo.class, "rl");
            horizontal = opmode.hardwareMap.get(Servo.class, "horizontal");
            vertical = opmode.hardwareMap.get(Servo.class,"vertical");

            lb.setDirection(Servo.Direction.REVERSE);
            lt.setDirection(Servo.Direction.REVERSE);
            rt.setDirection(Servo.Direction.REVERSE);

            servosInitialized = true;
        } catch (Exception e){
            RobotLog.add("Servo initialization failed");
            servosInitialized = false;
        }

        try{
            pl = opmode.hardwareMap.get(AnalogInput.class, "pl");
            pr = opmode.hardwareMap.get(AnalogInput.class, "pr");
            color = opmode.hardwareMap.get(ColorSensor.class, "color");
        } catch (Exception e){
            RobotLog.add("Sensor initialization failed");
            sensorsInitialized = false;
        }

        if(ahrsInitialized && motorsInitialized && servosInitialized && sensorsInitialized){
            RobotLog.add("All hardware found");
            robotInitialized = true;
        }
    }
}
