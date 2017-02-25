package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDriveStraight extends CommandGroup {
	public AutonomousDriveStraight(DriveTrain driveTrain) {
		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(new DriveStraight(SmartDashboardMap.DRIVE1, driveTrain));
		System.out.println("END MMC Encoder Drive Straight");
	}
}
