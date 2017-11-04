package org.firstinspires.ftc.teamcode.competitionCode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.teamcode.competitionCode.Utils.*;

/**
 * Created by robotics9277 on 11/4/2017.
 */

public class HDrive {
    MotorGroup left, right, strafe;
    AHRS gyro;

    public HDrive(MotorGroup left, MotorGroup right, MotorGroup strafe){
        this.left = left;
        this.right = right;
        this.strafe = strafe;
        this.gyro = null;
    }

    public HDrive(MotorGroup left, MotorGroup right, MotorGroup strafe, AHRS navx){
        this.left = left;
        this.right = right;
        this.strafe = strafe;
        this.gyro = navx;
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
    }
}
