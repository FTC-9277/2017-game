package org.firstinspires.ftc.teamcode.FtcExplosivesPackage;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by robotics9277 on 12/15/2017.
 */

public class ExplosiveNavX implements ExplosivePIDEnabledHardware{
    private static ExplosiveNavX instance = null;
    private NavxMicroNavigationSensor imu = null;

    private ExplosiveNavX(OpMode opMode, String name){
        imu = opMode.hardwareMap.get(NavxMicroNavigationSensor.class, name);
    }

    public static ExplosiveNavX getInstance(OpMode opmode, String name){
        if(instance == null){
            instance = new ExplosiveNavX(opmode, name);
        }
        return instance;
    }

    public double getYaw(){
        try{
            return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        } catch(Exception e){
            android.util.Log.d("Robot", "GYRO ERROR: " + e.getMessage());
            return getYaw();
        }
    }

    @Override
    public void close(){
        imu = null;
        instance = null;
    }

    @Override
    public double getLatency(){
        return (System.nanoTime() - imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).acquisitionTime)/1000000;
    }

    public double getUpdatedYaw(){
        while((System.nanoTime() - imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).acquisitionTime)/1000000 > 5){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    @Override
    public double getOutput() {
        return getYaw();
    }
}
