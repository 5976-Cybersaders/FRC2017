package org.usfirst.frc.team5976.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//DriveTrain motors CAN IDs
	//Master motors are the front motors
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

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//Talon Values
	public static final float PEAK_VOLTAGE = 6f;
	public static final float PEAK_VOLTAGE_TELEOP = 6f;
	public static final int ALLOWABLE_ERROR = 10;
	public static final double kP = 1, kI = 0, kD = 0;
	public static final int RAMP_RATE = 0;
	public static final boolean BRAKE_MODE = false;
}