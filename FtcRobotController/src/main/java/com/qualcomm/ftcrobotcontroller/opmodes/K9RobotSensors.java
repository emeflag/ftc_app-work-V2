package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 *  The K9RobotSensors class explores how to setup a sensor and display the results
 *  Note: Be sure to add your opmode to the "FtcOpModeRegister" class.
 */
public class K9RobotSensors extends OpMode {

    //Declare the lightSensor
    LightSensor lightSensor;

    //Declare the touchSensor
    TouchSensor touchSensor;

    @Override
    public void init () {

        // Setup the lightSensor
        lightSensor = hardwareMap.lightSensor.get("light_sensor");
        // turn on LED of light sensor.
        lightSensor.enableLed(true);

        // Setup the TouchSensor
        touchSensor = hardwareMap.touchSensor.get("touch_sensor");

    }

    @Override
    public void loop () {

        //get the raw value of the light sensor
        int light = lightSensor.getLightDetectedRaw();

        //Is the touch sensor pressed?
        boolean touch = touchSensor.isPressed();

        //Display the output values of the Light and Touch sensors
        telemetry.addData("5-LightSensor", light);
        telemetry.addData("6-TouchSensor", touch);
    }
}