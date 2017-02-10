package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends EncoderDriveCommand {
	private String dashboardKey;
	private double defaultAngle;
	
	public Turn(double angle, DriveTrain driveTrain) {
		super(driveTrain);
		revolutions = toRevolutionsFromDegrees(angle);
	}
	
	public Turn(String dashboardKey, double defaultAngle, DriveTrain driveTrain) {
		super(driveTrain);
		this.dashboardKey = dashboardKey;
		this.defaultAngle = defaultAngle;
	}
	
	protected void initialize() {
		if (dashboardKey != null) {
			revolutions = toRevolutionsFromDegrees(SmartDashboard.getNumber(dashboardKey, defaultAngle));
		}
	}
	
	protected void execute() {
		leftMaster.set(revolutions);
		rightMaster.set(revolutions);
	}
	
	public double toRevolutionsFromDegrees(double angle) {
		return toRevolutions(angle * Math.PI / 180);
	}
} 