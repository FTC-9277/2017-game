package org.firstinspires.ftc.teamcode.States.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.States.Framework.HazMatAutonomous;
import org.firstinspires.ftc.teamcode.States.Framework.Utils;

/**
 * Created by robotics9277 on 12/10/2017.
 */
@Autonomous(name = "Blue Jewel Auto")
public class BlueJewelAuto extends HazMatAutonomous {
    Servo horizontal, vertical;
    ColorSensor color;
    DistanceSensor distance;

    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    @Override
    public void initHardware() {
        horizontal = hardwareMap.get(Servo.class, "horizontal");
        vertical = hardwareMap.get(Servo.class, "vertical");
        color = hardwareMap.get(ColorSensor.class, "color");
        distance = hardwareMap.get(DistanceSensor.class, "color");
    }

    @Override
    public void initAction() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "Ae1zrnj/////AAAAGav1yQVpZUSKniMroT7r6iABdn4S2dvC3kr8kHPXmGVTUv+TPviY2luSPOvIb3766OLU7oSCOdj3mv92vurqm8ijBYuzI0lnp51aYSsHH3y4GBTw77Kpvav2XNkHJ1TtcUtou0aZH2hw4N5RrYKXXf+ahVWuXvUMJqU3ccVWCMxNA76qQfRTexbnryWrFftMXgM5+1QTR6srigPms0lW86MFJJ9AzwdB2WVbZe6PoEeiEgoOjd1/AAbTCMML2O7vRaM8eXCL1NS8SDZ3a2bJ6jopy/ChNkjMuQboWGn2A29XDcANIM+y28S+o0jfCWg7eMlai5HFdU0IZnPJ/efMbLsnddFyuGQNzihNS2ocx2mZ";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
    }

    @Override
    public void body() throws InterruptedException {
        relicTrackables.activate();

        horizontal.setPosition(0.625);

        sleep(250);

        vertical.setPosition(0.7);

        sleep(1000);

        if(distance.getDistance(DistanceUnit.CM) < 20){
            if(color.red() > color.blue()){
                horizontal.setPosition(horizontal.getPosition() + 0.3);
                Utils.sleep(1000);
                horizontal.setPosition(horizontal.getPosition() - 0.2);
            } else if(color.blue() > color.red()){
                horizontal.setPosition(horizontal.getPosition() - 0.3);
                Utils.sleep(1000);
                horizontal.setPosition(horizontal.getPosition() + 0.2);
            } else{
                telemetry.addData("Jewel Color"," Not Identified");
            }
        } else{
            telemetry.addData("Jewels", "Not Found");
        }

        Utils.sleep(500);

        vertical.setPosition(0.1);

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

        telemetry.addData("Vumark", vuMark);
    }

    @Override
    public void exit() {

    }
}
