package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Test optical distance sensor
 */
public class UltrasonicSensorTest extends OpMode {
    UltrasonicSensor distance;

    @Override
    public void init () {
        distance = hardwareMap.ultrasonicSensor.get("ultrasonic_sensor");
    }

    @Override
    public void loop() {
        double dist1 = distance.getUltrasonicLevel();

        telemetry.addData("dist1",dist1);


    }
}
