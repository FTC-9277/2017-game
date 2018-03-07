package org.firstinspires.ftc.teamcode.Supers.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDController;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDEnabledHardware;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.TelemetryLog;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

import static org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Utils.maxDouble;

/**
 * Created by Varun on 11/17/2017.
 */

public class DriveSubsystem extends Subsystem {
    private TelemetryLog driveLog;
    private MotorGroup left, right, strafe;
    private Servo ls,rs;
    private ExplosivePIDEnabledHardware imu;
    private ExplosivePIDController rc;
    private boolean PIDEnabled = false, gyroEnabled = false;
    private double PIDoutput;

    private final double STRAFE_MAX_HEIGHT = 0.5, STRAFE_MIN_HEIGHT = -0.5, STRAFE_OFFSET = 0;

    public DriveSubsystem(OpMode opmode, MotorGroup left, MotorGroup right, MotorGroup strafe, Servo ls, Servo rs){
        super(opmode);

        this.left = left;
        this.right = right;
        this.strafe = strafe;
        this.ls = ls;
        this.rs = rs;

        driveLog = new TelemetryLog(opmode);
    }

    public DriveSubsystem(OpMode opmode, MotorGroup left, MotorGroup right, MotorGroup strafe, Servo ls, Servo rs, ExplosivePIDEnabledHardware imu){
        super(opmode);

        this.left = left;
        this.right = right;
        this.strafe = strafe;
        this.ls = ls;
        this.rs = rs;

        this.imu = imu;
        this.rc = new ExplosivePIDController(imu);

        gyroEnabled = true;
        driveLog = new TelemetryLog(opmode);
    }

    public void enablePID(boolean enabled){
        rc.enable();
        PIDEnabled = enabled;
    }

    public void enablePID(boolean enabled, double kP, double kI, double kD){
        rc.enable(kP, kI, kD);
        PIDEnabled = enabled;
    }

    public void retunePID(double kP, double kI, double kD){
        rc.retune(kP,kI,kD);
    }

    public void closePID(){
        rc.resetPID();
        rc.close();
        rc.output = 0;
        PIDoutput = 0;
    }

    public void resetPID(){
        rc.resetPID();
    }

    public void setPIDTarget(double target){
        rc.setTarget(target);
    }

    public void setPIDTolerance(double tolerance){
        rc.setTolerance(tolerance);
    }

    public void isMoving(boolean moving){
        rc.isTurning(moving);
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {
        set(0,0,0);
    }

    @Override
    public void stop(){
        rc.close();
        imu.close();
        android.util.Log.d("HazmatRobot", "Drive Subsystem Stopped");
    }

    public void setStrafeHeight(double height){
        if(height >= STRAFE_MIN_HEIGHT && height <= STRAFE_MAX_HEIGHT){
            rs.setPosition(0.5 - (height + STRAFE_OFFSET));
            ls.setPosition(0.5 + (height + STRAFE_OFFSET));
        }
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
        if(Math.abs(z) > 0.1){
            rc.isTurning(true);
        } else{
            rc.isTurning(false);
        }

        if(Math.abs(x) > 0.1 || Math.abs(y) > 0.1){
            rc.isMoving(true);
        } else {
            rc.isMoving(false);
        }

        PIDoutput = rc.getOutput();
        setCapped(-y+z+PIDoutput, -y-z-PIDoutput, -x);

        driveLog.update();
        /*driveLog.add("PID Output", PIDoutput);
        driveLog.add("Current", rc.currentAngle);
        driveLog.add("Expected", rc.expAngle);
        driveLog.add("Turning", rc.isTurning);
        driveLog.add("Moving", rc.isMoving);*/
    }
}
