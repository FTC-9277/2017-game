package org.firstinspires.ftc.teamcode.States.Framework;

import static org.firstinspires.ftc.teamcode.States.Framework.Utils.sleep;

/**
 * Created by Varun on 11/16/2017.
 */

public abstract class Command implements Runnable {
    Thread t;
    Subsystem subsystem;
    private boolean isRunning = false, cancel = false;
    HazMatTeleOp opMode;
    Log cmdErrorLog;

    public Command(HazMatTeleOp opMode, Subsystem subsystem){
        this.t = new Thread(this);
        this.opMode = opMode;
        this.subsystem = subsystem;
        cmdErrorLog = new Log(opMode);
    }

    public void enable() {
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

    public void close() {
        cancel = true;
    }

    public void forceStop() {
        cancel = true;
        t.interrupt();
        finish();
    }

    public abstract void init();

    public abstract void start();

    public abstract void loop();

    public abstract void stop();

    @Override
    public void run() {
        isRunning = true;

        init();
        subsystem.enable();

        while(!isStarted() && !cancel){
            sleep(1);
        }

        start();

        while(!isLooping() && !cancel){
            sleep(1);
        }

        while(!isFinished() && !cancel){
            loop();
        }

        subsystem.disable();

        stop();
        subsystem.stop();
        finish();
    }

    private boolean isStarted(){
        return opMode.isStarted;

    }

    private boolean isLooping(){
        return opMode.isLooping;

    }

    private boolean isFinished(){
        return opMode.isFinished;

    }

    private void finish() {
        isRunning = false;
        t = null;
    }
}
