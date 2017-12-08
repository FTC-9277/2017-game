package org.firstinspires.ftc.teamcode.Experimental;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Experimental.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Experimental.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Experimental.Subsystems.JewelSubsystem;
import org.firstinspires.ftc.teamcode.Experimental.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.competitionCode.Log;
import org.firstinspires.ftc.teamcode.competitionCode.MotorGroup;

/**
 * Created by Varun on 11/17/2017.
 */

public class HazmatRobot {
    private final int NAVX_DIM_I2C_PORT = 0;
    AHRS ahrs;
    private boolean ahrsInitialized, driveInitialized, liftInitialized, intakeInitialized, jewelInitialized;

    public MotorGroup left, right, strafe, liftMotors;

    private DcMotor fLeft, fRight, bLeft, bRight, lStrafe,rStrafe, lLift, rLift;
    public Servo ll,rl,horizontal,vertical;
    public CRServo rt,lt,rb,lb,il;
    public AnalogInput pl,pr;
    public ColorSensor color;
    public DistanceSensor distance;

    Log RobotLog;

    DriveSubsystem drive;
    LiftSubsystem lift;
    IntakeSubsystem intake;
    JewelSubsystem jewel;

    public HazmatRobot(OpMode opmode){
        RobotLog = new Log(opmode);

        getHardware(opmode);

        if(driveInitialized && ahrsInitialized){
            drive = new DriveSubsystem(opmode, left, right , strafe, ahrs);
        }

        if(liftInitialized){
            //lift = new LiftSubsystem();
        }

        if(intakeInitialized){
            intake = new IntakeSubsystem();
        }

        if(jewelInitialized){
            jewel = new JewelSubsystem();
        }
    }

    private void getHardware(OpMode opmode){
        /**
         * Initialize ahrs gyro
         */
        try{
            ahrs = AHRS.getInstance(opmode.hardwareMap.deviceInterfaceModule.get("dim"), NAVX_DIM_I2C_PORT, AHRS.DeviceDataType.kProcessedData);
            ahrsInitialized = true;
        } catch (Exception e){
            RobotLog.add("AHRS initialization failed: " + e.getMessage());
            ahrsInitialized = false;
        }

        /**
         * Initialize Drive Train
         */
        try{
            fLeft = opmode.hardwareMap.get(DcMotor.class, "fLeft");
            bLeft = opmode.hardwareMap.get(DcMotor.class, "bLeft");
            fRight = opmode.hardwareMap.get(DcMotor.class, "fRight");
            bRight = opmode.hardwareMap.get(DcMotor.class, "bRight");
            lStrafe = opmode.hardwareMap.get(DcMotor.class, "lStrafe");
            rStrafe = opmode.hardwareMap.get(DcMotor.class, "rStrafe");

            left = new MotorGroup(opmode, bLeft,fLeft);
            right = new MotorGroup(opmode, bRight,fRight);
            strafe = new MotorGroup(opmode, lStrafe, rStrafe);

            left.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
            right.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);
            strafe.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);

            driveInitialized = true;
        } catch (Exception e){
            RobotLog.add("Drive initialization failed: " );
            driveInitialized = false;
        }

        /**
         * Initialize Lift
         */
        try{
            lLift = opmode.hardwareMap.get(DcMotor.class, "lLift");
            rLift = opmode.hardwareMap.get(DcMotor.class, "rLift");
            ll = opmode.hardwareMap.get(Servo.class, "ll");
            rl = opmode.hardwareMap.get(Servo.class, "rl");
            //pl = opmode.hardwareMap.get(AnalogInput.class, "pl");
            //pr = opmode.hardwareMap.get(AnalogInput.class, "pr");

            liftMotors = new MotorGroup(opmode,lLift,rLift);
            liftMotors.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD);

            liftInitialized = true;
        } catch (Exception e){
            RobotLog.add("Lift initialization failed");
            liftInitialized = false;
        }

        /**
         * Initialize Intake
         */
        try{
            rt = opmode.hardwareMap.get(CRServo.class, "rt");
            lt = opmode.hardwareMap.get(CRServo.class, "lt");
            rb = opmode.hardwareMap.get(CRServo.class, "rb");
            lb = opmode.hardwareMap.get(CRServo.class, "lb");
            il = opmode.hardwareMap.get(CRServo.class, "il");

            lb.setDirection(CRServo.Direction.REVERSE);
            lt.setDirection(CRServo.Direction.REVERSE);
            rt.setDirection(CRServo.Direction.REVERSE);

            intakeInitialized = true;
        } catch (Exception e){
            RobotLog.add("Intake initialization failed");
            intakeInitialized = false;
        }

        /**
         * Initialize Jewel
         */
        try{
            horizontal = opmode.hardwareMap.get(Servo.class, "horizontal");
            vertical = opmode.hardwareMap.get(Servo.class,"vertical");
            color = opmode.hardwareMap.get(ColorSensor.class, "color");
            distance = opmode.hardwareMap.get(DistanceSensor.class, "color");

            jewelInitialized = true;
        } catch (Exception e){
            RobotLog.add("Jewel initialization failed");
            jewelInitialized = false;
        }
    }
}
