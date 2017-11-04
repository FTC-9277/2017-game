package org.firstinspires.ftc.teamcode.competitionCode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.teamcode.competitionCode.Utils.*;

/**
 * Created by robotics9277 on 11/4/2017.
 */

public class HDrive {
    Log driveLog;
    MotorGroup left, right, strafe;
    AHRS gyro;
    boolean PIDEnabled = false, gyroEnabled = false;
    double errorScalar, angle, mSet, sSet, expAngle, error;

    public HDrive(OpMode opmode, MotorGroup left, MotorGroup right, MotorGroup strafe){
        this.left = left;
        this.right = right;
        this.strafe = strafe;
        this.gyro = null;
        driveLog = new Log(opmode);
    }

    public HDrive(OpMode opmode, MotorGroup left, MotorGroup right, MotorGroup strafe, AHRS navx){
        this.left = left;
        this.right = right;
        this.strafe = strafe;
        this.gyro = navx;
        gyroEnabled = true;
        driveLog = new Log(opmode);
        expAngle = gyro.getYaw();
    }

    public void enablePID(boolean enabled){
        PIDEnabled = enabled;
    }

    public void enablePID(boolean enabled, double scalar){
        PIDEnabled = enabled;
        errorScalar = scalar;
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

    public void fieldCentricDrive(double lx, double ly, double rx, double ry){
        if(gyroEnabled){
            angle = Math.toRadians(gyro.getYaw());
            mSet = (ly * Math.cos(angle)) - (lx * Math.sin(angle))/1.5;
            sSet = (ly * Math.sin(angle)) + (lx * Math.cos(angle));

            if(Math.abs(rx) > 0.3 && Math.abs(ry) > 0.3)  expAngle = Math.atan2(rx, ry);

            error = expAngle - gyro.getYaw();

            driveLog.add("Expected Angle", expAngle);
            driveLog.add("Current Angle", gyro.getYaw());

            if(PIDEnabled){
                setCapped(mSet - (error * errorScalar), mSet + (error * errorScalar), sSet);
            } else{
                setCapped(mSet, mSet, sSet);
            }
        } else{
            driveLog.add("NavX not enabled, cannot field centric drive");
        }
    }
}
