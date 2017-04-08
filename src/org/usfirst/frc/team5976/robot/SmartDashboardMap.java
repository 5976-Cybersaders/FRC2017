package org.usfirst.frc.team5976.robot;

public class SmartDashboardMap {
	public static final SmartValue kP = new SmartValue("P-Value", 1);
	public static final SmartValue kI = new SmartValue("I-Value", 0.002);
	public static final SmartValue kD = new SmartValue("D-Value", 0.001);
	public static final SmartValue ALLOWABLE_ERROR = new SmartValue("Allowable Error", 35);
	//Not boiler
	//Right is positive
	public static final SmartValue DRIVE1 = new SmartValue("Drive 1", 85.0);
	public static final SmartValue DRIVE2 = new SmartValue("Drive 2", 12);
	public static final SmartValue ANGLE = new SmartValue("Angle", 62.0);
	
	public static final SmartValue TURN_TIME = new SmartValue("Turn Time", 1000);
	public static final SmartValue TURN_DIRECTION = new SmartValue("Turn Direction(+)", 1);
	
	//Test
	public static final SmartValue TEST_TIME = new SmartValue("Test Time", 7750);
	public static final SmartValue TEST_SPEED = new SmartValue("Test Speed", -0.4);
	
	//Boiler
	//public static final SmartValue DRIVE1 = new SmartValue("Drive 1", 80);
	//public static final SmartValue DRIVE2 = new SmartValue("Drive 2", 19.5);
	//public static final SmartValue ANGLE = new SmartValue("Angle", -60.9303653);
	
	public static void reportAll() {
		report(kP);
		report(kI);
		report(kD);
		report(ALLOWABLE_ERROR);
		report(DRIVE1);
		report(DRIVE2);
		report(ANGLE);
		report(TURN_TIME);
		report(TURN_DIRECTION);
		report(TEST_TIME);
		report(TEST_SPEED);
	}
	
	private static void report(SmartValue variable) {
		System.out.println(variable.getKey() + ": " + variable.getDouble());
	}
}
 