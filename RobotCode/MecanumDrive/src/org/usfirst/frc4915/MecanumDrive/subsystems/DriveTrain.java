package org.usfirst.frc4915.MecanumDrive.subsystems;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MecanumDrive;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

//TODO write javadoc comments for all these methods

public class DriveTrain extends Subsystem {

	RobotDrive robotDrive;
	CustomDebugger debugger = Robot.debugger;

	public static List<CANTalon> motors = Arrays.asList(RobotMap.mecanumDriveControlsLeftFront, RobotMap.mecanumDriveControlsLeftRear, RobotMap.mecanumDriveControlsRightFront, RobotMap.mecanumDriveControlsRightRear);

	public static Gyro gyro = RobotMap.gyro;
	public static Ultrasonic distanceSensor = RobotMap.distanceSensor;

	public double throttle = 0;
	public boolean fieldMode = false; 

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive());

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

		robotDrive = RobotMap.driveTrainRobotDrive;
	}

	public RobotDrive getRobotDrive() {
		return robotDrive;
	}

	/**
	 * Drives a mecanum drivetrain in the direction of the joystick pointed
	 * Since the motors are enabled to use their encoders in RobotMap, each
	 * motor should go at the speeds that they need to more accurately. Because
	 * of this, we do not want to use setMaxOutput, because the value set in
	 * RobotMap.java is needed to be the same.
	 * 
	 * @param joystick
	 *            that controls the robot movement
	 */
	public void mecanumDrive(Joystick joystick) {

		double joystickX = joystick.getAxis(Joystick.AxisType.kX);
		double joystickY = joystick.getAxis(Joystick.AxisType.kY);
		double joystickTwist = joystick.getAxis(Joystick.AxisType.kTwist);
		throttle = 0.50 * (joystick.getThrottle()) + 0.50;
		if ((Math.abs(joystickX) < 0.2)) {
			joystickX = 0;
		}
		if ((Math.abs(joystickY) < 0.2)) {
			joystickY = 0;
		}
		if ((Math.abs(joystickTwist) < 0.2)) {
			joystickTwist = 0;
		}
		debugger.logError(LoggerNames.DRIVETRAIN, (joystickX + ", " + joystickY + ", " + joystickTwist));
		if ((Math.abs(joystickX) < 0.2) && (Math.abs(joystickY) < 0.2) && (Math.abs(joystickTwist) < 0.2)) {
			debugger.logError(LoggerNames.DRIVETRAIN, ("Stopping Motor"));
			robotDrive.stopMotor();
		} else {
			if(fieldMode == true){
				debugger.logError(LoggerNames.DRIVETRAIN, ("Driving Field Mode"));
				robotDrive.mecanumDrive_Cartesian(joystickX, joystickY, joystickTwist, gyro.getAngle());
			} else {
				debugger.logError(LoggerNames.DRIVETRAIN, ("Driving Robot Mode"));
				robotDrive.mecanumDrive_Cartesian(joystickX, joystickY, joystickTwist, 0);
			}

			/*
			 * leftFront.set(60); leftRear.set(60); rightFront.set(60);
			 * rightRear.set(60);
			 */
		}

	}
	public void calibrateGyro() {
		gyro.reset();
	}

	public void driveStraight(double speed) {
		robotDrive.mecanumDrive_Cartesian(0.0, speed, 0.0, 0.0);
	}

	/**
	 * 
	 * @param motor
	 *            is the motor on the wheels with an encoder used to determine
	 *            the distance traveled.
	 * @param elapsed
	 *            is the time since the last sampling of the motor.
	 * @return the distance traveled since the last sampling of the encoder.
	 */
	// TODO Make this actually work
	// calculates the distance traveled using the wheel circumference and the
	// number of wheel rotations.
	public double getDistanceForMotor(CANTalon motor, long elapsed) {
		int ticksPerRevolution = 1000;
		double circumferenceOfWheel = 6 * Math.PI;
		int inchesPerFoot = 12;
		debugger.logError(LoggerNames.DRIVETRAIN, ("Speed" + motor.getSpeed()));
		debugger.setFilter(LoggerNames.DRIVETRAIN);
		debugger.resetFilter();
		return motor.getSpeed() * elapsed / ticksPerRevolution * circumferenceOfWheel / inchesPerFoot;
	}

	// TODO Make a method that displays the speed of a motor

	public void arcadeDrive(Joystick stick) {
		debugger.logError(LoggerNames.DRIVETRAIN, "Arcade Drive");
		debugger.setFilter(LoggerNames.DRIVETRAIN);
		debugger.resetFilter();
		robotDrive.arcadeDrive(stick);
	}
   
	public boolean toggleFieldMode() {
    	fieldMode = !fieldMode;
    	return fieldMode;
    }
}
