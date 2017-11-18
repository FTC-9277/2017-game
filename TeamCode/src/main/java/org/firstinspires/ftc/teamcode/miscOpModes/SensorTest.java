package org.firstinspires.ftc.teamcode.miscOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

/**
 * Created by robotics9277 on 11/18/2017.
 */
@TeleOp(name = "Sensor Test")
public class SensorTest extends OpMode {
    AnalogInput pl, pr;

    @Override
    public void init() {
        pr = hardwareMap.get(AnalogInput.class, "pr");
        pl = hardwareMap.get(AnalogInput.class, "pl");
    }

    @Override
    public void loop() {
        telemetry.addData("Left", pl.getVoltage());
        telemetry.addData("Right", pr.getVoltage());
    }
}
