package org.firstinspires.ftc.teamcode.HardwareTests;

import android.util.Log;

import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HazMatVL6180X {
    I2cDeviceSynch v;
    Telemetry t;

    public HazMatVL6180X(I2cDeviceSynch device, Telemetry t) {
        this.t = t;
        this.v = device;
        v.engage();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log("ModelNo", "" + v.read8(0x000));
        log("VL6180X", "Fresh out of reset: " + v.read8(0x16));

        v.write8(0x207, 0x01);
        v.write8(0x208, 0x01);
        v.write8(0x096, 0x00);
        v.write8(0x097, 0xFD); // RANGE_SCALAR = 253
        v.write8(0x0E3, 0x00);
        v.write8(0x0E4, 0x04);
        v.write8(0x0E5, 0x02);
        v.write8(0x0E6, 0x01);
        v.write8(0x0E7, 0x03);
        v.write8(0x0F5, 0x02);
        v.write8(0x0D9, 0x05);
        v.write8(0x0DB, 0xCE);
        v.write8(0x0DC, 0x03);
        v.write8(0x0DD, 0xF8);
        v.write8(0x09F, 0x00);
        v.write8(0x0A3, 0x3C);
        v.write8(0x0B7, 0x00);
        v.write8(0x0BB, 0x3C);
        v.write8(0x0B2, 0x09);
        v.write8(0x0CA, 0x09);
        v.write8(0x198, 0x01);
        v.write8(0x1B0, 0x17);
        v.write8(0x1AD, 0x00);
        v.write8(0x0FF, 0x05);
        v.write8(0x100, 0x05);
        v.write8(0x199, 0x05);
        v.write8(0x1A6, 0x1B);
        v.write8(0x1AC, 0x3E);
        v.write8(0x1A7, 0x1F);
        v.write8(0x030, 0x00);

        v.write8(0x0011, 0x10);
        v.write8(0x010A, 0x30);
        v.write8(0x0031, 0xFF);
        v.write8(0x002E, 0x01);

        v.write8(0x16, 0b0);
        log("VL6180X", "Fresh out of reset: " + v.read8(0x016));
    }

    public HazMatVL6180X(I2cDeviceSynch device) {
        this(device, null);
    }

    public byte get() {
        synchronized (this) {
            v.write8(0x018, 0x01);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            byte range = v.read8(0x062);
            v.write8(0x015, 0x07);

            return range;
        }
    }

    public void log(String title, String message) {
        if(t != null) t.addData(title, message);
        else Log.d(title, message);
    }
}