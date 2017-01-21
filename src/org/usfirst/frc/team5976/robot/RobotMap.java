package org.usfirst.frc.team5976.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//DriveTrain motors
	//Master motors are the front motors
	public static final int LEFT_MASTER = 1;
	public static final int LEFT_SLAVE = 2;
	public static final int RIGHT_MASTER = 3;
	public static final int RIGHT_SLAVE = 4;
	
	//PDP
	public static final int LEFT_MASTER_PDP = 1;
	public static final int LEFT_SLAVE_PDP = 2;
	public static final int RIGHT_MASTER_PDP = 3;
	public static final int RIGHT_SLAVE_PDP = 4;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//Talon Values
	public static final float PEAK_VOLTAGE = 6.0f;
	public static final int ALLOWABLE_ERROR = 10;
	public static final int kP = 2, kI = 0, kD = 50;
	//private final int RAMP_RATE = 4;
}
