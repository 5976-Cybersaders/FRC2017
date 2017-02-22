package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GearDelivery;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDeliverGearDashboardEnabled extends CommandGroup {
	public AutonomousDeliverGearDashboardEnabled(DriveTrain driveTrain) {
		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(new DriveStraight("drive1", 12, driveTrain));	
		addSequential(new Turn("angle", 45, driveTrain));
		addSequential(new DriveStraight("drive2", 6, driveTrain));
		System.out.println("END INIT MMC");
	}
}
