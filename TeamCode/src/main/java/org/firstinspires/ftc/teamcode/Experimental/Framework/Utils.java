package org.firstinspires.ftc.teamcode.Experimental.Framework;

/**
 * Created by robotics9277 on 11/4/2017.
 */

public class Utils {
    public static double maxDouble(double... nums) {
        double currMax = Math.abs(nums[0]);

        for (double i : nums) {
            currMax = Math.abs(i) > currMax ? Math.abs(i) : currMax;
        }

        return currMax;
    }

    public static void sleep(long millis){
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
