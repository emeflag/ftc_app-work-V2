package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Test optical distance sensor
 */
public class OpticalDistanceSensorTest extends OpMode {
    OpticalDistanceSensor distance;

    @Override
    public void init () {
        distance = hardwareMap.opticalDistanceSensor.get("optical_sensor");
    }

    @Override
    public void loop() {
        double dist1 = distance.getLightDetected();
        double dist2 = distance.getLightDetectedRaw();

        telemetry.addData("dist1",dist1);
        telemetry.addData("dist2",dist2);


    }
}
