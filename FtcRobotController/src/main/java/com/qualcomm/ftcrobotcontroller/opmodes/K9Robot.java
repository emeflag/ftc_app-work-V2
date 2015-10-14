package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
//import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * K9Robot - Show use of the motors, servos, and sensors
 * Note: Be sure to add your opmode to the "FtcOpModeRegister" class.
 */
public class K9Robot extends OpMode {

    //Maximum power for the left and right motor
    //Do not allow the motors to exceed 20% maximum power.
    final static float LEFT_MOTOR_MAX_POWER  =  (float) 0.20;
    final static float RIGHT_MOTOR_MAX_POWER =  (float) 0.20;

    //Declare DcMotor class for the left and right drive motors
    DcMotor motorRight;
    DcMotor motorLeft;

    //Define the range limits of the Arm Servo, values derived by observation
    final static double ARM_MIN_RANGE  = 0.4;
    final static double ARM_MAX_RANGE  = 0.94;

    //Define the range limits of the Claw Server, values derived by observation
    final static double CLAW_MIN_RANGE  = 0.50;
    final static double CLAW_MAX_RANGE  = 1.00;

    //These are the initial locations of the arm and claw.
    double armPosition  = ARM_MIN_RANGE;
    double clawPosition = CLAW_MIN_RANGE;

    //Increment the location of the arm and claw by this much each time the appropriate gamepad
    //button is pushed
    double armDelta =  0.01;
    double clawDelta = 0.01;

    //Declare the arm and claw Servos
    Servo arm;
    Servo claw;

    //Declare the lightSensor
    LightSensor lightSensor;

    //Declare the touchSensor
    TouchSensor touchSensor;


    //Initialization step.
    @Override
    public void init() {

        // Setup the left and right motor
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        //Reverse the commanding power specification
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        //Setup the arm and claw servos
        arm  = hardwareMap.servo.get("servo_arm");
        claw = hardwareMap.servo.get("servo_claw");

        //Move the arm and claw to their initial positions
        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);

        // Setup the lightSensor
        lightSensor = hardwareMap.lightSensor.get("light_sensor");
        // turn on LED of light sensor.
        lightSensor.enableLed(true);

        // Setup the TouchSensor
        touchSensor = hardwareMap.touchSensor.get("touch_sensor");
    }

    //In the event-driven OpMode the loop method is called approximately every 15-30 milliseconds
    //It is important to return as quickly as possible from the loop class so that the
    //OpMode class can update new values of the gamepad.
    @Override
    public void loop() {

        //Read the left and right joy stick values for the y-coordinate (up-down)
        //for gamepad1. Note: because the "up" direction of the joy stick is a negative
        //value, we need to reverse the sign of the joy stick value
        //joystick values range between -1 and 1
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

        //Divide the joystick value by the maximum power value to achieve the desired range
        //of power values
        left = left   * LEFT_MOTOR_MAX_POWER;
        right = right * RIGHT_MOTOR_MAX_POWER;


        //Clip the right/left values so that the values never exceed +/1 of the
        //maximum power value specified. This is good programming practice to protect
        //your program from potential anomalous values
        right = Range.clip(right,  -RIGHT_MOTOR_MAX_POWER, RIGHT_MOTOR_MAX_POWER);
        left  = Range.clip(left,   -LEFT_MOTOR_MAX_POWER,  LEFT_MOTOR_MAX_POWER);

        //Set the new power values of the motors and return
        //doesn't seem to have any impact: motorLeft.setPowerFloat();
        motorLeft.setPower(left);


        //doesn't seem to have any impact: motorRight.setPowerFloat();
        motorRight.setPower(right);

        //If button A on gamepad1 is pushed then increment the arm position
        if (gamepad1.a) {
            armPosition = armPosition + armDelta;
        }

        //If button Y on gamepad1 is pushed then decrement the arm position
        if (gamepad1.y) {
            armPosition -= armDelta;
        }

        //If button X on gamepad1 is pushed then increment the claw position
        if (gamepad1.x) {
            clawPosition += clawDelta;

        }

        //If button B on gamepad1 is pushed then decrement the claw position
        if (gamepad1.b) {
            clawPosition -= clawDelta;

        }

        //Clip the position values so that they never exceed their allowed range.
        //Note: Good programming practice to protect your program from errors
        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

        //Set the position values to the arm and claw servo
        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);

        //Get the raw value of the light sensor
        int light = lightSensor.getLightDetectedRaw();

        //Is the touch sensor pressed?
        boolean touch = touchSensor.isPressed();

        //Display left and right power values
        telemetry.addData("1-motor left power", left);
        telemetry.addData("2-motor right power", right);

        //Display the arm and Claw Positions
        telemetry.addData("3-arm position", armPosition);
        telemetry.addData("4-claw position",clawPosition);

        //Display the values of the light and touch sensor
        telemetry.addData("5-LightSensor", light);
        telemetry.addData("6-TouchSensor", touch);

    }

}
