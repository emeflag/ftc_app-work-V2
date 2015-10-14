package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Trying to understand encoder usage
 */
public class K9Encoder1 extends LinearOpMode{

    DcMotor motor;
    DcMotorController.RunMode runMode;
    double motorPower = 0.05;
    int targetPosition = 2000;
    int currentPosition = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("motor_right");

        waitForStart();

        motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        sleep(5000);

        runMode = motor.getChannelMode();
        telemetry.addData("1-runmode", runMode.toString());
        sleep(1000);

        motor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        sleep(5000);

        motor.setTargetPosition(targetPosition);
        sleep(1000);

        motor.setPower(0.1);
        sleep(1000);

        while(true) {
            waitForNextHardwareCycle();
            currentPosition = motor.getCurrentPosition();
            int target = motor.getTargetPosition();
            runMode = motor.getChannelMode();

            telemetry.addData("2-runmode", runMode.toString());
            telemetry.addData("3-currentPosition", currentPosition);
            telemetry.addData("4-targetPosition", target);
        }






    }
}
