package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * The K9RobotServos class explores use of the Servos
 *  Note: Be sure to add your opmode to the "FtcOpModeRegister" class.
 */
public class K9RobotServos extends OpMode {

    //Define the range limits of the Arm Servo, values derived by observation
    //These servo limits are dependent on a hardware's particular setup
    final static double ARM_MIN_RANGE  = 0.4;
    final static double ARM_MAX_RANGE  = 0.94;

    //Define the range limits of the Claw Server, values derived by observation
    final static double CLAW_MIN_RANGE  = 0.50;
    final static double CLAW_MAX_RANGE  = 1.00;

    //These are the initial locations of the arm and claw.
    double armPosition  = ARM_MIN_RANGE;
    double clawPosition = CLAW_MIN_RANGE;

    //Increment the location of the arm and claw by this value when gamepad button pushed
    double armDelta =  0.01;
    double clawDelta = 0.01;

    //Declare the arm and claw servo objects
    Servo arm;
    Servo claw;

    //Initialization step
    @Override
    public void init () {
        //Setup the arm and claw servos
        arm  = hardwareMap.servo.get("servo_arm");
        claw = hardwareMap.servo.get("servo_claw");

        //Move the arm and claw to their initial positions
        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);
    }

    //In the event-driven OpMode the loop method is called approximately every 10-30 ms
    //It is important to return as quickly as possible from the loop class so that the
    //OpMode class can update new values of the gamepad, servos, motors, sensors
    @Override
    public void loop() {
        //If button A on gamepad1 is pushed then increment the arm position
        if (gamepad1.a) {
            armPosition = armPosition + armDelta;
        }
        //If button Y on gamepad1 is pushed then decrement the arm position
        if (gamepad1.y) {
            armPosition = armPosition - armDelta;
        }
        //If button X on gamepad1 is pushed then increment the claw position
        if (gamepad1.x) {
            clawPosition = clawPosition + clawDelta;
        }
        //If button B on gamepad1 is pushed then decrement the claw position
        if (gamepad1.b) {
            clawPosition = clawPosition - clawDelta;
        }
        //Clip the position values so that they never exceed their allowed range.
        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

        //Update the position values to the arm and claw servo
        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);

        //Display the arm and claw positions
        telemetry.addData("3-arm position", armPosition);
        telemetry.addData("4-claw position",clawPosition);
    }
}