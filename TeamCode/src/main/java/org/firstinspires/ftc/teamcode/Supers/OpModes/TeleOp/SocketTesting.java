package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by robotics9277 on 2/15/2018.
 */

@TeleOp(name = "Socket Test")
public class SocketTesting extends OpMode {
    Socket client = null;
    InputStream stream = null;
    InputStreamReader reader = null;
    BufferedReader in = null;
    double kP, kI, kD;

    @Override
    public void init() {
        try {
            client = new Socket("192.168.49.164", 4321);
            stream = client.getInputStream();
            reader = new InputStreamReader(stream);
            in = new BufferedReader(reader);
        } catch (Exception e) {
            Log.d("Robot", "Client Socket Exception: " + e.getMessage());
        }
    }

    @Override
    public void loop() {
        try {
            if(in.ready() && stream.available() > 0){
                try{
                    kP = Double.parseDouble(in.readLine());
                    kI = Double.parseDouble(in.readLine());
                    kD = Double.parseDouble(in.readLine());
                } catch (Exception e){
                    Log.d("Robot", "Socket exception: " + e.getMessage());
                }
                telemetry.addData("kP" ,kP);
                telemetry.addData("kI", kI);
                telemetry.addData("kD", kD);
                Log.d("Robot", "Received Data: kP: " + kP);
                Log.d("Robot", "Received Data: kI: " + kI);
                Log.d("Robot", "Received Data: kD: " + kD);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
