// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4915.MecanumDrive;

import org.usfirst.frc4915.MecanumDrive.commands.AutonomousCommand;
import org.usfirst.frc4915.MecanumDrive.commands.CloseAllGrabbers;
import org.usfirst.frc4915.MecanumDrive.commands.OpenGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.DriveStraight;
import org.usfirst.frc4915.MecanumDrive.commands.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.MoveStraightGivenDistanceCommand;
import org.usfirst.frc4915.MecanumDrive.commands.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.CloseSmallGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.IntermediateOpen;
import org.usfirst.frc4915.MecanumDrive.commands.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    public Joystick driveStick;
    public Joystick elevatorStick;
    public JoystickButton elevatorJumpToPositionZero;
    public JoystickButton elevatorJumpToPositionOne;
    public JoystickButton elevatorJumpToPositionTwo;
    public JoystickButton elevatorJumpToPositionThree;
    public JoystickButton elevatorJumpToPositionFour;
    public JoystickButton elevatorJumpToPositionFive;
    public JoystickButton elevatorJumpToPositionSix;
    public JoystickButton grabberOpen;
    public JoystickButton grabberClosed;
    public JoystickButton grabberIntermediate;
   
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    public OI() {
    	
        driveStick = new Joystick(0);
        elevatorStick = new Joystick(1);
        
        elevatorJumpToPositionZero = new JoystickButton(elevatorStick, 2); 
        elevatorJumpToPositionZero.whenPressed(new ElevatorJumpToPosition(Elevator.POSITION_ZERO));
        elevatorJumpToPositionOne = new JoystickButton(elevatorStick, 7); 
        elevatorJumpToPositionOne.whenPressed(new ElevatorJumpToPosition(Elevator.POSITION_ONE));
        elevatorJumpToPositionTwo = new JoystickButton(elevatorStick, 8);
        elevatorJumpToPositionTwo.whenPressed(new ElevatorJumpToPosition(Elevator.POSITION_TWO));
        elevatorJumpToPositionThree = new JoystickButton(elevatorStick, 9); 
        elevatorJumpToPositionThree.whenPressed(new ElevatorJumpToPosition(Elevator.POSITION_THREE));
        elevatorJumpToPositionFour = new JoystickButton(elevatorStick, 10); 
        elevatorJumpToPositionFour.whenPressed(new ElevatorJumpToPosition(Elevator.POSITION_FOUR));
        
        
        driveStick = new Joystick(0);
        elevatorStick = new Joystick(1);

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Move Straight 5 feet", new MoveStraightPositionModeCommand(5));
        SmartDashboard.putData("Move Backwards 5 feet", new MoveStraightPositionModeCommand(-5));
        SmartDashboard.putData("DriveStraight 1 second", new DriveStraight());

        SmartDashboard.putData("Close Grabber", new CloseGrabber());
        SmartDashboard.putData("Vent", new CloseSmallGrabber());
        SmartDashboard.putData("Intermediate Open", new IntermediateOpen());
        SmartDashboard.putData("Open Grabber", new OpenGrabber());
        SmartDashboard.putData("Close All Grabbers", new CloseAllGrabbers());
        
        
        SmartDashboard.putData("Jump to Elevator Position", new ElevatorJumpToPosition(Elevator.POSITION_ZERO));
        SmartDashboard.putData("Jump to Elevator Position", new ElevatorJumpToPosition(Elevator.POSITION_ONE));
        SmartDashboard.putData("Jump to Elevator Position", new ElevatorJumpToPosition(Elevator.POSITION_TWO));
        SmartDashboard.putData("Jump to Elevator Position", new ElevatorJumpToPosition(Elevator.POSITION_THREE));
        SmartDashboard.putData("Jump to Elevator Position", new ElevatorJumpToPosition(Elevator.POSITION_FOUR));

        
        // LiveWindowSendable info (Test mode)
        LiveWindow.addSensor("Other Sensors", "Accelerometer", RobotMap.accelerometer);
        LiveWindow.addSensor("Drive Train", "Distance Sensor", Robot.driveTrain.distanceSensor);
        LiveWindow.addActuator("Grabber", "Double Solenoid", RobotMap.mommaSolenoid);
        LiveWindow.addActuator("Grabber", "Solenoid", RobotMap.babySolenoid);
        // LiveWindow.addSensor("Elevator", "Upper limit switch", RobotMap.limitSwitchTop);
        // LiveWindow.addSensor("Elevator", "Lower limit switch", RobotMap.limitSwitchBottom);
        // LiveWindow.addSensor("Elevator", "Linear potentiometer", RobotMap.potentiometer);
        
        // Motor values
        SmartDashboard.putNumber("LeftFront Speed", RobotMap.mecanumDriveControls1LeftFront10.getSpeed());
        SmartDashboard.putNumber("LeftRear Speed", RobotMap.mecanumDriveControls1LeftRear11.getSpeed());
        SmartDashboard.putNumber("RightFront Speed", RobotMap.mecanumDriveControls1RightFront12.getSpeed());
        SmartDashboard.putNumber("RightRear Speed", RobotMap.mecanumDriveControls1RightRear13.getSpeed());
        
        SmartDashboard.putNumber("LeftFront Position", RobotMap.mecanumDriveControls1LeftFront10.getEncPosition());
        SmartDashboard.putNumber("LeftRear Position", RobotMap.mecanumDriveControls1LeftRear11.getEncPosition());
        SmartDashboard.putNumber("RightFront Position", RobotMap.mecanumDriveControls1RightFront12.getEncPosition());
        SmartDashboard.putNumber("RightRear Position", RobotMap.mecanumDriveControls1RightRear13.getEncPosition());
        
        SmartDashboard.putNumber("Elevator Speed", RobotMap.elevatorWinchMotor14.getSpeed());
        
        //SmartDashboard.putNumber("Linear Potentiometer height", RobotMap.potentiometer.get());
        
        // Shows the current version number on the driver station
        String parsedVersion = VersionFinder.parseVersionFromManifest(this);
        SmartDashboard.putString("Code Version", parsedVersion == null? "<not found>" : parsedVersion);
    }
}

