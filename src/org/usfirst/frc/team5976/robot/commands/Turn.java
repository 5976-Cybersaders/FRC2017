package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends EncoderDriveCommand {
	
	public Turn(double angle, DriveTrain driveTrain) {
		super(driveTrain);
		revolutions = toRevolutionsFromDegrees(angle);
	}
	
	protected void execute() {
		leftMaster.set(revolutions);
		rightMaster.set(revolutions);
	}
	
	
	public double toRevolutionsFromDegrees(double angle) {
		return toRevolutions(angle * Math.PI / 180);
	}
} 