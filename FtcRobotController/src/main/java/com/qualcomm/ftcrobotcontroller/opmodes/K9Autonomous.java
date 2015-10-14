package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Autonomous mode using the LinearOpMode class.
 * Drive by time. Set the power levels for the two motors and then drive for
 * a number of milliseconds by "sleeping" for the desired driving time.
 */
public class K9Autonomous  extends LinearOpMode {
    //Declare DcMotor objects for the right and left side motors
    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void runOpMode() throws InterruptedException {

        //Associate motor objects with the names of physical motors
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft  = hardwareMap.dcMotor.get("motor_left");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        //Wait for Start button to be pushed
        waitForStart();

        //In this linearOpMode example we're driving by time. We set the motor power then
        //"sleep" for the time that you want to drive with the desired settings

        //Drive straight at 20% power for 2 seconds
        motorLeft.setPower(0.2);
        motorRight.setPower(0.2);
        sleep(2000);

        //Turn left at 20% power for 1 second
        motorLeft.setPower(-0.2);
        motorRight.setPower(0.2);
        sleep(1000);

        //Drive straight at 20% power for 1 second
        motorLeft.setPower(0.2);
        motorRight.setPower(0.2);
        sleep(1000);

        //Turn right at 20% power for 1 second
        motorLeft.setPower(0.2);
        motorRight.setPower(-0.2);
        sleep(1000);

        //Stop and we're done
        motorLeft.setPower(0.0);
        motorRight.setPower(0.0);

        waitForNextHardwareCycle();
    }

}