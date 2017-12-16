package org.firstinspires.ftc.teamcode.miscOpModes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by robotics9277 on 12/6/2017.
 */
@TeleOp (name = "ServoTest2")
@Disabled
public class ServoTest2 extends OpMode {
    Servo ll, rl;

    BNO055IMU imu;
    CRServo rt, lt, rb, lb, il;
    Orientation angles;

    @Override
    public void init() {
        rt = hardwareMap.get(CRServo.class, "rt");
        lt = hardwareMap.get(CRServo.class, "lt");
        rb = hardwareMap.get(CRServo.class, "rb");
        lb = hardwareMap.get(CRServo.class, "lb");
        ll = hardwareMap.get(Servo.class, "ll");
        rl = hardwareMap.get(Servo.class, "rl");
        il = hardwareMap.get(CRServo.class, "il");

        imu = hardwareMap.get(BNO055IMU.class,"imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);
    }

    @Override
    public void loop() {

        /*if(gamepad1.x) {
            rt.setPower(-0.5);
            lt.setPower(0.5);
            rb.setPower(-0.5);
            lb.setPower(0.5);
            il.setPower(0.5);

            ll.setPosition(0.5);
            rl.setPosition(0.5);
        } else if(gamepad1.y){
            rt.setPower(0.5);
            lt.setPower(-0.5);
            rb.setPower(0.5);
            lb.setPower(-0.5);
            il.setPower(-0.5);
        } else {
            rt.setPower(0);
            lt.setPower(0);
            rb.setPower(0);
            lb.setPower(0);
            il.setPower(0);

            ll.setPosition(0);
            rl.setPosition(0);
        }*/

        if(gamepad1.a){
            rt.setPower(0.5);
        } else{
            rt.setPower(0);
        }

        if(gamepad1.b){
            rb.setPower(0.5);
        } else{
            rb.setPower(0);
        }

        if(gamepad1.x){
            lt.setPower(0.5);
        } else{
            lt.setPower(0);
        }

        if(gamepad1.y){
            lb.setPower(0.5);
        } else{
            lb.setPower(0);
        }
        /*angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        telemetry.addData("First Angle", angles.firstAngle);
        telemetry.addData("Second Angle", angles.secondAngle);
        telemetry.addData("Third Angle", angles.thirdAngle);*/

    }
}