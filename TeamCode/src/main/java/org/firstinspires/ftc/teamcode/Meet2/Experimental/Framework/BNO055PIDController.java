package org.firstinspires.ftc.teamcode.Meet2.Experimental.Framework;

import android.util.Log;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by robotics9277 on 12/9/2017.
 */

public class BNO055PIDController implements Runnable{
    BNO055IMU gyro;
    Orientation orientation;
    public boolean PIDEnabled = false, isTurning = false, isMoving = false, close = false;
    public double kP, currentAngle, expAngle, error, movingScalar, output, tolerance;

    private Thread t;

    public BNO055PIDController(BNO055IMU gyro){
        this.gyro = gyro;
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        try{
            gyro.initialize(parameters);
        } catch(Error e){
            Log.e("HazmatRobot", "BNO055 Parameter initialization failed");
        }

        kP = 0;
        currentAngle = 0;
        expAngle = 0;
        error = 0;
        movingScalar = 0;
        output = 0;
        tolerance = 0;

        t = new Thread(this);
        t.start();
    }

    public void enable(){
        PIDEnabled = true;
    }

    public void enable(double kP, double movingScalar){
        this.kP = kP;
        this.movingScalar = movingScalar;
        PIDEnabled = true;
    }

    public void isTurning(boolean isTurning){
        this.isTurning = isTurning;
    }

    public void isMoving(boolean isMoving){
        this.isMoving = isMoving;
    }

    public void setTarget(double target){
        expAngle = target;
    }

    public void setTolerance(double tolerance){this.tolerance = tolerance;}

    public void resetPID(){
        expAngle = currentAngle;
        error = 0;
        output = 0;
    }

    public double getOutput(){
        return output;
    }

    public void close(){
        gyro.close();
        close = true;
    }

    @Override
    public void run() {
        while(!close){
            if(PIDEnabled){
                orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                currentAngle = orientation.firstAngle;

                if(isTurning){
                    resetPID();
                } else{
                    error = currentAngle - expAngle;

                    if(error > 180){
                        error -= 360;
                    } else if(error < -180){
                        error += 360;
                    }

                    if(Math.abs(error) < Math.abs(tolerance)){
                        output = 0;
                    } else{
                        if(isMoving){
                            output = error * kP * movingScalar;
                        } else{
                            output = error * kP;
                        }
                    }
                }
            }
        }
    }
}
