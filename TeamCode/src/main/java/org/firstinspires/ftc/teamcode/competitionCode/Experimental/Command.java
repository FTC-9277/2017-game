package org.firstinspires.ftc.teamcode.competitionCode.Experimental;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.competitionCode.Log;

/**
 * Created by Varun on 11/16/2017.
 */

public abstract class Command implements Runnable {
    Thread t;
    private boolean isRunning = false, cancel = false;
    OpMode opMode;
    Log cmdErrorLog;

    public Command(OpMode opMode){
        this.t = new Thread(this);
        this.opMode = opMode;
        cmdErrorLog = new Log(opMode);
    }

    public void start() {
        if (t == null) {
            cmdErrorLog.add("Attempted to start un-initialized Command. Remember to call super() in command constructor");
            throw new RuntimeException("Attempted to start un-initialized Command. Are you calling super() in the " +
                    "Command's constructor?");
        }
        t.start();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void cancel() {
        cancel = true;
    }

    public void forceStop() {
        cancel = true;
        t.interrupt();
        finish();
    }

    public abstract void init();

    public abstract void loop();

    public abstract void stop();

    @Override
    public void run() {

    }

    private void finish() {
        isRunning = false;
    }
}
