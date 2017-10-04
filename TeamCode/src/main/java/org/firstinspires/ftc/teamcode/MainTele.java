package org.firstinspires.ftc.teamcode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by robotics9277 on 10/4/2017.
 */
@TeleOp(name = "Main")
public class MainTele extends OpMode {
    private final int NAVX_DIM_I2C_PORT = 0;
    private AHRS navx;

    @Override
    public void init() {
        navx = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);
    }

    @Override
    public void loop() {
        telemetry.addData("Yaw", navx.getYaw());
        telemetry.addData("Roll", navx.getRoll());
        telemetry.addData("Pitch", navx.getPitch());
    }
}
