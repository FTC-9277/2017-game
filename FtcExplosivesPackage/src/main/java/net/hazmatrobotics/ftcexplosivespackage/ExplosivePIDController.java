package org.firstinspires.ftc.teamcode.FtcExplosivesPackage;

/**
 * Created by FTC 9277 on 12/9/2017.
 * Rotational PID Controller for the REV integrated BNO055 IMU
 */
public class ExplosivePIDController implements Runnable{
    ExplosivePIDEnabledHardware gyro;
    public boolean PIDEnabled = false, isTurning = false, isMoving = false, close = false, wasTurning = false;
    public double kP, currentAngle, expAngle, error, movingScalar, output, tolerance;

    /**
     * Thread in which the PID Controller runs
     */
    private Thread t;

    /**
     * Consructor for the PID controller
     * @param gyro ExplosiveBNO055 gyro(wrapped version of the integrated IMU)
     */
    public ExplosivePIDController(ExplosivePIDEnabledHardware gyro){
        this.gyro = gyro;

        kP = 0;
        currentAngle = 0;
        expAngle = 0;
        error = 0;
        movingScalar = 0;
        output = 0;
        tolerance = 0;

        PIDEnabled = false;
        t = new Thread(this);
        t.start();
    }

    /**
     * Enabled PID
     */
    public void enable(){
        PIDEnabled = true;
    }

    /**
     * Enabled PID and set initial constants
     * @param kP Scalar constant for P
     * @param movingScalar Scalar constant to reduce PID strength when moving
     */
    public void enable(double kP, double movingScalar){
        this.kP = kP;
        this.movingScalar = movingScalar;
        PIDEnabled = true;
    }

    /**
     * Define whether robot is turning
     * @param isTurning True if turning, false if not
     */
    public void isTurning(boolean isTurning){
        this.isTurning = isTurning;
    }

    /**
     * Define whether robot is moving
     * @param isMoving True if moving, false if not
     */
    public void isMoving(boolean isMoving){
        this.isMoving = isMoving;
    }

    /**
     * Set the expected angle for the PID
     * @param target Expected angle in degrees
     */
    public void setTarget(double target){
        expAngle = target;
    }

    /**
     * Set the tolerance for PID error
     * @param tolerance Allowed tolerance in degrees
     */
    public void setTolerance(double tolerance){this.tolerance = tolerance;}

    /**
     * Resets PID to current angle
     */
    public void resetPID(){
        expAngle = currentAngle;
        error = 0;
        output = 0;
    }

    /**
     * Get PID Output
     * @return Output power
     */
    public double getOutput(){
        return output;
    }

    /**
     * Disable PID and stop thread
     */
    public void close(){
        gyro.close();
        PIDEnabled = false;
        close = true;
    }

    @Override
    public void run(){
        while(!close){
            if(PIDEnabled){
                currentAngle = gyro.getOutput();

                if(isTurning){
                    resetPID();
                    wasTurning = true;
                } else{
                    if(wasTurning){
                        resetPID();
                        wasTurning = false;
                        //currentAngle = gyro.getUpdatedYaw();
                        while(gyro.getLatency() > 5){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        currentAngle = gyro.getOutput();
                        resetPID();
                    }

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
            } else{
                try {
                    Utils.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
