package org.firstinspires.ftc.teamcode.States.Framework;

/**
 * Created by robotics9277 on 12/9/2017.
 */

public class BNO055PIDController implements Runnable{
    HazmatBNO055 gyro;
    public boolean PIDEnabled = false, isTurning = false, isMoving = false, close = false, wasTurning = false;
    public double kP, currentAngle, expAngle, error, movingScalar, output, tolerance;

    private Thread t;

    public BNO055PIDController(HazmatBNO055 gyro){
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
    public void run(){
        while(!close){
            if(PIDEnabled){
                currentAngle = gyro.getYaw();

                if(isTurning){
                    resetPID();
                    wasTurning = true;
                } else{
                    if(wasTurning){
                        resetPID();
                        wasTurning = false;
                        currentAngle = gyro.getUpdatedYaw();
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
