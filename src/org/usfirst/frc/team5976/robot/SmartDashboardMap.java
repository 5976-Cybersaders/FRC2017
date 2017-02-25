package org.usfirst.frc.team5976.robot;

public class SmartDashboardMap {
	public static final SmartValue kP = new SmartValue("P-Value", 1);
	public static final SmartValue kI = new SmartValue("I-Value", 0.002);
	public static final SmartValue kD = new SmartValue("D-Value", 0.001);
	public static final SmartValue ALLOWABLE_ERROR = new SmartValue("Allowable Error", 35);
	public static final SmartValue DRIVE1 = new SmartValue("Drive 1", 61);
	public static final SmartValue DRIVE2 = new SmartValue("Drive 2", 37);
	public static final SmartValue ANGLE = new SmartValue("Angle", -60);
	
	public static void reportAll() {
		report(kP);
		report(kI);
		report(kD);
		report(ALLOWABLE_ERROR);
		report(DRIVE1);
		report(DRIVE2);
		report(ANGLE);
	}
	
	private static void report(SmartValue variable) {
		System.out.println(variable.getKey() + ": " + variable.getValue());
	}
}
 