package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends EncoderDriveCommand {
	private String dashboardKey;
	private double defaultAngle;
	private double angle;
	
	public Turn(double angle, DriveTrain driveTrain) {
		super(driveTrain);
		this.angle = angle;
		revolutions = toRevolutionsFromDegrees(angle);
	}
	
	public Turn(String dashboardKey, double defaultAngle, DriveTrain driveTrain) {
		super(driveTrain);
		this.dashboardKey = dashboardKey;
		this.defaultAngle = defaultAngle;
	}
	
	protected void initialize() {
		super.initialize();
		if (dashboardKey != null) {
			angle = SmartDashboard.getNumber(dashboardKey, defaultAngle);
			revolutions = toRevolutionsFromDegrees(angle);
		}
		System.out.println("STARTING COMMAND TURN ANGLE: " + angle + " REVS: "+ revolutions);
	}
	
	protected void execute() {
		leftMaster.set(revolutions);
		rightMaster.set(revolutions);
	}
	
	public double toRevolutionsFromDegrees(double angle) {
		return toRevolutions(angle * Math.PI / 180);
	}
} 