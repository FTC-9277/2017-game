package org.firstinspires.ftc.teamcode.Experimental.Subsystems;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Experimental.Framework.HazmatAHRS;
import org.firstinspires.ftc.teamcode.Experimental.Framework.Subsystem;
import org.firstinspires.ftc.teamcode.Experimental.Framework.RotationalPIDController;
import org.firstinspires.ftc.teamcode.competitionCode.Log;
import org.firstinspires.ftc.teamcode.competitionCode.MotorGroup;
import org.firstinspires.ftc.teamcode.Experimental.Framework.RotationalPIDController.Orientation;

import static org.firstinspires.ftc.teamcode.competitionCode.Utils.*;

/**
 * Created by Varun on 11/17/2017.
 */

public class DriveSubsystem extends Subsystem {
    private Log driveLog;
    private MotorGroup left, right, strafe;
    private Servo ls,rs;
    private AHRS gyro;
    private BNO055IMU imu;
    private RotationalPIDController rc;
    private boolean PIDEnabled = false, gyroEnabled = false;
    private double angle, mSet, sSet, expAngle, PIDOutput;

    public DriveSubsystem(OpMode opmode, MotorGroup left, MotorGroup right, MotorGroup strafe){
        super(opmode);

        this.left = left;
        this.right = right;
        this.strafe = strafe;
        this.gyro = null;
        driveLog = new Log(opmode);
    }

    public DriveSubsystem(OpMode opmode, MotorGroup left, MotorGroup right, MotorGroup strafe, AHRS ahrs){
        super(opmode);

        this.left = left;
        this.right = right;
        this.strafe = strafe;

        this.gyro = ahrs;
        this.rc = new RotationalPIDController(gyro, Orientation.YAW);

        gyroEnabled = true;
        driveLog = new Log(opmode);
    }

    public DriveSubsystem(OpMode opmode, MotorGroup left, MotorGroup right, MotorGroup strafe, BNO055IMU imu){
        super(opmode);

        this.left = left;
        this.right = right;
        this.strafe = strafe;

        this.imu = imu;
        this.gyro = new HazmatAHRS(imu);
        this.rc = new RotationalPIDController(gyro, Orientation.YAW);

        gyroEnabled = true;
        driveLog = new Log(opmode);
    }

    public void enablePID(boolean enabled){
        rc.enable(enabled);
        PIDEnabled = enabled;
    }

    public void resetPID(){
        rc.reset();
        expAngle = gyro.getYaw();
    }

    public void setPID(double p, double i, double d){
        rc.setPID(p,i,d);
    }

    public void setPIDTarget(double target){
        expAngle = target;
    }

    @Override
    public void enable() {
        gyro.zeroYaw();
        expAngle = gyro.getYaw();
    }

    @Override
    public void disable() {
        set(0,0,0);
    }

    @Override
    public void stop(){
        gyro.close();
        android.util.Log.d("HazmatRobot", "Drive Subsystem Stopped");
    }

    private void set(double leftPow, double rightPow, double strafePow){
        left.set(leftPow);
        right.set(rightPow);
        strafe.set(strafePow);
    }

    private void setCapped(double leftPower, double rightPower, double strafePower) {
        double max = maxDouble(leftPower, rightPower, strafePower);

        if (max < 1) {
            set(leftPower, rightPower, strafePower);
            return;
        }
        set(leftPower / max, rightPower / max, strafePower / max);
    }

    public void arcadeDrive(double x, double y){
        setCapped(x+y, y-x, 0);
    }

    public void strafeArcadeDrive(double x, double y, double z){
        setCapped(y+z, y-z, x);
    }

    /*public void fieldCentricDrive(double lx, double ly, double rx, double ry){
        if(gyroEnabled){
            angle = Math.toRadians(gyro.getYaw());
            mSet = (ly * Math.cos(angle)) - (lx * Math.sin(angle))/1.5;
            sSet = (ly * Math.sin(angle)) + (lx * Math.cos(angle));

            rc.setTarget(expAngle);

            driveLog.add("Expected Angle", expAngle);
            driveLog.add("Current Angle", gyro.getYaw());

            if(PIDEnabled){
                setCapped(mSet + rc.getOutput(), mSet - rc.getOutput(), sSet);
            } else{
                setCapped(mSet, mSet, sSet);
            }
        } else{
            driveLog.add("AHRS not enabled, cannot field centric drive");
        }
    }*/

    public void fieldCentricDrive(double lx, double ly, double turn){
        if(gyroEnabled){
            angle = Math.toRadians(gyro.getYaw());
            mSet = (ly * Math.cos(angle)) - (lx * Math.sin(angle))/1.5;
            sSet = (ly * Math.sin(angle)) + (lx * Math.cos(angle));

            if(rc.getTarget() != expAngle){
                rc.setTarget(expAngle);
            }

            if(turn != 0){
                rc.reset();
            }

            driveLog.update();

            /*if(rc.isNewUpdateAvailable(rc.yawPIDResult)){
                PIDOutput = rc.getOutput();
            }*/

            PIDOutput = rc.getOutput();

            driveLog.add("Expected Angle", expAngle);
            driveLog.add("Current Angle", gyro.getYaw());
            driveLog.add("PID output", PIDOutput);
            driveLog.add("PID On Target", rc.isOnTarget());
            driveLog.add("Error", rc.getError());
            driveLog.add("PID Enabled", rc.isEnabled());
            driveLog.add("RC current", rc.getGyroYaw());

            if(PIDEnabled){
                setCapped(mSet + PIDOutput + turn, mSet - PIDOutput - turn, sSet);
            } else{
                setCapped(mSet, mSet, sSet);
            }
        } else{
            driveLog.add("NavX not enabled, cannot field centric drive");
        }
    }
}
