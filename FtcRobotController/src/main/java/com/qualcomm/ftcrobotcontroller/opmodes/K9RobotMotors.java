package com.qualcomm.ftcrobotcontroller.opmodes;

//These java classes need to be imported into you program
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * We recommend you use OpMode event-driven programming for teleopt mode.
 * K9RobotMotors - this OpMode drives the left and right motors in tank mode
 * Note: Be sure to register your opmode to the "FtcOpModeRegister" class.
 */
public class K9RobotMotors extends OpMode {
    //Maximum power for the left and right motor
    //Do not allow the motors to exceed 20% maximum power.
    final static float LEFT_MOTOR_MAX_POWER  =  (float) 0.2;
    final static float RIGHT_MOTOR_MAX_POWER =  (float) 0.2;

    //Declare DcMotor objects for the left and right drive motors
    DcMotor motorRight;
    DcMotor motorLeft;

    //Initialization step.
    @Override
    public void init() {

        //Associate motor objects with physical motors defined in configuration file.
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft  = hardwareMap.dcMotor.get("motor_left");

        //Reverse the commanding power specification for the left motor
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    //In the event-driven OpMode the loop method is called approximately every 10-30
    //ms. It is important to return as quickly as possible from the loop method so
    //that the OpMode class can update values for the gamepad, motors, servos, sensors
    @Override
    public void loop() {

        //Read the left and right joy stick values for the y-coordinate (up-down)
        //for gamepad1. Note: because the "up" direction of the joy stick is a negative
        //value, we need to reverse the sign of the joy stick value
        //joystick values range between -1 and 1
        float left  = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

        //Multiply the joystick value by the maximum power value to achieve the
        //desired range of power values (in our case .2 is the max power)
        left  = left  * LEFT_MOTOR_MAX_POWER;
        right = right * RIGHT_MOTOR_MAX_POWER;

        //Safeguard--clip the right and left values so that the values never exceed the
        //the maximum and minimum power desired
        right = Range.clip(right, -RIGHT_MOTOR_MAX_POWER, RIGHT_MOTOR_MAX_POWER);
        left  = Range.clip(left,  -LEFT_MOTOR_MAX_POWER,  LEFT_MOTOR_MAX_POWER);

        //Power the motors, motors
        motorRight.setPower(right);
        motorLeft.setPower(left);

        //Display left and right motor power values on driver-side app
        telemetry.addData("1-motor left power",left);
        telemetry.addData("2-motor right power",right);
    }
}
