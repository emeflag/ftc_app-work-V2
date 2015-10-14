package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * Test optical distance sensor
 *
 * This program simply reads the color sensor values for the red, green, blue, and clear
 * detectors the reports them on the driver controller app.
 * Testing an update
 */

public class ColorSensorTest extends OpMode {

    //Declare a ColorSensor object with the name "colorS"
    ColorSensor colorS;

    //Declare a DeviceInterfaceModule with the name "dIM"
    DeviceInterfaceModule dIM;

    //We commanding the the signal pin on port D5 on the device interface module
    static int LED_CHANNEL = 5;

    @Override
    public void init () {

        //Make a connection to the device interface module.
        dIM = hardwareMap.deviceInterfaceModule.get("dim");

        //Set the signal pin on LED_CHANNEL to output so we can command it.
        dIM.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);

        //Make a connection to the color sensor
        colorS = hardwareMap.colorSensor.get("color_sensor");

        //Turn off the voltage to the LED_CHANNEL thereby turning off the LED
        dIM.setDigitalChannelState(LED_CHANNEL, false);
    }

    @Override
    public void loop() {

        //Obtain the values of the clear, red, green, and blue detectors
        int clear = colorS.alpha();
        int r = colorS.red();
        int g = colorS.green();
        int b = colorS.blue();

        //Display the clear, red, green, and blue values
        telemetry.addData("alpha",clear);
        telemetry.addData("red",r);
        telemetry.addData("green",g);
        telemetry.addData("blue",b);

    }
}