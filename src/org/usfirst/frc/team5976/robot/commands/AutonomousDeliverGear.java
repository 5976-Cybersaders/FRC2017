package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GearDelivery;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDeliverGear extends CommandGroup {

	public AutonomousDeliverGear(DriveTrain driveTrain, GearDelivery gearDelivery) {
		//Drive variables should be in inches
		double drive1 = 1;
		double angle = 180;
		double drive2 = 1;
		double drive3 = -1;
		
		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(new DriveStraight(drive1, driveTrain));
		addSequential(new Turn(angle, driveTrain));
		addSequential(new DriveStraight(drive2, driveTrain));
		addSequential(new DeliverGear(gearDelivery));
		addSequential(new DriveStraight(drive3, driveTrain));
		System.out.println("END INIT MMC");
	}
}
