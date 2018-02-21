package org.firstinspires.ftc.teamcode.Supers.OpModes.TeleOp;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by robotics9277 on 2/15/2018.
 */

@TeleOp(name = "Socket Test")
public class SocketTesting extends OpMode {
    ServerSocket server = null;
    Socket client = null;
    InputStream stream = null;
    InputStreamReader reader = null;
    BufferedReader in = null;
    double kP, kI, kD;

    @Override
    public void init() {
        try {
            server = new ServerSocket(4321);
            client = server.accept(); //new Socket("127.0.0.1", 4321); //TODO: put this in its own thread to prevent timeouts
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
            telemetry.addData("Address", InetAddress.getLocalHost());
            //telemetry.addData("Socket", InetAddress.getByName("VarunUltrabook"));
            //telemetry.addData("ALL", InetAddress.getAllByName())
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
