package org.usfirst.frc.team5976.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//DriveTrain Motors CAN IDs
	//Master motors are the front motor
	public static final int LEFT_MASTER = 2;
	public static final int LEFT_SLAVE = 1;
	public static final int RIGHT_MASTER = 4;
	public static final int RIGHT_SLAVE = 3;
	
	//PWM
	public static final int CLIMB_MOTOR = 0;
	public static final int LEFT_SERVO = 1;
	public static final int RIGHT_SERVO = 2;
	//PDP
	public static final int LEFT_MASTER_PDP = 13;
	public static final int LEFT_SLAVE_PDP = 12;
	public static final int RIGHT_MASTER_PDP = 3;
	public static final int RIGHT_SLAVE_PDP = 2;
	
	//Talon Values
	public static final double PEAK_VOLTAGE_POSITION_MODE = 2.5;
	//***RUN TeleOp BEFORE THE FIRST PRACTICE MATCH***//
	//Change back to 6 if AutonomousTimedDriveStraight goes TOO fast
	//and affects our ability to go straight
	public static final double PEAK_VOLTAGE_TELEOP = 12.0;
	public static final int RAMP_RATE = 0;
	public static final boolean BRAKE_MODE = false;
}