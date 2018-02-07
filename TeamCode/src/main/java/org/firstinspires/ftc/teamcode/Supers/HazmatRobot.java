package org.firstinspires.ftc.teamcode.Supers;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.CRServoGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.TelemetryLog;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.Supers.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Supers.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Supers.Subsystems.JewelSubsystem;
import org.firstinspires.ftc.teamcode.Supers.Subsystems.LiftSubsystem;

/**
 * Created by Varun on 11/17/2017.
 */

public class HazmatRobot {
    private final int NAVX_DIM_I2C_PORT = 0;
    public ExplosiveBNO055 imu;
    private boolean gyroInitialized, driveInitialized, liftInitialized, intakeInitialized, jewelInitialized;

    public MotorGroup left, right, strafe, liftMotors;
    public CRServoGroup ti, bi;

    public DcMotor fLeft, fRight, bLeft, bRight, lStrafe,rStrafe, lLift, rLift;
    public Servo ll,rl,horizontal,vertical, ls, rs;
    public CRServo rt,lt,rb,lb,il;
    public AnalogInput pl,pr;
    public ColorSensor color;
    public DistanceSensor distance;
    public ModernRoboticsI2cRangeSensor lsRange, lfRange, rsRange, rfRange;

    TelemetryLog RobotLog;

    public DriveSubsystem drive;
    public LiftSubsystem lift;
    public IntakeSubsystem intake;
    public JewelSubsystem jewel;

    public HazmatRobot(OpMode opmode){
        RobotLog = new TelemetryLog(opmode);

        getHardware(opmode);

        if(driveInitialized && gyroInitialized){
            drive = new DriveSubsystem(opmode, left, right , strafe, ls, rs, imu);
        }

        if(liftInitialized){
            lift = new LiftSubsystem(opmode, liftMotors, ll, rl);
        }

        if(intakeInitialized){
            intake = new IntakeSubsystem(opmode, ti, bi, il);
        }

        if(jewelInitialized){
            jewel = new JewelSubsystem(opmode, horizontal, vertical, color, distance);
        }
    }

    private void getHardware(OpMode opmode){


        //BNO055IMU imu = opmode.hardwareMap.get(BNO055IMU.class, "heck");

        //navx = opmode.hardwareMap.get(NavxMicroNavigationSensor.class, "navx");
        //ahrs = (AHRS)imu;

        /**
         * Initialize gyro
         */
        try{
                imu = ExplosiveBNO055.getInstance(opmode,"imu");

                gyroInitialized = true;
            } catch (Exception e){
                RobotLog.add("Gyro initialization failed: " + e.getMessage());
                gyroInitialized = false;
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

            ls = opmode.hardwareMap.get(Servo.class, "ls");
            rs = opmode.hardwareMap.get(Servo.class, "rs");

            left = new MotorGroup(opmode, bLeft,fLeft);
            right = new MotorGroup(opmode, bRight,fRight);
            strafe = new MotorGroup(opmode, lStrafe, rStrafe);

            left.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
            right.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);
            strafe.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);

            driveInitialized = true;
        } catch (Exception e){
            RobotLog.add("Drive initialization failed: " + e.getMessage());
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
            RobotLog.add("Lift initialization failed: " + e.getMessage());
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

            ti = new CRServoGroup(opmode,rt,lt);
            bi = new CRServoGroup(opmode,rb,lb);

            intakeInitialized = true;
        } catch (Exception e){
            RobotLog.add("Intake initialization failed: " + e.getMessage());
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
            RobotLog.add("Jewel initialization failed: " + e.getMessage());
            jewelInitialized = false;
        }

        try{
            lsRange = opmode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "lsRange");
            rsRange = opmode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rsRange");
            lfRange = opmode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "lfRange");
            rfRange = opmode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rfRange");
        } catch (Exception e){
            RobotLog.add("Range sensor initialization  failed: " + e.getMessage());
        }
    }
}
