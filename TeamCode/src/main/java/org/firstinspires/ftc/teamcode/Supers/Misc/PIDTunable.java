package org.firstinspires.ftc.teamcode.Supers.Misc;

import java.io.Serializable;

/**
 * Created by Varun on 2/26/2018.
 */

public class PIDTunable implements Serializable{
    double[] d = new double[]{0,0,0};
    private static final long serialVersionUID = 9277L;

    public PIDTunable(double kP, double kI, double kD){
        d = new double[]{kP, kI, kD};
    }

    public double get(int i){
        try {
            return d[i];
        } catch(Exception e){
            //System.out.println("Attempting to pull unindexed dashboard values");
        }
        return 0;
    }
}
