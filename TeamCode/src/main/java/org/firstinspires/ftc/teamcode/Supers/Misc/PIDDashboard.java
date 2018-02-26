package org.firstinspires.ftc.teamcode.Supers.Misc;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Varun on 2/26/2018.
 */

public class PIDDashboard implements Runnable {
    Thread t;
    OpMode opMode;

    ServerSocket server = null;
    Socket client = null;
    InputStream stream = null;
    InputStreamReader reader = null;
    BufferedReader in = null;
    private double kP, kI, kD;
    PIDTunable tunable;
    boolean finished = false, hasNew = false;

    public PIDDashboard(OpMode opMode){
        this.opMode = opMode;

        t = new Thread(this);
        t.start();
    }

    public boolean hasNew(){
        return hasNew;
    }

    public PIDTunable get(){
        hasNew = false;
        return tunable;
    }

    public void close(){
        finished = true;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(4321);
            client = server.accept(); //new Socket("127.0.0.1", 4321); //TODO: put this in its own thread to prevent timeouts
            stream = client.getInputStream();
            reader = new InputStreamReader(stream);
            in = new BufferedReader(reader);
        } catch (Exception e) {
            Log.d("Robot", "Client Socket Exception: " + e.getMessage());
        }

        while(!finished){
            try {
                if(in.ready() && stream.available() > 0){
                    try{
                        kP = Double.parseDouble(in.readLine());
                        kI = Double.parseDouble(in.readLine());
                        kD = Double.parseDouble(in.readLine());
                        tunable = new PIDTunable(kP,kI,kD);
                        hasNew = true;
                    } catch (Exception e){
                        Log.d("Robot", "Socket exception: " + e.getMessage());
                    }
//                    opMode.telemetry.addData("kP" ,kP);
//                    opMode.telemetry.addData("kI", kI);
//                    opMode.telemetry.addData("kD", kD);
                    Log.d("Robot", "Received Data: kP: " + kP);
                    Log.d("Robot", "Received Data: kI: " + kI);
                    Log.d("Robot", "Received Data: kD: " + kD);
                }
                opMode.telemetry.addData("Address", InetAddress.getLocalHost());
                //telemetry.addData("Socket", InetAddress.getByName("VarunUltrabook"));
                //telemetry.addData("ALL", InetAddress.getAllByName())
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
