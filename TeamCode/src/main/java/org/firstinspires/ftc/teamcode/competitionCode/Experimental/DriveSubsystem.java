package org.firstinspires.ftc.teamcode.competitionCode.Experimental;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.competitionCode.Log;
import org.firstinspires.ftc.teamcode.competitionCode.MotorGroup;
import org.firstinspires.ftc.teamcode.competitionCode.Experimental.RotationalPIDController.Orientation;

import static org.firstinspires.ftc.teamcode.competitionCode.Utils.*;

/**
 * Created by Varun on 11/17/2017.
 */

public class DriveSubsystem extends Subsystem {
    private Log driveLog;
    private MotorGroup left, right, strafe;
    private AHRS gyro;
    private RotationalPIDController rc;
    private boolean PIDEnabled = false, gyroEnabled = false;
    private double angle, mSet, sSet, turn, expAngle;

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

    public void enablePID(boolean enabled){
        rc.enable(enabled);
        PIDEnabled = enabled;
    }

    public void resetPID(){
        rc.reset();
    }

    public void setPID(double p, double i, double d){
        rc.setPID(p,i,d);
    }

    @Override
    public void enable() {
        gyro.zeroYaw();
    }

    @Override
    public void disable() {
        set(0,0,0);
    }

    @Override
    public void stop(){
        gyro.close();
        android.util.Log.d("Robot", "Drive Subsystem Stopped");
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

            if(Math.abs(rx) > 0.1 || Math.abs(ry) > 0.1) {
                expAngle = 180 - Math.toDegrees(Math.atan2(rx,ry));
                if(expAngle > 180){
                    expAngle -= 360;
                }
                driveLog.add("Expected changing true");
            }

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
    }

    public void fieldCentricTurnDrive(double lx, double ly, double rx, double ry, double lTurn, double rTurn){
        if(gyroEnabled){
            angle = Math.toRadians(gyro.getYaw());
            mSet = (ly * Math.cos(angle)) - (lx * Math.sin(angle))/1.5;
            sSet = (ly * Math.sin(angle)) + (lx * Math.cos(angle));

            if(Math.abs(rx) > 0.1 || Math.abs(ry) > 0.1) {
                expAngle = 180 - Math.toDegrees(Math.atan2(rx,ry));
                if(expAngle > 180){
                    expAngle -= 360;
                }
                driveLog.add("Expected changing true");
            }

            rc.setTarget(expAngle);

            if(lTurn > 0.1 || rTurn > 0.1){
                if(lTurn > rTurn){
                    turn = -lTurn;
                } else{
                    turn = rTurn;
                }
                rc.reset();
            } else{
                turn = 0;
            }

            driveLog.add("Expected Angle", expAngle);
            driveLog.add("Current Angle", gyro.getYaw());

            if(PIDEnabled){
                setCapped(mSet + rc.getOutput() + turn, mSet - rc.getOutput() - turn, sSet);
            } else{
                setCapped(mSet, mSet, sSet);
            }
        } else{
            driveLog.add("NavX not enabled, cannot field centric drive");
        }
    }
}
