package org.firstinspires.ftc.teamcode.competitionCode.Experimental.Framework;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;

import static com.kauailabs.navx.ftc.navXPIDController.navXTimestampedDataSource.YAW;

/**
 * Created by Varun on 11/14/2017.
 */

public class RotationalPIDController extends navXPIDController{
    private final double DEFAULT_TARGET_ANGLE_DEGREES = 0.0;
    private final double DEFAULT_TOLERANCE_DEGREES = 3.0;
    private final double DEFAULT_MIN_MOTOR_OUTPUT_VALUE = -1.0;
    private final double DEFAULT_MAX_MOTOR_OUTPUT_VALUE = 1.0;
    private final double DEFAULT_YAW_PID_P = 0.0;
    private final double DEFAULT_YAW_PID_I = 0.0;
    private final double DEFAULT_YAW_PID_D = 0.0;
    private final boolean DEFAULT_CONTINUOUS = true;

    public enum Orientation{
        YAW(navXTimestampedDataSource.YAW),
        PITCH(navXTimestampedDataSource.PITCH),
        ROLL(navXTimestampedDataSource.ROLL);

        private navXTimestampedDataSource dataSource;
        private Orientation(navXTimestampedDataSource dataSource){
            this.dataSource = dataSource;
        }
    }

    private PIDResult yawPIDResult;

    public RotationalPIDController(AHRS navx_device, Orientation orientation) {
        super(navx_device, orientation.dataSource);

        this.setSetpoint(DEFAULT_TARGET_ANGLE_DEGREES);
        this.setContinuous(DEFAULT_CONTINUOUS);
        this.setOutputRange(DEFAULT_MIN_MOTOR_OUTPUT_VALUE, DEFAULT_MAX_MOTOR_OUTPUT_VALUE);
        this.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, DEFAULT_TOLERANCE_DEGREES);
        this.setPID(DEFAULT_YAW_PID_P, DEFAULT_YAW_PID_I, DEFAULT_YAW_PID_D);

        this.yawPIDResult = new navXPIDController.PIDResult();
    }

    public void setTarget(double target){
        this.setSetpoint(target);
    }

    public void setTolerance(double tolerance){
        this.setTolerance(ToleranceType.ABSOLUTE, tolerance);
    }

    public void setMinMaxOutput(double min, double max){
        this.setOutputRange(min,max);
    }

    @Override
    public void setPID(double p, double i, double d){
        this.setPID(p,i,d);
    }

    @Override
    public void setContinuous(boolean continuous){
        this.setContinuous(true);
    }

    public boolean updateAvailable(){
        return this.isNewUpdateAvailable(yawPIDResult);
    }

    public boolean waitForUpdate(int timeout) throws InterruptedException {
        return this.waitForNewUpdate(yawPIDResult, timeout);
    }

    public boolean onTarget(){
        return yawPIDResult.isOnTarget();
    }

    public double getOutput(){
        if(!yawPIDResult.isOnTarget()){
            return yawPIDResult.getOutput();
        } else{
            return 0;
        }
    }
}
