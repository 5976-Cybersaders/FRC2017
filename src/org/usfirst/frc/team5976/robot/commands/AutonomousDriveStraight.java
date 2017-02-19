package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GearDelivery;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDriveStraight extends CommandGroup {
	public AutonomousDriveStraight(DriveTrain driveTrain, GearDelivery gearDelivery) {
		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(new DriveStraight("drive1", 69.5, driveTrain));
		//addSequential(new DeliverGear(gearDelivery));
		//addSequential(new DriveStraight("drive2", 12, driveTrain));
		System.out.println("END MMC Drive Straight");
	}
}
